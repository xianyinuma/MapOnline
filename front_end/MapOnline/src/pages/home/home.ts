import {Component,ViewChild, ElementRef } from '@angular/core';
import {NavController, Platform} from 'ionic-angular';
import { Camera, CameraOptions } from '@ionic-native/camera';
// import { Transfer } from '@ionic-native/file-transfer';
import { Transfer, FileUploadOptions, TransferObject } from '@ionic-native/transfer';
import { PhotoViewer } from 'com-sarriaroman-photoviewer';
import { File } from '@ionic-native/file';
import { NgModel } from "@angular/forms";
import {UploadImgPage} from "../UploadImg/UploadImg";

declare const AMap: any;//声明

@Component({


  selector: 'page-home',
  templateUrl: 'home.html',
})


export class HomePage {
  public base64Image: string;


  constructor(public navCtrl: NavController, private camera: Camera) {


  }

  ionViewDidLoad() {
    //初始化地图
    let map = new AMap.Map('map', {
      resizeEnable: true,
      zoom: 11,
      center: [116.397428, 39.90923]
    });

    AMap.plugin(['AMap.ToolBar'], function () {
      map.addControl(new AMap.ToolBar());
    });

    let marker = new AMap.Marker({
      position: map.getCenter(),
      draggable: true,
      cursor: 'move'
    });
    marker.setMap(map);
    // 设置点标记的动画效果，此处为弹跳效果
    marker.setAnimation('AMAP_ANIMATION_BOUNCE');
  }






  getPhoto(){
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      sourceType: this.camera.PictureSourceType.PHOTOLIBRARY
    };

    this.camera.getPicture(options).then((imageData) => {

      this.base64Image = "data:image/jpeg;base64," + imageData;

      if (this.base64Image){
        this.navCtrl.push(UploadImgPage,{
          photo: this.base64Image
        });
      }
    }, (err) => {
      // Handle error
      console.log(err);

    });
  }


  takePicture() {
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE
    };

    this.camera.getPicture(options).then((imageData) => {
      // imageData is either a base64 encoded string or a file URI
      // If it's base64:
      this.base64Image = "data:image/jpeg;base64," + imageData;

      if (this.base64Image){
        this.navCtrl.push(UploadImgPage,{
          photo: this.base64Image
        });
      }
    }, (err) => {
      // Handle error
      console.log(err);

    });
  }








  // public takePicture(){
  //   this.options = {
  //     quality : 100,
  //     sourceType: this.camera.PictureSourceType.CAMERA,
  //     saveToPhotoAlbum: true,
  //     correctOrientation: true,
  //     destinationType: this.camera.DestinationType.DATA_URL,
  //     mediaType: this.camera.MediaType.VIDEO
  //   };
  //   this.camera.getPicture(this.options)
  //     .then((imageData)=>{
  //       this.base64Image = "data:image/jpeg;base64," +imageData;
  //     }).then((path)=>{
  //
  //   })
  // }

  // private openImgPicker() {
    // let temp = '';
    // ImagePicker.getPictures(this.imagePickerOpt)
    //   .then((results) => {
    //     for (var i = 0; i < results.length; i++) {
    //       temp = results[i];
    //     }

        // this.uploadImg(temp);

      // }, (err) => {
      //
      // });


    /*let str = '{"status":1,"msg":"提示：图片上传成功！","data":"http:\/\/192.168.1.20\/image\/580af6bcc4d40580af6bcc4d45.jpg"}';
     let res = JSON.parse(str);
     this.upload.success(res);*/
  // }


  // takePhoto(){
  //   this.acontent = "take";
  //   this.takePicture();
  // }

  // chooseFromAlbum(){
  //   this.acontent = "album";
  // }



}

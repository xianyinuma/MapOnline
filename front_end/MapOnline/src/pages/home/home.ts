import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { Geolocation } from '@ionic-native/geolocation';
import { AlertController } from 'ionic-angular';

import { UploadImgPage } from "../UploadImg/UploadImg";
import { DetailPage } from "../DetailPage/DetailPage";

import { User } from '../../app/user.service';

declare const AMap: any;//声明

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})


export class HomePage {
  public base64Image: string;
  loginResult: Boolean = User.loginResult;
  username: String = User.username;
  imageMessages: any = User.imageMessages;




  constructor(public navCtrl: NavController, private camera: Camera, private geolocation: Geolocation, private alertCtrl: AlertController) {
    this.imageMessages.forEach((data) => {

    });
  }

  placeLocation(data) {
    let title = data.title;
    let base64Coding = data.base64Coding;
    let longitude = data.longitude;
    let latitude = data.latitude;


  }


  ionViewDidLoad() {
    // var onSuccess = function (position) {
    //   console.log('纬度: ' + position.coords.latitude + '\n' +
    //     '经度: ' + position.coords.longitude + '\n' +
    //     '海拔: ' + position.coords.altitude + '\n' +
    //     '水平精度: ' + position.coords.accuracy + '\n' +
    //     '垂直精度: ' + position.coords.altitudeAccuracy + '\n' +
    //     '方向: ' + position.coords.heading + '\n' +
    //     '速度: ' + position.coords.speed + '\n' +
    //     '时间戳: ' + position.timestamp + '\n');



    //   let stickers: any = [];

    //   let map = new AMap.Map('map', {
    //     enableHighAccuracy: true,
    //     center: []
    //   });


    //   for (let i = 0; i < 2; i++) {
    //     // stickers.push()
    //   }

    //   stickers.push(addSticker(116.480983, 39.989628, map, "111", "222"));
    //   stickers.push(addSticker(120.480983, 39.989628, map, "111", "222"));


    //   let marker2 = new AMap.Marker({
    //     position: map.getCenter(),
    //     draggable: true,
    //     cursor: 'move'
    //   });

      
    //   for (let i = 0 ; i < stickers.length; i++){
    //     stickers[i].setMap(map);
    //   }

    //   marker2.setMap(map);
    //   // 设置点标记的动画效果，此处为弹跳效果
    //   marker2.setAnimation('AMAP_ANIMATION_BOUNCE');


    // };

    //定位数据获取失败响应
    // function onError(error) {
    //   console.log('code: ' + error.code + '\n' +
    //     'message: ' + error.message + '\n');
    // }


    // function addSticker(lati, longi, map, title, srcc) {
    //   let infowindow;
    //   var marker = new AMap.Marker({
    //     position: [lati, longi]
    //   });
    //   marker.on('click', function (e) {
    //     infowindow.open(map, e.target.getPosition());
    //   })
    //   AMap.plugin('AMap.InfoWindow', function () {
    //     infowindow = new AMap.InfoWindow({
    //       content: '<div class="info-title">' + title + '</div>' +
    //       '<img src="' + srcc + '" height="120" width="160" />',
    //       offset: new AMap.Pixel(0, -30)
    //     });
    //     infowindow.open(map, [lati, longi]);
    //   })
    //   return marker;
    // }


    // //开始获取定位数据
    // navigator.geolocation.getCurrentPosition(onSuccess, onError);
  }





  thisDetail() {
    this.navCtrl.push(DetailPage);
  }

  followSomeone() {
    let alert = this.alertCtrl.create({
      title: "Follow Others",
      subTitle: "Enter the username of whom you want to follow! ",
      inputs: [
        {
          name: 'othername',
          placeholder: 'username'
        }
      ],
      buttons: [
        {
          text: "Cancel",
          role: "cancel",
          handler: data => {
            console.log('Cancel clicked');
          }
        },
        {
          text: "add",
          handler: data => {
            if (data) { //todo check VALIDITY of username

            } else {
              return false;
            }
          }
        }
      ]
    });

    alert.present();

    if (!alert) {
      let alert2 = this.alertCtrl.create({
        title: "111",
        subTitle: "222",
        buttons: [
          {
            text: "ok"
          }
        ]
      });
      alert2.present();
    }
  }

  getPhoto() {
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      sourceType: this.camera.PictureSourceType.PHOTOLIBRARY
    };

    this.camera.getPicture(options).then((imageData) => {

      this.base64Image = "data:image/jpeg;base64," + imageData;

      if (this.base64Image) {
        this.navCtrl.push(UploadImgPage, {
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

      if (this.base64Image) {
        this.navCtrl.push(UploadImgPage, {
          photo: this.base64Image
        });
      }
    }, (err) => {
      // Handle error
      console.log(err);
    });
  }





}

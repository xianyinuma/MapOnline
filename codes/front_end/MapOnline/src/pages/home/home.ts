import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { Geolocation } from '@ionic-native/geolocation';
import { AlertController } from 'ionic-angular';

import { UploadImgPage } from "../UploadImg/UploadImg";
import { DetailPage } from "../DetailPage/DetailPage";

import { UserService } from '../../app/user.service';

import { Headers, Http, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

declare const AMap: any;//声明

@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
  providers: [UserService]
})


export class HomePage {
  public base64Image: string;
  friendMessages: any[];

  user: any;
  username: string;
  password: string;
  imageMessages: any;

  curLongitude: number = 120;
  curLatitude: number = 30;


  constructor(public http: Http, public userService: UserService, public navParam: NavParams, public navCtrl: NavController, private camera: Camera, private geolocation: Geolocation, private alertCtrl: AlertController) {
    this.user = this.userService.getUser();
    this.username = this.user.username;
    this.password = this.user.password;
  }




  ionViewDidLoad() {
    this.imageMessages = this.user.imageMessages;
    console.log(this.imageMessages.length);
    let thisIMs = this.imageMessages;
    let markers = [];
    let curlong;
    let curlati;


    //定位数据获取失败响应
    function onError(error) {
      console.log('code: ' + error.code + '\n' +
        'message: ' + error.message + '\n');
    }

    //开始获取定位数据
    navigator.geolocation.getCurrentPosition(position => {
      let infowindow;

      for (let i = 0; i < thisIMs.length; i++) {
        let imageI = thisIMs[i];
        // console.log(imageI);
        var marker = new AMap.Marker({
          position: [imageI.longitude, imageI.latitude]
        });
        marker.on('click', function (e) {
          infowindow.open(map, e.target.getPosition());
        })
        AMap.plugin('AMap.InfoWindow', function () {
          infowindow = new AMap.InfoWindow({
            content: '<div class="info-title">' + imageI.title + '</div>' +
            '<img src="data:image/png;base64,' + imageI.base64Coding + '"  height="120" width="160" />',

            offset: new AMap.Pixel(0, -30)
          });
          infowindow.open(map, [imageI.longitude, imageI.latitude]);
        })

        markers[i] = marker;
      }


      let map = new AMap.Map('map', {
        enableHighAccuracy: true,
        center: [position.coords.longitude, position.coords.latitude]
      });
      console.log(position.coords.longitude);

      this.curLongitude = position.coords.longitude;
      this.curLatitude = position.coords.latitude;
      // console.log(this.curLongitude + " " + this.curLatitude);

      // marker.setMap(map);
      for (let i = 0; i < thisIMs.length; i++) {
        markers[i].setMap(map);
      }

      let marker2 = new AMap.Marker({
        position: map.getCenter(),
        draggable: true,
        cursor: 'move'
      });
      marker2.setMap(map);

      // 设置点标记的动画效果，此处为弹跳效果
      marker2.setAnimation('AMAP_ANIMATION_BOUNCE');

    }, onError);
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

            console.log(data.othername);
            let url = "http://118.89.184.85:8080/follow-friend";
            let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
            let param = "username=" + this.username + "&password=" + this.password + "&friendUsername=" + data.othername;
            let options = new RequestOptions({ headers: headers, method: "post" });

            let returnJson: any;
            this.http.post(url, param, options)
              .toPromise()
              .then(res => res.json())
              .then(body => {
                console.log(body);
                if (body.friendID != null) {

                  returnJson = body;
                  let alert3 = this.alertCtrl.create({
                    title: "Info",
                    subTitle: "Success!",
                    buttons: ['ok']
                  });
                  alert3.present();

                  //login again
                  let url = "http://118.89.184.85:8080/login";
                  let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
                  let param = "username=" + this.username + "&password=" + this.password;
                  let options = new RequestOptions({ headers: headers, method: "post" });

                  this.http.post(url, param, options)
                    .toPromise()
                    .then(res => res.json())
                    .then(body => {
                      console.log(body);

                      let login_result = body.loginResult;
                      this.userService.setUser({
                        userID: body.userID,
                        username: this.username,
                        loginResult: body.loginResult,
                        imageMessages: body.imageMessages,
                        friendMessages: body.friendMessages,
                        password: this.password
                      });




                      this.navCtrl.setRoot(HomePage);
                    });


                } else {
                  let alert3 = this.alertCtrl.create({
                    title: "Info",
                    subTitle: "No such user or you have already add him/her!",
                    buttons: ['ok']
                  });
                  alert3.present();
                }

              });
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

      this.httpUploadPictureAndGetMsg(imageData);

    }, (err) => {
      // Handle error
      console.log(err);

    });
  }

  //todo
  takePicture() {
    let testImgData: string = `/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCAAyADIDASIAAhEBAxEB/8QAHAAAAgIDAQEAAAAAAAAAAAAAAAcFBgMECAIB/8QANBAAAgEDAgMFBAoDAAAAAAAAAQIDAAQRBRIGIUEHEzFhcRUiUZEUFyMyM0JSgaLBYnKS/8QAGAEBAQEBAQAAAAAAAAAAAAAAAwIEAAH/xAAdEQACAwADAQEAAAAAAAAAAAAAAQIRIQMSMUFR/9oADAMBAAIRAxEAPwCydonHVpwdbQp3X0nUJwTFAGwAP1Megz86Sdquo8capLqWtXDPGp2gDko/xUdBUJx1xBJxLxNe6k5Kxlu7gQnO2McgP79Sav3BkKW2mWdvuAdl3MPM1hnLqsNcEfYeFrQIBHZqyjqRzrTvOFbQ8jZqo8himVZQhV+1Vhy+FeL+IOimNCeXM4oVNiUhEa9o0ukOLi0kdVU55H3k8watHZ3x/fWl5FYapdzXFvKwUGZ9xU9CCeY9K3uMrdTE6EA7lPKlIH2t1DqeRrVB9loU8eHWxnUkkMMGiucoe0HV4oUjE2Qihcnyoq6Jwl+1TgpuGdcSezTOlX0mYcH8NvEof68vSp+zsBHbW8jxSzvcMqBYzjaPM9BTA7YLS3ueDO9nP2ltcRSxHPi27aR8maoHQZ7b2cEmaNcfrGRWXkeiw8NLTC1rqix27XSZPdsjy71XryNY9feeS+MZW9nXeECwybR6k1tS3UM2qxJan0YqFQenyrELyOLVZY7h1Mbe6Tjcp9RRp7ZZG6lapJEYVS5jlhyMSDdkfEMORpTatD3WpXCRrkAluXQYyaf189naabJ3LRtv5+54VQeFdJivtbld4xIk12kBUjIKfmH8h8qbjlREleFBi0a8liSRdOvWVlDBliYgg9RyorrMoqHYgUKvIADwFFPb/A8NXjfS21rhO9tUbbL3feR8s5YDIFJbQJZJ4Qk74RV3kk8sCug42zEM9RSB1+a24f4rvtPVkaKF92wdI5Bnb+2flWfkjZUH8M8V3bX1yq3WnTzDbhQkDMwQnk2R4ftWCW4srW4LWenzxM4IAeBwSvU8+R9fGt57H2qkdxZyrsC7cZxivtppxszJPcyKuDtyeo9fhUIQiNUlmjtY5IWzFIMrk0wexnT4hw99PmjDTyTSFHPPA+6cf80qeKNZhZe7tcbFGyMDr50+eADajgrRjYjELWyNg+OSMtnzzmmiqQcmSUj++3qaKwyY7xufU0UhBIxfhD0rlztSZvrK1Q7jnvVHj02LRRUnI98OTyrZuqyyBc+AY4r3r9xN7Px30mP9jRRR/RCnTsSJjk5CjHlzrp7slJPZ3o2T+Rh/NqKKVkPwsD/fb1ooorwk/9k=`


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

    this.httpUploadPictureAndGetMsg(imageData);
    });

  }

  private httpUploadPictureAndGetMsg(imageData: string) {
    let uploadUrl: string = "http://118.89.184.85:8080/upload";
    let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
    let param = "username=" + this.userService.getUser().username + "&password=" + this.userService.getUser().password + "&longitude=" + this.curLongitude + "&latitude=" + this.curLatitude + "&base64Coding=" + imageData;
    let options = new RequestOptions({ headers: headers, method: "post" });
    console.log(param);

    this.http.post(uploadUrl, param, options)
      .toPromise()
      .then(res => res.json())
      .then(body => {
        if (this.base64Image) {
          this.navCtrl.push(UploadImgPage, {
            photo: this.base64Image,
            photoJson: body
          });
        }
      });
  }





}

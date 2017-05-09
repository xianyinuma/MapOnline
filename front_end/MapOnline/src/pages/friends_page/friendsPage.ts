import { Component, Input } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Friend } from '../../Obj/Friend';
import { Geolocation } from '@ionic-native/geolocation';
import { FriendsDetailPage } from '../friendsDetail/friendsDetail';
import { AlertController } from 'ionic-angular';

import { HomePage } from '../home/home';
import { UserService } from '../../app/user.service';

import { Headers, Http, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';


declare const AMap: any;//声明

@Component({
  selector: 'page-friendsHome',
  templateUrl: 'friendsPage.html',
  providers: [UserService],

})

export class FriendsPage {
  friendJson = {
    "friendId": "123",
    "username": "xiaomaji",
    "imageMessages": [{ "imageID": 1, "title": "21212", "description": "12121212", "base64Coding": "", "tags": [{ "id": 1, "imageID": 1, "tagContent": "tagtag" }, { "id": 2, "imageID": 1, "tagContent": "tagtagtag" }], "longitude": 131.0, "latitude": 31.0 }]
  }

  imageMessages = this.friendJson.imageMessages;


  @Input() friend;

  constructor(public navCtrl: NavController,
    public navParams: NavParams,
    public geolocation: Geolocation,
    public alertCtrl: AlertController,
    public userService: UserService,
    public http: Http) {
    this.friend = navParams.get('friend');
    this.httpGetImgs();
    // console.log(this.friend);
  }


  private httpGetImgs() {
    let deleteUrl: string = "http://192.168.31.36:8080/view-friend";
    let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
    let param = "username=" + this.userService.getUser().username + "&password=" + this.userService.getUser().password + "&friendID=" + this.friend.userID;
    let options = new RequestOptions({ headers: headers, method: "post" });
    console.log(param);

    this.http.post(deleteUrl, param, options)
      .toPromise()
      .then(res => {
        this.imageMessages = res.json().imageMessages;
        console.log(this.imageMessages);
        
        this.showMap();
      });
  }


  unFollow() {


    let alert = this.alertCtrl.create({
      title: "Info",
      subTitle: "Successfully removed " + this.friend.name + " from your follow list! ",
      buttons: [{
        text: "OK",
        handler: () => {
          this.httpDeleteFriend();
          this.navCtrl.setRoot(HomePage);
        }
      }]
    });

    alert.present();
  }

  private httpDeleteFriend() {
    let deleteUrl: string = "http://192.168.31.36:8080/delete-friend";
    let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
    let param = "username=" + this.userService.getUser().username + "&password=" + this.userService.getUser().password + "&friendID=" + this.friend.userID;
    let options = new RequestOptions({ headers: headers, method: "post" });
    // console.log(param);

    let returnJson: any;
    this.http.post(deleteUrl, param, options)
      .toPromise()
      .then(res => {
        // console.log(res);
        returnJson = res;
        // let alert3 = this.alertCtrl.create({
        //   title: "Info",
        //   subTitle: returnJson,
        //   buttons: ['ok']
        // });
        // alert3.present();
      });
  }




  thisDetail(item) {
    this.navCtrl.push(FriendsDetailPage, {
      friendName: item,
      imageMessages: this.imageMessages
    });
  }

  showMap() {
    console.log(this.imageMessages.length);
    let thisIMs = this.imageMessages;
    let markers = [];

    var onSuccess = function (position) {

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
            content: '<div class="info-title">title</div>' +
            '<img src="data:image/png;base64,' + imageI.base64Coding + '"  height="120" width="160" />',
            offset: new AMap.Pixel(0, -30)
          });
          infowindow.open(map, [imageI.longitude, imageI.latitude]);
        })

        markers[i] = marker;
      }


      let map = new AMap.Map('map', {
        enableHighAccuracy: true,
        center: [thisIMs[0].longitude, thisIMs[0].latitude]
      });

      // marker.setMap(map);
      for (let i = 0; i < thisIMs.length; i++) {
        markers[i].setMap(map);
      }


    };
    //定位数据获取失败响应
    function onError(error) {
      console.log('code: ' + error.code + '\n' +
        'message: ' + error.message + '\n');
    }

    //开始获取定位数据
    navigator.geolocation.getCurrentPosition(onSuccess, onError);
  }

}

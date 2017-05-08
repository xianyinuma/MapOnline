import {Component, Input} from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import {Friend} from '../../Obj/Friend';
import { Geolocation } from '@ionic-native/geolocation';
import { FriendsDetailPage } from '../friendsDetail/friendsDetail';
import { AlertController } from 'ionic-angular';

import { HomePage } from '../home/home';
 


declare const AMap: any;//声明

@Component({
  selector: 'page-friendsHome',
  templateUrl: 'friendsPage.html',

})

export class FriendsPage {
  @Input() friend: Friend;

  constructor(public navCtrl: NavController, public navParams: NavParams,public geolocation: Geolocation, public alertCtrl: AlertController){
    this.friend = navParams.get('friend');
  }

  unFollow(name){


    let alert = this.alertCtrl.create({
      title: "Info",
      subTitle: "Successfully removed " + name + " from your follow list! ",
      buttons :[{
        text: "OK",
        handler: ()=>{
          this.navCtrl.setRoot(HomePage);
        }
      }]
    });

    alert.present();
  }

  thisDetail(item){
    this.navCtrl.push(FriendsDetailPage,{
      friendName : item
    });
  }

  ionViewDidLoad() {
    //初始化地图
    let map = new AMap.Map('map', {
      resizeEnable: true,
      zoom: 11,
      center: [116.397428, 39.90923]
    });

    // AMap.plugin(['AMap.ToolBar'], function () {
    //   map.addControl(new AMap.ToolBar());
    // });

    let marker = new AMap.Marker({
      position: map.getCenter(),
      draggable: true,
      cursor: 'move'
    });
    marker.setMap(map);
    // 设置点标记的动画效果，此处为弹跳效果
    marker.setAnimation('AMAP_ANIMATION_BOUNCE');
  }

}

import { Component, Input } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular';

import { HomePage } from '../home/home';



@Component({
  selector: 'page-upload',
  templateUrl: 'UploadImg.html'
})


export class UploadImgPage {
  @Input() base64Image: string;
  title: string;
  description: string;

  constructor(public navCtrl: NavController, public navParam: NavParams, public alertCtrl: AlertController) {
    this.base64Image = navParam.get('photo');
  }

  tags: string[] = [
    "tag1妈妈说tag要长要长要长要长",
    "tag2",
    "tag3",
    "tag4",
    "tag5",
    "tag6",
    "tag7",
    "tag8",
    "tag9",
    "tag10",

  ];


  deleteItem(item) {
    let ret: string[] = [];
    this.tags.forEach(i => {
      if (i != item) {
        ret.push(i);
      }
    });
    this.tags = ret;
  }

  share() {
    if (this.title != null) {
      

      this.navCtrl.setRoot(HomePage);
    } else {
      let alert = this.alertCtrl.create({
        title: "Warning",
        subTitle: "Please enter the Title",
        buttons:["OK"]
      });

      alert.present();
    }

  }
}

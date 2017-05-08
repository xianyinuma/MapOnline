import { Component, Input } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular';



@Component({
  selector: 'page-upload',
  templateUrl: 'UploadImg.html'
})


export class UploadImgPage{
  @Input() base64Image: string;
  info :string;

  constructor(public navCtrl: NavController, public navParam: NavParams, public alertCtrl: AlertController){
    this.base64Image = navParam.get('photo');



  }

  tags: string[] = [
    "tag1",
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


  deleteItem(item){
    let ret:string[] = [];
    this.tags.forEach(i =>{
      if (i != item){
        ret.push(i);
      }
    });
    this.tags = ret;
  }
}

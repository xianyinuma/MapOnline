import { Component, Input } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import { UserService } from '../../app/user.service';


@Component({
  selector: 'page-detail',
  templateUrl: 'DetailPage.html',
  providers: [UserService]
})


export class DetailPage{

  user: any;
  imageMessages:any;

  friendName: string ;

  photoCards: any[] = [];

  constructor(public userService:UserService, public navCtrl: NavController, public navParam: NavParams, public alertCtrl: AlertController){
    this.user = this.userService.getUser();
    this.imageMessages = this.user.imageMessages;
    this.friendName = this.user.username;

    for (let i =0 ;i < this.imageMessages.length; i++){
      let card = {
        title: this.imageMessages[i].title,
        description: this.imageMessages[i].description,
        tags: this.imageMessages[i].tags,
        photo: this.imageMessages[i].base64Coding
      }
      this.photoCards.push(card);

    }
    
  }

  deleteCard(card){
    let ret = [];
    this.photoCards.forEach((item) =>{
      if (item != card){
        ret.push(item);
      }
    });
    this.photoCards = ret;
  }
}

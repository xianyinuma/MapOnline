import { Component, Input } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular';


@Component({
  selector: 'page-friendsdetail',
  templateUrl: 'friendsDetail.html'
})


export class FriendsDetailPage {
  info: string;

  @Input() friendName: string;
  @Input() imageMessages: any;

  photoCards: any[] = [

  ];

  constructor(public navCtrl: NavController, public navParam: NavParams, public alertCtrl: AlertController) {
    this.friendName = this.navParam.get('friendName');
    this.imageMessages = this.navParam.get('imageMessages');

    for (let i = 0; i < this.imageMessages.length; i++) {
      let card = {
        title: this.imageMessages[i].title,
        description: this.imageMessages[i].description,
        tags: this.imageMessages[i].tags
      }
      this.photoCards.push(card);

    }
  }

}

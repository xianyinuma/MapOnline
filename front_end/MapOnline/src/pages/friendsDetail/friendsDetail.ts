import { Component, Input } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular';


@Component({
  selector: 'page-friendsdetail',
  templateUrl: 'friendsDetail.html'
})


export class FriendsDetailPage{
  info :string;

  @Input() friendName: string = "Ulice D. Sandwich";

  card1: any = {
    // photo: "",
    title: "title1",
    description: "des1",
    tags: ["tag1","tag2"]
  }

  card2: any = {
    // photo: " ",
    title: "title2",
    description: "des232",
    tags: ["tag1_1","tag1_2"]
  }
  photoCards: any[] = [
      this.card1,this.card2
  ];

  constructor(public navCtrl: NavController, public navParam: NavParams, public alertCtrl: AlertController){
    this.friendName = this.navParam.get('friendName');
  }

}

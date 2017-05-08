import { Component } from '@angular/core';
import { NavController, NavParams,AlertController } from 'ionic-angular';

import { FriendsPage } from '../friends_page/friendsPage';

import { Friend } from '../../Obj/Friend';

import { User } from '../../app/user.service';

@Component({
  selector: 'page-list',
  templateUrl: 'list.html'
})
export class ListPage {
  selectedItem: any;
  icons: string[] = ['flask', 'wifi', 'beer', 'football', 'basketball', 'paper-plane',
    'american-football', 'boat', 'bluetooth', 'build'];

  friendsMessage = User.friendsMessage;



  constructor(public navCtrl: NavController, public navParams: NavParams,public alertCtrl : AlertController) {
  }


  initializeFriendList() {

  }


  itemTapped(event, item) {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(FriendsPage, {
      friend: item
    });
  }
}

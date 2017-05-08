import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { HomePage } from '../home/home';
import { FriendsPage } from '../friends_page/friendsPage';

import { Friend } from '../../Obj/Friend';

@Component({
  selector: 'page-list',
  templateUrl: 'list.html'
})
export class ListPage {
  selectedItem: any;
  icons: string[] = ['flask', 'wifi', 'beer', 'football', 'basketball', 'paper-plane',
    'american-football', 'boat', 'bluetooth', 'build'];
  f: Friend = {
    name: "test1",
    note: 'hhh',
    icon: this.icons[Math.floor(Math.random() * this.icons.length)]
  };

  items: Array<Friend>  = [
    this.f,
    {
      name: 'Friendhhh0',
      note: '我真是日了' + 0 + "只狗了",
      icon: this.icons[Math.floor(Math.random() * this.icons.length)]
    },
    {
      name: 'Friendhhh1',
      note: '我真是日了' + 1 + "只狗了",
      icon: this.icons[Math.floor(Math.random() * this.icons.length)]
    }
  ];




  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.selectedItem = navParams.get('item');

  }


  initializeFriendList(){

  }


  itemTapped(event, item) {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(FriendsPage, {
      friend: item
    });
  }
}

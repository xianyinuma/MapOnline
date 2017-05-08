import {Component, Input} from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import {Friend} from '../../Obj/Friend';


@Component({
  selector: 'page-friendsHome',
  templateUrl: 'friendsPage.html',

})

export class FriendsPage {
  @Input() friend: Friend;

  constructor(public navCtrl: NavController, public navParams: NavParams){
    this.friend = navParams.get('friend');
  }

}

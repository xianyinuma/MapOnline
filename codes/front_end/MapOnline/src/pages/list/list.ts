import { Component } from '@angular/core';
import { NavController, NavParams,AlertController } from 'ionic-angular';

import { FriendsPage } from '../friends_page/friendsPage';

import { Friend } from '../../Obj/Friend';

import { UserService } from '../../app/user.service';

import { Headers, Http, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Component({
  selector: 'page-list',
  templateUrl: 'list.html',
  providers: [UserService]
})
export class ListPage {
 friendMessages = this.userService.getUser().friendMessages;
 
 username = this.userService.getUser().username;
 password = this.userService.getUser().password;

  constructor(public http: Http, public userService: UserService ,public navCtrl: NavController, public navParams: NavParams,public alertCtrl : AlertController) {
    // this.initializeFriendList();
    console.log(this.friendMessages);
  }

  



  initializeFriendList() {
       // 服务器通信
        // let url = "http://192.168.31.36:8080/upload";
        // let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
        // let param = "username=" + this.username + "&password=" + this.password;
        // let options = new RequestOptions({ headers: headers, method: "post" });

        // this.http.post(url, param, options)
        //   .toPromise()
        //   .then(res => res.json()).then(body => {
        //   });
  }


  itemTapped(item) {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(FriendsPage, {
      friend: item
    });
  }
}

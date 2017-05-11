import { Component, Input } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import { UserService } from '../../app/user.service';

import { Headers, Http, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

//for pic
import { DomSanitizer } from '@angular/platform-browser'

@Component({
  selector: 'page-detail',
  templateUrl: 'DetailPage.html',
  providers: [UserService]
})


export class DetailPage {

  user: any;
  imageMessages: any;

  friendName: string;

  photoCards: any[] = [];

  constructor(public userService: UserService, public navCtrl: NavController, public navParam: NavParams, public alertCtrl: AlertController, public http: Http, private sanitizer: DomSanitizer) {
    this.user = this.userService.getUser();
    this.imageMessages = this.user.imageMessages;
    this.friendName = this.user.username;

    for (let i = 0; i < this.imageMessages.length; i++) {
      let card = {
        title: this.imageMessages[i].title,
        description: this.imageMessages[i].description,
        tags: this.imageMessages[i].tags,
        photo: 'data:image/jpeg;base64,' + this.imageMessages[i].base64Coding,
        imageID: this.imageMessages[i].imageID,
      };

      card.photo = this.sanitizer.bypassSecurityTrustResourceUrl(card.photo);
      this.photoCards.push(card);

    }

  }

  // transform(content) {
  //   return this.sanitizer.bypassSecurityTrustHtml(content);
  // }


  deleteCard(card) {

    let deleteImgUrl: string = "http://118.89.184.85:8080/delete-image";
    let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
    let param = "username=" + this.userService.getUser().username + "&password=" + this.userService.getUser().password + "&imageID=" + card.imageID;//todo
    let options = new RequestOptions({ headers: headers, method: "post" });
    // console.log(param);

    this.http.post(deleteImgUrl, param, options)
      .toPromise()
      .then(res => {
        console.log(res);

        let ret = [];
        this.photoCards.forEach((item) => {
          if (item != card) {
            ret.push(item);
          }
        });
        this.photoCards = ret;

      });

  }

}

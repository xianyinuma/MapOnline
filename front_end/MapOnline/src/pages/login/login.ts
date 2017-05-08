import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import { HomePage } from '../home/home';
import { RegisterPage } from '../register/register';

// import { Http, Response } from '@angular/http';
import { HTTP } from '@ionic-native/http'

import { User } from '../../app/user.service';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})

// declare var i ;

export class LoginPage {
  loginJson = { "loginResult": "true", "userID": 1, "friendMesaages": [{ "userID": 2, "name": "xiaomaji" }, { "userID": 3, "name": "ji" }], "imageMessages": [{ "imageID": 1, "title": "21212", "description": "12121212", "base64Coding": "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", "tags": [{ "id": 1, "imageID": 1, "tagContent": "tagtag" }, { "id": 2, "imageID": 1, "tagContent": "tagtagtag" }], "longitude": 12.0, "latitude": 12.0 }] };

  username: string;
  password: string;


  constructor(public navCtrl: NavController, public alertCtrl: AlertController, private http: HTTP) {

  }





  register() {
    this.navCtrl.push(RegisterPage);
  }

  checkValid() {


    if (this.username != null && this.password != null) {
      if (this.username.length < 6 || this.password.length < 6) {
        let alert = this.alertCtrl.create({
          title: 'Warning',
          subTitle: "Username and Password Lenght should be no less than 6!",
          buttons: ['OK']
        });
        alert.present();
        return;
      } else {
        // 服务器通信
        let login_result = this.loginJson.loginResult;
        User.loginResult = login_result;
        
        if (login_result) {
          User.userID = this.loginJson.userID;
          User.username = this.username;
          User.friendMesaages = this.loginJson.friendMesaages;
          User.imageMessages = this.loginJson.imageMessages;

          let alert = this.alertCtrl.create({
            title: 'Congratulations!',
            subTitle: "Welcome " + this.username + " !",
            buttons: [{
              text: 'OK',
              handler: () => {
                this.navCtrl.setRoot(HomePage);
              }
            }]
          });
          alert.present();
        } else {
          let alert = this.alertCtrl.create({
            title: "Sorry",
            subTitle: "Sorry, you have entered invalid username or password! ",
            buttons: ['ok']
          });
          alert.present();
        }
        //以上是和服务器的通信结果
      }
    } else {
      let alert = this.alertCtrl.create({
        title: 'Warning',
        subTitle: "Username or Password cannot be empty! ",
        buttons: ['OK']
      });
      alert.present();
    }
  }

}

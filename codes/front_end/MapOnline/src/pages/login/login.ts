import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import { HomePage } from '../home/home';
import { RegisterPage } from '../register/register';

import { Headers, Http, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

import { UserService } from '../../app/user.service';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
  providers: [UserService]
})

// declare var i ;

export class LoginPage {
  loginJson = { "loginResult": "", "userID": 0, "friendMessages": [], "imageMessages": [{ "imageID": 1, "title": "21212", "description": "12121212", "base64Coding": "", "tags": [{ "id": 1, "imageID": 1, "tagContent": "tagtag" }, { "id": 2, "imageID": 1, "tagContent": "tagtagtag" }], "longitude": 131.0, "latitude": 31.0 }] };
  // loginJson :any;
  username: string;
  password: string;


  constructor(public userService: UserService, public navCtrl: NavController, public alertCtrl: AlertController, private http: Http) {

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
        let url = "http://118.89.184.85:8080/login";
        let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
        let param = "username=" + this.username + "&password=" + this.password;
        let options = new RequestOptions({ headers: headers, method: "post" });

        this.http.post(url, param, options)
          .toPromise()
          .then(res => res.json())
          .then(body => {
            console.log(body);
            this.loginJson = body;

            let login_result = this.loginJson.loginResult;
            this.userService.setUser({
              userID: this.loginJson.userID,
              username: this.username,
              loginResult: this.loginJson.loginResult,
              imageMessages: this.loginJson.imageMessages,
              friendMessages: body.friendMessages,
              password: this.password
            });

            console.log(this.loginJson.friendMessages.length);

            if (login_result) {

              console.log("login success");
              let alert = this.alertCtrl.create({
                title: 'Congratulations!',
                subTitle: "Welcome " + this.username + " !",
                buttons: [{
                  text: 'OK',
                  handler: () => {
                    this.navCtrl.setRoot(HomePage, {
                      loginJson: this.loginJson
                    });
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
          });


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

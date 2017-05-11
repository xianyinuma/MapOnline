import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AlertController } from 'ionic-angular';


import { Headers, Http, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Component({
  selector: 'page-register',
  templateUrl: 'register.html',
})

export class RegisterPage {
  password;
  cfpassword;
  username;

  constructor(public navCtrl: NavController, public alertCtrl: AlertController, private http: Http) {

  }

  registerJson = {
    userID: 1,
    registerResult: false
  };

  checkValid() {
    if (this.password != null && this.cfpassword != null && this.username != null) {
      if (this.checkSame(this.password, this.cfpassword) && this.checkValidUsername()) {
        //todo 将用户加入数据库
        let infoUpload = {
          username: this.username,
          password: this.password
        };

        let url = "http://118.89.184.85:8080/register";
        let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
        let param = "username=" + this.username + "&password=" + this.password;
        let options = new RequestOptions({ headers: headers, method: "post" });

        let result: boolean;
        this.http.post(url, param, options)
          .toPromise()
          .then(res => res.json()).then(body => {
            console.log(body);
            console.log(body.registerResult);
            result = body.registerResult;
            console.log("result is " + result);


            if (result) {

              let alert = this.alertCtrl.create({
                title: 'Congratulations! ',
                subTitle: this.username + " ! You have successfully registered !",
                buttons: [{
                  text: 'OK',
                  handler: () => {
                    this.navCtrl.pop();
                  }
                }]
              });
              alert.present();
            } else {
              let alert = this.alertCtrl.create({
                title: 'Error! ',
                subTitle: "Username  " + this.username + " has been used!",
                buttons: [{
                  text: 'OK',
                  handler: () => {
                    this.navCtrl.pop();
                    this.navCtrl.push(RegisterPage);
                  }
                }]
              });

              alert.present();
            }

          });



      } else {
        if (!this.checkSame(this.password, this.cfpassword)) {
          let alert = this.alertCtrl.create({
            title: 'Error ',
            subTitle: "Please be sure that the two passwords are the same !",
            buttons: [
              {
                text: 'ok',
                handler: () => {
                  this.username = "";
                  this.password = "";
                  this.cfpassword = "";
                }
              }]
          });


          alert.present();
        } else {
          let alert = this.alertCtrl.create({
            title: 'Error ',
            subTitle: "Username " + this.username + " has already been registered ! ",
            buttons: [
              {
                text: 'ok',
                handler: () => {
                  this.username = "";
                  this.password = "";
                  this.cfpassword = "";
                }
              }]
          });

          alert.present();
        }
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

  checkSame(pw1, pw2) {
    if (pw1 == pw2) return true;
    else return false;
  }

  checkValidUsername() {
    return true;//todo
  }

}

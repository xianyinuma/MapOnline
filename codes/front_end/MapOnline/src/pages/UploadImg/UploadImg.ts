import { Component, Input } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { AlertController } from 'ionic-angular';

import { HomePage } from '../home/home';
import { UserService } from '../../app/user.service';

import { Headers, Http, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Component({
  selector: 'page-upload',
  templateUrl: 'UploadImg.html',
  providers: [UserService]
})


export class UploadImgPage {



  @Input() base64Image: string;
  @Input() photoJson: any;

  username: string;
  password: string;

  title: string;
  description: string;
  tags: string[];

  constructor(public userService: UserService, public navCtrl: NavController, public navParam: NavParams, public alertCtrl: AlertController, public http: Http) {
    this.base64Image = navParam.get('photo');
    this.photoJson = navParam.get('photoJson');

    this.username = this.userService.getUser().username;
    this.password = this.userService.getUser().password;

    this.description = this.photoJson.description;
    this.tags = this.photoJson.tags;


  }


  deleteItem(item) {
    let ret: string[] = [];
    this.tags.forEach(i => {
      if (i != item) {
        ret.push(i);
      }
    });
    this.tags = ret;
  }

  share() {
    if (this.title != null) {
      let param = {
        "username": this.username,
        "password": this.password,
        "uploadResponse": {
          "imageID": this.photoJson.imageID,
          "title": this.title,
          "description": this.description, //todo
          "adultOrNot": this.photoJson.adultOrNot,
          "tags": this.tags,
          "weatherData": {
            "tempDay": 30,
            "tempNight": 12,
            "cityName": "鹤峰县",
            "conditionDay": "晴",
            "conditionNight": "晴"
          }
        }
      }

      let paramForTest = {
        "username": "majixiaomi",
        "password": "majixiaoji",
        "uploadResponse": {
          "imageID": 2,
          "title": "haha"
          , "description": "huhuhu",
          "adultOrNot": 0,
          "tags": [
            "man",
            "person",
            "dark",
            "looking",
            "standing",
            "front",
            "black",
            "monitor",
            "holding",
            "water",
            "red",
            "room",
            "flying"
          ],
          "weatherData": {
            "tempDay": 30,
            "tempNight": 12,
            "cityName": "鹤峰县",
            "conditionDay": "晴",
            "conditionNight": "晴"
          }
        }
      };

      // this.userService.setTest(this.base64Image);
      //
      //todo


      let uploadUrl: string = "http://192.168.31.36:8080/edit";
      let headers = new Headers({
        'Content-Type': 'application/json;charset=UTF-8',
        "Accept": "application/json",
        "connection": "Keep-Alive",
        "user-agent": "User-Agent,Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, DELETE",
        "Access-Control-Allow-Headers": "X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method"
    
  });


      let options = new RequestOptions({ headers: headers, method: "post" });
      console.log(JSON.stringify(paramForTest));

      this.http.post(uploadUrl, JSON.stringify(paramForTest), options)
        .toPromise()
        .then(res => res.json())
        .then(body => {
          console.log(body);

        });



      this.userService.setImageMessages({

      });



      this.navCtrl.setRoot(HomePage);
    } else {
      let alert = this.alertCtrl.create({
        title: "Warning",
        subTitle: "Please enter the Title",
        buttons: ["OK"]
      });

      alert.present();
    }

  }

}

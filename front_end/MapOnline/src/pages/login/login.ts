import { Component} from '@angular/core';
import { NavController} from 'ionic-angular';
import { AlertController } from 'ionic-angular';
import { HomePage } from '../home/home';
import { Location } from '@angular/common';
import { RegisterPage } from '../register/register';
import { BlankPage } from '../blank';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})

export class LoginPage{
  username;
  password;
  location : Location;


  constructor(public navCtrl: NavController,public alertCtrl: AlertController) {

  }



  register(){
    this.navCtrl.push(RegisterPage);
  }

  checkValid(){
    // let info = {
    //   username : this.username,
    //   password : this.password
    // };//todo

    if (this.username != null && this.password != null){
      let alert = this.alertCtrl.create({
        title: 'Congratulations!',
        subTitle: "Welcome " + this.username + " \n" + " Your pw is " + this.password,
        buttons: [{
          text: 'fuck you',
          handler: ()=>{
            this.navCtrl.setRoot(HomePage);
          }
        }]
      });
      alert.present();
    }else{
      let alert = this.alertCtrl.create({
        title: 'Warning',
        subTitle: "Username or Password cannot be empty! ",
        buttons: ['OK']
      });
      alert.present();
    }
  }

}

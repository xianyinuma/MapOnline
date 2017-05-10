import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { Camera } from '@ionic-native/camera';
import { Geolocation } from '@ionic-native/geolocation';
import { HttpModule, JsonpModule } from '@angular/http';
import { HTTP } from '@ionic-native/http';


import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { ListPage } from '../pages/list/list';
import { LoginPage } from '../pages/login/login';
import { RegisterPage } from '../pages/register/register';
import { FriendsPage } from '../pages/friends_page/friendsPage';
import { UploadImgPage } from '../pages/UploadImg/UploadImg';
import { DetailPage} from '../pages/DetailPage/DetailPage';
import { FriendsDetailPage } from '../pages/friendsDetail/friendsDetail';

// import  { TestPage } from '../pages/test/test';





import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';



class CameraMock extends Camera {
  getPicture(options) {
    return new Promise((resolve, reject) => {
      resolve("BASE_64_ENCODED_DATA_GOES_HERE");
    })
  }
}

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    ListPage,
    LoginPage,
    RegisterPage,
    FriendsPage,
    UploadImgPage,
    DetailPage,
    FriendsDetailPage,

    // TestPage

  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpModule,
    JsonpModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    ListPage,
    LoginPage,
    RegisterPage,
    FriendsPage,
    UploadImgPage,
    DetailPage,
    FriendsDetailPage,

    // TestPage

  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    Camera,
    Geolocation,
    HTTP
    

  ]
})
export class AppModule {}

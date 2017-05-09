import {Camera, CameraOptions} from '@ionic-native/camera';
import {Component} from "@angular/core";

@Component({
  templateUrl: 'Test.html'
})
export class CameraPage {
  public base64Image: string;

  constructor(private camera: Camera) {

  }

  takePicture() {
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE
    };

    this.camera.getPicture(options).then((imageData) => {
      // imageData is either a base64 encoded string or a file URI
      // If it's base64:
      this.base64Image = "data:image/jpeg;base64," + imageData;
    }, (err) => {
      // Handle error
      console.log(err);

    });
  }
}

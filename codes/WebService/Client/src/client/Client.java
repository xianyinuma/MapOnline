package client;

import microsoft.CognitiveService;
import moji.WeatherService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by Victor on 2017/5/5.
 */
public class Client {
//    public static void main(String[] args) throws Exception {
//
//        //获取服务
//        URL wsdlUrl = new URL("http://localhost:2333/CognitiveService?wsdl");
//        QName qName = new QName("http://microsoft/", "CognitiveImplService");
//        Service service = Service.create(wsdlUrl, qName);
//        CognitiveService cognitiveService = service.getPort(CognitiveService.class);
//
//        System.out.println(cognitiveService.depictPicture("Client/test/x1.jpg").getCaptions()[0].getText());
////        System.out.println(cognitiveService.adultJudge("test/x1.jpg").getIsAdultContent());
//
//
//        URL wsdlUrl1 = new URL("http://localhost:2333/WeatherService?wsdl");
//        QName qName1 = new QName("http://moji/", "WeatherImplService");
//        Service service1 = Service.create(wsdlUrl1, qName1);
//        WeatherService weatherService = service1.getPort(WeatherService.class);
//
//        System.out.println(weatherService.showWeather(39.91488908,116.40387397));
//
//    }
}

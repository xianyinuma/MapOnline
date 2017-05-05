package deploy;

import moji.WeatherImpl;
import microsoft.CognitiveImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by Victor on 2017/5/5.
 */
public class ServiceDeploy {
    public static void main(String[] args) throws Exception{
        Endpoint.publish("http://localhost:2333/CognitiveService", new CognitiveImpl());
        Endpoint.publish("http://localhost:2333/WeatherService", new WeatherImpl());
        System.out.println("Service deployed");
    }
}

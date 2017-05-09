package moji;

import entity.WeatherData;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Victor on 2017/5/5.
 */
@WebService
public interface WeatherService {
//    @WebResult(name = "result", targetNamespace = "weather.moji.com")
    @WebMethod public WeatherData showWeather(double lat, double lon);
}

package moji;

import entity.WeatherData;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Victor on 2017/5/5.
 */
@WebService
public interface WeatherService {
    @WebMethod public WeatherData showWeather(double lat, double lon);
}

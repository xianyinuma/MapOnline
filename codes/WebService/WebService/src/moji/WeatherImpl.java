package moji;

import entity.WeatherData;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Victor
 */


@WebService(name = "WeatherService")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public class WeatherImpl implements WeatherService{
    //北京
    public static final double lat = 39.91488908;
    public static final double lon = 116.40387397;

    public static void main(String[] args) {
        WeatherService w = new WeatherImpl();
//        WeatherData weatherData = w.showWeather(39.91488908, 116.40387397);
//        System.out.println(weatherData.toString());
    }

//    @Override
//    public WeatherData showWeather(double lat, double lon){
//
//        return new WeatherData();
//    }

    @Override
    public WeatherData showWeather(double lat, double lon) {
        JSONObject jsonObject = queryWeather(lat, lon);
        System.out.println(jsonObject);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONObject forecast = (JSONObject) data.getJSONArray("forecast").get(0);

        WeatherData weatherData = new WeatherData(Integer.parseInt((String) forecast.get("tempDay")),
                Integer.parseInt((String) forecast.get("tempNight")),
                (String) data.getJSONObject("city").get("name"),
                (String) forecast.get("conditionDay"),
                (String) forecast.get("conditionNight"));

        System.out.println(weatherData);

        return weatherData;
    }

    private JSONObject queryWeather(double lat, double lon) {
        String host = "http://aliv2.data.moji.com";
        String path = "/whapi/json/aliweather/briefforecast3days";
        String method = "POST";
        String appcode = "aabc282793e54e57ab6b61a6132dc6f1";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("lat", String.valueOf(lat));
        bodys.put("lon", String.valueOf(lon));
        bodys.put("token", "443847fa1ffd4e69d929807d42c2db1b");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));

            HttpEntity entity = response.getEntity();


            if (entity != null) {
                String result = EntityUtils.toString(entity);

//                System.out.println(result);

                return JSONObject.fromObject(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
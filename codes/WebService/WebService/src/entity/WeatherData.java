package entity;

import javax.xml.bind.annotation.*;

/**
 * Created by Victor on 2017/5/5.
 */

@XmlRootElement(name = "weatherData", namespace = "weather.moji.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "weatherData", namespace = "weather.moji.com")
public class WeatherData {
    @XmlElement(name = "tempDay", namespace = "")
    private int tempDay;
    @XmlElement(name = "tempNight", namespace = "")
    private int tempNight;
    @XmlElement(name = "cityName", namespace = "")
    private String cityName;
    @XmlElement(name = "conditionDay", namespace = "")
    private String conditionDay;
    @XmlElement(name = "conditionNight", namespace = "")
    private String conditionNight;

    public WeatherData() {
    }

    public WeatherData(int tempDay, int tempNight, String cityName, String conditionDay, String conditionNight) {
        this.tempDay = tempDay;
        this.tempNight = tempNight;
        this.cityName = cityName;
        this.conditionDay = conditionDay;
        this.conditionNight = conditionNight;
    }

    public void setTempDay(int tempDay) {
        this.tempDay = tempDay;
    }

    public void setTempNight(int tempNight) {
        this.tempNight = tempNight;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setConditionDay(String conditionDay) {
        this.conditionDay = conditionDay;
    }

    public void setConditionNight(String conditionNight) {
        this.conditionNight = conditionNight;
    }

    public int getTempDay() {
        return tempDay;
    }

    public int getTempNight() {
        return tempNight;
    }

    public String getCityName() {
        return cityName;
    }

    public String getConditionDay() {
        return conditionDay;
    }

    public String getConditionNight() {
        return conditionNight;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "tempDay=" + tempDay +
                ", tempNight=" + tempNight +
                ", cityName='" + cityName + '\'' +
                ", conditionDay='" + conditionDay + '\'' +
                ", conditionNight='" + conditionNight + '\'' +
                '}';
    }
}
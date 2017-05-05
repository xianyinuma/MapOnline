package entity;

/**
 * Created by Victor on 2017/5/5.
 */
public class WeatherData {
    private int tempDay, tempNight;
    private String cityName, conditionDay, conditionNight;

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
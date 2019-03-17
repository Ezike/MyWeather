package ezike.tobenna.myweather.data;

import com.squareup.moshi.Json;

/**
 * @author tobennaezike
 */
public class Current {

    @Json(name = "feelslike_c")
    private double feelslikeC;

    @Json(name = "feelslike_f")
    private double feelslikeF;

    @Json(name = "uv")
    private double uv;

    @Json(name = "last_updated")
    private String lastUpdated;

    @Json(name = "is_day")
    private int isDay;

    @Json(name = "temp_c")
    private double tempC;

    @Json(name = "temp_f")
    private double tempF;

    @Json(name = "precip_mm")
    private double precipMm;

    @Json(name = "precip_in")
    private double precipIn;

    @Json(name = "wind_kph")
    private double windKph;

    private Condition condition;

    @Json(name = "wind_mph")
    private double windMph;

    @Json(name = "vis_km")
    private double visKm;

    @Json(name = "vis_miles")
    private double visMiles;

    public double getFeelslikeC() {
        return feelslikeC;
    }

    public void setFeelslikeC(double feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public double getFeelslikeF() {
        return feelslikeF;
    }

    public void setFeelslikeF(double feelslikeF) {
        this.feelslikeF = feelslikeF;
    }

    public int getIsDay() {
        return isDay;
    }

    public void setIsDay(int isDay) {
        this.isDay = isDay;
    }

    public double getPrecipIn() {
        return precipIn;
    }

    public void setPrecipIn(double precipIn) {
        this.precipIn = precipIn;
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }

    public double getTempF() {
        return tempF;
    }

    public void setTempF(double tempF) {
        this.tempF = tempF;
    }

    public double getPrecipMm() {
        return precipMm;
    }

    public void setPrecipMm(double precipMm) {
        this.precipMm = precipMm;
    }

    public double getWindKph() {
        return windKph;
    }

    public void setWindKph(double windKph) {
        this.windKph = windKph;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public double getWindMph() {
        return windMph;
    }

    public void setWindMph(double windMph) {
        this.windMph = windMph;
    }

    public double getVisKm() {
        return visKm;
    }

    public void setVisKm(double visKm) {
        this.visKm = visKm;
    }

    public double getVisMiles() {
        return visMiles;
    }

    public void setVisMiles(double visMiles) {
        this.visMiles = visMiles;
    }
}
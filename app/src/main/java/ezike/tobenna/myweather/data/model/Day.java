package ezike.tobenna.myweather.data.model;

import com.squareup.moshi.Json;

/**
 * @author tobennaezike
 */
public class Day {

    @Json(name = "avgvis_km")
    private double avgvisKm;

    @Json(name = "uv")
    private double uv;

    @Json(name = "avgtemp_f")
    private double avgtempF;

    @Json(name = "avgtemp_c")
    private double avgtempC;

    @Json(name = "maxtemp_c")
    private double maxtempC;

    @Json(name = "maxtemp_f")
    private double maxtempF;

    @Json(name = "mintemp_c")
    private double mintempC;

    @Json(name = "avgvis_miles")
    private double avgvisMiles;

    @Json(name = "mintemp_f")
    private double mintempF;

    @Json(name = "totalprecip_in")
    private double totalprecipIn;

    @Json(name = "condition")
    private Condition condition;

    @Json(name = "maxwind_kph")
    private double maxwindKph;

    @Json(name = "maxwind_mph")
    private double maxwindMph;

    @Json(name = "totalprecip_mm")
    private double totalprecipMm;

    public double getAvgvisKm() {
        return avgvisKm;
    }

    public void setAvgvisKm(double avgvisKm) {
        this.avgvisKm = avgvisKm;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }

    public double getAvgtempF() {
        return avgtempF;
    }

    public void setAvgtempF(double avgtempF) {
        this.avgtempF = avgtempF;
    }

    public double getAvgtempC() {
        return avgtempC;
    }

    public void setAvgtempC(double avgtempC) {
        this.avgtempC = avgtempC;
    }

    public double getMaxtempC() {
        return maxtempC;
    }

    public void setMaxtempC(double maxtempC) {
        this.maxtempC = maxtempC;
    }

    public double getMaxtempF() {
        return maxtempF;
    }

    public void setMaxtempF(double maxtempF) {
        this.maxtempF = maxtempF;
    }

    public double getMintempC() {
        return mintempC;
    }

    public void setMintempC(double mintempC) {
        this.mintempC = mintempC;
    }

    public double getAvgvisMiles() {
        return avgvisMiles;
    }

    public void setAvgvisMiles(double avgvisMiles) {
        this.avgvisMiles = avgvisMiles;
    }

    public double getMintempF() {
        return mintempF;
    }

    public void setMintempF(double mintempF) {
        this.mintempF = mintempF;
    }

    public double getTotalprecipIn() {
        return totalprecipIn;
    }

    public void setTotalprecipIn(double totalprecipIn) {
        this.totalprecipIn = totalprecipIn;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public double getMaxwindKph() {
        return maxwindKph;
    }

    public void setMaxwindKph(double maxwindKph) {
        this.maxwindKph = maxwindKph;
    }

    public double getMaxwindMph() {
        return maxwindMph;
    }

    public void setMaxwindMph(double maxwindMph) {
        this.maxwindMph = maxwindMph;
    }

    public double getTotalprecipMm() {
        return totalprecipMm;
    }

    public void setTotalprecipMm(double totalprecipMm) {
        this.totalprecipMm = totalprecipMm;
    }
}
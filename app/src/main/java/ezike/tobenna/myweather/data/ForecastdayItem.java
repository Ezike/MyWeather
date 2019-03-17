package ezike.tobenna.myweather.data;

import com.squareup.moshi.Json;

public class ForecastdayItem {

    @Json(name = "date")
    private String date;

    @Json(name = "astro")
    private Astro astro;

    @Json(name = "day")
    private Day day;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
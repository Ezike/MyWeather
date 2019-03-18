package ezike.tobenna.myweather.data.local.entity;

import com.squareup.moshi.Json;

import ezike.tobenna.myweather.data.model.Astro;
import ezike.tobenna.myweather.data.model.Day;

/**
 * @author tobennaezike
 */
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
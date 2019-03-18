package ezike.tobenna.myweather.data.model;

import com.squareup.moshi.Json;

/**
 * @author tobennaezike
 */
public class Condition {

    @Json(name = "code")
    private int code;

    @Json(name = "icon")
    private String icon;

    @Json(name = "text")
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
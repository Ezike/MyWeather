package ezike.tobenna.myweather.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * @author tobennaezike
 * @since 23/03/19
 */
abstract class PreferenceProvider {

    private Context mContext;

    public PreferenceProvider(Context context) {
        mContext = context;
    }

    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }
}

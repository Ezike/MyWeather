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

    private SharedPreferences mSharedPreferences;

    public PreferenceProvider(Context context) {
        mContext = context;
    }

    public SharedPreferences getSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return mSharedPreferences;
    }
}

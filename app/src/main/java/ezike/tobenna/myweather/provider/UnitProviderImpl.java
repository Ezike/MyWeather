package ezike.tobenna.myweather.provider;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import ezike.tobenna.myweather.utils.UnitSystem;

/**
 * @author tobennaezike
 * @since 23/03/19
 */
@Singleton
public class UnitProviderImpl extends PreferenceProvider implements UnitProvider {

    private static final String UNIT_SYSTEM = "UNIT_SYSTEM";
    private Context mContext;

    @Inject
    public UnitProviderImpl(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public UnitSystem getUnitSystem() {
        String selectedName = getSharedPreferences().getString(UNIT_SYSTEM, UnitSystem.METRIC.name());
        return UnitSystem.valueOf(selectedName);
    }
}

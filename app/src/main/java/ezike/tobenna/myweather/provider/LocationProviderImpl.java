package ezike.tobenna.myweather.provider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;

import javax.inject.Inject;

import androidx.core.content.ContextCompat;
import ezike.tobenna.myweather.data.local.entity.WeatherLocation;

class LocationProviderImpl extends PreferenceProvider implements LocationProvider {

    private static final String USE_DEVICE_LOCATION = "USE_DEVICE_LOCATION";

    private static final String CUSTOM_LOCATION = "CUSTOM_LOCATION";

    private Context mContext;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private Location deviceLocation;

    @Inject
    LocationProviderImpl(Context context, FusedLocationProviderClient client) {
        super(context);
        mFusedLocationProviderClient = client;
        mContext = context;
    }

    @Override
    public boolean isLocationChanged(WeatherLocation location) {
        boolean isChanged;
        try {
            isChanged = hasDeviceLocationChanged(location);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return isChanged || hasCustomLocationChanged(location);
    }

    @Override
    public String getPreferredLocationString() {
        if (isUsingDeviceLocation()) {

            Location deviceLocation;
            try {
                deviceLocation = getLastDeviceLocation();
                if (deviceLocation == null) {
                    return getCustomLocationName();
                } else {
                    String latitude = String.valueOf(deviceLocation.getLatitude());
                    String longitude = String.valueOf(deviceLocation.getLongitude());
                    return (latitude + "," + longitude);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            return getCustomLocationName();
        return null;
    }

    private boolean hasDeviceLocationChanged(WeatherLocation location) {
        if (!isUsingDeviceLocation()) {
            return false;
        }
        Location deviceLocation;
        try {
            deviceLocation = getLastDeviceLocation();
            if (deviceLocation != null) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        double comparisonThreshold = 0.03;
        return Math.abs(deviceLocation.getLatitude() - location.getLatitude()) > comparisonThreshold &&
                Math.abs(deviceLocation.getLongitude() - location.getLongitude()) > comparisonThreshold;
    }

    private boolean hasCustomLocationChanged(WeatherLocation location) {
        if (!isUsingDeviceLocation()) {
            String customLocationName = getCustomLocationName();
            return !customLocationName.equals(location.getName());
        }
        return false;
    }

    private boolean isUsingDeviceLocation() {
        return getSharedPreferences().getBoolean(USE_DEVICE_LOCATION, true);
    }

    private String getCustomLocationName() {
        return getSharedPreferences().getString(CUSTOM_LOCATION, null);
    }


    @SuppressLint("MissingPermission")
    private Location getLastDeviceLocation() throws Exception {
        if (hasLocationPermission()) {
            mFusedLocationProviderClient.getLastLocation().addOnSuccessListener((Activity) mContext, location -> {
                if (location != null) {
                    deviceLocation = location;
                }
            });
        } else
            throw new Exception();
        return deviceLocation;
    }

    private boolean hasLocationPermission() {
        return ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}

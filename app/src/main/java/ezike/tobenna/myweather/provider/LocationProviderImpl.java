package ezike.tobenna.myweather.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnSuccessListener;

import javax.inject.Inject;

import ezike.tobenna.myweather.data.model.WeatherLocation;
import timber.log.Timber;

public class LocationProviderImpl extends PreferenceProvider implements LocationProvider, OnSuccessListener<Location> {

    private static final String USE_DEVICE_LOCATION = "USE_DEVICE_LOCATION";

    private static final String CUSTOM_LOCATION = "CUSTOM_LOCATION";

    private Context mContext;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private Location deviceLocation;

    @Inject
    public LocationProviderImpl(Context context, FusedLocationProviderClient client) {
        super(context);
        mFusedLocationProviderClient = client;
        mContext = context;
    }

    @Override
    public boolean isLocationChanged(WeatherLocation location) {
        Timber.d("Device location change %b", hasDeviceLocationChanged(location));
        return hasDeviceLocationChanged(location) || hasCustomLocationChanged(location);
    }

    @Override
    public String getPreferredLocationString() {
        if (isUsingDeviceLocation()) {
            startLocationUpdates();
            if (getLastDeviceLocation() == null) {
                Toast.makeText(mContext, "Device location not available", Toast.LENGTH_LONG).show();
                    return getCustomLocationName();
                } else {
                String latitude = String.valueOf(getLastDeviceLocation().getLatitude());
                String longitude = String.valueOf(getLastDeviceLocation().getLongitude());
                Timber.d("Coordinates %s,%s", latitude, longitude);
                    return (latitude + "," + longitude);
                }
        } else {
            return getCustomLocationName();
        }
    }

    private boolean hasDeviceLocationChanged(WeatherLocation location) {
        if (!isUsingDeviceLocation()) {
            return false;
        }

        double comparisonThreshold = 0.03;

        if (getLastDeviceLocation() != null && location != null) {
            return Math.abs(getLastDeviceLocation().getLatitude() - location.getLatitude()) > comparisonThreshold
                    && Math.abs(getLastDeviceLocation().getLongitude() - location.getLongitude()) > comparisonThreshold;

        }
        return false;
    }

    private boolean hasCustomLocationChanged(WeatherLocation location) {
        if (!isUsingDeviceLocation()) {
            String customLocationName = getCustomLocationName();
            return !customLocationName.equals(location.getName());
        }
        return false;
    }

    private boolean isUsingDeviceLocation() {
        startLocationUpdates();
        return getSharedPreferences().getBoolean(USE_DEVICE_LOCATION, true);
    }

    private String getCustomLocationName() {
        return getSharedPreferences().getString(CUSTOM_LOCATION, null);
    }

    private Location getLastDeviceLocation() {

        startLocationUpdates();

        return deviceLocation;
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this);

        new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        deviceLocation = location;
                    }
                }

            }
        };
    }

    @Override
    public void onSuccess(Location location) {
        if (location != null) {
            deviceLocation = location;
        } else {
            Timber.d("Device Location not yet available. Please try again");
        }
    }

}

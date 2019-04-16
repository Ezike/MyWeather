package ezike.tobenna.myweather.utils;

import android.annotation.SuppressLint;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

import javax.inject.Inject;

import timber.log.Timber;

public class LocationHandler implements LifecycleObserver {

    private static LocationHandler sInstance;

    @Inject
    FusedLocationProviderClient mFusedClient;

    private LocationRequest locationRequest;

    private LocationCallback mLocationCallback;

    private LocationHandler(LifecycleOwner lifecycleOwner,
                            LocationCallback callback) {
        lifecycleOwner.getLifecycle().addObserver(this);
        mLocationCallback = callback;
    }

    public static LocationHandler getLocationHandler(LifecycleOwner lifecycleOwner,
                                                     LocationCallback locationCallback) {
        if (sInstance == null) {
            sInstance = new LocationHandler(lifecycleOwner, locationCallback);
        }
        return sInstance;
    }

    private LocationRequest getLocationRequest() {
        try {
            locationRequest = new LocationRequest();
            locationRequest.setNumUpdates(1);
            locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            locationRequest.setSmallestDisplacement(2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return locationRequest;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @SuppressLint("MissingPermission")
    void requestLocation() {
        try {
            if (mFusedClient != null) {
                getLocationRequest();
                Timber.d("requesting location");
                mFusedClient.requestLocationUpdates(locationRequest, mLocationCallback, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void stopLocationUpdates() {
        try {
            if (mFusedClient != null) {
                Timber.d("stop location requests");
                mFusedClient.removeLocationUpdates(mLocationCallback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

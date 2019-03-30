package ezike.tobenna.myweather.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class Utilities {

    /**
     * checks if device has internet connection
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    /**
     * shows a dialog
     */
    public static void showDialog(Context context, String title, String message, DialogInterface.OnClickListener positive,
                                  DialogInterface.OnClickListener negative) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", positive)
                .setNegativeButton("Cancel", negative)
                .create()
                .show();
    }

    /**
     * checks if gps is enabled
     */
    public static boolean isLocationProviderEnabled(LocationManager locationManager) {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER);
    }


    /**
     * enables GPS by opening settings
     */
    public static void enableLocationProvider(Context context, String title, String message) {
        showDialog(context, title, message, (dialog, which) -> openSettingsActivity(context), null);
    }


    /**
     * open settings for enabling gps
     */
    public static void openSettingsActivity(Context context) {
        context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }


    /**
     * show toast
     */
    public static void showToast(Context context, String message, int length) {
        Toast.makeText(context, message, length).show();
    }

}

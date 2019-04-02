package ezike.tobenna.myweather.ui.activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import ezike.tobenna.myweather.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        scheduleSplashScreen();
    }

    private void scheduleSplashScreen() {
        new Handler().postDelayed(() -> {
            routeToActivity();
            finish();
        }, 1000L);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void routeToActivity() {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent, options.toBundle());
    }
}

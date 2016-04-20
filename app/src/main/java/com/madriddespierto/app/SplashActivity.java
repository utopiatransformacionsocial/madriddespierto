package com.madriddespierto.app;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.madriddespierto.app.common.Constants;

public class SplashActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        HomeActivity.startActivity(SplashActivity.this);
      }
    }, Constants.SPLASH_DELAY_TIME);
  }
}

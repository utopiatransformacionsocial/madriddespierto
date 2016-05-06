package com.madriddespierto.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.madriddespierto.app.common.Constants;
import com.madriddespierto.app.utils.ShareUtils;

public class HomeActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.nav_view) NavigationView navigationView;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);

    initDrawer();

    initNavigationView();
  }

  @OnClick(R.id.fab)
  public void onClick(View view ){
    if (view.getId()== R.id.fab) {

      WebFragment mWebFragment = (WebFragment)getFragmentManager().findFragmentByTag("WebFragment");

      if (mWebFragment!=null && mWebFragment instanceof WebFragment) {
        ((WebFragment) mWebFragment).shareCurrentURL();
      }
    }else if(view.getId()== R.id.navigation_header_container){
      ShareUtils.openUrl(HomeActivity.this, Constants.URL_FACEBOOK_PROFILE);
    }
  }

  private void initNavigationView() {
    navigationView.setNavigationItemSelectedListener(this);

    View container = navigationView.getHeaderView(0);
    if(container!=null) {
     container.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          HomeActivity.this.onClick(v);
        }
      });
    }
  }

  private void initDrawer() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name,
            R.string.app_name);
    drawer.setDrawerListener(toggle);
    toggle.syncState();
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {

      WebFragment mWebFragment = (WebFragment)getFragmentManager().findFragmentByTag("WebFragment");

      if (mWebFragment!=null && mWebFragment instanceof WebFragment) {
        if (!((WebFragment) mWebFragment).onBack()) {
          super.onBackPressed();
        }
      }else{
        super.onBackPressed();
      }
    }
  }

  //@Override public boolean onCreateOptionsMenu(Menu menu) {
  //  // Inflate the menu; this adds items to the action bar if it is present.
  //  getMenuInflater().inflate(R.menu.home, menu);
  //  return true;
  //}
  //
  //@Override public boolean onOptionsItemSelected(MenuItem item) {
  //  int id = item.getItemId();
  //  if (id == R.id.action_settings) {
  //    return true;
  //  }
  //
  //  return super.onOptionsItemSelected(item);
  //}

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();

     if (id == R.id.nav_about) {
       showAboutView();
    } else if (id == R.id.nav_share) {
       shareApp();
    } else if (id == R.id.nav_rate) {
       rateApp();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  private void showAboutView() {
    AboutActivity.startActivity(this);
  }

  private void shareApp() {
    ShareUtils.shareTitleAndText( HomeActivity.this, getString(R.string.title_share_app),
        getString(R.string.content_share_app) + " " +  getString(R.string.link_app_play_store) );
  }

  private void rateApp() {
    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
    try {
      startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
    } catch (android.content.ActivityNotFoundException anfe) {
      startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
    }
  }

  public static void startActivity(Activity activity) {
    Intent i = new Intent(activity, HomeActivity.class);
    activity.startActivity(i);
    activity.finish();
  }

  public void showSnackMessage(View view, String message) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        //.setAction("Action", null)
        .show();
  }
}

package com.madriddespierto.app;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * A placeholder fragment containing a simple view.
 */
public class AboutActivityFragment extends Fragment {

  public AboutActivityFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    //View view = inflater.inflate(R.layout.fragment_about, container, false);

    PackageInfo pInfo = null;
    try {
      pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }

    String version = "";
    String packageName = "";

    if (pInfo != null) {
      version = pInfo.versionName;
      packageName = pInfo.packageName;
    }

    Element versionElement = new Element();
    versionElement.setTitle("Version " + version);

    View aboutPage = new AboutPage(getActivity())
        .setDescription( getString(R.string.content_about))
        .isRTL(false)
        .setImage(R.drawable.logo_app)
        .addItem(versionElement)
        .addGroup(getString(R.string.contact_us))
        .addEmail(getString(R.string.email_contact))
        .addFacebook(getString(R.string.facebook_contact))
        .addTwitter(getString(R.string.twitter_contact))
        .addPlayStore( packageName)
        .addGitHub( getString(R.string.github_id))
        .create();

    return aboutPage;
  }
}

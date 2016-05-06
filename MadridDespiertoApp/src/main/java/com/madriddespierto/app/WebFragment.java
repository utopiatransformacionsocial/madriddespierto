package com.madriddespierto.app;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.madriddespierto.app.common.Constants;
import com.madriddespierto.app.utils.ShareUtils;
import im.delight.android.webview.AdvancedWebView;

public class WebFragment extends Fragment implements AdvancedWebView.Listener {

  @Bind(R.id.webview) AdvancedWebView mWebview;

  @Bind(R.id.progress_bar) ProgressBar mProgressBar;

  public WebFragment() {}

  public static WebFragment newInstance() {
    WebFragment fragment = new WebFragment();
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_web, container, false);
    ButterKnife.bind(this, rootView);

    mWebview.setListener(WebFragment.this, this);
    mWebview.loadUrl(Constants.URL_INIT, true);
    mWebview.addHttpHeader("X-Requested-With", Constants.HEADER_APP);
    mWebview.getSettings().setSupportZoom(true);
    mWebview.getSettings().setBuiltInZoomControls(true);
    mWebview.getSettings().setDisplayZoomControls(false);
    mWebview.setWebChromeClient(new WebChromeClient() {

      @Override
      public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
        AdvancedWebView newWebView = new AdvancedWebView(getActivity());
        // myParentLayout.addView(newWebView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
        transport.setWebView(newWebView);
        resultMsg.sendToTarget();

        return true;
      }

    });

    return rootView;
  }

  @Override public void onResume() {
    super.onResume();
    mWebview.onResume();
  }

  @Override public void onPause() {
    super.onPause();
    mWebview.onPause();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    if (mWebview != null) {
      mWebview.onDestroy();
    }
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    mWebview.onActivityResult(requestCode, resultCode, data);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onPageStarted(String url, Bitmap favicon) {
      mProgressBar.setVisibility(View.VISIBLE);

  }

  @Override public void onPageFinished(String url) {
    //if (getActivity() instanceof HomeActivity) {
      mProgressBar.setVisibility(View.GONE);
    //}
  }

  @Override public void onPageError(int errorCode, String description, String failingUrl) {

  }

  @Override public void onDownloadRequested(String url, String userAgent, String contentDisposition,
      String mimetype, long contentLength) {

  }

  @Override public void onExternalPageRequest(String url) {

  }

  public void shareCurrentURL() {
    ShareUtils.shareText(getActivity(), mWebview.getUrl());
  }

  public boolean onBack() {
    if (mWebview.canGoBack()) {
      mWebview.goBack();
      return true;
    }else{
      return false;
    }
  }
}

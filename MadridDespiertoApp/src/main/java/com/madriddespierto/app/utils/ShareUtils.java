package com.madriddespierto.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.madriddespierto.app.R;

/**
 * Created by J. Santiago Berrocoso on 20/4/16.
 * jsbs87@gmail.com
 */
public class ShareUtils {
  public static void shareText(Context context, String url) {
    Intent share = new Intent(android.content.Intent.ACTION_SEND);
    share.setType("text/plain");
    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    share.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.subject_text_share));
    share.putExtra(Intent.EXTRA_TEXT, url);

    context.startActivity( Intent.createChooser(share,  context.getString(R.string.title_share_dialog)));
  }

  public static void shareTitleAndText(Context context,String title, String text) {
    Intent share = new Intent(android.content.Intent.ACTION_SEND);
    share.setType("text/plain");
    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    share.putExtra(Intent.EXTRA_SUBJECT, title);
    share.putExtra(Intent.EXTRA_TEXT, text);

    context.startActivity( Intent.createChooser(share,  context.getString(R.string.title_share_dialog)));
  }

  public static void openUrl(Activity activity, String url) {
    final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    activity.startActivity(intent);
  }
}

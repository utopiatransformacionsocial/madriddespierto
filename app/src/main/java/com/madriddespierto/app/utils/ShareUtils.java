package com.madriddespierto.app.utils;

import android.content.Context;
import android.content.Intent;
import com.madriddespierto.app.R;

/**
 * Created by J. Santiago Berrocoso on 20/4/16.
 * jsbs87@gmail.com
 */
public class ShareUtils {
  public static void shareText(Context context, String url) {
    Intent share = new Intent(android.content.Intent.ACTION_SEND);
    share.setType("text/plain");
    //share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

    // Add data to the intent, the receiving app will decide
    // what to do with it.
    share.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.subject_text_share));
    share.putExtra(Intent.EXTRA_TEXT, url);

    context.startActivity( Intent.createChooser(share,  context.getString(R.string.title_share_dialog)));
  }
}

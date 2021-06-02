package ir.afraapps.basic.helper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import ir.afraapps.basic.R;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/6/17.
 */

public class USnackbar {

  private static Snackbar snackbar;


  public static void show(Activity activity, String message, String actionLabel, Runnable action, int duration) {
    snackbar = Snackbar.make(activity.findViewById(android.R.id.content), "", duration);

    ViewGroup view = (ViewGroup) snackbar.getView();
    TextView tv = view.findViewById(R.id.snackbar_text);
    TextView tvAction = view.findViewById(R.id.snackbar_action);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      toRTL(view.getChildAt(0));
    }
    tv.setTextColor(Color.LTGRAY);
    tvAction.setTextColor(UColor.getAccentColor(activity));

    tvAction.setTypeface(UTypeface.getSansRegular());

    snackbar.setText(message);

    if (action != null) {
      snackbar.setAction(actionLabel, v -> action.run());

    } else {
      actionLabel = activity.getString(R.string.ok);
      snackbar.setAction(actionLabel, v -> snackbar.dismiss());
    }

    snackbar.show();
  }


  public static void show(ViewGroup container, String message, String actionLabel, Runnable action, int duration) {
    snackbar = Snackbar.make(container, "", duration);

    ViewGroup view = (ViewGroup) snackbar.getView();
    TextView tv = view.findViewById(R.id.snackbar_text);
    TextView tvAction = view.findViewById(R.id.snackbar_action);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      toRTL(view.getChildAt(0));
    }
    tv.setTextColor(Color.LTGRAY);
    tvAction.setTextColor(UColor.getAccentColor(container.getContext()));

    tvAction.setTypeface(UTypeface.getSansRegular());

    snackbar.setText(message);

    if (action != null) {
      snackbar.setAction(actionLabel, v -> action.run());

    } else {
      actionLabel = container.getContext().getString(R.string.ok);
      snackbar.setAction(actionLabel, v -> snackbar.dismiss());
    }

    snackbar.show();
  }


  @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
  private static void toRTL(View v) {
    if (v == null) return;
    v.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    if (v instanceof ViewGroup) {
      ViewGroup viewGroup = ((ViewGroup) v);
      for (int i = 0; i < viewGroup.getChildCount(); i++) {
        toRTL(viewGroup.getChildAt(i));
      }
    }
  }


  public static void show(Activity activity, String message, String actionLabel, Runnable action) {
    show(activity, message, actionLabel, action, Snackbar.LENGTH_INDEFINITE);
  }


  public static void show(ViewGroup container, String message, String actionLabel, Runnable action) {
    show(container, message, actionLabel, action, Snackbar.LENGTH_INDEFINITE);
  }


  public static void show(Activity activity, String message) {
    show(activity, message, null, null, Snackbar.LENGTH_INDEFINITE);
  }


  public static void show(ViewGroup container, String message) {
    show(container, message, null, null, Snackbar.LENGTH_INDEFINITE);
  }


  public static void show(Activity activity, int message, int actionLabel, Runnable action) {
    show(activity,
      activity.getString(message),
      activity.getString(actionLabel),
      action);
  }

  public static void show(ViewGroup container, int message, int actionLabel, Runnable action) {
    show(container,
      container.getContext().getString(message),
      container.getContext().getString(actionLabel),
      action);
  }


  public static void show(Activity activity, int resId) {
    show(activity,
      activity.getString(resId),
      null,
      null);
  }

  public static void show(ViewGroup container, int resId) {
    show(container,
      container.getContext().getString(resId),
      null,
      null);
  }


  public static void dismiss() {
    if (snackbar != null && snackbar.isShown()) {
      snackbar.dismiss();
    }
  }


  public static boolean isShowing() {
    return snackbar != null && snackbar.isShown();
  }


}
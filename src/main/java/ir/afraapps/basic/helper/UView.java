package ir.afraapps.basic.helper;

import android.content.Context;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StyleRes;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import ir.afraapps.basic.core.Base;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/7/17.
 */

public class UView {

  public static void setTextAppearance(Context context, TextView textView, @StyleRes int style) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      textView.setTextAppearance(style);
    } else {
      textView.setTextAppearance(context, style);
    }
  }


  public static void selectViewChildes(ViewGroup viewGroup, boolean select) {
    if (viewGroup == null) return;
    int childCount = viewGroup.getChildCount();
    for (int i = 0; i < childCount; i++) {
      View child = viewGroup.getChildAt(i);
      child.setSelected(select);
      if (child instanceof ViewGroup) {
        selectViewChildes((ViewGroup) child, select);
      }
    }
  }


  public static void enableViewChildes(ViewGroup viewGroup, boolean enabled) {
    if (viewGroup == null) return;

    viewGroup.setEnabled(enabled);
    int childCount = viewGroup.getChildCount();
    for (int i = 0; i < childCount; i++) {
      View child = viewGroup.getChildAt(i);
      child.setEnabled(enabled);
      if (child instanceof ViewGroup) {
        enableViewChildes((ViewGroup) child, enabled);
      }
    }
  }


  public static void activateViewChildes(ViewGroup viewGroup, boolean activated) {
    if (viewGroup == null) return;

    viewGroup.setActivated(activated);
    int childCount = viewGroup.getChildCount();
    for (int i = 0; i < childCount; i++) {
      View child = viewGroup.getChildAt(i);
      child.setActivated(activated);
      if (child instanceof ViewGroup) {
        activateViewChildes((ViewGroup) child, activated);
      }
    }
  }


  public static void changeViewChildColor(final ViewGroup vg, int color) {
    try {
      for (int i = 0; i < vg.getChildCount(); i++) {
        View child = vg.getChildAt(i);
        if (child instanceof ViewGroup) {
          changeViewChildColor((ViewGroup) child, color);

        } else if (child instanceof TextView) {
          ((TextView) child).setTextColor(color);

        } else if (child instanceof ImageView) {
          ((ImageView) child).setColorFilter(color);
        }

      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static void hideViewByRotate(View view, final Runnable endAction) {
    ViewCompat.animate(view)
      .scaleX(0f)
      .scaleY(0f)
      .alpha(0)
      .rotation(360)
      .setDuration(300)
      .setListener(new ViewPropertyAnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(View view) {
          super.onAnimationEnd(view);
          view.setVisibility(View.GONE);
          if (endAction != null) {
            endAction.run();
          }
        }
      });
  }


  public static void hideViewByRotate(View view) {
    hideViewByRotate(view, null);
  }


  public static void showViewByRotate(View view) {
    ViewCompat.animate(view)
      .scaleX(1f)
      .scaleY(1f)
      .alpha(0.9f)
      .rotation(0)
      .setDuration(300)
      .setListener(new ViewPropertyAnimatorListenerAdapter() {
        @Override
        public void onAnimationStart(View view) {
          super.onAnimationEnd(view);
          view.setVisibility(View.VISIBLE);
        }
      });
  }


  public static void setBgColor(RemoteViews remoteViews, @IdRes int resId, @ColorInt int color) {
    remoteViews.setInt(resId, "setBackgroundColor", color);
  }


  public static void setBgResource(RemoteViews remoteViews, @IdRes int resId, @DrawableRes int drawableRes) {
    remoteViews.setInt(resId, "setBackgroundResource", drawableRes);
  }


  public static void setBgResource(RemoteViews remoteViews, @IdRes int resId, String drawableName) {
    int drawableRes = Base.getResources().getIdentifier(drawableName, "drawable", Base.getContext().getPackageName());
    if (drawableRes != 0) {
      remoteViews.setInt(resId, "setBackgroundResource", drawableRes);
    }
  }


}

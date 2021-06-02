package ir.afraapps.basic.core;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/6/17.
 */

public class Base {

  @SuppressLint("StaticFieldLeak")
  private static Context context;
  private static Handler handler;


  public static Context getContext() {
    return context;
  }


  public static View inflateLayout(Context context, @LayoutRes int res, ViewGroup viewGroup) {
    return LayoutInflater.from(context).inflate(res, viewGroup);
  }

  public static View inflateLayout(Context context, @LayoutRes int res) {
    return inflateLayout(context, res, null);
  }


  public static String getString(@StringRes int res) {
    return context.getString(res);
  }


  public static String getString(@StringRes int res, Object... formatArgs) {
    return context.getString(res, formatArgs);
  }


  public static Resources getResources() {
    return context.getResources();
  }


  public static DisplayMetrics getDisplayMetrics() {
    return getResources().getDisplayMetrics();
  }


  public static Handler getHandler() {
    return handler;
  }


  public static void post(Runnable action) {
    handler.post(action);
  }


  public static void postDelayed(Runnable action, long delayMillis) {
    handler.postDelayed(action, delayMillis);
  }

  static void init(Application application) {
    context = application.getApplicationContext();
    handler = new Handler();
  }

}

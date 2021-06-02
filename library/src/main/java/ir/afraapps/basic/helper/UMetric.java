package ir.afraapps.basic.helper;

import androidx.annotation.DimenRes;
import android.util.TypedValue;

import ir.afraapps.basic.core.Base;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/6/17.
 */

public class UMetric {


  public static int getDisplayWidth() {
    return Base.getDisplayMetrics().widthPixels;
  }


  public static int getDisplayHeight() {
    return Base.getDisplayMetrics().heightPixels;
  }


  public static int getDisplayMinWidth() {
    return Math.min(getDisplayWidth(), getDisplayHeight());
  }


  public static int toDIP(int size) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, Base.getDisplayMetrics());
  }


  public static float toDIPFloat(int size) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, Base.getDisplayMetrics());
  }


  public static int toSP(int size) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, Base.getDisplayMetrics());
  }


  public static float toSPFloat(int size) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, Base.getDisplayMetrics());
  }


  public static float toSPFloat(float size) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, Base.getDisplayMetrics());
  }


  public static int getDimen(@DimenRes int... resId) {
    int result = 0;
    for (int id : resId) {
      result += Base.getResources().getDimensionPixelSize(id);
    }
    return result;
  }


  public static int byteToMB(long value) {
    int MB = 1048576;
    return (int) (value / MB);
  }


}

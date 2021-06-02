package ir.afraapps.basic.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import android.util.TypedValue;

import ir.afraapps.basic.R;
import ir.afraapps.basic.core.Base;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/9/17.
 */

public class UColor {


  public static int getAttrColor(int attr) {
    TypedValue typedValue = new TypedValue();

    TypedArray a = Base.getContext().obtainStyledAttributes(typedValue.data, new int[]{attr});
    int color = a.getColor(0, 0);

    a.recycle();

    return color;
  }


  public static int getAttrColor(Context context, int attr) {
    TypedValue typedValue = new TypedValue();

    TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{attr});
    int color = a.getColor(0, 0);

    a.recycle();

    return color;
  }


  public static int getPrimaryColor() {
    return getAttrColor(R.attr.colorPrimary);
  }


  public static int getPrimaryColor(Context context) {
    return getAttrColor(context, R.attr.colorPrimary);
  }


  public static int getPrimaryDarkColor() {
    return getAttrColor(R.attr.colorPrimaryDark);
  }


  public static int getPrimaryDarkColor(Context context) {
    return getAttrColor(context, R.attr.colorPrimaryDark);
  }


  public static int getAccentColor() {
    return getAttrColor(R.attr.colorAccent);
  }


  public static int getAccentColor(Context context) {
    return getAttrColor(context, R.attr.colorAccent);
  }


  public static int get(@ColorRes int res) {
    return ContextCompat.getColor(Base.getContext(), res);
  }

  /**
   * Set lightness of color
   *
   * @param color The color for change lightness
   * @param value The set value (0 ... 1)
   * @return The color that changed to the desired lightness
   */
  @ColorInt
  public static int setLightness(@ColorInt int color, float value) {
    float[] hsl = new float[3];
    ColorUtils.colorToHSL(color, hsl);
    hsl[2] = value;
    hsl[2] = Math.min(Math.max(hsl[2], 0.0f), 1.0f);
    return ColorUtils.HSLToColor(hsl);
  }


  /**
   * Increase or decrease lightness of color
   *
   * @param color The color for change lightness
   * @param value The value that is supposed to be added to the current value (0 ... 1)
   * @return The color that changed to the desired lightness
   */
  @ColorInt
  public static int changeLightness(@ColorInt int color, float value) {
    float[] hsl = new float[3];
    ColorUtils.colorToHSL(color, hsl);
    hsl[2] += value;
    hsl[2] = Math.min(Math.max(hsl[2], 0.0f), 1.0f);
    return ColorUtils.HSLToColor(hsl);
  }


  /**
   * Set color alpha
   *
   * @param color The color for change alpha
   * @param alpha New alpha value of color
   * @return The color that changed this alpha
   */
  @ColorInt
  public static int setAlpha(@ColorInt int color, int alpha) {
    return ColorUtils.setAlphaComponent(color, alpha);
  }


  public static ColorMatrixColorFilter getTintFilter(int color, int alpha) {
    float lr = 0.2126f;
    float lg = 0.7152f;
    float lb = 0.0722f;

    ColorMatrix grayscaleMatrix = new ColorMatrix(new float[]{
      lr, lg, lb, 0, 0, //
      lr, lg, lb, 0, 0, //
      lr, lg, lb, 0, 0, //
      0, 0, 0, 0, alpha, //
    });

    int dr = Color.red(color);
    int dg = Color.green(color);
    int db = Color.blue(color);
    float drf = dr / 255f;
    float dgf = dg / 255f;
    float dbf = db / 255f;
    float af = alpha / 255f;

    ColorMatrix tintMatrix = new ColorMatrix(new float[]{
      drf, 0, 0, 0, 0, //
      0, dgf, 0, 0, 0, //
      0, 0, dbf, 0, 0, //
      0, 0, 0, af, 0, //
    });

    tintMatrix.preConcat(grayscaleMatrix);
    return new ColorMatrixColorFilter(tintMatrix);
  }


  public static ColorMatrixColorFilter getTintFilter(int color) {
    return getTintFilter(color, 255);
  }


  public static ColorMatrixColorFilter getReverseFilter() {
    return new ColorMatrixColorFilter(new float[]{
      -1.0f, 0, 0, 0, 255, // red
      0, -1.0f, 0, 0, 255, // green
      0, 0, -1.0f, 0, 255, // blue
      0, 0, 0, 1.0f, 0  // alpha
    });
  }


}

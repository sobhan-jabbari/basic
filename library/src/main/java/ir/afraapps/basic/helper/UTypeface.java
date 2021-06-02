package ir.afraapps.basic.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.FontRes;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.TypefaceCompat;
import ir.afraapps.basic.R;
import ir.afraapps.basic.core.Base;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/7/17.
 */

public class UTypeface {


  public static Typeface get(@FontRes int res) {
    return ResourcesCompat.getFont(Base.getContext(), res);
  }


  @SuppressLint("RestrictedApi")
  public static Typeface get(String fontName) {
    final Context   context   = Base.getContext();
    final Resources resources = context.getResources();
    int             resId     = resources.getIdentifier(fontName, "font", context.getPackageName());
    TypedValue      value     = new TypedValue();
    resources.getValue(resId, value, true);
    String path = value.string.toString();
    return TypefaceCompat.createFromResourcesFontFile(context, resources, resId, path, Typeface.NORMAL);
  }


  public static Typeface getFromAsset(@Nullable String fontName) {
    if (UText.isEmpty(fontName)) return Typeface.DEFAULT;
    return Typeface.createFromAsset(Base.getContext().getAssets(), fontName);
  }


  public static Typeface getSansRegular() {
    return ResourcesCompat.getFont(Base.getContext(), R.font.sans_regular);
  }

  public static Typeface getSansLight() {
    return ResourcesCompat.getFont(Base.getContext(), R.font.sans_light);
  }


  public static Typeface getSansBold() {
    return ResourcesCompat.getFont(Base.getContext(), R.font.sans_medium);
  }


  public static void changeViewFont(final View v, final Typeface font, float size, int color, int style) {
    try {

      final Typeface typeface  = font != null ? font : getSansLight();
      final int      fontStyle = style < 0 ? Typeface.NORMAL : style;

      if (v instanceof ViewGroup) {
        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {
          View child = vg.getChildAt(i);
          changeViewFont(child, font, size, color, style);
        }
      } else if (v instanceof TextView) {
        TextView tv = (TextView) v;
        if (size > 0) {
          tv.setTextSize(size);
        }
        if (color != 0) {
          tv.setTextColor(color);
        }
        tv.setTypeface(typeface, fontStyle);

      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static void changeViewFont(View v, Typeface font) {
    if (v == null)
      return;
    changeViewFont(v, font, 0, 0, Typeface.NORMAL);
  }


  public static void changeViewFont(View v, Typeface font, float size) {
    if (v == null)
      return;
    changeViewFont(v, font, size, 0, Typeface.NORMAL);
  }


  public static void changeViewFont(View v, float size) {
    if (v == null)
      return;
    changeViewFont(v, getSansLight(), size, 0, Typeface.NORMAL);
  }


  public static void changeViewFont(View v, int color) {
    if (v == null)
      return;
    changeViewFont(v, getSansLight(), 0, color, Typeface.NORMAL);
  }


  public static void changeViewFont(View v) {
    changeViewFont(v, getSansLight());
  }


}

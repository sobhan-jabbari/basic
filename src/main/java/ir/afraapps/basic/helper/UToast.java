package ir.afraapps.basic.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ir.afraapps.basic.R;
import ir.afraapps.basic.core.Base;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/6/17.
 */

public class UToast {

  public static void show(String message, int duration,
                          int gravity, int offsetX, int offsetY) {
    // Context context = getThemeContext(style);
    Context context = Base.getContext();
    Toast   toast   = new Toast(context);
    @SuppressLint("InflateParams")
    View layout = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
    TextView txtMessage = layout.findViewById(R.id.txt_toast_message);
    txtMessage.setText(message);

    txtMessage.setTypeface(UTypeface.getSansRegular());

    toast.setGravity(gravity, offsetX, offsetY);
    toast.setDuration(duration);
    toast.setView(layout);
    toast.show();
  }


  @SuppressLint("RestrictedApi")
  private static Context getThemeContext(@StyleRes int style) {
    return new ContextThemeWrapper(Base.getContext(), style);
  }


  public static void show(String message) {
    show(message, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, UMetric.toDIP(64));
  }


  public static void show(int resId) {
    show(Base.getString(resId));
  }


  public static void show(String message, int gravity, int offsetX, int offsetY) {
    show(message, Toast.LENGTH_SHORT, gravity, offsetX, offsetY);
  }


}

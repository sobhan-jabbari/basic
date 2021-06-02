package ir.afraapps.basic.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/10/17.
 */

public class USoftKeyboard {


  public static boolean close(Activity context) {
    View view = context.getCurrentFocus();
    return view != null && get(context).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
  }

  public static boolean close(View view) {
    return view != null && get(view.getContext()).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
  }


  public static boolean show(EditText editText) {
    editText.requestFocus();
    return get(editText.getContext()).showSoftInput(editText, InputMethodManager.SHOW_FORCED);
  }


  private static InputMethodManager get(Context context) {
    return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
  }


}

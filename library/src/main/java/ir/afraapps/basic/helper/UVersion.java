package ir.afraapps.basic.helper;

import android.content.pm.PackageManager;
import android.os.Build;

import ir.afraapps.basic.core.Base;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/11/17.
 */

public class UVersion {


  /**
   * @return current api is kitkat or above (+19)
   */
  public static boolean isKitkat() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
  }


  /**
   * @return current api is Lollipop or above (+21)
   */
  public static boolean isLollipop() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
  }


  /**
   * @return current api is Marshmallow or above (+23)
   */
  public static boolean isMarshmallow() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
  }


  /**
   * @return current api is Nougat or above (+24)
   */
  public static boolean isNougat() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
  }


  /**
   * @return device android version code
   */
  public static int getCode() {
    try {
      return Base.getContext().getPackageManager().getPackageInfo(
        Base.getContext().getPackageName(), 0).versionCode;

    } catch (PackageManager.NameNotFoundException e) {
      // error
    }
    return 0;
  }


  /**
   * @return device android version name as persian format
   */
  public static String getName() {
    try {
      return Base.getContext().getPackageManager().getPackageInfo(
        Base.getContext().getPackageName(), 0).versionName;

    } catch (PackageManager.NameNotFoundException e) {
      // error
    }
    return "";
  }


}

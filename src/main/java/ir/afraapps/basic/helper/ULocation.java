package ir.afraapps.basic.helper;

import android.content.Context;
import android.location.LocationManager;

import ir.afraapps.basic.core.Base;


/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/8/17.
 */

public class ULocation {

  public static boolean isGPSProviderEnable() {
    try {
      return get().isProviderEnabled(LocationManager.GPS_PROVIDER);

    } catch (Exception e) {
      return false;
    }
  }


  public static boolean isNetworkProviderEnable() {
    try {
      return get().isProviderEnabled(LocationManager.NETWORK_PROVIDER);

    } catch (Exception e) {
      return false;
    }
  }


  private static LocationManager get() {
    return (LocationManager) Base.getContext().getSystemService(Context.LOCATION_SERVICE);
  }

}

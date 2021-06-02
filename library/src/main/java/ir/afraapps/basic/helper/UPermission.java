package ir.afraapps.basic.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;

import ir.afraapps.basic.core.Base;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/8/17.
 */

public final class UPermission {


  public static boolean isGranted(Activity activity, String[] permissions, int requestCode) {
    boolean grantedPermission = true;
    for (String permission : permissions) {
      if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
        grantedPermission = false;
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
        break;
      }

    }

    return grantedPermission;
  }


  public static boolean isGranted(Activity activity, String permissions, int requestCode) {
    return isGranted(activity, new String[]{permissions}, requestCode);
  }


  public static boolean isGranted(@NonNull Fragment fragment, String[] permissions, int requestCode) {
    if (fragment.getContext() == null) return false;

    boolean grantedPermission = true;
    for (String permission : permissions) {
      if (ContextCompat.checkSelfPermission(fragment.getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
        grantedPermission = false;
        fragment.requestPermissions(permissions, requestCode);
        break;
      }

    }

    return grantedPermission;
  }

  public static boolean isGranted(Fragment fragment, String permissions, int requestCode) {
    return isGranted(fragment, new String[]{permissions}, requestCode);
  }


  public static boolean isGranted(String... permissions) {
    for (String permission : permissions) {
      if (ContextCompat.checkSelfPermission(Base.getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
        return false;
      }
    }

    return true;
  }


  public static boolean isGrantedLocation() {
    return isGranted(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
  }


  public static boolean isGrantedLocation(Activity activity, int requestCode) {
    return isGranted(activity,
      new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
      requestCode);
  }


  public static boolean isGrantedLocation(Fragment fragment, int requestCode) {
    return isGranted(fragment,
      new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
      requestCode);
  }


  public static boolean isGrantedFineLocation(Activity activity, int requestCode) {
    return isGranted(activity,
      new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
      requestCode);
  }


  public static boolean isGrantedFineLocation(Fragment fragment, int requestCode) {
    return isGranted(fragment,
      new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
      requestCode);
  }


  public static boolean isGrantedFineLocation() {
    return isGranted(Manifest.permission.ACCESS_FINE_LOCATION);
  }


  public static boolean isGrantedCamera(Activity activity, int requestCode) {
    return isGranted(activity,
      new String[]{Manifest.permission.CAMERA}, requestCode);
  }


  public static boolean isGrantedCamera(Fragment fragment, int requestCode) {
    return isGranted(fragment,
      new String[]{Manifest.permission.CAMERA}, requestCode);
  }


  public static boolean isGrantedStorage(Activity activity, int requestCode) {
    return isGranted(activity,
      new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE},
      requestCode);
  }


  public static boolean isGrantedStorage(Fragment fragment, int requestCode) {
    return isGranted(fragment,
      new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE},
      requestCode);
  }


  public static boolean isGrantedPhoneState(Activity activity, int requestCode) {
    return isGranted(activity, new String[]{Manifest.permission.READ_PHONE_STATE,}, requestCode);
  }


  public static boolean isGrantedPhoneState(Fragment fragment, int requestCode) {
    return isGranted(fragment, new String[]{Manifest.permission.READ_PHONE_STATE,}, requestCode);
  }


  public static boolean shouldShowMessage(Activity activity, String[] permissions) {
    boolean canRequest = false;
    if (permissions.length > 0) {
      for (String permission : permissions) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
          canRequest = true;
        }
      }
    }

    return canRequest;
  }


  public static boolean checkPermissionResult(Activity activity, int requestCode,
                                              @NonNull final String[] permissions,
                                              @NonNull int[] grantResults,
                                              @StringRes int actionLabel,
                                              @StringRes int message) {
    USnackbar.dismiss();

    if (UPermission.isGranted(grantResults)) {
      return true;

    } else {
      if (UPermission.shouldShowMessage(activity, permissions)) {
        USnackbar.show(activity, message, actionLabel,
          () -> ActivityCompat.requestPermissions(activity, permissions, requestCode));

      } else {
        USnackbar.show(activity, message, actionLabel, () -> {
          Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", activity.getPackageName(), null));
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          try {
            activity.startActivityForResult(intent, requestCode);

          } catch (Exception e) {
            USnackbar.dismiss();
          }
        });
      }

      return false;
    }
  }


  public static boolean checkPermissionResult(Activity activity, Fragment fragment, int requestCode,
                                              @NonNull final String[] permissions,
                                              @NonNull int[] grantResults,
                                              @StringRes int actionLabel,
                                              @StringRes int message) {
    USnackbar.dismiss();

    if (UPermission.isGranted(grantResults)) {
      return true;

    } else {
      if (UPermission.shouldShowMessage(activity, permissions)) {
        USnackbar.show(activity, message, actionLabel,
          () -> fragment.requestPermissions(permissions, requestCode));

      } else {
        USnackbar.show(activity, message, actionLabel, () -> {
          Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", activity.getPackageName(), null));
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          try {
            activity.startActivityForResult(intent, requestCode);

          } catch (Exception e) {
            USnackbar.dismiss();
          }
        });
      }

      return false;
    }
  }


  public static boolean isGranted(int[] grantResults) {
    boolean isGranted = true;
    if (grantResults.length > 0) {
      for (int result : grantResults) {
        if (result != PackageManager.PERMISSION_GRANTED) {
          isGranted = false;
        }
      }

    } else {
      isGranted = false;
    }

    return isGranted;
  }


}

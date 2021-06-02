package ir.afraapps.basic.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.os.Build;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.RemoteViews;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import ir.afraapps.basic.core.Base;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/8/17.
 */

public class UImage {


  public static void setVector(ImageView imageView, int resId) {
    if (imageView == null || resId == 0) {
      return;
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      imageView.setImageResource(resId);

    } else {
      VectorDrawableCompat drawable = VectorDrawableCompat
        .create(imageView.getContext().getResources(), resId, null);
      imageView.setImageDrawable(drawable);
    }

  }


  public static void setVector(Context context, RemoteViews remoteViews, int resId, int drawable, int color) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      remoteViews.setImageViewResource(resId, drawable);
      remoteViews.setInt(resId, "setColorFilter", color);

    } else {
      // Drawable d = AppCompatDrawableManager.get().getDrawable(context, drawable);
      VectorDrawableCompat d = VectorDrawableCompat.create(context.getResources(), drawable, null);
      if (d == null) {
        return;
      }
      Bitmap b = Bitmap.createBitmap(d.getIntrinsicWidth(),
        d.getIntrinsicHeight(),
        Bitmap.Config.ARGB_8888);
      Canvas c = new Canvas(b);
      d.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
      d.setBounds(0, 0, c.getWidth(), c.getHeight());
      d.draw(c);
      remoteViews.setImageViewBitmap(resId, b);
    }
  }


  public static void setVector(Context context, RemoteViews remoteViews, int resId, int drawable) {
    setVector(context, remoteViews, resId, drawable, 0);

  }


  public static boolean saveBitmap(Bitmap bitmap, File targent, Bitmap.CompressFormat format, int quality) {
    try {
      FileOutputStream output = new FileOutputStream(targent);
      bitmap.compress(format, quality, output);
      output.flush();
      output.close();
      return true;
    } catch (Exception e) {
      return false;
    }
  }


  public static boolean saveBitmap(Bitmap bitmap, String path, String name, Bitmap.CompressFormat format, int quality) {
    return saveBitmap(bitmap, new File(path, name), format, quality);
  }


  public static boolean saveBitmap(Bitmap bitmap, File file, String name, Bitmap.CompressFormat format, int quality) {
    return saveBitmap(bitmap, file.getAbsolutePath(), name, format, quality);
  }


  public static boolean saveToJPG(Bitmap bitmap, File file, String name) {
    return saveBitmap(bitmap, file.getAbsolutePath(), name, Bitmap.CompressFormat.JPEG, 100);
  }

  public static boolean saveToJPG(Bitmap bitmap, String path, String name) {
    return saveBitmap(bitmap, path, name, Bitmap.CompressFormat.JPEG, 100);
  }


  public static boolean saveToJPG(Bitmap bitmap, File file, String name, int quality) {
    return saveBitmap(bitmap, file.getAbsolutePath(), name, Bitmap.CompressFormat.JPEG, quality);
  }

  public static boolean saveToJPG(Bitmap bitmap, String path, String name, int quality) {
    return saveBitmap(bitmap, path, name, Bitmap.CompressFormat.JPEG, quality);
  }

  public static boolean saveToPNG(Bitmap bitmap, String path, String name) {
    return saveBitmap(bitmap, path, name, Bitmap.CompressFormat.PNG, 100);
  }


  public static boolean saveToPNG(Bitmap bitmap, String path, String name, int quality) {
    return saveBitmap(bitmap, path, name, Bitmap.CompressFormat.PNG, quality);
  }

  /**
   * @param target the target of image that can be file or resource or byteArray
   * @param width  the max with of image. if > 0 will loaded by inSampleSize option
   * @return image as Bitmap
   */
  public static Bitmap getImage(Object target, int width) {
    Bitmap bitmap = null;

    int sampleSize = 1;

    BitmapFactory.Options opt = new BitmapFactory.Options();
    opt.inJustDecodeBounds = true;
    if (target instanceof File) {
      BitmapFactory.decodeFile(((File) target).getAbsolutePath(), opt);

    } else if (target instanceof Integer) {
      BitmapFactory.decodeResource(Base.getResources(), ((int) target), opt);

    } else if (target instanceof byte[]) {
      byte[] byteArray = (byte[]) target;
      BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, opt);
    }

    int outWidth = opt.outWidth;

    while (width > 0 && outWidth / sampleSize > width) {
      sampleSize *= 2;
    }

    opt.inSampleSize = sampleSize;
    opt.inJustDecodeBounds = false;

    try {
      if (target instanceof File) {
        bitmap = BitmapFactory.decodeFile(((File) target).getAbsolutePath(), opt);

      } else if (target instanceof Integer) {
        bitmap = BitmapFactory.decodeResource(Base.getResources(), ((int) target), opt);

      } else if (target instanceof byte[]) {
        byte[] byteArray = (byte[]) target;
        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, opt);
      }
      return bitmap;

    } catch (OutOfMemoryError e) {
      return getImage(target, (int) (width * 0.7f));
    }
  }


  public static byte[] toByteArray(Bitmap bitmap) {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
    return stream.toByteArray();
  }


  public static Bitmap bitmapFromBase64(String base64) {
    byte[] data = Base64.decode(base64, Base64.DEFAULT);
    return BitmapFactory.decodeByteArray(data, 0, data.length);
  }


}

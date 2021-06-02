package ir.afraapps.basic.helper;

import android.webkit.MimeTypeMap;

public class UFile {

  public static boolean isImage(String mimeType) {
    return mimeType != null && mimeType.startsWith("image/");
  }

  public static boolean isVideo(String mimeType) {
    return mimeType != null && mimeType.startsWith("video/");
  }

  public static boolean isAudio(String mimeType) {
    return mimeType != null && mimeType.startsWith("audio/");
  }

  public static boolean isMedia(String mimeType) {
    return mimeType != null && (mimeType.startsWith("image/") || mimeType.startsWith("audio/") || mimeType.startsWith("video/"));
  }

  public static boolean isFile(String mimeType) {
    return mimeType != null && mimeType.startsWith("application/");
  }

  public static boolean isPdf(String mimeType) {
    return mimeType != null && mimeType.equals("application/pdf");
  }

  public static boolean isApk(String mimeType) {
    return mimeType != null && mimeType.equals("application/apk");
  }

  public static boolean isDoc(String mimeType) {
    return mimeType != null && (mimeType.equals("application/pdf")
      || mimeType.equals("application/pps")
      || mimeType.equals("application/ppt")
      || mimeType.equals("application/pptx")
      || mimeType.equals("application/ppsx")
      || mimeType.equals("application/pptsx")
      || mimeType.equals("application/doc")
      || mimeType.equals("application/vnd.ms-powerpoint")
      || mimeType.equals("application/msword")
      || mimeType.equals("application/docx"));
  }


  public static String getExtension(String mimeType) {
    if (isApk(mimeType)) {
      return "apk";
    }
    return MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
  }

}

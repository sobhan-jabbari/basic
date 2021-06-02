package ir.afraapps.basic.helper;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/7/17.
 */

public class UText {

    private static final char[] persianDigits = {'۰', '۱', '۲', '۳', '۴', '۵', '۶',
            '۷', '۸', '۹'};

    public static final char[] ARABIC_DIGITS = {'٠', '١', '٢', '٣', '٤', '٥',
            '٦', '٧', '٨', '٩'};

    public static final char[] ENGLISH_DIGITS = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9'};


    public static String formatNumber(Object number, char[] digits) {

        if (number == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        String stringNumber = null;

        if (number instanceof String) {
            stringNumber = (String) number;

        } else if (number instanceof Integer) {
            stringNumber = Integer.toString((int) number);

        } else if (number instanceof Long) {
            stringNumber = Long.toString((long) number);

        } else if (number instanceof Float) {
            stringNumber = Float.toString((float) number);

        } else if (number instanceof Double) {
            stringNumber = Double.toString((double) number);
        }

        if (stringNumber == null) {
            return null;
        }

        for (char i : stringNumber.toCharArray()) {
            if (Character.isDigit(i)) {
                sb.append(digits[Integer.parseInt(i + "")]);
            } else {
                sb.append(i);
            }
        }

        return sb.toString();
    }

    public static String formatNumber(Object number) {
        return formatNumber(number, persianDigits);
    }


    public static String toLatinDigits(Object number) {
        return formatNumber(number, ENGLISH_DIGITS);
    }

    public static boolean isEmpty(String text) {
        return TextUtils.isEmpty(text) || text.equalsIgnoreCase("null");
    }


    public static boolean contains(String str1, String str2) {
        return !isEmpty(str1) && !isEmpty(str2) && str1.contains(str2);
    }


    public static boolean equals(String str1, String str2) {
        return !isEmpty(str1) && !isEmpty(str2) && TextUtils.equals(str1, str2);
    }


    public static boolean isValidateText(String string) {
        String text = string != null ? string.trim() : null;
        return !TextUtils.isEmpty(text);
    }

    public static boolean isValidMobileNumber(String number) {
        Pattern pattern = Pattern.compile("^(((\\+|00)98)|98|0)?9[01239]\\d{8}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }


    public static boolean isValidAllPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("^(((\\+|00)98)|98|0)?[1-9]\\d{9}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }


    public static boolean isValidNationalCode(String number) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }


    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static String join(Iterable tokens) {
        return TextUtils.join(",", tokens);
    }


    public static String encodeBase64(String text) {
        String encoded = "";
        try {
            // encoded = URLEncoder.encode(text, "UTF-8");
            encoded = Base64.encodeToString(text.getBytes(), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encoded;
    }

    public static String decodeBase64(String text) {
        String decoded = "";
        try {
            // decoded = URLDecoder.decode(text, "UTF-8");
            decoded = new String(Base64.decode(text, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decoded;
    }


    public static String formatTime(Object object) {
        DateFormat format = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        try {
            return format.format(object);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTime() {
        DateFormat format = new SimpleDateFormat("HH-mm-ss", Locale.ENGLISH);
        try {
            return format.format(new Date());
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatFloat(float number) {
        String result;
        if (number % 1.0 != 0) {
            result = String.format(Locale.getDefault(), "%.1f", number);
        } else {
            result = String.format(Locale.getDefault(), "%.0f", number);
        }
        return result;
    }


    public static String formatInteger(int number) {
        return String.format(Locale.getDefault(), "%,d", number);
    }


    public static int toInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return 0;
        }

    }


    public static Spanned fromHtml(String text) {
        if (isEmpty(text))
            return null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(text);
        }
    }

    public static Spanned fromHtmlWithColor(String text) {
        if (isEmpty(text))
            return null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_OPTION_USE_CSS_COLORS);
        } else {
            return Html.fromHtml(text);
        }
    }


    public static boolean StoreTextToFile(String text, File target) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(target));
            writer.write(text);

        } catch (IOException ex) {
            return false;

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

}

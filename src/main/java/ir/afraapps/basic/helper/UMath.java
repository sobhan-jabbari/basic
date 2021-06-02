package ir.afraapps.basic.helper;

import java.util.Random;

/**
 * In the name of Allah
 * <p>
 * Created by ali on 3/10/18.
 */
public class UMath {


  public static int randInt(int min, int max) {
    return new Random().nextInt(max - min) + min;
  }

  int limitRange(int value, int min, int max) {
    return Math.min(Math.max(value, min), max);
  }

  boolean isInRange(int value, int min, int max) {
    return (value >= min) && (value <= max);
  }

}

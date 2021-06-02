package ir.afraapps.basic.core;

import android.app.Application;


/**
 * In the name of Allah
 * <p>
 * Created by ali on 11/6/17.
 */

public class BasicApp extends Application {


  @Override
  public void onCreate() {
    super.onCreate();
    Base.init(this);
  }


}

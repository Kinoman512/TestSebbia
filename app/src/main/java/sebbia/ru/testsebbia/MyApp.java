package sebbia.ru.testsebbia;

import android.app.Application;
import android.content.Context;

/**
 * Created by User on 01.02.2017.
 */

public class MyApp extends Application{

    static Context myApp;
    @Override
    public void onCreate() {
        myApp = this;
    }

    public static Context getApp() {
        return  myApp;
    }
}

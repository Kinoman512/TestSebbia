package sebbia.ru.testsebbia;

import android.app.Fragment;

/**
 * Created by User on 01.02.2017.
 */

public class MyFragmentManager {



    static MainActivity activity;

    public static void setActivity(MainActivity act){
        activity = act;
    }

    public static void replaceFragment(Fragment fragment){
        activity.replaceFragment(fragment);
    }




}

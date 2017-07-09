package narek.example.com.yandex_weather_app.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import narek.example.com.yandex_weather_app.R;

public class FragmentUtils {

    public static void openFragment(Fragment fragment, FragmentManager manager, FragmentTag tag) {
        openFragment(fragment, manager, tag, false);
    }

    public static void openFragment(Fragment fragment,
                                    FragmentManager manager,
                                    FragmentTag tag,
                                    boolean whitBackStack) {
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (whitBackStack)
            fragmentTransaction.addToBackStack(tag.toString());
        fragmentTransaction.commit();
    }

    public static boolean isFragmentExist(FragmentManager manager, FragmentTag tag) {
        Fragment activeFragment = manager.findFragmentByTag(tag.toString());
        return activeFragment != null;
    }
}

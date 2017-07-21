package narek.example.com.yandex_weather_app.util;

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
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if (!isFragmentExist(manager, tag)) {
            fragmentTransaction.add(R.id.container, fragment, tag.toString());
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            if (whitBackStack)
                fragmentTransaction.addToBackStack(tag.toString());
            fragmentTransaction.commit();
        } else {
            Fragment activeFragment = manager.findFragmentByTag(tag.toString());
            for (Fragment fragment1 : manager.getFragments()) {
                if (fragment1 != null)
                    fragmentTransaction.hide(fragment1);
            }
            fragmentTransaction.show(activeFragment);
            fragmentTransaction.commit();

        }
    }

    public static boolean isFragmentExist(FragmentManager manager, FragmentTag tag) {
        Fragment activeFragment = manager.findFragmentByTag(tag.toString());
        return activeFragment != null;
    }
}

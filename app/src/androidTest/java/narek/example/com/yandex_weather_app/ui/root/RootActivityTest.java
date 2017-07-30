package narek.example.com.yandex_weather_app.ui.root;

import android.os.SystemClock;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatImageButton;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui.abut_us.AbutUsFragment;
import narek.example.com.yandex_weather_app.ui.find_city.FindCityFragment;
import narek.example.com.yandex_weather_app.ui.weather.WeatherFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
public class RootActivityTest {

    @Rule
    public ActivityTestRule<RootActivity> rootActivityActivityTestRule = new ActivityTestRule<RootActivity>(RootActivity.class);

    @Test
    public void onNavigationItemSelected() throws Exception {

        onView(withClassName(equalTo(AppCompatImageButton.class.getName()))).perform(click());

        onView(withText(R.string.settings)).perform(click());

        onView(withId(R.id.interval_seek_bar)).check(matches(isDisplayed()));
    }

    @Test
    public void openAboutUsFragment() throws Exception {

        onView(withClassName(equalTo(AppCompatImageButton.class.getName()))).perform(click());

        onView(withText(R.string.about_us)).perform(click());

        onView(withId(R.id.icon_about_iv)).check(matches(isDisplayed()));
        onView(withId(R.id.text)).check(matches(isDisplayed()));
        onView(withId(R.id.contacts_tv)).check(matches(isDisplayed()));
    }

    @Test
    public void openFindCityFragment() throws Exception {

        onView(withClassName(equalTo(AppCompatImageButton.class.getName()))).perform(click());

        onView(withText(R.string.find_city)).perform(click());

        onView(withId(R.id.find_city_ed)).check(matches(isDisplayed()));

    }
}
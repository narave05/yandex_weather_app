package narek.example.com.yandex_weather_app.ui.find_city;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatImageButton;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui.root.RootActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
public class FindCityFragmentTest {

    public static final String TOMSK = "Tomsk";
    public static final int MILLIS = 3000;
    @Rule
    public ActivityTestRule<RootActivity> rootActivityActivityTestRule = new ActivityTestRule<>(RootActivity.class);

    @Test
    public void isEditTextWorksFineWithRecyclerView() throws Exception {

        onView(withClassName(equalTo(AppCompatImageButton.class.getName()))).perform(click());

        onView(withText(R.string.find_city)).perform(click());

        onView(withId(R.id.find_city_ed)).check(matches(isDisplayed())).perform(typeText(TOMSK));

        SystemClock.sleep(MILLIS);

        closeSoftKeyboard();

        onView(withId(R.id.recycle_view_cities)).perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.swipe_refresh)).check(matches(isDisplayed()));
    }
}
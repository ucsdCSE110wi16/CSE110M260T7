package com.example.jem.ucsdcarpool;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
/**
 * Created by Jem on 3/6/16.
 */

@RunWith(AndroidJUnit4.class)

public class driver_Test {
    @ClassRule
    public static DisableAnimationsRule disableAnimation;

    @Rule
    public ActivityTestRule<Driver> registerrule = new ActivityTestRule<>(Driver.class);


    @Test
    public void RegisterAcitityTest() {
        //test
        onView(withId(R.id.user_driverlicense_update_driver))
                .perform(typeText("fjfjfj"), closeSoftKeyboard());
        onView(withId(R.id.user_expiredate_update_driver))
                .perform(typeText("fj"), closeSoftKeyboard());
        onView(withId(R.id.user_make_update_driver))
                .perform(typeText("fj@fj.com"), closeSoftKeyboard());
        onView(withId(R.id.user_year_update_driver))
                .perform(typeText("fj@fj.com"), closeSoftKeyboard());
        onView(withId(R.id.user_color_update_driver))
                .perform(typeText("fj@fj.com"), closeSoftKeyboard());


        //checktest
        onView(withId(R.id.user_driverlicense_update_driver)).check(matches(withText("fjfjfj")));
        onView(withId(R.id.user_expiredate_update_driver)).check(matches(withText("fj")));
        onView(withId(R.id.user_make_update_driver)).check(matches(withText("fj@fj.com")));
        onView(withId(R.id.user_year_update_driver)).check(matches(withText("fj@fj.com")));
        onView(withId(R.id.user_color_update_driver)).check(matches(withText("fj@fj.com")));


        //button reset
        onView(withId(R.id.driver_reset))
                .perform(click()).check(matches(isDisplayed()));
        //reset check
        onView(withId(R.id.user_driverlicense_update_driver)).check(matches(withText("")));
        onView(withId(R.id.user_expiredate_update_driver)).check(matches(withText("")));
        onView(withId(R.id.user_make_update_driver)).check(matches(withText("")));
        onView(withId(R.id.user_year_update_driver)).check(matches(withText("")));
        onView(withId(R.id.user_color_update_driver)).check(matches(withText("")));

        //TODO: add two button!!!
        onView(withId(R.id.backtobasicprofile)).perform(click());
        onView(withId(R.id.user_name_update_custom)).check(matches(isDisplayed()));
    }
}
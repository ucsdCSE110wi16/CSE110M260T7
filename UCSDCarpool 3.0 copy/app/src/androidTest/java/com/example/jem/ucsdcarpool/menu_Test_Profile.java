package com.example.jem.ucsdcarpool;

import android.support.test.rule.ActivityTestRule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Jem on 3/6/16.
 */
public class menu_Test_Profile {
    @ClassRule
    public static DisableAnimationsRule disableAnimation;

    @Rule
    public ActivityTestRule<Menu> registerrule = new ActivityTestRule<>(Menu.class);

    @Test
    public void RegisterAcitityTest() {
        onView(withId(R.id.home_profile)).perform(click());
        onView(withId(R.id.basic_profile_for_test)).check(matches(withText("Basic Profile")));

        onView(withId(R.id.back_basic)).perform(click());
        onView(withId(R.id.home_profile)).check(matches(isDisplayed()));

//        onView(withId(R.id.home_main)).perform(click());
//        onView(withId(R.id.next_trip_for_test)).check(matches(withText("YOUR NEXT TRIP")));
//
//        onView(withId(R.id.home_Find_Schedule_Driver)).perform(click());
//        onView(withId(R.id.DrivertextView)).check(matches(withText("View Existing Carpools")));
//
//        onView(withId(R.id.home_Find_Schedule)).perform(click());
//        onView(withId(R.id.text2)).check(matches(withText("Set Your Date")));
//
//        onView(withId(R.id.home_Log_Out)).perform(click());
//        onView(withId(R.id.text2)).check(matches(isDisplayed()));
    }
}

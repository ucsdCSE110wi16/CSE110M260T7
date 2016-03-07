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

public class reset_Button_Test {
    @ClassRule
    public static DisableAnimationsRule disableAnimation;

    @Rule
    public ActivityTestRule<Profile> registerrule = new ActivityTestRule<>(Profile.class);


    @Test
    public void profileTest() {
        //test
        onView(withId(R.id.user_name_update_custom))
                .perform(typeText("fjfjfj"), closeSoftKeyboard());
        onView(withId(R.id.user_ID_update_custom))
                .perform(typeText("fj"), closeSoftKeyboard());
        onView(withId(R.id.user_email_update_custom))
                .perform(typeText("fj@fj.com"), closeSoftKeyboard());
        onView(withId(R.id.user_gender_update_custom))
                .perform(typeText("fj@fj.com"), closeSoftKeyboard());
        onView(withId(R.id.user_ssn_update_custom))
                .perform(typeText("fj@fj.com"), closeSoftKeyboard());
        onView(withId(R.id.user_address_update_custom))
                .perform(typeText("fj@fj.com"), closeSoftKeyboard());
        onView(withId(R.id.user_phone_update_custom))
                .perform(typeText("fj@fj.com"), closeSoftKeyboard());
        onView(withId(R.id.user_zipcode_update_custom))
                .perform(typeText("fj@fj.com"), closeSoftKeyboard());
        //button reset
        onView(withId(R.id.Basic_reset))
                .perform(click()).check(matches(isDisplayed()));
        //reset check
        onView(withId(R.id.user_name_update_custom)).check(matches(withText("")));
        onView(withId(R.id.user_ID_update_custom)).check(matches(withText("")));
        onView(withId(R.id.user_email_update_custom)).check(matches(withText("")));
        onView(withId(R.id.user_gender_update_custom)).check(matches(withText("")));
        onView(withId(R.id.user_ssn_update_custom)).check(matches(withText("")));
        onView(withId(R.id.user_address_update_custom)).check(matches(withText("")));
        onView(withId(R.id.user_phone_update_custom)).check(matches(withText("")));
        onView(withId(R.id.user_zipcode_update_custom)).check(matches(withText("")));
    }
}
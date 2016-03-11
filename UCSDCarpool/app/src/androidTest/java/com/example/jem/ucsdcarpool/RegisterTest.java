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

public class RegisterTest {
    @ClassRule
    public static DisableAnimationsRule disableAnimation;

    @Rule
    public ActivityTestRule<Register> registerrule = new ActivityTestRule<>(Register.class);


    @Test
    public void RegisterAcitityTest() {
        onView(withId(R.id.user_name))
                .perform(typeText("fjfjfj"), closeSoftKeyboard());
        onView(withId(R.id.user_password))
                .perform(typeText("fj"), closeSoftKeyboard());
        onView(withId(R.id.user_email))
                .perform(typeText("fj@fj.com"), closeSoftKeyboard());

        onView(withId(R.id.user_name)).check(matches(withText("fjfjfj")));
        onView(withId(R.id.user_password)).check(matches(withText("fj")));
        onView(withId(R.id.user_email)).check(matches(withText("fj@fj.com")));

        onView(withId(R.id.reset))
                .perform(click()).check(matches(isDisplayed()));

        onView(withId(R.id.user_name)).check(matches(withText("")));
        onView(withId(R.id.user_password)).check(matches(withText("")));
        onView(withId(R.id.user_email)).check(matches(withText("")));

        onView(withId(R.id.register))
                .perform(click()).check(matches(isDisplayed()));

    }
}
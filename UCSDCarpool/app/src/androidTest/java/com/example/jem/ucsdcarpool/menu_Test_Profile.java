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

    }
}

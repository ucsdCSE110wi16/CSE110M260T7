package com.example.jem.ucsdcarpool;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;

/**
 * Created by yucheng on 3/6/16.
 */


@RunWith(AndroidJUnit4.class)

public class Log_out_Test {

    @ClassRule
    public static DisableAnimationsRule disableAnimation;

    @Rule
    public ActivityTestRule<Menu> registerrule = new ActivityTestRule<>(Menu.class);

    @Test
    public void LogoutTest() {
        onView(withId(R.id.home_Log_Out))
                .check(matches(isDisplayed()));

        onView(withId(R.id.home_Log_Out)).check(matches(isClickable()));

        onView(withId(R.id.home_Log_Out)).perform(click());

        onView(withId(R.id.login)).check(matches(isDisplayed()));

        onView(withId(R.id.login)).check(matches(isClickable()));

        onView(withId(R.id.quit)).check(matches(isDisplayed()));

        onView(withId(R.id.quit)).check(matches(isClickable()));

    }

}

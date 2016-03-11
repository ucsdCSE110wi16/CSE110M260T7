package com.example.jem.ucsdcarpool;

import android.support.test.rule.ActivityTestRule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by yucheng on 3/7/16.
 */
public class arrange_back_Test {

    @ClassRule
    public static DisableAnimationsRule disableAnimation;

    @Rule
    public ActivityTestRule<Find_schedule_customer> ArrangeBackRule = new ActivityTestRule<>(Find_schedule_customer.class);

    @Test
    public void LogoutTest() {
        onView(withId(R.id.btn_back))
                .check(matches(isDisplayed()));

        onView(withId(R.id.btn_back)).check(matches(isClickable()));

        onView(withId(R.id.btn_back)).perform(click());

        onView(withId(R.id.home_profile)).check(matches(isDisplayed()));

        onView(withId(R.id.home_main)).check(matches(isDisplayed()));

        onView(withId(R.id.home_Find_Schedule_Driver)).check(matches(isDisplayed()));

        onView(withId(R.id.home_Find_Schedule)).check(matches(isDisplayed()));

        onView(withId(R.id.home_Log_Out)).check(matches(isDisplayed()));

    }
}

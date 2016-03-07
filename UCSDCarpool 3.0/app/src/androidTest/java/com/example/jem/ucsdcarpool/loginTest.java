package com.example.jem.ucsdcarpool;

/**
 * Created by Yukana on 16/3/6.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

//@Rule
//public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

@RunWith(AndroidJUnit4.class)

public class loginTest{
    @ClassRule
    public static DisableAnimationsRule disableAnimation;

    @Rule
    public final ActivityTestRule<Log_in> log_in = new ActivityTestRule<>(Log_in.class);


    public void loginAcitityTest() {
        onView(withId(R.id.user_name))
                .perform(typeText("qq@qq.com"));
        onView(withId(R.id.user_password))
                .perform(typeText("qq"));
        onView(withId(R.id.login))
                .perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.register))
                .perform(click()).check(matches(isDisplayed()));
    }
}

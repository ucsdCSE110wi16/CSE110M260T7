package com.example.jem.ucsdcarpool;

/**
 * Created by Yukana on 16/3/6.
 */

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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;


@RunWith(AndroidJUnit4.class)

public class loginTest{
    @ClassRule
    public static DisableAnimationsRule disableAnimation;

    @Rule
    public final ActivityTestRule<Log_in> log_in = new ActivityTestRule<>(Log_in.class);

    @Test
    public void loginAcitityTest() {
        onView(withId(R.id.user_name))
                .perform(typeText("qq@qq.com"), closeSoftKeyboard());
        onView(withId(R.id.pwd))
                .perform(typeText("qq"), closeSoftKeyboard());

        onView(withId(R.id.user_name)).check(matches(withText("qq@qq.com")));
        onView(withId(R.id.pwd)).check(matches(withText("qq")));
        onView(withId(R.id.quit))
                .perform(click());


        onView(withId(R.id.starMarkInput)).check(matches(isDisplayed()));


    }
}
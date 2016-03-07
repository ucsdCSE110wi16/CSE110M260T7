package com.example.jem.ucsdcarpool;

/**
 * Created by Yukana on 16/3/6.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.ClassRule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;

//@Rule

@RunWith(AndroidJUnit4.class)

public class loginTest{
    @ClassRule
   // public static DisableAnimationsRule disableAnimation;
    public ActivityTestRule loginRules = new ActivityTestRule(Log_in.class);


    public void loginAcitityTest() {
    // Do your testing

        onView(withId(R.id.user_name))      // withId(R.id.my_view) is a ViewMatcher
                .perform(typeText("qq@qq.com"));


        onView(withId(R.id.pwd))      // withId(R.id.my_view) is a ViewMatcher
                .perform(typeText("qq"));

        onView(withId(R.id.login))
                .perform(click());
    }
}

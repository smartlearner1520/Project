package com.example.yanglei.myapplication;

/**
 * Created by Dell on 4/5/2018.
 */
import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest {
    /*
  For this test, a toast will only appear if there is an error
  Failure Case => Pass
  Success Case => Failed
  Note: Test_emptyPass test separately, as Test_success will move on to next activity and without a back button
  to continue the last test
   */
    @Rule
    public ActivityTestRule<LoginFirstPage> mActivityTestRule = new ActivityTestRule<>(LoginFirstPage.class);

    @Test //Success Case
    public void Test_success(){
        String email = "lauraong97@gmail.com";
        String password = "Sutd@1234";

        //find the email address edit text and type in the email address
        onView(withId(R.id.username)).perform(typeText(email), closeSoftKeyboard());

        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());

        //click the login button
        onView(withId(R.id.login)).perform(click());

        //Wait for 2s for the toast message to appear
        SystemClock.sleep(2000);
        //Check whether there the "Invalid email or password." message appears, if not the test is a fail
        LoginFirstPage activity = LoginFirstPage.getActivity();
        onView(withText(R.string.login_invalid)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
    @Test //Failure Case: Empty Email Field
    public void Test_emptyEmail(){
        String password = "Sutd@1234";

        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());

        //click the login button
        onView(withId(R.id.login)).perform(click());

        //Wait for 2s for the toast message to appear
        SystemClock.sleep(2000);
        //Check whether there the "Invalid email or password." message appears, if not the test is a fail
        LoginFirstPage activity = LoginFirstPage.getActivity();
        onView(withText(R.string.login_invalid)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
    @Test //Failure Case: Empty Password Field
    public void Test_emptyPass(){
        String email = "lauraong97@gmail.com";

        //find the email address edit text and type in the email address
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());

        //click the login button
        onView(withId(R.id.login)).perform(click());

        //Wait for 2s for the toast message to appear
        SystemClock.sleep(2000);
        //Check whether there the "Please enter your password." message appears, if not the test is a success
        LoginFirstPage activity = LoginFirstPage.getActivity();
        onView(withText(R.string.login_emptypass)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
    @Test //Failure Case: Invalid password
    public void Test_invalidPass(){
        String email = "lauraong97@gmail.com";
        String password = "sutd1234";//1 capital, one small length larger then 8

        //find the email address edit text and type in the email address
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());

        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());

        //click the login button
        onView(withId(R.id.login)).perform(click());

        //Wait for 2s for the toast message to appear
        SystemClock.sleep(2000);
        //Check whether there the "Invalid email or password." message appears, if not the test is a success
        LoginFirstPage activity = LoginFirstPage.getActivity();
        onView(withText(R.string.login_emptypass)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
    @Test //Failure Case: Invalid Email
    public void Test_invalidEmail(){
        String email = "@@@@@@@gmail.com";
        String password = "Sutd@1234";

        //find the email address edit text and type in the email address
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());

        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());

        //click the login button
        onView(withId(R.id.login)).perform(click());

        //Wait for 2s for the toast message to appear
        SystemClock.sleep(2000);
        //Check whether there the "Invalid email or password." message appears, if not the test is a success
        LoginFirstPage activity = LoginFirstPage.getActivity();
        onView(withText(R.string.login_emptypass)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
}

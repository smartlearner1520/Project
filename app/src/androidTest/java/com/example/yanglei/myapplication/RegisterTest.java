package com.example.yanglei.myapplication;

import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
/**
 * Created by Dell on 4/5/2018.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterTest {
    @Test //Success Case
    public void Test_success(){
        String name = "Jerry@gmail.com";
        String nric = "S8320356F";
        String cc = "65";
        String phone = "91234567";
        String password = "Sutd@1234";

        onView(withId(R.id.username)).perform(typeText(name), closeSoftKeyboard());

        //find the nric edit text and type in nric
        onView(withId(R.id.NRIC)).perform(typeText(nric), closeSoftKeyboard());


        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        //find the cc edit text and type in the password
        onView(withId(R.id.CC)).perform(typeText(cc), closeSoftKeyboard());
        //find the phone edit text and type in the password
        onView(withId(R.id.Phone)).perform(typeText(phone), closeSoftKeyboard());



        //click the Next button
        onView(withId(R.id.Next)).perform(click());

        //Wait for 2s for the toast message to appear
        SystemClock.sleep(2000);
        //Register second page should load if possible
        RegisterSecondPage activity = RegisterSecondPage.getActivity();
        onView(withText(R.string.success_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
    @Test //Wrong email
    public void Test_FailMail(){
        String name = "Jerry";
        String nric = "S8320356F";
        String cc = "65";
        String phone = "91234567";
        String password = "Sutd@1234";

        onView(withId(R.id.username)).perform(typeText(name), closeSoftKeyboard());

        //find the nric edit text and type in nric
        onView(withId(R.id.NRIC)).perform(typeText(nric), closeSoftKeyboard());


        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        //find the cc edit text and type in the password
        onView(withId(R.id.CC)).perform(typeText(cc), closeSoftKeyboard());
        //find the phone edit text and type in the password
        onView(withId(R.id.Phone)).perform(typeText(phone), closeSoftKeyboard());



        //click the Next button
        onView(withId(R.id.Next)).perform(click());

        //Wait for 2s for the toast message to appear
        SystemClock.sleep(2000);
        //Register second page should load if possible
        RegisterSecondPage activity = RegisterSecondPage.getActivity();
        onView(withText(R.string.success_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }@Test //Wrong password
    public void Test_FailMail(){
        String name = "Jerry@gmail.com";
        String nric = "S8320356F";
        String cc = "65";
        String phone = "91234567";
        String password = "sutd1234";

        onView(withId(R.id.username)).perform(typeText(name), closeSoftKeyboard());

        //find the nric edit text and type in nric
        onView(withId(R.id.NRIC)).perform(typeText(nric), closeSoftKeyboard());


        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        //find the cc edit text and type in the password
        onView(withId(R.id.CC)).perform(typeText(cc), closeSoftKeyboard());
        //find the phone edit text and type in the password
        onView(withId(R.id.Phone)).perform(typeText(phone), closeSoftKeyboard());
        //click the Next button
        onView(withId(R.id.Next)).perform(click());

        //Wait for 2s for the toast message to appear
        SystemClock.sleep(2000);
        //Register second page should load if possible
        RegisterSecondPage activity = RegisterSecondPage.getActivity();
        onView(withText(R.string.success_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }
    @Test //Failure Case: Duplicate NRIC in Database
    public void Test_checkduplicate(){
        String name = "Yanglei@gmail.com";
        String nric = "S9123456A";
        String password = "Sutd@1234";
        String dob = "23/05/1993";
        String cc = "65";
        String phone = "91234567";

        onView(withId(R.id.username)).perform(typeText(name), closeSoftKeyboard());

        //find the nric edit text and type in nric
        onView(withId(R.id.NRIC)).perform(typeText(nric), closeSoftKeyboard());

        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());

        //find the cc edit text and type in the password
        onView(withId(R.id.CC)).perform(typeText(cc), closeSoftKeyboard());
        //find the phone edit text and type in the password
        onView(withId(R.id.Phone)).perform(typeText(phone), closeSoftKeyboard());
        //click the Next button
        onView(withId(R.id.Next)).perform(click());

        //Wait for 2s for the toast message to appear
        SystemClock.sleep(2000);
        //Check whether there the "Creating Acccount.." message appears, if not the test is a fail
        RegisterSecondPage activity = RegisterSecondPage.getActivity();
        onView(withText(R.string.success_toast)).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

}

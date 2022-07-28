package com.example.cardiacjournal;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onView;

import static org.hamcrest.CoreMatchers.anything;

import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class splash_screenTest {
    @Rule
    public ActivityScenarioRule<splash_screen> activityRule =
            new ActivityScenarioRule<>(splash_screen.class);



    @Test
    public void testScreen() {
        onView(withId(R.id.splash)).check(matches(isDisplayed()));
        onView(withId(R.id.logotext)).check(matches(isDisplayed())); //Check the lgootext on the screen
        onView(withId(R.id.logo)).check(matches(isDisplayed())); //check the logo on the screen


    }


}

package com.example.cardiacjournal;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.anyOf;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.espresso.contrib.*;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    ConstraintLayout a;

    @Rule
    public ActivityScenarioRule<MainActivity> mactivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testScreen() {
        //onView(withId(R.id.main)).check(matches(isDisplayed()));
        onView(withId(R.id.add_record_btn)).perform(click());
        onView(withId(R.id.inputfragment)).check(matches(isDisplayed()));
        onView(withId(R.id.systolic)).perform(ViewActions.typeText("120"));
        onView(withId(R.id.diastolic)).perform(ViewActions.typeText("80"));
        onView(withId(R.id.bpm_in)).perform(ViewActions.typeText("85"));
        Espresso.pressBack();
        onView(withId(R.id.add_record_btn)).perform(click());
        //onView(withId(R.id.main)).check(matches(isDisplayed()));
        onView(withText("120")).check(matches(isDisplayed()));
        onView(withText("80")).check(matches(isDisplayed()));
        onView(withText("85")).check(matches(isDisplayed()));

    }




}
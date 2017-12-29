package com.example.akshayshah.architecture.crudActivity;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.akshayshah.architecture.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by akshay.shah on 26/12/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    //before runs before every test
    //here only features common to every @Test module should be initialized
    //not compulsory but a good practice to have them
    @Before
    public void startNewAcitivity() {
        //Example of @Before
        //even without the @Before method the test will run to completion
        Intent intent = new Intent(InstrumentationRegistry.getTargetContext(), MainActivity.class);
        mainActivityActivityTestRule.launchActivity(intent);
    }

    //after runs after every test
    //here features common to every @Test module should be entered
    //not compulsory but a good practice to have them
    @After
    public void finishActivity() {
        //Example of @After
        //even without the @After method the test will run to completion
        mainActivityActivityTestRule.finishActivity();
    }

    @Test
    public void button_getUserListButtonTest() {
        Espresso.onView(withId(R.id.buttonAddUser)).perform(click());
        Espresso.onView(withId(R.id.buttonGetUser)).perform(click());
        Espresso.onView(withId(R.id.listViewAllUsers)).check(matches(isDisplayed()));
    }

    @Test
    public void button_removeButtonTest() {
        Espresso.onView(withId(R.id.buttonAddUser)).perform(click());
        Espresso.onView(withId(R.id.buttonGetUser)).perform(click());
        Espresso.onView(withId(R.id.buttonRemoveUser)).perform(click());
        Espresso.onView(withId(R.id.listViewAllUsers)).check(matches(not(hasDescendant(withText("akshay")))));
    }
}

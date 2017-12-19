package com.example.akshayshah.sampleexample;

import android.widget.Button;

import com.example.akshayshah.sampleexample.crudActivity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import static org.junit.Assert.assertNotNull;

/**
 * Created by akshay.shah on 19/12/17.
 */

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void Test_buildActivity() {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void Test_verifyActivityNotNull() {
        assertNotNull(activity);
    }

    @Test
    public void Test_buttonClick() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        Button buttonAddUser = (Button) activity.findViewById(R.id.buttonRemoveUser);
        buttonAddUser.performClick();
        assertNotNull(buttonAddUser);
    }
}

package com.example.akshayshah.sampleexample;

import android.widget.Button;
import android.widget.Toast;

import com.example.akshayshah.sampleexample.crudActivity.MainActivity;

import org.apache.tools.ant.Main;
import org.hamcrest.CustomMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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
    }
}

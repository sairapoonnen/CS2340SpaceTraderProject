package cs2340.gatech.edu.cs2340spacetraderproject;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import cs2340.gatech.edu.cs2340spacetraderproject.views.TravelActivity;

/**
 * By Jooeun Kim (jkim3479)
 */

@RunWith(AndroidJUnit4.class)
public class TravelActivityTest {

    @Rule
    public ActivityTestRule<TravelActivity> mActivityRule =
            new ActivityTestRule<>(TravelActivity.class);

    @Before
    public void setUp() {

    }

}

package cs2340.gatech.edu.cs2340spacetraderproject;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Robots;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Water;
import cs2340.gatech.edu.cs2340spacetraderproject.views.EditItemActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static cs2340.gatech.edu.cs2340spacetraderproject.model.Market.Market;
import static cs2340.gatech.edu.cs2340spacetraderproject.model.Player.Player;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
    //O.Henry
    //Tests the OnConfirmPressed() method in EditItemActivity class
    public class EditItemActivityTest {


        /** this line is preferred way to hook up to activity */
        @Rule
        public ActivityTestRule<EditItemActivity> mActivityRule =
                new ActivityTestRule<>(EditItemActivity.class, true, false);

        @Before
        public void setUp() {


        }
        @Test
        public void buySuccessTest() {
            Intent intent = new Intent();
            intent.putExtra("BUY",new Water());
            Market().setSS(new SolarSystem("Bob", 1,1,1,1));
            mActivityRule.launchActivity(intent);
            int creds = Player().getCredits();
            onView(withId(R.id.confirm)).perform(click());
            assertTrue(Player().getCredits() < creds);
            mActivityRule.finishActivity();
        }
        @Test
        public void buyFailTest() {
            Intent intent = new Intent();
            intent.putExtra("BUY",new Water());
            Market().setSS(new SolarSystem("Bob", 1,1,1,1));

            mActivityRule.launchActivity(intent);
            Player().setCredits(0);
            onView(withId(R.id.confirm)).perform(click());
            assertTrue(Player().getCredits() == 0);
            mActivityRule.finishActivity();
        }

        @Test
        public void sellSuccessTest() {
            Intent intent = new Intent();
            intent.putExtra("SELL",new Water());
            Market().setSS(new SolarSystem("Bob", 1,1,1,1));
            Market().setMarket(new ArrayList());

            mActivityRule.launchActivity(intent);
            int creds = Player().getCredits();
            onView(withId(R.id.confirm)).perform(click());
            assertTrue(Player().getCredits() > creds);
        }

        @Test
        public void sellFailTest() {
            Intent intent = new Intent();
            intent.putExtra("SELL",new Robots());
            Market().setSS(new SolarSystem("Bob", 1,1,1,1));
            Market().setMarket(new ArrayList());

            mActivityRule.launchActivity(intent);
            int creds = Player().getCredits();
            onView(withId(R.id.confirm)).perform(click());
            assertTrue(Player().getCredits() == creds);
        }
    }

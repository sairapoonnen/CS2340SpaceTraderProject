package cs2340.gatech.edu.cs2340spacetraderproject;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static cs2340.gatech.edu.cs2340spacetraderproject.model.Player.Player;

import cs2340.gatech.edu.cs2340spacetraderproject.views.ConfigurationActivity;

/**
 * Created by Charlie Ye (cye45) on 4/8/19.
 */

@RunWith(AndroidJUnit4.class)
    public class ConfigurationActivityTest {
        /** this line is preferred way to hook up to activity */
        @Rule
        public ActivityTestRule<ConfigurationActivity> mActivityRule =
                new ActivityTestRule<>(ConfigurationActivity.class);

        @Before
        public void setUp() {
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("16")));
            onView(withId(R.id.pilotSkillPoint)).check(matches(withText("0")));
            onView(withId(R.id.engineerSkillPoint)).check(matches(withText("0")));
            onView(withId(R.id.traderSkillPoint)).check(matches(withText("0")));
            onView(withId(R.id.fighterSkillPoint)).check(matches(withText("0")));
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 0);
        }

        @Test
        public void addPilotSkillSuccess() {
            onView(withId(R.id.pilot_plus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("15")));
            onView(withId(R.id.pilotSkillPoint)).check(matches(withText("1")));
            Assert.assertEquals(Player().getPilotSkill(), 1);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 1);
        }

        @Test
        public void addEngineerSkillSuccess() {
            onView(withId(R.id.engineer_plus)).perform(click());
            onView(withId(R.id.engineer_plus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("14")));
            onView(withId(R.id.engineerSkillPoint)).check(matches(withText("2")));
            Assert.assertEquals(Player().getEngineerSkill(), 2);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 2);
        }

        @Test
        public void addFighterSkillSuccess() {
            for (int i = 0; i < 3; i++) {
                onView(withId(R.id.fighter_plus)).perform(click());
            }
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("13")));
            onView(withId(R.id.fighterSkillPoint)).check(matches(withText("3")));
            Assert.assertEquals(Player().getFighterSkill(), 3);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 3);
        }

        @Test
        public void addTraderSkillSuccess() {
            for (int i = 0; i < 4; i++) {
                onView(withId(R.id.trader_plus)).perform(click());
            }
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("12")));
            onView(withId(R.id.traderSkillPoint)).check(matches(withText("4")));
            Assert.assertEquals(Player().getTraderSkill(), 4);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 4);
        }

        @Test
        public void addTraderSkillFail() {
            for (int i = 0; i < 8; i++) {
                onView(withId(R.id.trader_plus)).perform(click());
                onView(withId(R.id.engineer_plus)).perform(click());
            }
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("0")));
            onView(withId(R.id.traderSkillPoint)).check(matches(withText("8")));
            Assert.assertEquals(Player().getTraderSkill(), 8);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 16);
            onView(withId(R.id.trader_plus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("0")));
            onView(withId(R.id.traderSkillPoint)).check(matches(withText("8")));
            Assert.assertEquals(Player().getTraderSkill(), 8);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 16);
        }

        @Test
        public void addPilotSkillFail() {
            for (int i = 0; i < 8; i++) {
                onView(withId(R.id.pilot_plus)).perform(click());
                onView(withId(R.id.trader_plus)).perform(click());
            }
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("0")));
            onView(withId(R.id.pilotSkillPoint)).check(matches(withText("8")));
            Assert.assertEquals(Player().getPilotSkill(), 8);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 16);
            onView(withId(R.id.pilot_plus)).perform(click());
            onView(withId(R.id.pilot_plus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("0")));
            onView(withId(R.id.pilotSkillPoint)).check(matches(withText("8")));
            Assert.assertEquals(Player().getPilotSkill(), 8);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 16);
        }

        @Test
        public void addEngineerSkillFail() {
            for (int i = 0; i < 8; i++) {
                onView(withId(R.id.engineer_plus)).perform(click());
                onView(withId(R.id.trader_plus)).perform(click());
            }
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("0")));
            onView(withId(R.id.engineerSkillPoint)).check(matches(withText("8")));
            Assert.assertEquals(Player().getEngineerSkill(), 8);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 16);
            onView(withId(R.id.engineer_plus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("0")));
            onView(withId(R.id.engineerSkillPoint)).check(matches(withText("8")));
            Assert.assertEquals(Player().getEngineerSkill(), 8);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 16);
        }

        @Test
        public void addFighterSkillFail() {
            for (int i = 0; i < 8; i++) {
                onView(withId(R.id.engineer_plus)).perform(click());
                onView(withId(R.id.fighter_plus)).perform(click());
            }
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("0")));
            onView(withId(R.id.fighterSkillPoint)).check(matches(withText("8")));
            Assert.assertEquals(Player().getFighterSkill(), 8);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 16);
            onView(withId(R.id.fighter_plus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("0")));
            onView(withId(R.id.fighterSkillPoint)).check(matches(withText("8")));
            Assert.assertEquals(Player().getFighterSkill(), 8);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 16);
        }

        @Test
        public void removePilotSkillSuccess() {
            onView(withId(R.id.pilot_plus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("15")));
            onView(withId(R.id.pilotSkillPoint)).check(matches(withText("1")));
            Assert.assertEquals(Player().getPilotSkill(), 1);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 1);
            onView(withId(R.id.pilot_minus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("16")));
            onView(withId(R.id.pilotSkillPoint)).check(matches(withText("0")));
            Assert.assertEquals(Player().getPilotSkill(), 0);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 0);
        }

        @Test
        public void removeEngineerSkillSuccess() {
            onView(withId(R.id.engineer_plus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("15")));
            onView(withId(R.id.engineerSkillPoint)).check(matches(withText("1")));
            Assert.assertEquals(Player().getEngineerSkill(), 1);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 1);
            onView(withId(R.id.engineer_minus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("16")));
            onView(withId(R.id.engineerSkillPoint)).check(matches(withText("0")));
            Assert.assertEquals(Player().getEngineerSkill(), 0);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 0);
        }

        @Test
        public void removeFighterSkillSuccess() {
            onView(withId(R.id.trader_plus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("15")));
            onView(withId(R.id.traderSkillPoint)).check(matches(withText("1")));
            Assert.assertEquals(Player().getTraderSkill(), 1);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 1);
            onView(withId(R.id.trader_minus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("16")));
            onView(withId(R.id.traderSkillPoint)).check(matches(withText("0")));
            Assert.assertEquals(Player().getTraderSkill(), 0);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 0);
        }

        @Test
        public void removeTraderSkillSuccess() {
            onView(withId(R.id.fighter_plus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("15")));
            onView(withId(R.id.fighterSkillPoint)).check(matches(withText("1")));
            Assert.assertEquals(Player().getFighterSkill(), 1);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 1);
            onView(withId(R.id.fighter_minus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("16")));
            onView(withId(R.id.fighterSkillPoint)).check(matches(withText("0")));
            Assert.assertEquals(Player().getFighterSkill(), 0);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 0);
        }

        @Test
        public void removePilotSkillFail() {
            onView(withId(R.id.pilot_minus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("16")));
            onView(withId(R.id.pilotSkillPoint)).check(matches(withText("0")));
            Assert.assertEquals(Player().getPilotSkill(), 0);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 0);
        }

        @Test
        public void removeFighterSkillFail() {
            onView(withId(R.id.trader_minus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("16")));
            onView(withId(R.id.traderSkillPoint)).check(matches(withText("0")));
            Assert.assertEquals(Player().getTraderSkill(), 0);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 0);
        }

        @Test
        public void removeTraderSkillFail() {
            onView(withId(R.id.fighter_minus)).perform(click());
            onView(withId(R.id.totalSkillPoint)).check(matches(withText("16")));
            onView(withId(R.id.fighterSkillPoint)).check(matches(withText("0")));
            Assert.assertEquals(Player().getFighterSkill(), 0);
            Assert.assertEquals(Player().getPilotSkill() + Player().getFighterSkill()
                    + Player().getEngineerSkill() + Player().getTraderSkill(), 0);
        }

        @After
        public void tearDown() {
            Player().setPilotSkill(0);
            Player().setEngineerSkill(0);
            Player().setFighterSkill(0);
            Player().setTraderSkill(0);
        }
}


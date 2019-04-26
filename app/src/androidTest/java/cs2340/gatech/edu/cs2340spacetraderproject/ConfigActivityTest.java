package cs2340.gatech.edu.cs2340spacetraderproject;

//Created By Jooeun Kim (jkim3479)

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import cs2340.gatech.edu.cs2340spacetraderproject.model.Gnat;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.views.ConfigurationActivity;

public class ConfigActivityTest {
    /** this line is preferred way to hook up to activity */
    @Rule
    public ActivityTestRule<ConfigurationActivity> mActivityRule =
            new ActivityTestRule<>(ConfigurationActivity.class);

    @Before
    public void setUp() {

        Player player = Player.Player();

        //check for initial engineer skill point
        onView(withId(R.id.engineerSkillPoint)).check(matches(withText("0")));
        Assert.assertEquals("Initial engineer skill point should equal to 0.",
                0, player.getEngineerSkill());

        //check for initial fighter skill point
        onView(withId(R.id.fighterSkillPoint)).check(matches(withText("0")));
        Assert.assertEquals("Initial fighter skill point should equal to 0.", 0,
                player.getFighterSkill());

        //check for initial pilot skill point
        onView(withId(R.id.pilotSkillPoint)).check(matches(withText("0")));
        Assert.assertEquals("Initial pilot skill point should equal to 0.", 0,
                player.getPilotSkill());

        //check for initial trader skill point
        onView(withId(R.id.traderSkillPoint)).check(matches(withText("0")));
        Assert.assertEquals("Initial trader skill point should equal to 0.", 0,
                player.getTraderSkill());

        //check for initial undistributed skill points
        onView(withId(R.id.totalSkillPoint)).check(matches(withText("16")));
        Assert.assertEquals("Added up skill points should equal to 0", 0,
                player.getTotalSkill());
    }

    /**
     * player name checks
     */
    @Test
    public void nameCheckSuccess() {

        Player player = Player.Player();
        player.setName("Kim");

        onView(withId(R.id.player_name)).check(matches(withText("Kim")));
        Assert.assertEquals("Kim", player.getName());
    }

    @Test
    public void nameCheckFail() {

        Player player = Player.Player();
        player.setName("Waters");

        onView(withId(R.id.player_name)).check(matches(withText("Kim")));
        Assert.assertEquals("Kim", player.getName());
    }

    /**
     * game difficulty checks
     */
    @Test
    public void difficultyCheckSuccess() {

        Player player = Player.Player();
        player.setGameDifficulty("HARD");

        onView(withId(R.id.game_difficulty)).check(matches(withText("HARD")));
        Assert.assertEquals("HARD", player.getGameDifficulty());
    }

    @Test
    public void difficultyCheckFail() {

        Player player = Player.Player();
        player.setGameDifficulty("BEGINNER");

        onView(withId(R.id.game_difficulty)).check(matches(withText("HARD")));
        Assert.assertEquals("HARD", player.getGameDifficulty());
    }

    /**
     * ship type checks
     */
    @Test
    public void shipCheckSuccess() {

        Player player = Player.Player();
        player.setSpaceship(new Gnat());

        //no textView display shown in activity
        Assert.assertEquals("Gnat", player.getSpaceship().getName());
    }

    @Test
    public void shipCheckFail() {

        Player player = Player.Player();
        player.setSpaceship(new Gnat());

        //no textView display shown in activity
        Assert.assertEquals("Qnat", player.getSpaceship().getName());
    }

    /**
     * engineer skill point checks
     */
    @Test
    public void engineerSkillPlusSuccess() {

        Player player = Player.Player();

        onView(withId(R.id.engineer_plus)).perform(click());
        onView(withId(R.id.engineer_plus)).perform(click());
        onView(withId(R.id.engineer_plus)).perform(click());

        //Engineer skill point check
        onView(withId(R.id.engineerSkillPoint)).check(matches(withText("3")));
        Assert.assertEquals(3, player.getEngineerSkill());

        //total remaining skill point check
        onView(withId(R.id.totalSkillPoint)).check(matches(withText("12")));
        Assert.assertEquals(12, player.getTotalSkill());
    }

    @Test
    public void engineerSkillPlusFail() {

        Player player = Player.Player();

        /**
         * makes the point == 2
         */
        onView(withId(R.id.engineer_plus)).perform(click());
        onView(withId(R.id.engineer_plus)).perform(click());

        //Engineer skill point check
        onView(withId(R.id.engineerSkillPoint)).check(matches(withText("3")));
        Assert.assertEquals(3, player.getEngineerSkill());

        //total remaining skill point check
        onView(withId(R.id.totalSkillPoint)).check(matches(withText("12")));
        Assert.assertEquals(12, player.getTotalSkill());
    }

    @Test
    public void engineerSkillMinusSuccess() {

        Player player = Player.Player();


    }

    @Test
    public void engineerSkillMinusFail() {

        Player player = Player.Player();


    }

    /**
     * fighter skill point checks
     */
    @Test
    public void fighterSkillPlusSuccess() {

        Player player = Player.Player();


    }

    @Test
    public void fighterSkillPlusFail() {
        Player player = Player.Player();


    }

    @Test
    public void fighterSkillMinusSuccess() {

        Player player = Player.Player();


    }

    @Test
    public void fighterSkillMinusFail() {

        Player player = Player.Player();


    }

    /**
     * pilot skill point checks
     */
    @Test
    public void pilotSkillPlusSuccess() {

        Player player = Player.Player();


    }

    @Test
    public void pilotSkillPlusFail() {
        Player player = Player.Player();


    }

    @Test
    public void pilotSkillMinusSuccess() {

        Player player = Player.Player();


    }

    @Test
    public void pilotSkillMinusFail() {

        Player player = Player.Player();


    }

    /**
     * trader skill point checks
     */
    @Test
    public void traderSkillPlusSuccess() {

        Player player = Player.Player();


    }

    @Test
    public void traderSkillPlusFail() {
        Player player = Player.Player();


    }

    @Test
    public void traderSkillMinusSuccess() {

        Player player = Player.Player();


    }

    @Test
    public void traderSkillMinusFail() {

        Player player = Player.Player();


    }

    /**
     * after test conditions
     */
    @After
    public void tearDown() {

        Player player = Player.Player();

        player.setName(null);
        player.setGameDifficulty(null);
        player.setSpaceship(null);
        player.setEngineerSkill(0);
        player.setFighterSkill(0);
        player.setPilotSkill(0);
        player.setTraderSkill(0);
    }
}

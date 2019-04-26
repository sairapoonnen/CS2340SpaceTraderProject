package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Gnat;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Pirate;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;

public class PirateEncounterActivity extends AppCompatActivity {
    Random rand = new Random();
    Player player = Player.Player();
    Pirate randPirate = new Pirate(rand.nextInt(5), rand.nextInt(5), rand.nextInt(5), rand.nextInt(5), rand.nextInt(401) + 100, new Gnat());
    private SolarSystem planet;

    MediaPlayer fight;
    MediaPlayer flee;
    MediaPlayer grand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_pirate);

        planet = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        //button sound effect setup
        fight = MediaPlayer.create(this, R.raw.fight);
        flee = MediaPlayer.create(this, R.raw.flee);
        grand = MediaPlayer.create(this, R.raw.grand);

        grand.start();
    }

    public void onFightPressed(View view) {

        fight.start();

        Random rand = new Random();
        int prob = rand.nextInt(10 + (player.getFighterSkill() - randPirate.getFighterSkill()));
        if (prob < 5) {
            Intent intent = new Intent(PirateEncounterActivity.this, PirateFightFailActivity.class);
            intent.putExtra("PLANET", planet);
            startActivity(intent);
        } else {
            Intent intent = new Intent(PirateEncounterActivity.this, PirateFightSuccessActivity.class);
            intent.putExtra("PLANET", planet);
            startActivity(intent);
        }


    }

    public void onFleePressed(View view) {

        flee.start();

        Random rand = new Random();
        int prob = rand.nextInt(10 + (player.getPilotSkill() - randPirate.getPilotSkill()));
        if (prob < 5) {
            Intent intent = new Intent(PirateEncounterActivity.this, PirateFleeFailActivity.class);
            intent.putExtra("PLANET", planet);
            startActivity(intent);
        } else {
            Intent intent = new Intent(PirateEncounterActivity.this, PirateFleeSuccessActivity.class);
            intent.putExtra("PLANET", planet);
            startActivity(intent);
        }

    }


}

package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;

import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;

public class AsteroidEncounterActivity extends AppCompatActivity {
    Player player = Player.Player();
    private SolarSystem planet;

    MediaPlayer buttonSound;
    MediaPlayer warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_asteroid);

        planet = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        //button sound effect setup
        buttonSound = MediaPlayer.create(this, R.raw.button);
        warning = MediaPlayer.create(this, R.raw.warning);

        warning.start();
    }

    public void onAvoidPressed(View view) {

        buttonSound.start();

        Random rand = new Random();
        int prob = rand.nextInt(10 * player.getPilotSkill());
        if (prob < 5) {
            Log.d("Outcome", "Fail, prob: " + prob);
            Intent intent = new Intent(AsteroidEncounterActivity.this, AsteroidFail.class);
            intent.putExtra("PLANET", planet);
            startActivity(intent);
        } else {
            Log.d("Outcome", "Success, prob: " + prob);
            Intent intent = new Intent(AsteroidEncounterActivity.this, AsteroidSuccess.class);
            intent.putExtra("PLANET", planet);
            startActivity(intent);
        }


    }

}

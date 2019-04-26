package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;

public class PirateFleeSuccessActivity extends AppCompatActivity {
    private Player player = Player.Player();
    private SolarSystem planet;

    MediaPlayer buttonSound;
    MediaPlayer success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_pirate_flee);

        planet = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        //button sound effect setup
        buttonSound = MediaPlayer.create(this, R.raw.button);
        success = MediaPlayer.create(this, R.raw.success);

        success.start();
    }

    public void onBackPress(View view) {

        buttonSound.start();

        Intent intent = new Intent(PirateFleeSuccessActivity.this, PlanetActivity.class);
        intent.putExtra("PLANET", planet);
        startActivity(intent);
    }

}

package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;

public class AsteroidFail extends AppCompatActivity {
    private Player player = Player.Player();
    private SolarSystem planet;

    MediaPlayer buttonSound;
    MediaPlayer fail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fail_asteroid);

        planet = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        Log.d("Before", "Rep: " + player.getReputation());
        player.setReputation(-1);
        Log.d("After", "Rep: " + player.getReputation());

        //button sound effect setup
        buttonSound = MediaPlayer.create(this, R.raw.button);
        fail = MediaPlayer.create(this, R.raw.asteroidfail);

        fail.start();

    }

    public void onBackPress(View view) {

        buttonSound.start();

        Intent intent = new Intent(AsteroidFail.this, PlanetActivity.class);
        intent.putExtra("PLANET", planet);
        startActivity(intent);
    }

}

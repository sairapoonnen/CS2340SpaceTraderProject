package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;

public class ChestFailureActivity extends AppCompatActivity {
    private Player player = Player.Player();
    private SolarSystem ss;

    MediaPlayer buttonSound;
    MediaPlayer fail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fail_chest);
        ss = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        //button sound effect setup
        buttonSound = MediaPlayer.create(this, R.raw.button);
        fail = MediaPlayer.create(this, R.raw.fail);

        fail.start();

    }

    public void onBackPressed(View view) {

        buttonSound.start();

        player.setCredits(player.getCredits() - 100);
        Intent intent = new Intent(ChestFailureActivity.this, MarketActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);

        buttonSound.release();
    }

}

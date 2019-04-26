package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class PirateFleeFailActivity extends AppCompatActivity {
    Player player = Player.Player();
    Spaceship spaceship = player.getSpaceship();
    private SolarSystem planet;

    MediaPlayer buttonSound;
    MediaPlayer fail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fail_pirate_flee);

        planet = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        //button sound effect setup
        buttonSound = MediaPlayer.create(this, R.raw.button);
        fail = MediaPlayer.create(this, R.raw.fail);

        fail.start();
    }

    public void onResume() {
        super.onResume();

        int numItems = spaceship.cargo.size();
        Random rand = new Random();
        if (numItems != 0) {
            int index = rand.nextInt(numItems);
            spaceship.removeCargo(index);
        }
//        List<TradeGood> items = market.getMarket();
//        items.remove(item);
//        market.setMarket(items);

    }

    public void onBackPress(View view) {

        buttonSound.start();

        Intent intent = new Intent(PirateFleeFailActivity.this, PlanetActivity.class);
        intent.putExtra("PLANET", planet);
        startActivity(intent);
    }
}

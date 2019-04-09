package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class PirateFightSuccessActivity extends AppCompatActivity {
    private Player player = Player.Player();
    private Spaceship spaceship = player.getSpaceship();
    private Market market = Market.Market();

    private SolarSystem planet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_pirate_fight);

        planet = (SolarSystem) getIntent().getSerializableExtra("PLANET");

    }

    public void onResume() {
        super.onResume();

        int numItems = market.getMarket().size();
        Random rand = new Random();
        int index = rand.nextInt(numItems);
        TradeGood item = market.getMarket().get(index);
        TextView textView = (TextView)findViewById(R.id.item);
        textView.setText(item.getName());

        if (spaceship.getCargo().size() == spaceship.getCargoBays()) {
            Toast.makeText(getApplicationContext(), "Cargo full! Can't accept new item!", Toast.LENGTH_SHORT).show();
        } else {
            spaceship.addCargo(item);
        }


    }

    public void onBackPress(View view) {
        Intent intent = new Intent(PirateFightSuccessActivity.this, PlanetActivity.class);
        intent.putExtra("PLANET", planet);
        startActivity(intent);
    }

}

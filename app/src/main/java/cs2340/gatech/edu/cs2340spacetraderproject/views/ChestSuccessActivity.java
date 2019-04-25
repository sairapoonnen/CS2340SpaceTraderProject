package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class ChestSuccessActivity extends AppCompatActivity {
    private Player player = Player.Player();
    private Spaceship spaceship = player.getSpaceship();
    private Market market = Market.Market();
    private SolarSystem ss;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_chest);
        ss = (SolarSystem) getIntent().getSerializableExtra("PLANET");

    }

    public void onResume() {
        super.onResume();


        int numItems = market.getMarket().size();
        Random rand = new Random();
        int index = rand.nextInt(numItems);
        TradeGood item = market.getMarket().get(index);

        spaceship.addCargo(item);

        TextView textView = findViewById(R.id.item);
        textView.setText(item.getName());
    }

    public void onBackPress(View view) {
        Intent intent = new Intent(ChestSuccessActivity.this, MarketActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);
    }

}

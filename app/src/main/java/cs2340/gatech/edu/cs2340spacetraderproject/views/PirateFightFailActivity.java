package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class PirateFightFailActivity extends AppCompatActivity {
    Player player = Player.Player();
    Spaceship spaceship = player.getSpaceship();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fail_pirate_fight);
    }

    public void onResume() {
        super.onResume();

        int numItems = spaceship.cargo.size();
        Random rand = new Random();
        int index = rand.nextInt(numItems);
        spaceship.removeCargo(index);
//        List<TradeGood> items = market.getMarket();
//        items.remove(item);
//        market.setMarket(items);

    }

    public void onBackPress(View view) {
        Intent intent = new Intent(PirateFightFailActivity.this, MarketBuyActivity.class);
        startActivity(intent);
    }
}

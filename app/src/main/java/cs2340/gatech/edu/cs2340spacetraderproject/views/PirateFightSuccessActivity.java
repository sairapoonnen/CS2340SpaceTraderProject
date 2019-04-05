package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class PirateFightSuccessActivity extends AppCompatActivity {
    Player player = Player.Player();
    Spaceship spaceship = player.getSpaceship();
    Market market = Market.Market();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_pirate_fight);

    }

    public void onResume() {
        super.onResume();


        int numItems = market.getMarket().size();
        Random rand = new Random();
        int index = rand.nextInt(numItems);
        TradeGood item = market.getMarket().get(index);
//        List<TradeGood> items = market.getMarket();
//        items.remove(item);
//        market.setMarket(items);

        spaceship.addCargo(item);

    }

    public void onBackPress(View view) {
        Intent intent = new Intent(PirateFightSuccessActivity.this, MarketBuyActivity.class);
        startActivity(intent);
    }

}

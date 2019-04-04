package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Firearms;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Food;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Furs;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Games;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Machines;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Medicine;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Narcotics;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Ore;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Robots;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Water;
import cs2340.gatech.edu.cs2340spacetraderproject.viewmodels.ConfigurationViewModel;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.GameDifficulty;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;

import java.util.*;

public class PlanetActivity extends AppCompatActivity {

    private Universe universe = Universe.Universe();
    private Market market = Market.Market();
    private SolarSystem ss;


    /*
    private Water water = new Water();
    private Furs furs = new Furs();
    private Food food = new Food();
    private Ore ore = new Ore();
    private Games games =  new Games();
    private Firearms firearms = new Firearms();
    private Medicine medicine = new Medicine();
    private Machines machines = new Machines();
    private Narcotics narcotics = new Narcotics();
    private Robots robots = new Robots();
    */

    //widgets
    private TextView name;
    private Button toMap;
    private Button toMarket;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        ss = (SolarSystem) getIntent().getSerializableExtra("PLANET");
        //Log.d("itemHashAfter", item.toString());

        //Log.d("Test", "planet: " + ss.getName());

        //ss.addMarket(new Water());
        //ss.addMarket(new Water());
        //ss.addMarket(new Water());
        ss.addMarket(new Water());
        ss.addMarket(new Furs());
        //ss.addMarket(new Furs());
        //ss.addMarket(new Furs());
        //ss.addMarket(new Furs());

        if (ss.getTech() == 1) {
            ss.addMarket(new Food());
        } else if (ss.getTech() == 2) {
            ss.addMarket(new Ore());
        } else if (ss.getTech() == 3) {
            ss.addMarket(new Games());
            ss.addMarket(new Firearms());
        } else if (ss.getTech() == 4) {
            ss.addMarket(new Medicine());
            ss.addMarket(new Machines());
        } else if (ss.getTech() == 5) {
            ss.addMarket(new Narcotics());
        } else if (ss.getTech() >= 6){
            ss.addMarket(new Robots());
        }

        Log.d("test", ss.getMarket().get(0).getName() + ", tech level: " + ss.getTech());
        market.setMarket(ss.getMarket());
        market.setSS(ss); //for events

        name = findViewById(R.id.planet_name);
        name.setText(ss.getName());
        //add textView for planet tech level and resource type?


    }

    //button handler to go back to Map
    public void onMapPressed(View view) {
        Intent intent = new Intent(PlanetActivity.this, TravelActivity.class);
        startActivity(intent);
    }

    //button handler to go to Market
    public void onMarketPressed(View view) {

        Random rand = new Random();
        int prob = rand.nextInt(100);

        if (prob < 60) {
            Intent intent = new Intent(PlanetActivity.this, RandomChestActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(PlanetActivity.this, MarketBuyActivity.class);
            startActivity(intent);
        }



    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("Resume?", "onResume");
    }
}

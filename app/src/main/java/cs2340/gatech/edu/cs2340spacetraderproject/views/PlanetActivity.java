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

    //widgets
    private TextView name;
    private Button toMap;
    private Button toMarket;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);



        //selecting random planet to visit, will replace once Travel is implemented
        Random rand = new Random();

        SolarSystem ss = universe.getSolarSystem().get(rand.nextInt(10));
        market.setMarket(ss.getMarket());
        market.setSS(ss); //for events

        name = findViewById(R.id.planet_name);
        name.setText(ss.getName());
        //add textView for planet tech level and resource type?

        //test
        Log.d("Test", "planet: " + ss.getName());

    }

    //button handler to go back to Map
    public void onMapPressed(View view) {
        Intent intent = new Intent(PlanetActivity.this, UniverseConfigurationActivity.class);
        startActivity(intent);
    }

    //button handler to go to Market
    public void onMarketPressed(View view) {
        Intent intent = new Intent(PlanetActivity.this, MarketBuyActivity.class);
        startActivity(intent);
    }
}

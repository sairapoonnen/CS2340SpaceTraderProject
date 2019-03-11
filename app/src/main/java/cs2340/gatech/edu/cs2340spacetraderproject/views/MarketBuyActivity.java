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

public class MarketBuyActivity extends AppCompatActivity {

    private Market market = Market.Market();

    //widgets
    private TextView credits;
    private TextView cargo;
    private TextView cost;
    private Button leave;
    private Button sell;
    private Button Confirm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketbuy);

    }

    public void onLeavePressed(View view) {
        Intent intent = new Intent(MarketBuyActivity.this, PlanetActivity.class);
        startActivity(intent);
    }

    public void onSellPressed(View view) {
        Intent intent = new Intent(MarketBuyActivity.this, MarketSellActivity.class);
        startActivity(intent);
    }
}

package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.*;

public class UniverseConfigurationActivity extends AppCompatActivity {

    //have map here in the future?
    //or just have list of planets

    private Universe universe = Universe.Universe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_configuration);

        /*
        for (SolarSystem ss: universe.getSolarSystem()) {

            ss.addMarket(new Water(), 50);
            ss.addMarket(new Furs(), 50);

            if (ss.getTech() == 2 && ss.getResource() != 3) {
                ss.addMarket(new Water(), 100);
            }
        }
        */



    }

    // button handler to go to planet screen
    public void onNextPressed(View view) {
        Intent intent = new Intent(UniverseConfigurationActivity.this, PlanetActivity.class);
        startActivity(intent);
    }

}

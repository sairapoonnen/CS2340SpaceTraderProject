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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_configuration);

        int[] info;
        // info = [quantity, totalPrice (w/o var), basePrice, var, MTLU]
        for (SolarSystem ss: universe.getSolarSystem()) {

            boolean waterChanged =  false;
            boolean furChanged = false;

            if (ss.getResource() == 4) { //resource = LOTSOFWATER
                waterChanged = true;
                info = new int[]{1000, (water.getBasePrice() + (water.getIPL() * (ss.getTech() - water.getMTLP()))), water.getBasePrice(), water.getVar(), water.getMTLU() };
                ss.addMarket("water", info);
            } else if (ss.getTech() == 2 && ss.getResource() != 3) { //tech level 2 and not a desert
                waterChanged = true;
                info = new int[]{500, (water.getBasePrice() + (water.getIPL() * (ss.getTech() - water.getMTLP()))), water.getBasePrice(), water.getVar(), water.getMTLU() };
                ss.addMarket("water", info);
            }

            if (ss.getResource() == 7) { //resource = richfauna
                furChanged = true;
                info = new int[]{1000, (furs.getBasePrice() + (furs.getIPL() * (ss.getTech() - furs.getMTLP()))), furs.getBasePrice(), furs.getVar(), furs.getMTLU()};
                ss.addMarket("fur", info);
            } else if (ss.getTech() == 0 && ss.getResource() != 8) { //tech level 0 and not lifeless
                furChanged = true;
                info = new int[]{500, (furs.getBasePrice() + (furs.getIPL() * (ss.getTech() - furs.getMTLP()))), furs.getBasePrice(), furs.getVar(), furs.getMTLU()};
                ss.addMarket("fur", info);
            }

            if (!waterChanged) {
                info = new int[]{200, (water.getBasePrice() + (water.getIPL() * (ss.getTech() - water.getMTLP()))), water.getBasePrice(), water.getVar(), water.getMTLU() };
                ss.addMarket("water", info);
            }

            if (!furChanged) {
                info = new int[]{200, (furs.getBasePrice() + (furs.getIPL() * (ss.getTech() - furs.getMTLP()))), furs.getBasePrice(), furs.getVar(), furs.getMTLU()};
                ss.addMarket("fur", info);
            }

            if (ss.getResource() == 5 && ss.getTech() >= 1) { //resource = richsoil
                info =  new int[]{1000, (food.getBasePrice() + (food.getIPL() * (ss.getTech() - food.getMTLP()))), food.getBasePrice(), food.getVar(), food.getMTLU()};
                ss.addMarket("food", info);
            } else if (ss.getTech() == 1 && ss.getResource() != 5) { //tech level 1
                info =  new int[]{500, (food.getBasePrice() + (food.getIPL() * (ss.getTech() - food.getMTLP()))), food.getBasePrice(), food.getVar(), food.getMTLU()};
                ss.addMarket("food", info);
            }

            if (ss.getTech() >= 2 && ss.getResource() == 1) { //resource = mineralrich
                info = new int[]{1000, (ore.getBasePrice() + (ore.getIPL() * (ss.getTech() - ore.getMTLP()))), ore.getBasePrice(), ore.getVar(), ore.getMTLU()};
                ss.addMarket("ore", info);
            } else if (ss.getTech() == 3) {
                info = new int[]{500, (ore.getBasePrice() + (ore.getIPL() * (ss.getTech() - ore.getMTLP()))), ore.getBasePrice(), ore.getVar(), ore.getMTLU()};
                ss.addMarket("ore", info);
            }

            if (ss.getTech() >= 3 && ss.getResource() == 11) { //resource = artistic
                info = new int[]{1000, (games.getBasePrice() + (games.getIPL() * (ss.getTech() - games.getMTLP()))), games.getBasePrice(), games.getVar(), games.getMTLU()};
                ss.addMarket("ore", info);
            } else if (ss.getTech() == 6) {
                info = new int[]{500, (games.getBasePrice() + (games.getIPL() * (ss.getTech() - games.getMTLP()))), games.getBasePrice(), games.getVar(), games.getMTLU()};
                ss.addMarket("ore", info);
            }

            if (ss.getResource() == 12 && ss.getTech() >= 3) { //resource = warlike
                info = new int[]{1000, (firearms.getBasePrice() + (firearms.getIPL() * (ss.getTech() - firearms.getMTLP()))), firearms.getBasePrice(), firearms.getVar(), firearms.getMTLU()};
                ss.addMarket("firearms", info);
            } else if (ss.getTech() == 5) {
                info = new int[]{500, (firearms.getBasePrice() + (firearms.getIPL() * (ss.getTech() - firearms.getMTLP()))), firearms.getBasePrice(), firearms.getVar(), firearms.getMTLU()};
                ss.addMarket("firearms", info);
            }

            if ((ss.getResource() == 9 || ss.getResource() == 10) && ss.getTech() >= 4) { //resource = lots of herbs or weirdmushrooms
                info = new int[]{1000, (medicine.getBasePrice() + (medicine.getIPL() * (ss.getTech() - medicine.getMTLP()))), medicine.getBasePrice(), medicine.getVar(), medicine.getMTLU()};
                ss.addMarket("medicine", info);
            } else if (ss.getTech() == 6) {
                info = new int[]{500, (medicine.getBasePrice() + (medicine.getIPL() * (ss.getTech() - medicine.getMTLP()))), medicine.getBasePrice(), medicine.getVar(), medicine.getMTLU()};
                ss.addMarket("medicine", info);
            }

            if (ss.getTech() == 5) {
                info = new int[]{1000, (machines.getBasePrice() + (machines.getIPL() * (ss.getTech() - machines.getMTLP()))), machines.getBasePrice(), machines.getVar(), machines.getMTLU()};
                ss.addMarket("machines", info);
            } else if (ss.getTech() >= 4) {
                info = new int[]{500, (machines.getBasePrice() + (machines.getIPL() * (ss.getTech() - machines.getMTLP()))), machines.getBasePrice(), machines.getVar(), machines.getMTLU()};
                ss.addMarket("machines", info);
            }

            if (ss.getTech() == 5) {
                info = new int[]{1000, (narcotics.getBasePrice() + (narcotics.getIPL() * (ss.getTech() - narcotics.getMTLP()))), narcotics.getBasePrice(), narcotics.getVar(), narcotics.getMTLU()};
                ss.addMarket("narcotics", info);
            } else if (ss.getTech() >= 5) {
                info = new int[]{500, (narcotics.getBasePrice() + (narcotics.getIPL() * (ss.getTech() - narcotics.getMTLP()))), narcotics.getBasePrice(), narcotics.getVar(), narcotics.getMTLU()};
                ss.addMarket("narcotics", info);
            }

            if (ss.getTech() == 7) {
                info = new int[]{1000, (robots.getBasePrice() + (robots.getIPL() * (ss.getTech() - robots.getMTLP()))), robots.getBasePrice(), robots.getVar(), robots.getMTLU()};
                ss.addMarket("robots", info);
            } else if (ss.getTech() >= 6) {
                info = new int[]{500, (robots.getBasePrice() + (robots.getIPL() * (ss.getTech() - robots.getMTLP()))), robots.getBasePrice(), robots.getVar(), robots.getMTLU()};
                ss.addMarket("robots", info);
            }

        }




    }

    // button handler to go to planet screen
    public void onNextPressed(View view) {
        Intent intent = new Intent(UniverseConfigurationActivity.this, PlanetActivity.class);
        startActivity(intent);
    }

}

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

public class RandomChestActivity extends AppCompatActivity {

    private SolarSystem ss;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_chest);
        ss = (SolarSystem) getIntent().getSerializableExtra("PLANET");

    }

    public void onOpenPressed(View view) {
        Random rand = new Random();
        int prob = rand.nextInt(100);
        if (prob < 50) {
            Intent intent = new Intent(RandomChestActivity.this, ChestSuccessActivity.class);
            intent.putExtra("PLANET", ss);
            startActivity(intent);
        } else {
            Intent intent = new Intent(RandomChestActivity.this, ChestFailureActivity.class);
            intent.putExtra("PLANET", ss);
            startActivity(intent);
        }
    }

    public void onPassPressed(View view) {
        Intent intent = new Intent(RandomChestActivity.this, MarketBuyActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);
    }

}

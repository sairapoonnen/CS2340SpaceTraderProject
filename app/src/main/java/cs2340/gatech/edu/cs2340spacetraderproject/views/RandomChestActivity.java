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

public class RandomChestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_chest);

    }

    public void onOpenPressed(View view) {
        Random rand = new Random();
        int prob = rand.nextInt(100);
        if (prob < 90) {
            Intent intent = new Intent(RandomChestActivity.this, ChestSuccessActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(RandomChestActivity.this, ChestFailureActivity.class);
            startActivity(intent);
        }

    }

    public void onPassPressed(View view) {
        Intent intent = new Intent(RandomChestActivity.this, MarketBuyActivity.class);
        startActivity(intent);
    }

}

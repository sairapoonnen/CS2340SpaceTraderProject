package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cs2340.gatech.edu.cs2340spacetraderproject.R;

public class AsteroidSuccess extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_asteroid);

    }

    public void onBackPress(View view) {
        Intent intent = new Intent(AsteroidSuccess.this, MarketBuyActivity.class);
        startActivity(intent);
    }

}

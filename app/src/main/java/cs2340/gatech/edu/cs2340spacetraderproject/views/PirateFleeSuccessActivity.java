package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;

public class PirateFleeSuccessActivity extends AppCompatActivity {
    private Player player = Player.Player();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_pirate_flee);
    }

    public void onBackPress(View view) {
        Intent intent = new Intent(PirateFleeSuccessActivity.this, MarketBuyActivity.class);
        startActivity(intent);
    }

}

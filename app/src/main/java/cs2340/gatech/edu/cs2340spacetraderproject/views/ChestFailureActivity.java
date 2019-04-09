package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;

public class ChestFailureActivity extends AppCompatActivity {
    private Player player = Player.Player();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fail_chest);

    }

    public void onBackPressed(View view) {
        player.setCredits(player.getCredits() - 100);
        Intent intent = new Intent(ChestFailureActivity.this, MarketBuyActivity.class);
        startActivity(intent);
    }

}

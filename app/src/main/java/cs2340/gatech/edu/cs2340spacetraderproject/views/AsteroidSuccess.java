package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;

public class AsteroidSuccess extends AppCompatActivity {

    private Player player = Player.Player();

    private SolarSystem planet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_asteroid);

        planet = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        Log.d("Before", "Rep: " + player.getReputation());
        player.setReputation(1);
        Log.d("After", "Rep: " + player.getReputation());

    }

    public void onBackPress(View view) {
        Intent intent = new Intent(AsteroidSuccess.this, PlanetActivity.class);
        intent.putExtra("PLANET", planet);
        startActivity(intent);
    }

}

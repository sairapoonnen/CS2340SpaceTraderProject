package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;

public class AsteroidEncounterActivity extends AppCompatActivity {
    Player player = Player.Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_asteroid);

    }

    public void onAvoidPressed(View view) {
        Random rand = new Random();
        int prob = rand.nextInt(100 * 2 ^ (player.getPilotSkill() / 3));
        if (prob < 50) {
            Intent intent = new Intent(AsteroidEncounterActivity.this, AsteroidSuccess.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(AsteroidEncounterActivity.this, AsteroidFail.class);
            startActivity(intent);
        }

    }

}

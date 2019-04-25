package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;

public class PlanetSurfaceActivity extends AppCompatActivity {

    private SolarSystem ss;
    private TextView name;
    private ImageView background;
    private Random rand = new Random();
    private Button marketButton;
    private Button mapButton;
    private Button shipYardButton;
    private Market market = Market.Market();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_surface);

        ss = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        name = findViewById(R.id.planet_name);
        name.setText(String.format("Welcome to %s", ss.getName()));

        background = findViewById(R.id.background);

        if (ss.getTech() <= 2) {
            background.setImageResource(R.drawable.city2);
        } else if (ss.getTech() <= 4) {
            background.setImageResource(R.drawable.city3);
        } else if (ss.getTech() <= 6) {
            background.setImageResource(R.drawable.city1);
        }

        move(background);
        marketButton = findViewById(R.id.to_market);
        mapButton = findViewById(R.id.to_map);
        shipYardButton = findViewById(R.id.to_shipyard);
        marketButton.setVisibility(View.INVISIBLE);
        mapButton.setVisibility(View.INVISIBLE);
        shipYardButton.setVisibility(View.INVISIBLE);
        name.setVisibility(View.INVISIBLE);
        shipYardButton.postDelayed(new Runnable() {
            public void run() {
                shipYardButton.setVisibility(View.VISIBLE);
                shipYardButton.startAnimation(AnimationUtils.loadAnimation(PlanetSurfaceActivity.this, R.anim.fade_in));
            }
        }, 1250);
        marketButton.postDelayed(new Runnable() {
            public void run() {
                marketButton.setVisibility(View.VISIBLE);
                marketButton.startAnimation(AnimationUtils.loadAnimation(PlanetSurfaceActivity.this, R.anim.fade_in));
            }
        }, 1750);
        mapButton.postDelayed(new Runnable() {
            public void run() {
                mapButton.setVisibility(View.VISIBLE);
                mapButton.startAnimation(AnimationUtils.loadAnimation(PlanetSurfaceActivity.this, R.anim.fade_in));
            }
        }, 2250);
        name.postDelayed(new Runnable() {
            public void run() {
                name.setVisibility(View.VISIBLE);
                name.startAnimation(AnimationUtils.loadAnimation(PlanetSurfaceActivity.this, R.anim.fade_in));
            }
        }, 875);
    }

    //button handler to go back to Map
    public void onMapPressed(View view) {
        Intent intent = new Intent(PlanetSurfaceActivity.this, TravelActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);
    }

    //button handler to go to Market
    public void onMarketPressed(View view) {
        int randomAct = rand.nextInt(4);
        if (randomAct == 0) {
            Intent intent = new Intent(PlanetSurfaceActivity.this, RandomChestActivity.class);
            intent.putExtra("PLANET", ss);
            startActivity(intent);
        } else {
            Intent intent = new Intent(PlanetSurfaceActivity.this, MarketActivity.class);
            intent.putExtra("PLANET", ss);
            startActivity(intent);
        }

    }

    public static void move(final ImageView view){
        ValueAnimator va = ValueAnimator.ofFloat(100f, -200f);
        int mDuration = 4500; //in millis
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationX((float)animation.getAnimatedValue());
            }
        });
        va.start();
    }
}

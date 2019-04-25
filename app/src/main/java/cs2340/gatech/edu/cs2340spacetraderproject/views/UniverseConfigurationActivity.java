package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;
import cs2340.gatech.edu.cs2340spacetraderproject.viewmodels.UniverseViewModel;

import java.util.Random;

public class UniverseConfigurationActivity extends AppCompatActivity {

    //have map here in the future?
    //or just have list of planets

    //private Universe universe = Universe.Universe();

    UniverseViewModel viewModel;

    private RecyclerView recyclerView;
    private PlanetAdapter adapter;
    private ImageView background;
    private Button nextButton;

    private Universe universe = Universe.Universe();
    SolarSystem ss;

    private DatabaseReference mDatabase;
    private Player player = Player.Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_configuration);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //viewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);

        //viewModel.generateMarkets();
        Random rand = new Random();

        ss = universe.getSolarSystem().get(rand.nextInt(10));

        background = findViewById(R.id.background);
        nextButton = findViewById(R.id.nextButton);
        nextButton.setVisibility(View.INVISIBLE);
        move(background);
        nextButton.postDelayed(new Runnable() {
            public void run() {
                nextButton.setVisibility(View.VISIBLE);
                nextButton.startAnimation(AnimationUtils.loadAnimation(
                        UniverseConfigurationActivity.this, R.anim.fade_in));
            }
        }, 5800);
        background.postDelayed(new Runnable() {
            public void run() {
                background.setVisibility(View.GONE);
                background.startAnimation(AnimationUtils.loadAnimation(
                        UniverseConfigurationActivity.this, R.anim.fade_out));
            }
        }, 1000);


    }

    // button handler to go to planet screen
    public void onNextPressed(View view) {

        for (int i = 0; i < 10; i++) {
            String stored = "" + universe.getSolarSystem().get(i).getX() +
                    " " + universe.getSolarSystem().get(i).getY() + " " +
                    universe.getSolarSystem().get(i).getTech() + " " +
                    universe.getSolarSystem().get(i).getResource();
            mDatabase.child("Universe").child(universe.getSolarSystem().
                    get(i).getName()).setValue(stored);

        }

        mDatabase.child("Player").setValue(player);
        mDatabase.child("SolarSystem").setValue(ss.getName());

        Intent intent = new Intent(UniverseConfigurationActivity.this, PlanetActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);
    }

    public static void move(final ImageView view){
        ValueAnimator va = ValueAnimator.ofFloat(0f, -450f);
        int mDuration = 6000; //in millis
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((float)animation.getAnimatedValue());
            }
        });
        va.start();
    }

}

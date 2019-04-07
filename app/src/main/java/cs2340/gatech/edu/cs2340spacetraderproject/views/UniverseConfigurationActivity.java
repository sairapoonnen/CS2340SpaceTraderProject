package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

}

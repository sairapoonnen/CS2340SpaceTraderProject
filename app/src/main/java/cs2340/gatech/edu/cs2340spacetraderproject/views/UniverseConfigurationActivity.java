package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cs2340.gatech.edu.cs2340spacetraderproject.R;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_configuration);

        //viewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);

        //viewModel.generateMarkets();
        Random rand = new Random();

        ss = universe.getSolarSystem().get(rand.nextInt(10));



    }

    // button handler to go to planet screen
    public void onNextPressed(View view) {
        Intent intent = new Intent(UniverseConfigurationActivity.this, PlanetActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);
    }

}

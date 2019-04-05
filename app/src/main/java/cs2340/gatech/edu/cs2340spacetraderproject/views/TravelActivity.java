package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import android.widget.TextView;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class TravelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlanetAdapter adapter;

    private TextView location;
    private TextView fuel;

    private Player player = Player.Player();
    private Market market = Market.Market();
    private Universe universe = Universe.Universe();

    private int subtract;

    private DatabaseReference mDatabase;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        mDatabase = FirebaseDatabase.getInstance().getReference();

           /*
         Set up our recycler view by grabbing the layout for a single item
         */
        recyclerView = findViewById(R.id.planet_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new PlanetAdapter();
        recyclerView.setAdapter(adapter);

        location = findViewById(R.id.location_input);
        fuel = findViewById(R.id.fuel_input);

        setTitle("Map");

        location.setText("" + market.getSS().getName());
        fuel.setText("" + player.getSpaceship().getFuel());

    }

    @Override
    public void onResume() {
        super.onResume();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Log.d("Null?", "" + player.getCredits());
        location.setText("" + market.getSS().getName());
        fuel.setText("" + player.getSpaceship().getFuel());

        List<SolarSystem> planetList = new ArrayList<>();

        for (SolarSystem ss : universe.getSolarSystem()) {
            if (player.getSpaceship().calculateDistance(market.getSS().getX(), market.getSS().getY(), ss.getX(), ss.getY()) <= player.getSpaceship().getFuel() && !market.getSS().getName().equals(ss.getName())) {
                planetList.add(ss);
            }
        }

        adapter.setItemList(planetList);
        //Log.d("allItems", Arrays.toString(planetList.toArray()));


        adapter.setItemClickListener(new PlanetAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(SolarSystem planet) {

                subtract = player.getSpaceship().getFuel() - player.getSpaceship().calculateDistance(market.getSS().getX(), market.getSS().getY(), planet.getX(), planet.getY());

                player.getSpaceship().setFuel(subtract);
                int randomAct = rand.nextInt(6);
                if (randomAct == 0) {
                    Intent intent = new Intent(TravelActivity.this, RandomChestActivity.class);
                    startActivity(intent);
                } else if (randomAct == 1) {
                    Intent intent = new Intent(TravelActivity.this, PirateEncounterActivity.class);
                    startActivity(intent);
                } else if (randomAct == 2) {
                    Intent intent = new Intent(TravelActivity.this, AsteroidEncounterActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(TravelActivity.this, PlanetActivity.class);
                    //Log.d("itemHashBefore", item.toString());
                    intent.putExtra("PLANET", planet);
                    startActivity(intent);
                    //startActivityForResult(intent, EDIT_REQUEST);
                }


            }
        });
    }

    public void onBackPressed(View view) {
        finish();
    }

    public void onSavePress(View view) {

        List<TradeGood> items = player.getSpaceship().getCargo();
        player.getSpaceship().setCargoEmpty();
        mDatabase.child("Player").setValue(player);
        mDatabase.child("SolarSystem").setValue(market.getSS().getName());
        //mDatabase.child("Universe").removeValue();
        for (TradeGood item : items) {
            mDatabase.child("Items").child(item.getName()).setValue(item.getName());
        }

        player.getSpaceship().cargo = items;
    }
}

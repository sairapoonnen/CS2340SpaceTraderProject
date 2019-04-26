package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
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

import cs2340.gatech.edu.cs2340spacetraderproject.HomeWatcher;
import cs2340.gatech.edu.cs2340spacetraderproject.MusicService;
import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class TravelActivity extends AppCompatActivity {


    private PlanetAdapter adapter;

    private TextView location;
    private TextView fuel;

    private Player player = Player.Player();
    private Market market = Market.Market();
    private Universe universe = Universe.Universe();

    private int subtract;

    private Random rand = new Random();

    MediaPlayer buttonSound;
    MediaPlayer travel;

    //Home watcher
    HomeWatcher mHomeWatcher;

    //for music service
    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon = new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = (MusicService) ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        RecyclerView recyclerView;



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

        //BIND music service
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        //home watcher calls
        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });

        mHomeWatcher.startWatch();

        //button sound effect setup
        buttonSound = MediaPlayer.create(this, R.raw.button);
        travel = MediaPlayer.create(this, R.raw.travelbutton);

        travel.start();

    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Log.d("Null?", "" + player.getCredits());
        location.setText("" + market.getSS().getName());
        fuel.setText("" + player.getSpaceship().getFuel());

        List<SolarSystem> planetList = new ArrayList<>();

        for (SolarSystem ss : universe.getSolarSystem()) {
            if (player.getSpaceship().calculateDistance(market.getSS().getX(),
                    market.getSS().getY(), ss.getX(), ss.getY()) <=
                    player.getSpaceship().getFuel() &&
                    !market.getSS().getName().equals(ss.getName())) {
                planetList.add(ss);
            }
        }

        adapter.setItemList(planetList);
        //Log.d("allItems", Arrays.toString(planetList.toArray()));


        adapter.setItemClickListener(new PlanetAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(SolarSystem planet) {

                subtract = player.getSpaceship().getFuel() -
                        player.getSpaceship().calculateDistance(market.getSS().getX(),
                                market.getSS().getY(), planet.getX(), planet.getY());

                player.getSpaceship().setFuel(subtract);
                int randomAct = rand.nextInt(5);
                Log.d("Random", "rand int: " + randomAct);
                if (randomAct == 0) {
                    Intent intent = new Intent(TravelActivity.this, PirateEncounterActivity.class);
                    intent.putExtra("PLANET", planet);
                    startActivity(intent);
                } else if (randomAct == 1) {
                    Intent intent = new Intent(TravelActivity.this, AsteroidEncounterActivity.class);
                    intent.putExtra("PLANET", planet);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(TravelActivity.this,
                            PlanetActivity.class);
                    //Log.d("itemHashBefore", item.toString());
                    intent.putExtra("PLANET", planet);
                    startActivity(intent);
                    //startActivityForResult(intent, EDIT_REQUEST);
                }


            }
        });

        if (mServ != null) {
            mServ.resumeMusic();
        }
    }

    public void onBackPressed(View view) {

        buttonSound.start();

        Intent intent = new Intent(TravelActivity.this, PlanetSurfaceActivity.class);
        intent.putExtra("PLANET", market.getSS().getName());
        startActivity(intent);
    }

    public void onSavePress(View view) {

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

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

    @Override
    protected void onPause() {
        super.onPause();

        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }

    }

    @Override
    protected void onDestroy() {
        //UNBIND music service
        super.onDestroy();

        mHomeWatcher.stopWatch();

        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);

    }
}

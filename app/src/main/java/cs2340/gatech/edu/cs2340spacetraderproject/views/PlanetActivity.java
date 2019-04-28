package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cs2340.gatech.edu.cs2340spacetraderproject.HomeWatcher;
import cs2340.gatech.edu.cs2340spacetraderproject.MusicService;
import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Firearms;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Food;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Furs;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Games;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Machines;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Medicine;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Narcotics;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Ore;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Robots;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.Water;
import cs2340.gatech.edu.cs2340spacetraderproject.viewmodels.ConfigurationViewModel;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.GameDifficulty;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;

import java.util.HashMap;

import java.util.*;

public class PlanetActivity extends AppCompatActivity {

    private Universe universe = Universe.Universe();
    private Market market = Market.Market();
    private SolarSystem ss;

    private Player player = Player.Player();

    private DatabaseReference mDatabase;

    private TextView whiteBox;

    MediaPlayer buttonSound;

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

    /*
    private Water water = new Water();
    private Furs furs = new Furs();
    private Food food = new Food();
    private Ore ore = new Ore();
    private Games games =  new Games();
    private Firearms firearms = new Firearms();
    private Medicine medicine = new Medicine();
    private Machines machines = new Machines();
    private Narcotics narcotics = new Narcotics();
    private Robots robots = new Robots();
    */

    //widgets
    private ConstraintLayout constraintLayout;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        TextView name;

        mDatabase = FirebaseDatabase.getInstance().getReference();

        ss = (SolarSystem) getIntent().getSerializableExtra("PLANET");
        //Log.d("itemHashAfter", item.toString());

        whiteBox = findViewById(R.id.whiteBox);
        whiteBox.startAnimation(AnimationUtils.loadAnimation(
                PlanetActivity.this, R.anim.fade_out));
        whiteBox.setVisibility(View.INVISIBLE);


        Log.d("Test", "planet: " + ss.getName());

        //ss.addMarket(new Water());
        //ss.addMarket(new Water());
        //ss.addMarket(new Water());
        ss.addMarket(new Water());
        ss.addMarket(new Furs());
        //ss.addMarket(new Furs());
        //ss.addMarket(new Furs());
        //ss.addMarket(new Furs());

        if (ss.getTech() == 1) {
            ss.addMarket(new Food());
        } else if (ss.getTech() == 2) {
            ss.addMarket(new Ore());
        } else if (ss.getTech() == 3) {
            ss.addMarket(new Games());
            ss.addMarket(new Firearms());
        } else if (ss.getTech() == 4) {
            ss.addMarket(new Medicine());
            ss.addMarket(new Machines());
        } else if (ss.getTech() == 5) {
            ss.addMarket(new Narcotics());
        } else if (ss.getTech() >= 6){
            ss.addMarket(new Robots());
        }

        Log.d("test", ss.getMarket().get(0).getName() + ", tech level: " + ss.getTech());
        market.setMarket(ss.getMarket());
        market.setSS(ss); //for events

        name = findViewById(R.id.planet_name);
        name.setText(ss.getName());
        //add textView for planet tech level and resource type?

        constraintLayout = findViewById(R.id.constraintLayout);
        if (ss.getTech() <= 2) {
            constraintLayout.setBackground(ContextCompat.getDrawable(PlanetActivity.this, R.drawable.planet2));
        } else if (ss.getTech() <= 4) {
            constraintLayout.setBackground(ContextCompat.getDrawable(PlanetActivity.this, R.drawable.planet1));
        } else if (ss.getTech() <= 6) {
            constraintLayout.setBackground(ContextCompat.getDrawable(PlanetActivity.this, R.drawable.planet3));
        }

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
    }

    //button handler to go back to Map
    public void onEnterPressed(View view) {

        buttonSound.start();

        Intent intent = new Intent(PlanetActivity.this, PlanetSurfaceActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);
    }

    //add to profile page when done



    @Override
    public void onResume() {
        super.onResume();

        Log.d("Resume?", "onResume");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (mServ != null) {
            mServ.resumeMusic();
        }
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

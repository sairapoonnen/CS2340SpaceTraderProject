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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ThrowOnExtraProperties;

import cs2340.gatech.edu.cs2340spacetraderproject.HomeWatcher;
import cs2340.gatech.edu.cs2340spacetraderproject.MusicService;
import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;

public class ViewPlanetActivity extends AppCompatActivity {

    private SolarSystem ss;
    private SolarSystem returnSS;
    private Player player = Player.Player();
    private Market market = Market.Market();
    private Universe universe = Universe.Universe();

    private TextView planetName;
    private TextView planetTech;
    private TextView planetResource;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewplanet);
        ss = (SolarSystem) getIntent().getSerializableExtra("PLANET");
        returnSS = (SolarSystem) getIntent().getSerializableExtra("RETURN");

        planetName = findViewById(R.id.planet_name);
        planetResource = findViewById(R.id.planet_resource);
        planetTech = findViewById(R.id.planet_tech);

        planetName.setText(ss.getName());
        planetTech.setText(ss.getTechArray()[ss.getTech()]);
        planetResource.setText(ss.getResourceArray()[ss.getResource()]);

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

    public void onTravelPressed(View view) {
        int subtract = player.getSpaceship().getFuel() -
                player.getSpaceship().calculateDistance(returnSS.getX(),
                        returnSS.getY(), ss.getX(), ss.getY());
        if (subtract >= 0) {
            player.getSpaceship().setFuel(subtract);

            Intent intent = new Intent(ViewPlanetActivity.this, FlyingCutsceneActivity.class);
            intent.putExtra("PLANET", ss);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Cannot travel to this planet because not enough fuel.",
                Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed(View view) {

        buttonSound.start();

        Intent intent = new Intent(ViewPlanetActivity.this, TravelMapActivity.class);
        intent.putExtra("PLANET", returnSS);
        startActivity(intent);
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

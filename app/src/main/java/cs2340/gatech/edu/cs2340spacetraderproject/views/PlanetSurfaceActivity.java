package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.animation.ValueAnimator;
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
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.HomeWatcher;
import cs2340.gatech.edu.cs2340spacetraderproject.MusicService;
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

    MediaPlayer bt;
    MediaPlayer surface;

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
        bindService(new Intent(this, MusicService.class),
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
        bt = MediaPlayer.create(this, R.raw.button2);
        surface = MediaPlayer.create(this, R.raw.surface);

        surface.start();
    }

    //button handler to go back to Map
    public void onMapPressed(View view) {

        surface.stop();
        bt.start();

        Intent intent = new Intent(PlanetSurfaceActivity.this, TravelActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);
    }

    //button handler to go to Market
    public void onMarketPressed(View view) {

        surface.stop();
        bt.start();

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

    @Override
    protected void onResume() {
        super.onResume();

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

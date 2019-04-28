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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import cs2340.gatech.edu.cs2340spacetraderproject.HomeWatcher;
import cs2340.gatech.edu.cs2340spacetraderproject.MusicService;
import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;

public class FlyingCutsceneActivity2 extends AppCompatActivity {
    private ImageView ship;
    private Button next;
    private SolarSystem ss;
    private Random rand = new Random();
    private ImageView backgroundOne;
    private ImageView backgroundTwo;

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
        setContentView(R.layout.activity_travel_cutscene2);
        ss = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        ship = findViewById(R.id.ship);
        next = findViewById(R.id.next);
        backgroundOne = findViewById(R.id.background_one);
        backgroundTwo = findViewById(R.id.background_two);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, -1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1500L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX + width);
            }
        });
        animator.start();

        moveShip1(ship);
        moveShip2(ship);
        moveShip3(ship);

        next.setVisibility(View.INVISIBLE);
        next.postDelayed(new Runnable() {
            public void run() {
                next.setVisibility(View.VISIBLE);
                next.startAnimation(AnimationUtils.loadAnimation(FlyingCutsceneActivity2.this, R.anim.fade_in));
            }
        }, 4500);

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

    public void onNextPressed(View view)  {

        int randomAct = rand.nextInt(5);
        Log.d("Random", "rand int: " + randomAct);
        if (randomAct == 0) {
            Intent intent = new Intent(FlyingCutsceneActivity2.this, PirateEncounterActivity.class);
            intent.putExtra("PLANET", ss);
            startActivity(intent);
        } else if (randomAct == 1) {
            Intent intent = new Intent(FlyingCutsceneActivity2.this, AsteroidEncounterActivity.class);
            intent.putExtra("PLANET", ss);
            startActivity(intent);
        } else {
            Intent intent = new Intent(FlyingCutsceneActivity2.this,
                    PlanetActivity.class);
            //Log.d("itemHashBefore", item.toString());
            intent.putExtra("PLANET", ss);
            startActivity(intent);
            //startActivityForResult(intent, EDIT_REQUEST);
        }
    }

    public static void moveShip1(final ImageView view){
        ValueAnimator va = ValueAnimator.ofFloat(-400f, 200f);
        int mDuration = 2500; //in millis
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationX((float)animation.getAnimatedValue());
            }
        });
        va.start();
    }

    public static void moveShip2(final ImageView view){
        ValueAnimator va = ValueAnimator.ofFloat(200f, 0f);
        int mDuration = 1000; //in millis
        va.setStartDelay(2500);
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationX((float)animation.getAnimatedValue());
            }
        });
        va.start();
    }

    public static void moveShip3(final ImageView view){
        ValueAnimator va = ValueAnimator.ofFloat(0f, 1200f);
        int mDuration = 1000; //in millis
        va.setStartDelay(3500);
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationX((float)animation.getAnimatedValue());
            }
        });
        va.start();
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

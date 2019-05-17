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
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import cs2340.gatech.edu.cs2340spacetraderproject.HomeWatcher;
import cs2340.gatech.edu.cs2340spacetraderproject.MusicService;
import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;

public class TravelMapActivity extends AppCompatActivity {

    private SolarSystem ss;
    private Player player = Player.Player();
    private Market market = Market.Market();
    private Universe universe = Universe.Universe();

    // step 1: add some instance
    private float mScale = 1f;
    private ScaleGestureDetector mScaleDetector;
    GestureDetector gestureDetector;
    private TextView nix_here;
    private TextView tuzi_here;
    private TextView hades_here;
    private TextView sol_here;
    private TextView andevian_here;
    private TextView relva_here;
    private TextView malcoria_here;
    private TextView terosa_here;
    private TextView brax_here;
    private TextView gypsophil_here;

    private ImageView nix;
    private ImageView tuzi;
    private ImageView hades;
    private ImageView sol;
    private ImageView andevian;
    private ImageView relva;
    private ImageView malcoria;
    private ImageView terosa;
    private ImageView brax;
    private ImageView gypsophil;

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
        setContentView(R.layout.activity_travel_map);
        ss = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        nix_here = findViewById(R.id.nix_here);
        tuzi_here = findViewById(R.id.tuzi_here);
        hades_here = findViewById(R.id.hades_here);
        sol_here = findViewById(R.id.sol_here);
        andevian_here = findViewById(R.id.andevian_here);
        relva_here = findViewById(R.id.relva_here);
        malcoria_here = findViewById(R.id.malcoria_here);
        terosa_here = findViewById(R.id.terosa_here);
        brax_here = findViewById(R.id.brax_here);
        gypsophil_here = findViewById(R.id.gypsophil_here);

        nix = findViewById(R.id.nix);
        tuzi = findViewById(R.id.tuzi);
        hades = findViewById(R.id.hades);
        sol = findViewById(R.id.sol);
        andevian = findViewById(R.id.andevian);
        relva = findViewById(R.id.relva);
        malcoria = findViewById(R.id.malcoria);
        terosa = findViewById(R.id.terosa);
        brax = findViewById(R.id.brax);
        gypsophil = findViewById(R.id.gypsophil);

        gestureDetector = new GestureDetector(this, new GestureListener());
        mScaleDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener()
        {
            @Override
            public boolean onScale(ScaleGestureDetector detector)
            {
                float scale = 1 - detector.getScaleFactor();

                float prevScale = mScale;
                mScale += scale;

                if (mScale < 0.1f) // Minimum scale condition:
                    mScale = 0.1f;

                if (mScale > 10f) // Maximum scale condition:
                    mScale = 10f;
                ScaleAnimation scaleAnimation = new ScaleAnimation(1f / prevScale, 1f / mScale, 1f / prevScale, 1f / mScale, detector.getFocusX(), detector.getFocusY());
                scaleAnimation.setDuration(0);
                scaleAnimation.setFillAfter(true);
                ScrollView layout =(ScrollView) findViewById(R.id.scrollViewZoom);
                layout.startAnimation(scaleAnimation);
                return true;
            }
        });

        if (ss.getName().equals("Nix")) {
            nix_here.setText("You Are Here");
        } else if (ss.getName().equals("Tuzi")) {
            tuzi_here.setText("You Are Here");
        } else if (ss.getName().equals("Hades")) {
            hades_here.setText("You Are Here");
        } else if (ss.getName().equals("Sol")) {
            sol_here.setText("You Are Here");
        } else if (ss.getName().equals("Andevian")) {
            andevian_here.setText("You Are Here");
        } else if (ss.getName().equals("Relva")) {
            relva_here.setText("You Are Here");
        } else if (ss.getName().equals("Malcoria")) {
            malcoria_here.setText("You Are Here");
        } else if (ss.getName().equals("Terosa")) {
            terosa_here.setText("You Are Here");
        } else if (ss.getName().equals("Brax")) {
            brax_here.setText("You Are Here");
        } else if (ss.getName().equals("Gypsophil")) {
            gypsophil_here.setText("You Are Here");
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
        travel = MediaPlayer.create(this, R.raw.travelbutton);

        travel.start();
    }


    public void onNixPressed(View view) {
        SolarSystem nixSS = universe.getSolarSystem().get(2);
        Intent intent = new Intent(TravelMapActivity.this, ViewPlanetActivity.class);
        intent.putExtra("PLANET", nixSS);
        intent.putExtra("RETURN", ss);
        startActivity(intent);
    }

    public void onTuziPressed(View view) {
        SolarSystem nixSS = universe.getSolarSystem().get(1);
        Intent intent = new Intent(TravelMapActivity.this, ViewPlanetActivity.class);
        intent.putExtra("PLANET", nixSS);
        intent.putExtra("RETURN", ss);
        startActivity(intent);
    }

    public void onGypsophilPressed(View view) {
        SolarSystem nixSS = universe.getSolarSystem().get(0);
        Intent intent = new Intent(TravelMapActivity.this, ViewPlanetActivity.class);
        intent.putExtra("PLANET", nixSS);
        intent.putExtra("RETURN", ss);
        startActivity(intent);
    }

    public void onHadesPressed(View view) {
        SolarSystem nixSS = universe.getSolarSystem().get(3);
        Intent intent = new Intent(TravelMapActivity.this, ViewPlanetActivity.class);
        intent.putExtra("PLANET", nixSS);
        intent.putExtra("RETURN", ss);
        startActivity(intent);
    }

    public void onTerosaPressed(View view) {
        SolarSystem nixSS = universe.getSolarSystem().get(4);
        Intent intent = new Intent(TravelMapActivity.this, ViewPlanetActivity.class);
        intent.putExtra("PLANET", nixSS);
        intent.putExtra("RETURN", ss);
        startActivity(intent);
    }

    public void onMalcoriaPressed(View view) {
        SolarSystem nixSS = universe.getSolarSystem().get(5);
        Intent intent = new Intent(TravelMapActivity.this, ViewPlanetActivity.class);
        intent.putExtra("PLANET", nixSS);
        intent.putExtra("RETURN", ss);
        startActivity(intent);
    }

    public void onBraxPressed(View view) {
        SolarSystem nixSS = universe.getSolarSystem().get(6);
        Intent intent = new Intent(TravelMapActivity.this, ViewPlanetActivity.class);
        intent.putExtra("PLANET", nixSS);
        intent.putExtra("RETURN", ss);
        startActivity(intent);
    }

    public void onSolPressed(View view) {
        SolarSystem nixSS = universe.getSolarSystem().get(7);
        Intent intent = new Intent(TravelMapActivity.this, ViewPlanetActivity.class);
        intent.putExtra("PLANET", nixSS);
        intent.putExtra("RETURN", ss);
        startActivity(intent);
    }

    public void onAndevianPressed(View view) {
        SolarSystem nixSS = universe.getSolarSystem().get(8);
        Intent intent = new Intent(TravelMapActivity.this, ViewPlanetActivity.class);
        intent.putExtra("PLANET", nixSS);
        intent.putExtra("RETURN", ss);
        startActivity(intent);
    }

    public void onRelvaPressed(View view) {
        SolarSystem nixSS = universe.getSolarSystem().get(9);
        Intent intent = new Intent(TravelMapActivity.this, ViewPlanetActivity.class);
        intent.putExtra("PLANET", nixSS);
        intent.putExtra("RETURN", ss);
        startActivity(intent);
    }


    // step 3: override dispatchTouchEvent()
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        mScaleDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return gestureDetector.onTouchEvent(event);
    }

//step 4: add private class GestureListener

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            // double tap fired.
            return true;
        }
    }

    public void onBackPressed(View view) {

        buttonSound.start();

        Intent intent = new Intent(TravelMapActivity.this, PlanetSurfaceActivity.class);
        intent.putExtra("PLANET", ss);
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

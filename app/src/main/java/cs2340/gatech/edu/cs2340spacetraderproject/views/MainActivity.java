package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cs2340.gatech.edu.cs2340spacetraderproject.HomeWatcher;
import cs2340.gatech.edu.cs2340spacetraderproject.MusicService;
import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;
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

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.ClassNotFoundException;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ImageView background;
    private Button createPlayer;
    private Button loadGame;

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

//        //button sound effect
//        final MediaPlayer buttonSound = MediaPlayer.create(this, R.raw.button);
//
//        Button bt1 = (Button) findViewById(R.id.new_player);
//
//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                buttonSound.start();
//            }
//        });
//
//        Button bt2 = (Button) this.findViewById(R.id.load);
//
//        bt2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                buttonSound.start();
//            }
//        });

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

        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background = findViewById(R.id.background);
        move(background);
        createPlayer = findViewById(R.id.new_player);
        loadGame = findViewById(R.id.load);
        createPlayer.setVisibility(View.INVISIBLE);
        loadGame.setVisibility(View.INVISIBLE);
        createPlayer.postDelayed(new Runnable() {
            public void run() {
                createPlayer.setVisibility(View.VISIBLE);
                createPlayer.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in));
            }
        }, 4500);
        loadGame.postDelayed(new Runnable() {
            public void run() {
                loadGame.setVisibility(View.VISIBLE);
                loadGame.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in));
            }
        }, 5000);

//        Button makePlayer = (Button) findViewById(R.id.new_player);
//
//        makePlayer.setOnClickListener(new OnClickListener(){
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ConfigurationActivity.class));
//            }
//        });
    }

    public void createPlayer(View view) {
//        mDatabase.child("Player").setValue(null);
//        mDatabase.child("SolarSystem").setValue(null);
        mDatabase.setValue(null);
        Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
        startActivity(intent);

    }

    public void loadPressed(View view)  {
        mDatabase.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "You have no saved games", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,
                            ConfigurationActivity.class);
                    startActivity(intent);
                } else {
                    //Player player = null;
                    String ssName = null;
                    List<String> solars = new ArrayList<>();
                    ArrayList<SolarSystem> list = new ArrayList();
                    //player = (Player)dataSnapshot.child("Player").getValue();
                    Player player = dataSnapshot.child("Player").getValue(Player.class);
                    ssName = (String) dataSnapshot.child("SolarSystem").getValue();

                    for (DataSnapshot sSystems: dataSnapshot.child("Universe").getChildren()) {
                        String s  =  sSystems.getKey() + " " + sSystems.getValue();
                        solars.add(s);
                    }

                    SolarSystem current = null;

                    for (String ss: solars) {
                        String[] splitted = ss.split(" ");
                        SolarSystem solarSys = new SolarSystem(splitted[0],
                                Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]),
                                Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]));
                        if (splitted[0].equals(ssName)) {
                            current = solarSys;
                        }
                        list.add(solarSys);
                    }
                    Universe universe = new Universe(list);
                    Universe.single_instance = universe;

                    List<String> items = new ArrayList<>();

                    for (DataSnapshot sSystems: dataSnapshot.child("Items").getChildren()) {
                        String s  = sSystems.getKey();
                        items.add(s);
                    }

                    List<TradeGood> tradeItems = new ArrayList<>();
                    String[] arr = {"Firearms", "Food", "Furs", "Games", "Machines", "Medicine",
                            "Narcotics", "Ore", "Robots", "TradeGood", "Water"};
                    List<String> allItems = Arrays.asList(arr);


                        for (String item: items)  {
                            TradeGood t = null;
                            if (item.equals("Firearms")){
                                t = new Firearms();

                            } else if(item.equals("Food")) {
                                t = new Food();

                            } else if (item.equals("Furs")) {
                                t = new Furs();

                            } else if (item.equals("Games")) {
                                t = new Games();

                            }else if (item.equals("Machines")) {
                                t = new Machines();

                            }else if (item.equals("Narcotics")) {
                                t = new Narcotics();

                            }else if (item.equals("Ore")) {
                                t = new Ore();

                            }else if (item.equals("Robots")) {
                                t = new Robots();

                            }else if (item.equals("Water")) {
                                t = new Water();

                            } else if(item.equals("Medicine")) {
                                t = new Medicine();

                            }
                            tradeItems.add(t);

                        }

                        player.getSpaceship().cargo = tradeItems;


                    if (player != null) {
                        Player.single_instance = player;


                        Intent intent = new Intent(MainActivity.this, PlanetActivity.class);
                        intent.putExtra("PLANET", current);
                        startActivity(intent);
                    }

                }

            }
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public static void move(final ImageView view){
        ValueAnimator va = ValueAnimator.ofFloat(500f, 50f);
        int mDuration = 6000; //in millis
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

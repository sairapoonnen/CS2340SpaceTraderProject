package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.animation.ValueAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModel;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cs2340.gatech.edu.cs2340spacetraderproject.HomeWatcher;
import cs2340.gatech.edu.cs2340spacetraderproject.MusicService;
import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Gnat;
import cs2340.gatech.edu.cs2340spacetraderproject.viewmodels.ConfigurationViewModel;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.GameDifficulty;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;

import java.util.*;

public class ConfigurationActivity extends AppCompatActivity {

    ConfigurationViewModel viewModel;


    /*widgets*/
    private EditText nameField;
    private Spinner difficultySpinner;
    private TextView pilotSkillPoint;
    private TextView fighterSkillPoint;
    private TextView traderSkillPoint;
    private TextView engineerSkillPoint;
    private TextView totalSkillPoint;
    private ImageView backgroundOne;
    private ImageView backgroundTwo;


    /*data for player being edited*/
    private Player player = Player.Player();
    private final int maxSkill = 16;
    private int totalSkill = player.getTotalSkill();
    private boolean playerCreated = false;

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
        setContentView(R.layout.activity_configuration);


        /*reference to viewModel*/
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);

        nameField = findViewById(R.id.player_name);
        difficultySpinner = findViewById(R.id.difficultySpinner);
        pilotSkillPoint = findViewById(R.id.pilotSkillPoint);
        fighterSkillPoint = findViewById(R.id.fighterSkillPoint);
        traderSkillPoint = findViewById(R.id.traderSkillPoint);
        engineerSkillPoint = findViewById(R.id.engineerSkillPoint);
        totalSkillPoint = findViewById(R.id.totalSkillPoint);

        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, GameDifficulty.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, -1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(15000L);
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

        //BIND music service
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        //Home watcher calls
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
    }

    public void createPlayer(String name, String difficulty) {
        player.setName(name);
        player.setGameDifficulty(difficulty);
        player.setSpaceship(new Gnat());
        player.setCredits(100);


    }

    /*Button handler for the Create Player button*/
    public void onCreatePressed(View view) {
        if (totalSkill == 16) {
            String TAG = "PlayerInfo";
            playerCreated = true;

            player.setName(nameField.getText().toString());
            player.setGameDifficulty(difficultySpinner.getSelectedItem().toString());
            player.setSpaceship(new Gnat());
            player.setCredits(1000);


            view.setEnabled(false);

            Log.d(TAG, "Player Name: " + player.getName());
            Log.d(TAG, "Credits: " + player.getCredits());
            Log.d(TAG, "Spaceship: " + player.getSpaceship().getName());
            Log.d(TAG, "Pilot Skill: " + Integer.toString(player.getPilotSkill()));
            Log.d(TAG, "Fighter Skill: " + Integer.toString(player.getFighterSkill()));
            Log.d(TAG, "Trader Skill: " + Integer.toString(player.getTraderSkill()));
            Log.d(TAG, "Engineer Skill: " + Integer.toString(player.getEngineerSkill()));
            Log.d(TAG, "Game Difficulty: " + player.getGameDifficulty());
            Toast.makeText(getApplicationContext(),
                    "Player created!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Must allocate all skill points before creation.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onPlusPilotPressed(View view) {
        if (totalSkill < maxSkill) {
            player.setPilotSkill(player.getPilotSkill() + 1);
            pilotSkillPoint.setText(String.format("%s", player.getPilotSkill()));
            totalSkill++;
            totalSkillPoint.setText(String.format("%s", maxSkill - totalSkill));
        }
    }

    public void onMinusPilotPressed(View view) {
        if (player.getPilotSkill() > 0) {
            player.setPilotSkill(player.getPilotSkill() - 1);
            pilotSkillPoint.setText(String.format("%s", player.getPilotSkill()));
            totalSkill--;
            totalSkillPoint.setText(String.format("%s", maxSkill - totalSkill));
        }
    }


    public void onPlusFighterPressed(View view) {
        if (totalSkill < maxSkill) {
            player.setFighterSkill(player.getFighterSkill() + 1);
            fighterSkillPoint.setText(String.format("%s", player.getFighterSkill()));
            totalSkill++;
            totalSkillPoint.setText(String.format("%s",maxSkill - totalSkill));
        }
    }

    public void onMinusFighterPressed(View view) {
        if (player.getFighterSkill() > 0) {
            player.setFighterSkill(player.getFighterSkill() - 1);
            fighterSkillPoint.setText(String.format("%s", player.getFighterSkill()));
            totalSkill--;
            totalSkillPoint.setText(String.format("%s", maxSkill - totalSkill));
        }
    }


    public void onPlusTraderPressed(View view) {
        if (totalSkill < maxSkill) {
            player.setTraderSkill(player.getTraderSkill() + 1);
            traderSkillPoint.setText(String.format("%s", player.getTraderSkill()));
            totalSkill++;
            totalSkillPoint.setText(String.format("%s", maxSkill - totalSkill));
        }
    }

    public void onMinusTraderPressed(View view) {
        if (player.getTraderSkill() > 0) {
            player.setTraderSkill(player.getTraderSkill() - 1);
            traderSkillPoint.setText(String.format("%s", player.getTraderSkill()));
            totalSkill--;
            totalSkillPoint.setText(String.format("%s", maxSkill - totalSkill));
        }
    }

    public void onPlusEngineerPressed(View view) {
        if (totalSkill < maxSkill) {
            player.setEngineerSkill(player.getEngineerSkill() + 1);
            engineerSkillPoint.setText(String.format("%s", player.getEngineerSkill()));
            totalSkill++;
            totalSkillPoint.setText(String.format("%s", maxSkill - totalSkill));
        }
    }

    public void onMinusEngineerPressed(View view) {
        if (player.getEngineerSkill() > 0) {
            player.setEngineerSkill(player.getEngineerSkill() - 1);
            engineerSkillPoint.setText(String.format("%s", player.getEngineerSkill()));
            totalSkill--;
            totalSkillPoint.setText(String.format("%s", maxSkill - totalSkill));
        }
    }

    /*Button handler for generating universe*/
    public void onGeneratePressed(View view) {
        if (playerCreated) {
            Universe universe = Universe.Universe();

            Random rand = new Random();

            universe.addSolarSystem(new SolarSystem("Gypsophil", 23, 75,
                    rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Tuzi", 44, 110,
                    rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Nix", 32, 80,
                    rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Hades", 50, 70,
                    rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Terosa", 14, 19,
                    rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Malcoria", 9, 41,
                    rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Brax", 61, 4,
                    rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Sol", 75, 59,
                    rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Andevian", 83, 37,
                    rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Relva", 96, 22,
                    rand.nextInt(8), rand.nextInt(13)));


            String TAG = "UniverseInfo";
            Log.i(TAG, "Universe: ");

            largeLog("Solar System", universe.toString());


            Intent intent = new Intent(ConfigurationActivity.this,
                    UniverseConfigurationActivity.class);
            startActivity(intent);


        } else {
            Toast.makeText(getApplicationContext(),
                    "Must create player before generating universe",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            Log.d(tag, content.substring(0, 4000));
            largeLog(tag, content.substring(4000));
        } else {
            Log.d(tag, content);
        }
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
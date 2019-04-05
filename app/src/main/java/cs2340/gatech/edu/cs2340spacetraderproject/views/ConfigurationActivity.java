package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    private DatabaseReference mDatabase;

    /*widgets*/
    private EditText nameField;
    private Spinner difficultySpinner;
    private TextView pilotSkillPoint;
    private TextView fighterSkillPoint;
    private TextView traderSkillPoint;
    private TextView engineerSkillPoint;
    private TextView totalSkillPoint;


    /*data for player being edited*/
    private Player player = Player.Player();
    private final int maxSkill = 16;
    private int totalSkill = player.getEngineerSkill() + player.getFighterSkill() + player.getPilotSkill() + player.getTraderSkill();
    private boolean playerCreated = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        /*reference to viewModel*/
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);


        nameField = findViewById(R.id.player_name);
        difficultySpinner = findViewById(R.id.difficultySpinner);
        pilotSkillPoint = findViewById(R.id.pilotSkillPoint);
        fighterSkillPoint = findViewById(R.id.fighterSkillPoint);
        traderSkillPoint = findViewById(R.id.traderSkillPoint);
        engineerSkillPoint = findViewById(R.id.engineerSkillPoint);
        totalSkillPoint = findViewById(R.id.totalSkillPoint);

        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GameDifficulty.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

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
        } else {
            Toast.makeText(getApplicationContext(), "Must allocate all skill points before creation.", Toast.LENGTH_SHORT).show();
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

            universe.addSolarSystem(new SolarSystem("Gypsophil", 23, 75, rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Tuzi", 44, 110, rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Nix", 32, 80, rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Hades", 50, 70, rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Terosa", 14, 19, rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Malcoria", 9, 41, rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Brax", 61, 4, rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Sol", 75, 59, rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Andevian", 83, 37, rand.nextInt(8), rand.nextInt(13)));
            universe.addSolarSystem(new SolarSystem("Relva", 96, 22, rand.nextInt(8), rand.nextInt(13)));


            String TAG = "UniverseInfo";
            Log.i(TAG, "Universe: ");

//            for (SolarSystem ss : universe.getSolarSystem()) {
//                Log.i(TAG, "Solar System Name: " + ss.getName() + ", coordinates: " + Integer.toString(ss.getX()) + ", " + Integer.toString(ss.getY()) + ", Tech Level: " + ss.getTechArray()[ss.getTech()] + ", Resources: " + ss.getResourceArray()[ss.getResource()] );
//            }

            largeLog("Solar System", universe.toString());


            Intent intent = new Intent(ConfigurationActivity.this, UniverseConfigurationActivity.class);
            startActivity(intent);


        } else {
            Toast.makeText(getApplicationContext(), "Must create player before generating universe", Toast.LENGTH_SHORT).show();
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

}
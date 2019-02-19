package cs2340.gatech.edu.cs2340spacetraderproject.views;

//import android.arch.lifecycle.ViewModelProviders;
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

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.viewmodels.ConfigurationViewModel;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.GameDifficulty;

public class ConfigurationActivity extends AppCompatActivity {

    /*reference to viewModel*/
    private ConfigurationViewModel viewModel;

    /*widgets*/
    private EditText nameField;
    private Spinner difficultySpinner;
    private TextView pilotSkillPoint;
    private TextView fighterSkillPoint;
    private TextView traderSkillPoint;
    private TextView engineerSkillPoint;
    private TextView totalSkillPoint;


    /*data for player being edited*/
    private Player player = new Player();
    private final int maxSkill = 16;
    private int totalSkill = player.getEngineerSkill() + player.getFighterSkill() + player.getPilotSkill() + player.getTraderSkill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

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
            player.setName(nameField.getText().toString());
            player.setGameDifficulty(difficultySpinner.getSelectedItem().toString());
            finish();
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

}
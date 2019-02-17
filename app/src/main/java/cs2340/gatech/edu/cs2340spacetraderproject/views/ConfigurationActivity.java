package cs2340.gatech.edu.cs2340spacetraderproject.views;

//import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.viewmodels.ConfigurationViewModel;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.GameDifficulty;

public class ConfigurationActivity extends AppCompatActivity{

    /*reference to viewModel*/
    private ConfigurationViewModel viewModel;

    /*widgets*/
    private EditText nameField;
    private Spinner difficultySpinner;

    /*data for player being edited*/
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        nameField = findViewById(R.id.player_name);
        difficultySpinner = findViewById(R.id.difficultySpinner);

        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GameDifficulty.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

    }

    /*Button handler for the Create Player button*/
    public void onCreatePress(View view) {
        player.setName(nameField.getText().toString());
        player.setGameDifficulty((String) difficultySpinner.getSelectedItem());
    }




}

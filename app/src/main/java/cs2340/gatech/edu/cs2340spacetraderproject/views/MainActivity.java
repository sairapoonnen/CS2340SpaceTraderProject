package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button makePlayer = (Button) findViewById(R.id.new_player);

        makePlayer.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConfigurationActivity.class));
            }
        });
    }
}

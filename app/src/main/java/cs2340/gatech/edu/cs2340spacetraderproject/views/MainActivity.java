package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    Toast.makeText(getApplicationContext(), "You have no saved games", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                    startActivity(intent);
                } else {
                    //Player player = null;
                    String ssName = null;
                    ArrayList<String> solars = new ArrayList<>();
                    ArrayList<SolarSystem> list = new ArrayList();
                    //player = (Player)dataSnapshot.child("Player").getValue();
                    Player player = dataSnapshot.child("Player").getValue(Player.class);
                    ssName = (String) dataSnapshot.child("SolarSystem").getValue();

                    for (DataSnapshot sSystems: dataSnapshot.child("Universe").getChildren()) {
                        String s  = (String) sSystems.getKey() + " " + (String)sSystems.getValue();
                        solars.add(s);
                    }

                    SolarSystem current = null;

                    for (String ss: solars) {
                        String[] splitted = ss.split(" ");
                        SolarSystem solarSys = new SolarSystem(splitted[0], Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]));
                        if (splitted[0].equals(ssName)) {
                            current = solarSys;
                        }
                        list.add(solarSys);
                    }
                    Universe universe = new Universe(list);
                    Universe.single_instance = universe;

                    ArrayList<String> items = new ArrayList<>();

                    for (DataSnapshot sSystems: dataSnapshot.child("Items").getChildren()) {
                        String s  = (String) sSystems.getKey();
                        items.add(s);
                    }

                    ArrayList<TradeGood> tradeItems = new ArrayList<>();
                    String[] arr = {"Firearms", "Food", "Furs", "Games", "Machines", "Medicine", "Narcotics", "Ore", "Robots", "TradeGood", "Water"};
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





//                    Log.d("NAME SS",ssName);
//                    Log.d("Player Name", player.getName());
//                    if (player != null) {
//                        Player.single_instance = player;
//
//                        ArrayList<SolarSystem> solarSystems = new ArrayList<>();
//                        for (int i = 0; i < 10; i++) {
//                            String splitted =
//                        }

//

                        Intent intent = new Intent(MainActivity.this, PlanetActivity.class);
                        intent.putExtra("PLANET", current);
                        startActivity(intent);
                    }

//

                }

            }
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}

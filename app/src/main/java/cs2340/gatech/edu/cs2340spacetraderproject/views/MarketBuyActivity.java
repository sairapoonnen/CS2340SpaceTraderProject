package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;
import cs2340.gatech.edu.cs2340spacetraderproject.viewmodels.ConfigurationViewModel;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.GameDifficulty;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;

import java.util.*;

public class MarketBuyActivity extends AppCompatActivity {

    private Market market = Market.Market();
    private Player player = Player.Player();
    private SolarSystem ss;

    private ItemAdapter adapter;

    MediaPlayer buttonSound;

    //widgets
    private TextView credits;
    private TextView cargo;
    private TextView cost;
    private Button leave;
    private Button sell;
    private Button Confirm;
    private ConstraintLayout constraintLayout;

    //private int cargo_have;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketbuy);
        ss = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        RecyclerView recyclerView;

         /*
         Set up our recycler view by grabbing the layout for a single item
         */
        recyclerView = findViewById(R.id.item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

        constraintLayout = findViewById(R.id.constraintLayout);
        if (ss.getTech() <= 3) {
            constraintLayout.setBackground(ContextCompat.getDrawable(MarketBuyActivity.this, R.drawable.market2static));
        } else if (ss.getTech() <= 6) {
            constraintLayout.setBackground(ContextCompat.getDrawable(MarketBuyActivity.this, R.drawable.market1static));
        }


        /*
        List<TradeGood> itemList;
        itemList = market.getMarket();
        Log.d("test", itemList.get(4).getName());
        adapter.setItemList(itemList);
        */

        credits = findViewById(R.id.credits_amt);
        cargo = findViewById(R.id.cargo_amt);

        //button sound effect setup
        buttonSound = MediaPlayer.create(this, R.raw.button);

    }


    @Override
    public void onResume() {
        super.onResume();


        Log.d("Null?", "" + player.getCredits());
        credits.setText("" + player.getCredits());
        if (player.getSpaceship().getCargo() == null) {
            cargo.setText("" + 0 + "/" + player.getSpaceship().getCargoBays());
        } else {
            cargo.setText("" + player.getSpaceship().getCargo().size() + "/" +
                    player.getSpaceship().getCargoBays());
        }


        List<TradeGood> itemList = new ArrayList<>();
        itemList = market.getMarket();
        adapter.setItemList(itemList);
        Log.d("allItems", Arrays.toString(itemList.toArray()));


        adapter.setItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(TradeGood item) {

                if ( player.getSpaceship().getCargo().size() ==
                        player.getSpaceship().getCargoBays()) {
                    Toast.makeText(getApplicationContext(),
                            "Cargo hold full. Cannot buy more goods.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MarketBuyActivity.this, EditItemActivity.class);
                    Log.d("itemHashBefore", item.toString());
                    intent.putExtra("BUY", item);
                    startActivity(intent);
                    //startActivityForResult(intent, EDIT_REQUEST);
                }
            }
        });
    }


    public void onLeavePressed(View view) {

        buttonSound.start();

        Intent intent = new Intent(MarketBuyActivity.this, MarketActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);

    }


    public void onSellPressed(View view) {

        buttonSound.start();

        Intent intent = new Intent(MarketBuyActivity.this, MarketSellActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);

    }
}

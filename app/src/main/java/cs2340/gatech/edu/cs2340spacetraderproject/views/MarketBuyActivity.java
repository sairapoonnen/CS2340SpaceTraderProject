package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
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

    private RecyclerView recyclerView;
    private ItemAdapter adapter;

    //widgets
    private TextView credits;
    private TextView cargo;
    private TextView cost;
    private Button leave;
    private Button sell;
    private Button Confirm;

    //private int cargo_have;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketbuy);

         /*
         Set up our recycler view by grabbing the layout for a single item
         */
        recyclerView = findViewById(R.id.item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);


        /*
        List<TradeGood> itemList;
        itemList = market.getMarket();
        Log.d("test", itemList.get(4).getName());
        adapter.setItemList(itemList);
        */

        credits = findViewById(R.id.credits_amt);
        cargo = findViewById(R.id.cargo_amt);

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
        Intent intent = new Intent(MarketBuyActivity.this, TravelActivity.class);
        startActivity(intent);
    }


    public void onSellPressed(View view) {
        Intent intent = new Intent(MarketBuyActivity.this, MarketSellActivity.class);
        startActivity(intent);
    }
}

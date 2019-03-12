package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.arch.lifecycle.ViewModelProviders;
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
import cs2340.gatech.edu.cs2340spacetraderproject.viewmodels.ConfigurationViewModel;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.GameDifficulty;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;

import java.util.*;

public class MarketSellActivity extends AppCompatActivity {

    private ItemAdapter adapter;
    private Player player;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketsell);

         /*
         Set up our recycler view by grabbing the layout for a single item
         */
        RecyclerView recyclerView = findViewById(R.id.item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        List<String> itemList = new ArrayList<>();
        itemList.addAll(player.getSpaceship().getCargo().keySet());
        adapter.setItemList(itemList);

        adapter.setItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(String item) {
                Intent intent = new Intent(MarketSellActivity.this, EditItemActivity.class);
                intent.putExtra("Sell", "sell");
                //startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }

    public void onLeavePressed(View view) {
        Intent intent = new Intent(MarketSellActivity.this, PlanetActivity.class);
        startActivity(intent);
    }

    public void onConfirmPressed(View view) {

    }

    public void onBuyPressed(View view) {
        Intent intent = new Intent(MarketSellActivity.this, MarketBuyActivity.class);
        startActivity(intent);
    }
}

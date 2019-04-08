package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
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
import cs2340.gatech.edu.cs2340spacetraderproject.model.Market;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;
import cs2340.gatech.edu.cs2340spacetraderproject.viewmodels.ConfigurationViewModel;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Spaceship;
import cs2340.gatech.edu.cs2340spacetraderproject.model.GameDifficulty;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;

import java.util.*;

public class EditItemActivity extends AppCompatActivity {

    private Market market = Market.Market();
    private Player player = Player.Player();

    private TradeGood item;
    private List<TradeGood> itemList = market.getMarket();

    private List<TradeGood> cargo = player.getSpaceship().getCargo();

    private TextView itemName;
    private TextView itemPrice;

    private int totalPrice;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edititem);

        itemName = findViewById(R.id.item_name);
        itemPrice = findViewById(R.id.item_price);

        if (getIntent().hasExtra("BUY")) {
            setTitle("Buying Item");
            item = (TradeGood) getIntent().getSerializableExtra("BUY");
            Log.d("itemHashAfter", item.toString());
        } else {
            setTitle("Selling Item");
            item = (TradeGood) getIntent().getSerializableExtra("SELL");
        }

        totalPrice = item.getBasePrice() + (item.getIPL() * (market.getSS().getTech() - item.getMTLP()));
        itemName.setText(item.getName());
        itemPrice.setText("" + totalPrice);

    }

    public void onConfirmedPressed(View view) {
        if (getIntent().hasExtra("BUY")) {

            if (item.getBasePrice() > player.getCredits()) {
                Toast.makeText(getApplicationContext(), "Not enough credits to buy item", Toast.LENGTH_SHORT).show();
            } else {
                /*
                itemList.remove(item);
                Log.d("itemHash", item.toString());

                Log.d("allItemsBuy", Arrays.toString(itemList.toArray()));
                market.setMarket(itemList);
                Log.d("allItemsBuy", Arrays.toString(itemList.toArray()));
                */


                cargo.add(item);

                player.setCredits(player.getCredits() - totalPrice);
            }

        } else {

            if (market.getSS().getTech() < item.getMTLU()) {
                Toast.makeText(getApplicationContext(), "This planet cannot use this resource", Toast.LENGTH_SHORT).show();
            } else {
                itemList.add(item);
                Log.d("itemHash", item.toString());

                Log.d("allItemsSell", Arrays.toString(cargo.toArray()));
                cargo.remove(item);
                Log.d("allItemsSell", Arrays.toString(cargo.toArray()));

                player.setCredits(player.getCredits() + totalPrice);
            }
        }

        finish();
    }

    public void onCancelPressed(View view) {
        onBackPressed();
    }
}

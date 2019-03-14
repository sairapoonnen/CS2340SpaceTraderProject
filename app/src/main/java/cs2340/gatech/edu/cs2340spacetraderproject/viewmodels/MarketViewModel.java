package cs2340.gatech.edu.cs2340spacetraderproject.viewmodels;

import android.arch.lifecycle.ViewModel;

import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;
import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

import java.util.*;

public class MarketViewModel extends ViewModel {

    private Universe universe = Universe.Universe();
    private Player player = Player.Player();

    private SolarSystem ss;

    private List<TradeGood> itemList = new ArrayList<>();
    private List<TradeGood> cargo = player.getSpaceship().getCargo();

    public void setSS(SolarSystem ss) {
        this.ss = ss;
    }

}

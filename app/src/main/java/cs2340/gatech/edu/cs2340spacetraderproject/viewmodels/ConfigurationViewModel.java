package cs2340.gatech.edu.cs2340spacetraderproject.viewmodels;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;

public class ConfigurationViewModel extends ViewModel {
    private Player player;
    private Universe universe;

    public void setPlayer(Player player) { this.player = player; }

    public void setUniverse(Universe universe) { this.universe = universe; }

    public Player getPlayer() { return player; }

    public Universe getUniverse() { return universe; }

}

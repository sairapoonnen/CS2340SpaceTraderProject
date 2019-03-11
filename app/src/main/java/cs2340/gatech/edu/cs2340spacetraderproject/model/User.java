package cs2340.gatech.edu.cs2340spacetraderproject.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import cs2340.gatech.edu.cs2340spacetraderproject.model.Player;
import cs2340.gatech.edu.cs2340spacetraderproject.model.Universe;


@Entity
public class User {
    @PrimaryKey
    private int id;

    @NonNull

    @ColumnInfo
    private Player player;

    @ColumnInfo
    private Universe universe;

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public void setPlayer(Player player) { this.player = player; }

    public Player getPlayer() { return player; }

    public void setUniverse(Universe universe) { this.universe = universe; }

    public Universe getUniverse() { return universe; }



}

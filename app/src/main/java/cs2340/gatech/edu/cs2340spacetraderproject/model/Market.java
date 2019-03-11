package cs2340.gatech.edu.cs2340spacetraderproject.model;

import java.util.HashMap;

import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class Market {

    private HashMap market;

    private static Market single_instance = null;

    private Market() {
        super();
    }

    public static Market Market() {
        if (single_instance == null) {
            single_instance = new Market();
        }

        return single_instance;
    }

    public void setMarket(HashMap market) { this.market = market; }

    public HashMap getMarket() { return market; }
}

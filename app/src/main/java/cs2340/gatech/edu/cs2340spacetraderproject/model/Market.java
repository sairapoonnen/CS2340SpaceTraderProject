package cs2340.gatech.edu.cs2340spacetraderproject.model;

import java.util.HashMap;

import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class Market {

    private HashMap<String, int[]> market;
    private SolarSystem ss;

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

    public void setSS(SolarSystem ss) { this.ss = ss; }

    public HashMap<String, int[]> getMarket() { return market; }

    public SolarSystem getSS() { return ss; }

}

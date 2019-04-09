package cs2340.gatech.edu.cs2340spacetraderproject.model;

import java.util.List;

import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class Market {

    //private HashMap<String, int[]> market;
    private List market;
    private SolarSystem ss;

    private static Market single_instance = null;

    public Market() {
        super();
    }

    public static Market Market() {
        if (single_instance == null) {
            single_instance = new Market();
        }

        return single_instance;
    }

    public void setMarket(List market) { this.market = market; }

    public void setSS(SolarSystem ss) { this.ss = ss; }

    public List<TradeGood> getMarket() { return market; }

    public SolarSystem getSS() { return ss; }

}

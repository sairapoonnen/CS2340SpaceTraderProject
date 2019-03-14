package cs2340.gatech.edu.cs2340spacetraderproject.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class SolarSystem {

    private String name;
    private int x;
    private int y;
    private int tech;
    private int resource;
    private List<TradeGood> market = new ArrayList<>();

    public SolarSystem(String name, int x, int y, int tech, int resource) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.tech = tech; //8 levels, 0-7
        this.resource = resource; //13 types, 0-12
    }

    private String[] techArray = new String[]{"Pre-Agriculture", "Agriculture", "Medieval", "Renaissance",
            "Early Industrial", "Industrial", "Post-Industrial", "High-Tech"};

    private String[] resourceArray = new String[]{"NOSPECIALRESOURCES", "MINERALRICH", "MINERALPOOR",
            "DESERT", "LOTSOFWATER", "RICHSOIL", "POORSOIL", "RICHFAUNA", "LIFELESS",
            "WEIRDMUSHROOMS", "LOTSOFHERBS", "ARTISTIC", "WARLIKE"};

    public void setName(String name) { this.name = name; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public void setTech(int tech) { this.tech = tech; }

    public void setResource(int resource) { this.resource = resource; }

//    public void addMarket(String item, int[] info) { market.put(item, info); }
    public void addMarket(TradeGood item) { market.add(item); }

    public String getName() { return name; }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getTech() { return tech; }

    public String[] getTechArray() { return techArray; }

    public int getResource() {return resource; }

    public String[] getResourceArray() { return resourceArray; }

   // public HashMap getMarket() { return market; }
    public List<TradeGood> getMarket() { return market; }

    public String toString() {
        return "Solar System Name: " + name + ", coordinates: (" + x + ", " + y + "), Tech Level: " + techArray[tech] + ", Resources: " + resourceArray[resource];
    }
}

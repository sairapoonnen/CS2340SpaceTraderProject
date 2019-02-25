package cs2340.gatech.edu.cs2340spacetraderproject.model;

import java.util.*;

public class Universe {

    private ArrayList<SolarSystem> solarSystem;

    public void addSolarSystem(SolarSystem ss) {
        solarSystem.add(ss);
    }

    public ArrayList<SolarSystem> getSolarSystem() { return solarSystem; }

    public Universe() {
        solarSystem = new ArrayList<SolarSystem>();
    }
}

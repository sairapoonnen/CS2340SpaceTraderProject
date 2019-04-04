package cs2340.gatech.edu.cs2340spacetraderproject.model;

import java.util.*;

public class Universe {

    private ArrayList<SolarSystem> solarSystem;

    public void addSolarSystem(SolarSystem ss) {
        solarSystem.add(ss);
    }

    public ArrayList<SolarSystem> getSolarSystem() { return solarSystem; }

    public static Universe single_instance = null;

    private Universe() {
        solarSystem = new ArrayList<SolarSystem>();
    }

    public Universe(ArrayList<SolarSystem> ss) {
        this.solarSystem = ss;
    }

    public static Universe Universe() {
        if (single_instance == null) {
            single_instance = new Universe();
        }

        return single_instance;
    }

    public String toString() {
        String solarSystems = "Universe:\n";
        for (SolarSystem ss: solarSystem) {
            solarSystems += ss.toString() + "\n";
        }

        return solarSystems;
    }
}

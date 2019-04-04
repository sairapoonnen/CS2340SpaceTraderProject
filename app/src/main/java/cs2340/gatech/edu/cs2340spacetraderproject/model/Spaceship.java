package cs2340.gatech.edu.cs2340spacetraderproject.model;

import java.util.ArrayList;
import java.util.List;

import cs2340.gatech.edu.cs2340spacetraderproject.model.tradegoods.TradeGood;

public class Spaceship {
    private String name;
    private int hullStrength;
    private int cargoBays;
    //private HashMap<String, int[]> cargo;
    public List<TradeGood> cargo = new ArrayList<>();
    private int weaponSlots;
    private int gadgetSlots;
    private int shieldSlots;
    private int crewQuarters;
    private int distance;
    private int fuel;

    public Spaceship(String name, int hullStrength, int cargoBays, int weaponSlots, int gadgetSlots, int shieldSlots, int crewQuarters, int distance, int fuel) {
        this.name = name;
        this.hullStrength = hullStrength;
        this.cargoBays = cargoBays;
        this.weaponSlots = weaponSlots;
        this.gadgetSlots = gadgetSlots;
        this.shieldSlots = shieldSlots;
        this.crewQuarters = crewQuarters;
        this.distance = distance;
        this.fuel = fuel;
    }

    public Spaceship() {

    }

    public String getName() { return name; }

    public int getHullStrength() {
        return hullStrength;
    }

    public int getCargoBays() {
        return cargoBays;
    }

    public int getWeaponSlots() {
        return weaponSlots;
    }

    public int getGadgetSlots() {
        return gadgetSlots;
    }

    public int getShieldSlots() {
        return shieldSlots;
    }

    public int getCrewQuarters() {
        return crewQuarters;
    }

    public int getDistance() {
        return distance;
    }

    public int getFuel() { return fuel; }

    //public HashMap<String, int[]> getCargo() { return cargo; }

    //public void setCargo(HashMap cargo) { this.cargo = cargo; }
    public List<TradeGood> getCargo() { return cargo; }

    public void addCargo(TradeGood item) { cargo.add(item);}

    public void setCargoEmpty() {
        cargo = null;
    }

    public void setCargoBays(int cargoBays) {
        this.cargoBays = cargoBays;
    }

    public void setFuel(int fuel) { this.fuel = fuel; }

    public int calculateDistance (int x1, int y1, int x2, int y2) {

        double inside = Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);

        double sqrt = Math.sqrt(inside);

        return (int) Math.abs(sqrt);

    }
}

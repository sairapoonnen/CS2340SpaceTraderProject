package cs2340.gatech.edu.cs2340spacetraderproject.model;

import java.util.HashMap;

public class Spaceship {
    private String name;
    private int hullStrength;
    private int cargoBays;
    private HashMap<String, int[]> cargo;
    private int weaponSlots;
    private int gadgetSlots;
    private int shieldSlots;
    private int crewQuarters;
    private int distance;

    public Spaceship(String name, int hullStrength, int cargoBays, int weaponSlots, int gadgetSlots, int shieldSlots, int crewQuarters, int distance) {
        this.name = name;
        this.hullStrength = hullStrength;
        this.cargoBays = cargoBays;
        this.weaponSlots = weaponSlots;
        this.gadgetSlots = gadgetSlots;
        this.shieldSlots = shieldSlots;
        this.crewQuarters = crewQuarters;
        this.distance = distance;
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

    public HashMap<String, int[]> getCargo() { return cargo; }

    public void setCargo(HashMap cargo) { this.cargo = cargo; }

    public void setCargoBays(int cargoBays) {
        this.cargoBays = cargoBays;
    }
}

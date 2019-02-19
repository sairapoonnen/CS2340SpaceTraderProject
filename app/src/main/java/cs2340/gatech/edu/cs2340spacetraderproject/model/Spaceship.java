package cs2340.gatech.edu.cs2340spacetraderproject.model;

public class Spaceship {
    private int hullStrength;
    private int cargoBays;
    private int weaponSlots;
    private int gadgetSlots;
    private int shieldSlots;
    private int crewQuarters;
    private int distance;

    public Spaceship(int hullStrength, int cargoBays, int weaponSlots, int gadgetSlots, int shieldSlots, int crewQuarters, int distance) {
        this.hullStrength = hullStrength;
        this.cargoBays = cargoBays;
        this.weaponSlots = weaponSlots;
        this.gadgetSlots = gadgetSlots;
        this.shieldSlots = shieldSlots;
        this.crewQuarters = crewQuarters;
        this.distance = distance;
    }

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

    public void setCargoBays(int cargoBays) {
        this.cargoBays = cargoBays;
    }
}

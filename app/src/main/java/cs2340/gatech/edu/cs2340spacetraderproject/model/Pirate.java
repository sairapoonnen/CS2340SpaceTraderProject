package cs2340.gatech.edu.cs2340spacetraderproject.model;

public class Pirate {
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private int credits;
    private Spaceship spaceship;

    public Pirate(int pilotSkill, int fighterSkill, int traderSkill, int engineerSkill, int credits, Spaceship spaceship) {
        this.pilotSkill = pilotSkill;
        this.fighterSkill = fighterSkill;
        this.traderSkill = traderSkill;
        this.engineerSkill = engineerSkill;
        this.credits = credits;
        this.spaceship = spaceship;
    }
    public int getPilotSkill() { return this.pilotSkill; }

    public int getFighterSkill() {
        return this.fighterSkill;
    }

    public int getTraderSkill() { return this.traderSkill; }

    public int getEngineerSkill() {
        return this.engineerSkill;
    }

    public int getCredits() {
        return this.credits;
    }

    public Spaceship getSpaceship() {
        return this.spaceship;
    }

    public void setPilotSkill(int pilotSkill) { this.pilotSkill = pilotSkill; }

    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }

    public void setTraderSkill(int traderSkill) {
        this.traderSkill = traderSkill;
    }

    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

}

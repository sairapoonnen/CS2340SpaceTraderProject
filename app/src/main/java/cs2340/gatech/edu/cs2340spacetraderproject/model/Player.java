package cs2340.gatech.edu.cs2340spacetraderproject.model;

public class Player {

    private String name;
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private int credits;
    private Spaceship spaceship;
    private String gameDifficulty;
    private SolarSystem ss;


    public Player(String name, int pilotSkill, int fighterSkill, int traderSkill, int engineerSkill, String gameDifficulty) {
        this.name = name;
        this.pilotSkill = pilotSkill;
        this.fighterSkill = fighterSkill;
        this.traderSkill = traderSkill;
        this.engineerSkill = engineerSkill;
        this.credits = 1000;
        this.spaceship = new Gnat();
        this.gameDifficulty = gameDifficulty;
    }

    public static Player single_instance = null;

    public Player() {
//        this.name = "";
//        this.pilotSkill = 0;
//        this.fighterSkill = 0;
//        this.traderSkill = 0;
//        this.engineerSkill = 0;
//        this.credits = 1000;
//        this.spaceship = new Gnat();
//        this.gameDifficulty = null;
    }

    public static Player Player() {
        if (single_instance == null) {
            single_instance = new Player();
        }

        return single_instance;
    }




    public String getName() { return this.name; }

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

    public String getGameDifficulty() {
        return this.gameDifficulty;
    }

    public SolarSystem getSS() { return this.ss; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

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

    public void setGameDifficulty(String gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public void setSS(SolarSystem ss) { this.ss = ss; }

}

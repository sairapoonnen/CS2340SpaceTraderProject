package cs2340.gatech.edu.cs2340spacetraderproject.model;

public enum GameDifficulty {
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    private String difficulty;

    public String getDifficulty() { return this.difficulty; }

    private GameDifficulty(String difficulty) { this.difficulty = difficulty; }

}

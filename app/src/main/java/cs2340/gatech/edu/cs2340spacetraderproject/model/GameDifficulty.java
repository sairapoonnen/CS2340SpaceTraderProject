package cs2340.gatech.edu.cs2340spacetraderproject.model;

public enum GameDifficulty {
    BEGINNER("BEGINNER"),
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD"),
    IMPOSSIBLE("IMPOSSIBLE");

    private String difficulty;

    public String toString() { return this.difficulty; }

    private GameDifficulty(String difficulty) { this.difficulty = difficulty; }

}

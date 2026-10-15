package model;

import java.time.LocalDate;

public class HighScoreEntry {

    String playerName;
    int score;
    LocalDate date;

    public HighScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
        this.date = LocalDate.now();
    }
    public HighScoreEntry(String playerName, int score, LocalDate date) {
        this.playerName = playerName;
        this.score = score;
        this.date = date;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public LocalDate getDate() {
        return date;
    }
}

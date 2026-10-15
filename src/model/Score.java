package model;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Score {
    int score;
    int highscore;
    int count;
    HighScoreEntry[] highScoreList = new HighScoreEntry[10];
    //ArrayList<HighScoreEntry> highScoreList = new ArrayList<>();

    private static final String HIGH_SCORES_FILE = "highscores.txt";


    public Score() {
        this.score = 0;
        loadHighScores();

    }

    public int getScore() {
        return score;
    }
    public int getCount() {
        return count;
    }

    public void addScore(int numberGroupTiles){

        //formula was 5n² − 15n + 30
        score += (5 *(numberGroupTiles*numberGroupTiles)) - ( 15*numberGroupTiles) + 30;


    }
    public void printScore(int score){
        System.out.println("Score: "+ score);

    }

    public void resetScore(){
        this.score = 0;
    }

    // highscor epart


    //function, add score to to the higshcor elist, in order, where it hsould be in place

    public void addToHighScoreList(String name){
        HighScoreEntry entry = new HighScoreEntry(name, this.score);

        //insert to list
        if (count < highScoreList.length) {
            highScoreList[count] = entry;
            count++;
        } else {

            highScoreList[count - 1] = entry;
        }

        // SORTING
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (highScoreList[j].score < highScoreList[j + 1].score) {
                    HighScoreEntry temp = highScoreList[j];
                    highScoreList[j] = highScoreList[j + 1];
                    highScoreList[j + 1] = temp;
                }
            }
        }


        saveHighScores();

    }
    public boolean isNewHighScore() {
        // list not full yet, just add
        if (count < highScoreList.length) {
            //addToHighScoreList("Alvaro");
            return true;
        }

        // list is full, check last element
        int scoreLastElement = highScoreList[count - 1].getScore();
        if (score >= scoreLastElement) {
            //addToHighScoreList("Alvaro");
            return true;
        } else {
            return false;
        }
    }

    public HighScoreEntry[] getHighScoreList() {
        return highScoreList;
    }

    public String getHighScoreListString() {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += highScoreList[i].getPlayerName() + "\t\t"
                    +  highScoreList[i].getScore()      + "\t\t"
                    +  highScoreList[i].getDate()       + "\n";
        }
        return result;
    }

    public String highScoreLogic(String name){
        if (isNewHighScore()) {
            addToHighScoreList(name);
            return getHighScoreListString();
        }
        return getHighScoreListString();
    }


    private void loadHighScores() {
        try {
            if (!Files.exists(Paths.get(HIGH_SCORES_FILE))) return;

            List<String> lines = Files.readAllLines(Paths.get(HIGH_SCORES_FILE));
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length != 3) continue;

                String name = parts[0];
                int scoreValue = Integer.parseInt(parts[1]);
                LocalDate date = LocalDate.parse(parts[2]);

                if (count < highScoreList.length) {
                    highScoreList[count++] = new HighScoreEntry(name, scoreValue, date);
                }
            }
        } catch (Exception e) {
            System.err.println("Could not load high scores: " + e.getMessage());
        }
    }

    private void saveHighScores() {
        try {
            List<String> lines = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                HighScoreEntry e = highScoreList[i];
                lines.add(e.getPlayerName() + "|" + e.getScore() + "|" + e.getDate());
            }
            Files.write(Paths.get(HIGH_SCORES_FILE), lines);
        } catch (IOException e) {
            System.err.println("Could not save high scores: " + e.getMessage());
        }
    }



}

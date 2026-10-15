package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class GameScreenView extends JPanel {



    private static final long serialVersionUID = 1L;
    private ScoreView scoreView;
    private ButtonView buttonView;
    private BoardView boardView;
    private TimeView timeView;

    private JButton pauseGame;
    private int numColors;

    private int score;

    // Setting defaul to 8x8

    int rows = 8, cols = 8;


    ActionListener newGameListener;
    ActionListener tileTouchedListener;
    ActionListener TimetickListener;
    ActionListener TimeUpListener;




    public GameScreenView(int rows, int cols, int minMatches, int numCol,
                          ActionListener newGameListener,
                          ActionListener tileTouched,
                          ActionListener TimetickListener,
                          ActionListener hintButtonListener,
                          ActionListener TimeUpListener,
                          ActionListener pasuGameButtonListener) throws HeadlessException {


        this.rows = rows;
        this.cols = cols;

        this.newGameListener = newGameListener;
        this.tileTouchedListener = tileTouched;
        this.TimetickListener = TimetickListener;
        this.TimeUpListener = TimeUpListener;

        this.numColors = numCol;


        setLayout(new BorderLayout());

        scoreView = new ScoreView();
        timeView = new TimeView(TimetickListener, TimeUpListener);

        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        //topPanel.add(new JPanel()); // left spacer mirrors timeView width


        // Bottom: buttons + dropdown
        buttonView = new ButtonView(hintButtonListener);
        add(buttonView, BorderLayout.SOUTH);

        // Center: the board
        boardView = new BoardView(rows, cols, tileTouchedListener, minMatches, numColors);
        add(boardView, BorderLayout.CENTER);



        //Pause game button
        pauseGame = new JButton();
        pauseGame.setText("Pause Game");
        pauseGame.addActionListener(pasuGameButtonListener);


        topPanel.add(pauseGame);
        topPanel.add(scoreView);
        topPanel.add(timeView);
        add(topPanel, BorderLayout.NORTH);

    }

    // Delegate to boardView
    public boolean isMoveAvailable() {
        return boardView.isMoveAvailable();
        //DONEEEEEE
        // TODO you need to delegate this behavior to the boardView
    }

    // Call this method to start a new Game

    public void newGame(int minMatches, int numColors, int boardSize, boolean timerEnabled) {
        remove(boardView);
        scoreView.resetScore();

        this.rows = boardSize;
        this.cols = boardSize;
        this.numColors = numColors;

        boardView = new BoardView(rows, cols, tileTouchedListener, minMatches, numColors);
        add(boardView, BorderLayout.CENTER);

        revalidate();
        repaint();

        timeView.timeReset();
        if (timerEnabled) {
            timeView.timeStart();
            timeView.setVisible(true);
        } else {
            timeView.setVisible(false);
        }

        System.out.println(boardView);
    }

    public void processTouchedTile(TileView tv) {
        //DONE?
        // TODO
        // You need to implement this method. It is called when a tileview is touched
        System.out.println("GameView == processing tile touch");

        //Is this it?
        int nMatches = boardView.processTouchedTile(tv);
        scoreView.updateScore(nMatches);

        //System.out.println(boardView); // debug

    }

    public void timeTick(){
        timeView.timeUpdate();
    }



    public void setMinMatches(int m) {
        boardView.setMinMatches(m);
    }
    public void setNumColors(int num){
        boardView.setNumColors(num);
    }


    public int getCurrentScore() {
        return scoreView.getScore();
    }
    public void stopTime(){
        timeView.timeStop();
    }

    public String getHighScoresList(){
        return scoreView.getHighScoreList();
    }

    public String highScoreLogic(String name){
        return scoreView.highScoreLogic(name);
    }
    public boolean isNewHighScore(){
        return scoreView.isNewHighScore();
    }

    public void timeStop(){
        timeView.timeStop();
    }
    public void timeStart(){
        timeView.timeStart();
    }

    public void giveHint() {
        String hint = boardView.findHintGroup();

        if (hint == null) {
            JOptionPane.showMessageDialog(this, "No hints available — no valid moves left!");
            return;
        }

        JOptionPane.showMessageDialog(this, "Group at: " + hint, "Hint", JOptionPane.INFORMATION_MESSAGE);
    }


}













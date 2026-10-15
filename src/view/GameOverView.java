package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class GameOverView extends JPanel {
    private JLabel gameOverText;
    private JLabel gameOverReason;
    private JLabel gameOverScore;
    private JLabel gameOverHighscoretext;

    private JButton goToStartMenuButton;
    private JButton newGameButton;

    public GameOverView(ActionListener newGameButtonListener, ActionListener goToStartMenuButtonListener) {

        setLayout(new BorderLayout());

        gameOverText = new JLabel("GAME OVER!", SwingConstants.CENTER);
        gameOverText.setFont(new Font("SansSerif", Font.BOLD, 36));
        gameOverText.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(gameOverText, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        gameOverReason = new JLabel("", SwingConstants.CENTER);
        gameOverReason.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gameOverReason.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameOverScore = new JLabel("", SwingConstants.CENTER);
        gameOverScore.setFont(new Font("SansSerif", Font.BOLD, 18));
        gameOverScore.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameOverHighscoretext = new JLabel("", SwingConstants.CENTER);
        gameOverHighscoretext.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gameOverHighscoretext.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(gameOverReason);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(gameOverScore);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(gameOverHighscoretext);

        add(centerPanel, BorderLayout.CENTER);

        newGameButton = new JButton("Play Again");
        newGameButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        newGameButton.addActionListener(newGameButtonListener);

        goToStartMenuButton = new JButton("Start Menu");
        goToStartMenuButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        goToStartMenuButton.addActionListener(goToStartMenuButtonListener);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonsPanel.add(goToStartMenuButton);
        buttonsPanel.add(newGameButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void setFinalScore(int score) {
        gameOverScore.setText("Your score was: " + score + " points");
    }

    public void reasonGameOver(boolean noMovesAvailable) {
        if (noMovesAvailable) {
            gameOverReason.setText("You ran out of possible movements");
        } else {
            gameOverReason.setText("You ran out of time");
        }
    }

    public void highScoresList(String highScoresList) {
        System.out.println("The high scores list is: \n" + highScoresList);

        String html = "<html>"
                + highScoresList.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;")
                .replace("\n", "<br>")
                + "</html>";
        gameOverHighscoretext.setText(html);
    }

    public String getHighScoreName() {
        String gmOvername = JOptionPane.showInputDialog(this, "New high score! Enter your name:");
        if (gmOvername == null || gmOvername.trim().isEmpty()) {
            return "N/A";
        }
        return gmOvername.trim();
    }
}
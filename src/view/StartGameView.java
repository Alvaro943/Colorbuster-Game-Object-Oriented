package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartGameView extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton startGameButton;
    private JButton instructionsButton;
    private JButton exitGameButton;
    private JLabel gameName;
    private JLabel editorNameAlvaro;

    public StartGameView(ActionListener startGameButtonListener,
                         ActionListener exitGameButtonListener,
                         ActionListener instructionsButtonListener) {

        setLayout(new BorderLayout());

        gameName = new JLabel("COLOR BUSTER", SwingConstants.CENTER);
        gameName.setFont(new Font("SansSerif", Font.BOLD, 44));
        gameName.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        add(gameName, BorderLayout.NORTH);

        startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGameButton.setMaximumSize(new Dimension(200, 45));
        startGameButton.addActionListener(startGameButtonListener);

        instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        instructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsButton.setMaximumSize(new Dimension(200, 45));
        instructionsButton.addActionListener(instructionsButtonListener);

        exitGameButton = new JButton("Exit Game");
        exitGameButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        exitGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitGameButton.setMaximumSize(new Dimension(200, 45));
        exitGameButton.addActionListener(exitGameButtonListener);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(Box.createVerticalGlue());
        buttonsPanel.add(startGameButton);
        buttonsPanel.add(Box.createVerticalStrut(15));
        buttonsPanel.add(instructionsButton);
        buttonsPanel.add(Box.createVerticalStrut(15));
        buttonsPanel.add(exitGameButton);
        buttonsPanel.add(Box.createVerticalGlue());
        add(buttonsPanel, BorderLayout.CENTER);

        editorNameAlvaro = new JLabel("Alvaro Garcia Arbaizar", SwingConstants.CENTER);
        editorNameAlvaro.setFont(new Font("SansSerif", Font.PLAIN, 14));
        editorNameAlvaro.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(editorNameAlvaro, BorderLayout.SOUTH);
    }

    public void instructionsText() {
        String instructions =
                "COLOR BUSTER\n\n" +
                        "Match groups of same-colored tiles to score points.\n\n" +
                        "HOW TO PLAY:\n" +
                        "• Click any tile that has same-colored neighbors (up, down, left, or right).\n" +
                        "• All connected tiles of that color are removed.\n" +
                        "• Tiles above will fall down to fill the gaps.\n" +
                        "• New random tiles appear at the top.\n\n" +
                        "SCORING:\n" +
                        "• Bigger groups = more points.\n\n" +
                        "GAME SETTINGS:\n" +
                        "• Minimum matches: smallest group size that counts as a valid move.\n" +
                        "• Number of colors: how many different tile colors appear.\n" +
                        "• Board size: dimensions of the playing field.\n" +
                        "• Timer: race against 60 seconds, or play untimed.\n\n" +
                        "GAME ENDS WHEN:\n" +
                        "• You run out of valid moves, or\n" +
                        "• The timer reaches zero (if enabled).\n\n" +
                        "Beat your high score!";

        JOptionPane.showMessageDialog(this, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }
}
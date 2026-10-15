package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PauseGameView extends JPanel {

    private JLabel pauseGameText;
    private JButton goToStartMenuButton;
    private JButton newGameButton;
    private JButton resumeGameButton;

    public PauseGameView(ActionListener newGameButtonListener,
                         ActionListener goToStartMenuButtonListener,
                         ActionListener resumeGameButtonListener) {

        setLayout(new BorderLayout());

        pauseGameText = new JLabel("Pause Menu", SwingConstants.CENTER);
        pauseGameText.setFont(new Font("SansSerif", Font.BOLD, 36));
        add(pauseGameText, BorderLayout.NORTH);

        resumeGameButton = new JButton("Resume Game");
        resumeGameButton.addActionListener(resumeGameButtonListener);

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(newGameButtonListener);

        goToStartMenuButton = new JButton("Start Menu");
        goToStartMenuButton.addActionListener(goToStartMenuButtonListener);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(resumeGameButton);
        buttonsPanel.add(newGameButton);
        buttonsPanel.add(goToStartMenuButton);
        add(buttonsPanel, BorderLayout.CENTER);
    }
}
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DesignGameView extends JPanel {

    private JLabel designGameText;

    private JButton goToStartMenuButton;
    private JButton newGameButton;
    private JComboBox numColorsSelector;
    private JComboBox sizeBoardSelector;
    private JCheckBox timerEnabledCheckBox;

    private JLabel numColorsLabel;
    private JLabel boardSizeLabel;
    private JLabel timerLabel;


    public DesignGameView(ActionListener numColorsComboBoxListener,
                          ActionListener sizeBoardSelectorListener,
                          ActionListener timerEnabledListener,
                          ActionListener  newGameButtonListener,
                          ActionListener goToStartMenuButtonListener) {

        setLayout(new BorderLayout());

        designGameText = new JLabel("Game Settings", SwingConstants.CENTER);
        designGameText.setFont(new Font("SansSerif", Font.BOLD, 36));
        designGameText.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(designGameText, BorderLayout.NORTH);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        numColorsLabel = new JLabel("Number of colors: ");
        numColorsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        numColorsSelector = new JComboBox();
        numColorsSelector.addItem("2");
        numColorsSelector.addItem("3");
        numColorsSelector.addItem("4");
        numColorsSelector.addItem("5");
        numColorsSelector.setSelectedItem("5");
        numColorsSelector.setFont(new Font("SansSerif", Font.PLAIN, 16));
        numColorsSelector.addActionListener(numColorsComboBoxListener);

        JPanel colorsRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        colorsRow.add(numColorsLabel);
        colorsRow.add(numColorsSelector);
        settingsPanel.add(colorsRow);

        boardSizeLabel = new JLabel("Size of board: ");
        boardSizeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        sizeBoardSelector = new JComboBox();
        sizeBoardSelector.addItem("6");
        sizeBoardSelector.addItem("8");
        sizeBoardSelector.addItem("12");
        sizeBoardSelector.setSelectedItem("6");
        sizeBoardSelector.setFont(new Font("SansSerif", Font.PLAIN, 16));
        sizeBoardSelector.addActionListener(sizeBoardSelectorListener);

        JPanel sizeRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        sizeRow.add(boardSizeLabel);
        sizeRow.add(sizeBoardSelector);
        settingsPanel.add(sizeRow);

        timerLabel = new JLabel("Timer: ");
        timerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        timerEnabledCheckBox = new JCheckBox("Enabled");
        timerEnabledCheckBox.setSelected(true);
        timerEnabledCheckBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        timerEnabledCheckBox.addActionListener(timerEnabledListener);

        JPanel timerRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        timerRow.add(timerLabel);
        timerRow.add(timerEnabledCheckBox);
        settingsPanel.add(timerRow);

        add(settingsPanel, BorderLayout.CENTER);

        newGameButton = new JButton("Start Game");
        newGameButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        newGameButton.addActionListener(newGameButtonListener);

        goToStartMenuButton = new JButton("Back to Menu");
        goToStartMenuButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        goToStartMenuButton.addActionListener(goToStartMenuButtonListener);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonsPanel.add(goToStartMenuButton);
        buttonsPanel.add(newGameButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    // Getters so the Controller can read the chosen settings when the user clicks Start
    public int getNumColors() {
        return Integer.parseInt((String) numColorsSelector.getSelectedItem());
    }

    public int getBoardSize() {
        return Integer.parseInt((String) sizeBoardSelector.getSelectedItem());
    }

    public boolean isTimerEnabled() {
        return timerEnabledCheckBox.isSelected();
    }
}
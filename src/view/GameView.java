/**
 *
 */
package view;
import javax.swing.*;
import java.awt.*;

//import view.ScoreView;
//import view.ButtonView;
import controller.GameController;

import java.awt.event.*;

/**
 * @author Frank J. Mitropoulos
 *
 */
public class GameView extends JFrame {
	// Create the HUD Panel
	// Create the Board
	private static GameView instance;

	public enum GameState { START, DESIGN, PLAYING, PAUSED, GAME_OVER }

	private GameState state;
	private JPanel currentPanel;


	private GameScreenView gameScreenView;
	private StartGameView startGameView;
	private GameOverView gameOverView;
	private PauseGameView pauseGameView;
	private DesignGameView designGameView;


	/**
	 * @param title
	 * @param
	 * @throws HeadlessException
	 */


	private GameView(String title, int rows, int cols, int minMatches, int numColors,
	                ActionListener newGameListener,
	                ActionListener tileTouched,
	                ActionListener timetickListener,
	                ActionListener hintButtonListener,
	                ActionListener timeUpListener,
	                ActionListener startGameButtonListener,
					ActionListener exitGameButtonListener,
					ActionListener instructionsButtonListener,
					ActionListener goToStartMenuButtonListener,
					ActionListener pasuGameButtonListener,
					ActionListener resumeGameButtonListener,
					ActionListener numColorsComboBoxListener,
					ActionListener sizeBoardSelectorListener,
					ActionListener timerEnabledListener) throws HeadlessException {
		super(title);
		setResizable(false);

		setBounds(800, 300, 400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		startGameView = new StartGameView(startGameButtonListener, exitGameButtonListener, instructionsButtonListener);
		designGameView = new DesignGameView(numColorsComboBoxListener, sizeBoardSelectorListener, timerEnabledListener, newGameListener, goToStartMenuButtonListener );
		gameScreenView = new GameScreenView(rows, cols, minMatches, numColors,
				newGameListener, tileTouched, timetickListener,
				hintButtonListener, timeUpListener, pasuGameButtonListener);
		gameOverView = new GameOverView(newGameListener, goToStartMenuButtonListener);
		pauseGameView = new PauseGameView(newGameListener, goToStartMenuButtonListener, resumeGameButtonListener);


		setState(GameState.START);
		setVisible(true);




	}
	public static GameView getInstance(String title, int rows, int cols, int minMatches, int numColors,
	                                   ActionListener newGameListener,
	                                   ActionListener tileTouched,
	                                   ActionListener timetickListener,
	                                   ActionListener hintButtonListener,
	                                   ActionListener timeUpListener,
	                                   ActionListener startGameButtonListener,
	                                   ActionListener exitGameButtonListener,
	                                   ActionListener instructionsButtonListener,
	                                   ActionListener goToStartMenuButtonListener,
	                                   ActionListener pasuGameButtonListener,
	                                   ActionListener resumeGameButtonListener,
	                                   ActionListener numColorsComboBoxListener,
	                                   ActionListener sizeBoardSelectorListener,
	                                   ActionListener timerEnabledListener) {
		if (instance == null) {
			instance = new GameView(title, rows, cols, minMatches, numColors,
					newGameListener, tileTouched, timetickListener,
					hintButtonListener, timeUpListener,
					startGameButtonListener, exitGameButtonListener,
					instructionsButtonListener, goToStartMenuButtonListener,
					pasuGameButtonListener, resumeGameButtonListener,
					numColorsComboBoxListener, sizeBoardSelectorListener,
					timerEnabledListener);
		}
		return instance;
	}


	public void setState(GameState newState) {
		state = newState;
		if (currentPanel != null) remove(currentPanel);

		switch (state) {
			case START:      currentPanel = startGameView; break;
			case DESIGN:	currentPanel = designGameView; break;
			case PLAYING:   currentPanel = gameScreenView; break;
			case PAUSED:    currentPanel = pauseGameView; break;
			case GAME_OVER: currentPanel = gameOverView; break;

		}

		add(currentPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}


	public boolean isMoveAvailable() { return gameScreenView.isMoveAvailable(); }
	public void timeTick() { gameScreenView.timeTick(); }
	public void processTouchedTile(TileView tv) { gameScreenView.processTouchedTile(tv); }


	public void newGame(int minMatches, int numColors, int boardSize, boolean timerEnabled) {
		gameScreenView.newGame(minMatches, numColors, boardSize, timerEnabled);
		setState(GameState.PLAYING);
	}

	public void endGame(boolean noMovesAvailable) {
		gameScreenView.stopTime();
		int finalScore = gameScreenView.getCurrentScore();
		gameOverView.setFinalScore(finalScore);
		gameOverView.reasonGameOver(noMovesAvailable);
		setState(GameState.GAME_OVER);

		String highScList;
		if (gameScreenView.isNewHighScore()) {
			// New high score → prompt for name, add it, get updated list
			String name = gameOverView.getHighScoreName();
			highScList = gameScreenView.highScoreLogic(name);
		} else {
			// Not a high score → just show the existing list, no prompt
			highScList = gameScreenView.getHighScoresList();
		}

		gameOverView.highScoresList(highScList);





	}
	public void setMinMatches(int m) { gameScreenView.setMinMatches(m); }
	public void setNumColors(int num){
		gameScreenView.setNumColors(num);
	}
	public void exitGame() {
		System.exit(0);
	}
	public void instructionsText(){
		startGameView.instructionsText();
	}

	public void pauseGame(){
		gameScreenView.timeStop();
	}
	public void resumeGame(){
		gameScreenView.timeStart();
	}

	public void giveHint(){
		gameScreenView.giveHint();
	}








}

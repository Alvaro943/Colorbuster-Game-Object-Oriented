/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.GameView;
import view.TileView;

/**
 * @author Frank J. Mitropoulos
 *
 */
public class GameController {
	// These aren't used, but could be depending on your gme and what you want to do
	private int gameStatus;
	private int rows;
	private int cols;
	
	private int moveNumber = 0;

	//This is hardcoded as the default
	private int minMatches = 3;
	private int numColors = 5;
	private int boardSize = 6;
	private boolean timerEnabled = true;


	private GameView gameView;

	private static GameController instance;


	/**
	 * Create the GameView and pass in the appropriate listeners
	 */
	private GameController() {
		super();		
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
			
				gameView = GameView.getInstance("ColorBuster Alvaro",6,6, minMatches, numColors,
						new NewGameListener(),
						new TileTouchedListener(),
						new TimetickListener(),
						new hintButtonListener(),
						new TimeUpListener(),
						new startGameButtonListener(),
				        new exitGameButtonListener(),
						new instructionsButtonListener(),
						new goToStartMenuButtonListener(),
						new pasuGameButtonListener(),
						new resumeGameButtonListener(),
						new numColorsComboBoxListener(),
						new sizeBoardSelectorListener(),
						new timerEnabledListener());
				gameView.setVisible(true);	
				
			}
		});

		
	}
	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	public void startGame(){

		gameView.newGame(minMatches, numColors, boardSize, timerEnabled);
		//gameView.setState(GameView.GameState.PLAYING);

	}
	public void endGame(boolean noMovesAvailable){
		gameView.endGame(noMovesAvailable);
		//gameView.setState(GameView.GameState.GAME_OVER);

	}
	
	// Listener used to process touches on TileView
	
	class TileTouchedListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			TileView tv = (TileView) event.getSource();
			System.out.println("Tile touched..." + tv.toString());

			//DONEEEEEE
			// TODO -- you implement this
			// delegate to GameView!
			System.out.println("Delegating to gameview");
			gameView.processTouchedTile(tv);

			// If no move is available display a message
			
			if (!gameView.isMoveAvailable()) {
				//gameView.highScoreManager();
				//gameView.setState(GameView.GameState.GAME_OVER);
				endGame(true);

//				JOptionPane.showMessageDialog(gameView,
//					    "No more moves...");
			}
		}
		
	}
	
	// Listener used to process click on New Game Button
	
	class NewGameListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			System.out.println("Starting new game...");

			//DONEEEEEEE
			// TODO
			// You implement this method. Delegate to GameView!

			//gameView.newGame(minMatches);
			//gameView.setMinMatches(minMatches);
			startGame();

		}
		
	}
	class TimetickListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
		//Time changes
			gameView.timeTick();


		}
	}
	class TimeUpListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {

			//gameView.setState(GameView.GameState.GAME_OVER);

			endGame(false);
			//JOptionPane.showMessageDialog(gameView, "Time Up!");

		}
	}
	class hintButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {

		gameView.giveHint();

		}
	}
	class startGameButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {

//			System.out.println("Game goes frm start menu to playing");
//			startGame();
			gameView.setState(GameView.GameState.DESIGN);

		}
	}
	class exitGameButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			gameView.exitGame();
		}
	}
	class instructionsButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {

			gameView.instructionsText();


		}
	}
	class goToStartMenuButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {

			//MAYBE ASK THIS
			gameView.setState(GameView.GameState.START);


		}
	}
	class pasuGameButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			gameView.pauseGame();
			gameView.setState(GameView.GameState.PAUSED);


		}
	}
	class resumeGameButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			gameView.resumeGame();
			gameView.setState(GameView.GameState.PLAYING);


		}
	}
	class numColorsComboBoxListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			JComboBox<?> cb = (JComboBox<?>) event.getSource();
			numColors = Integer.parseInt((String) cb.getSelectedItem());
			System.out.println("Number of colors of the tiles is set to " + numColors);

			//gameView.setNumColors(numColors);


		}
	}
	class sizeBoardSelectorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			JComboBox<?> cb = (JComboBox<?>) event.getSource();
			boardSize = Integer.parseInt((String) cb.getSelectedItem());
			System.out.println("Board size set to " + boardSize);




		}
	}
	class timerEnabledListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			JCheckBox cb = (JCheckBox) event.getSource();
			timerEnabled = cb.isSelected();
			System.out.println("Timer enabled: " + timerEnabled);


		}
	}


}

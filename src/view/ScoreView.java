/**
 * 
 */
package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import model.Score;



/**
 * @author Frank J. Mitropoulos
 * 
 *  A very simple score panel
 *  call updateScore and pass in the score to update the display
 */
public class ScoreView extends JPanel {


	private static final long serialVersionUID = 1L;

	private Score score;
	private JLabel scoreLabel;
	
	public ScoreView() {
		score = new Score();
		//String sc = Integer.toString(score.getScore());
		scoreLabel = new JLabel("Score: "+ score.getScore());
		add(scoreLabel);

	}
	public int getScore() {
		return score.getScore();
	}
	
	public void updateScore(int nMatches) {

		score.addScore(nMatches);
		displayScore();
	}
	public void resetScore(){
		score.resetScore();
		displayScore();
	}
	public void displayScore() {
		String sc = Integer.toString(score.getScore());
		scoreLabel.setText("Score: " + sc);

	}

	public String getHighScoreList(){
		return score.getHighScoreListString();

	}
	public String highScoreLogic(String name){
		return score.highScoreLogic(name);
	}

	public boolean isNewHighScore(){
		return score.isNewHighScore();
	}
}

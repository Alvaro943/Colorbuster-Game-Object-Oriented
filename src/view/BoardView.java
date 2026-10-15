package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JPanel;

import model.Board;
import model.Tile;

public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;
	private Board b;
	private int rows;
	private int cols;
	private int tileSize;
	private ActionListener listener;
	private int spacer;

	private int minMatches = 3;
	private int numColors;

	private TileView[][] tileGrid;

	public BoardView(int rows, int cols, ActionListener lis, int minMatches, int numColors) {
		spacer = 4;
		listener = lis;
		Dimension s = getPreferredSize();
		System.out.println("BoardView: " + s);
		this.rows = rows;
		this.cols = cols;

		int totalSpace = (cols + 1) * spacer;
		System.out.println("totalspace : " + totalSpace);

		tileSize = (s.width - totalSpace) / cols;
		System.out.println("TileSize : " + tileSize);

		setLayout(null);
		tileGrid = new TileView[rows][cols];
		b = new Board(rows, cols, minMatches, numColors);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				TileView tv = new TileView(b.tileAt(row, col));
				add(tv);
				tileGrid[row][col] = tv;

				tv.setBounds((tileSize * col + spacer), (tileSize * ((rows - 1) - row) + spacer),
						tileSize, tileSize);
				tv.setPosition(tileSize * col + spacer, (tileSize * ((rows - 1) - row) + spacer));

				tv.addListener(listener);
			}
		}
	}

	public void setMinMatches(int m) {
		b.setMinMatches(m);
	}

	public void setNumColors(int num) {
		b.setNumColors(num);
	}

	public void updateBoardViewFromBoard() {
		System.out.println("In updateBoardViewFromBoard...");
		TileView tv;
		removeAll();
		tileGrid = new TileView[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (b.hasTileAt(row, col)) {
					tv = new TileView(b.tileAt(row, col));
				} else {
					tv = new TileView(row, col);
				}
				add(tv);
				tileGrid[row][col] = tv;

				tv.setBounds((tileSize * col + spacer), (tileSize * ((rows - 1) - row) + spacer),
						tileSize, tileSize);
				tv.setPosition(tileSize * col + spacer, (tileSize * ((rows - 1) - row) + spacer));

				tv.addListener(listener);
			}
		}
	}

	public int processTouchedTile(TileView tv) {
		HashSet<Tile> matches = new HashSet<Tile>();
		b.locateNeighbors(tv.getRow(), tv.getCol(), tv.getColor(), matches);
		System.out.println("Total matches: " + matches.size());
		if (matches.size() < b.getMinMatches()) {
			System.out.println("Not min macthes met");
			return 0;
		}

		b.removeMatchingTiles(matches);
		b.collapseColumns();

		updateBoardViewFromBoard();
		System.out.println(b);

		return matches.size();
	}

	public String toString() {
		return b.toString();
	}

	public boolean isMoveAvailable() {
		return b.isMoveAvailable();
	}

	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}

	public String findHintGroup() {
		return b.findHintGroup();
	}
}
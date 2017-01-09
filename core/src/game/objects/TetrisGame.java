package game.objects;

import java.util.ArrayList;

import game.objects.Tetromino.Tile;

/**
 * Keeps the state of the current game.
 * @author jsteketee
 */
public class TetrisGame {
	private GameGrid grid;
	private Tetromino curBlock;
	private Tetromino nextBlock;
	private TetrominoGenerator blockBank;
	private ArrayList<Integer> fullLines = new ArrayList<Integer>();
	private ScoreKeeper scoreKeeper;
	private boolean gameOver = false;

	public TetrisGame() {
		grid = new GameGrid();
		blockBank = new TetrominoGenerator();
		curBlock = blockBank.getRandomBlock();
		nextBlock = blockBank.getRandomBlock();
		scoreKeeper = new ScoreKeeper();
	}
	public GameGrid getGrid() {
		return this.grid;
	}
	public void setGrid(GameGrid grid) {
		this.grid = grid;
	}
	public Tetromino getCurBlock() {
		return this.curBlock;
	}
	public Tetromino getNextBlock() {
		return this.nextBlock;
	}
	/**
	 * Populates the grid according to the location of current block.
	 */
	public void rootBlock() {
		for (Tile t : curBlock.getTiles())
			grid.setTileAt(t.getX(), t.getY(), curBlock.getColor());
	}
	/**
	 * Sets the curBlock to the next block and generates a random nextBlock.
	 */
	public void cycleCurBlock() {
		curBlock = nextBlock;
		nextBlock = blockBank.getRandomBlock();
		gameOver = isCollision();
	}
	/**
	 * Checks if the current Tetromino is out of bounds or at the same location
	 * as another rooted tile.
	 * @return the occurrence of a collision.
	 */
	public boolean isCollision() {
		boolean collision = false;
		for (Tile t : curBlock.getTiles()) {
			if (t.getX() < 1 || t.getX() > GameGrid.WIDTH || t.getY() < 1
					|| t.getY() > GameGrid.HEIGHT || grid.isTileAt(t.getX(), t.getY())) {
				collision = true;
				break;
			}
		}
		return collision;
	}
	public ArrayList<Integer> getFullLines() {
		return fullLines;
	}
	public void setFullLines(ArrayList<Integer> fullLines) {
		this.fullLines = fullLines;
		scoreKeeper.scoreLineClear(fullLines);
	}
	public long getScore() {
		return scoreKeeper.getScore();
	}
	public void setScore(int score) {
		this.scoreKeeper.setScore(score);
	}
	public boolean isGameOver() {
		return gameOver;
	}
}

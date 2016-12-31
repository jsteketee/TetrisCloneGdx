package game.objects;

import com.mygdx.game.TetrominoGenerator;

import game.objects.Tetromino.Tile;

/**
 * Keeps the state of the current game.
 * 
 * @author jsteketee
 */
public class TetrisGame {
	private GameGrid grid;
	private Tetromino curBlock;
	private Tetromino nextBlock;
	private TetrominoGenerator blockBank;

	public TetrisGame() {
		grid = new GameGrid();
		blockBank = new TetrominoGenerator();
		curBlock = blockBank.getRandomBlock();
		nextBlock = blockBank.getRandomBlock();
	}

	public GameGrid getGrid() {
		return this.grid;
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
	}
}

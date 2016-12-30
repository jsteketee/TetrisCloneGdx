package gameObjects;

/**
 * Keeps the state of the current game.
 * @author jsteketee
 */
public class TetrisGame
{
	private GameGrid grid;
	private Tetromino curBlock;
	private Tetromino nextBlock;

	public TetrisGame(Tetromino nextBlock, Tetromino curBlock)
	{
		grid = new GameGrid();
		this.curBlock = curBlock;
		this.nextBlock = nextBlock;
	}

	public GameGrid getGrid()
	{
		return this.grid;
	}
	public Tetromino getCurBlock()
	{
		return this.curBlock;
	}
	public Tetromino getNextBlock()
	{
		return this.nextBlock;
	}
}

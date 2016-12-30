package gameObjects;

/**
 * GameGrid represents each tile in the grid as an integer in a 2d array.
 * @author jsteketee
 */
public class GameGrid
{

	private int[][] grid;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 21;

	/**
	 * constructs a grid with no tiles
	 */
	public GameGrid()
	{
		grid = new int[HEIGHT][WIDTH];
		this.setTileAt(4,4,1);
		this.setTileAt(4,5,1);
		this.setTileAt(4,6,1);

		this.setTileAt(4,7,1);
	}

	/**
	 * returns the integer value stored at the given coordinates.
	 * @param x the horizontal distance form the bottom left corner
	 * @param y the vertical distance from the bottom left corner
	 * @return the integer at (x,y)
	 */
	public int getTileAt(int x, int y)
	{
		return grid[GameGrid.HEIGHT - y][x - 1];
	}

	/**
	 * sets the integer value stored at the given coordinates.
	 * @param x the horizontal distance form the bottom left corner
	 * @param y the vertical distance from the bottom left corner
	 * @param color the color of the tile
	 */
	public void setTileAt(int x, int y, int color)
	{
		grid[GameGrid.HEIGHT - y][x - 1] = color;
	}
}

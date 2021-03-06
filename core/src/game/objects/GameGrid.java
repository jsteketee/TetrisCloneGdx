package game.objects;

/**
 * GameGrid represents each tile in the grid as an integer in a 2d array.
 * @author jsteketee
 */
public class GameGrid {

	private int[][] grid;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 21;

	/**
	 * constructs a grid with no tiles
	 */
	public GameGrid() {
		grid = new int[HEIGHT][WIDTH];
	}

	/**
	 * returns the integer value stored at a given location.
	 * @param x the horizontal distance form the bottom left corner of the grid
	 * @param y the vertical distance from the bottom left corner of the grid
	 * @return the integer at (x,y)
	 */
	public int getTileAt(int x, int y) {
		return grid[GameGrid.HEIGHT - y][x - 1];
	}

	/**
	 * Indicates the presence of a tile at a given location.
	 * @param x the horizontal distance form the bottom left corner of the grid
	 * @param y the vertical distance from the bottom left corner of the grid
	 * @return a boolean representing the presence of a tile
	 */
	public boolean isTileAt(int x, int y) {
		return (getTileAt(x, y) != 0);
	}

	/**
	 * Indicates that there is a tile at every x position in a given row.
	 * @param y the height of the row
	 * @return boolean value indicated the row is full
	 */
	public boolean isFullRow(int y) {
		boolean full = true;
		for (int x = 1; x <= GameGrid.WIDTH; x++) {
			if (!isTileAt(x, y)) full = false;
		}
		return full;
	}

	/**
	 * sets the integer value stored at the given coordinates.
	 * @param x the horizontal distance form the bottom left corner
	 * @param y the vertical distance from the bottom left corner
	 * @param color the color of the tile
	 */
	public void setTileAt(int x, int y, int color) {
		grid[GameGrid.HEIGHT - y][x - 1] = color;
	}

	/**
	 * Copies a given row from one location to another.
	 * @param startY the row to be copied.
	 * @param destY the destination of the copied row
	 */
	public void shiftRow(int startY, int destY) {
		// TODO
			for (int x = 1; x <= GameGrid.WIDTH; x++) {
				setTileAt(x, destY, getTileAt(x, startY));
			}
			// grid[GameGrid.HEIGHT - destY] = grid[GameGrid.HEIGHT - startY];~
	}

}

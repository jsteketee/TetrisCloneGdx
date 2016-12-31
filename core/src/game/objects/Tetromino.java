package game.objects;

/**
 * Each Tetromino is made of four tiles.
 * 
 * @author jsteketee
 */
public abstract class Tetromino {
	protected Tile[] block = new Tile[4];
	protected int color;

	/**
	 * Each tile keeps a location on the grid.
	 */
	public class Tile {
		private int x;
		private int y;

		public Tile(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
	}
	public final Tile[] getTiles() {
		return block;
	}

	public final int getColor() {
		return this.color;
	}

	public final void translateDown() {
		for (Tile t : block)
			--t.y;
	}
	public final void translateUp() {
		for (Tile t : block)
			++t.y;
	}
	public final void translateRight() {
		for (Tile t : block)
			++t.x;
	}
	public final void translateLeft() {
		for (Tile t : block)
			--t.x;
	}
	public void rotateRight() {
		Tile origin = block[0];
		int x;
		int y;
		for (int i = 1; i < block.length; i++) {
			x = block[i].x - origin.x;
			y = block[i].y - origin.y;
			block[i].x = y + origin.x;
			block[i].y = -x + origin.y;
		}
	}
	public void rotateLeft() {
		Tile origin = block[0];
		int x;
		int y;
		for (int i = 1; i < block.length; i++) {
			x = block[i].x - origin.x;
			y = block[i].y - origin.y;
			block[i].x = y + origin.x;
			block[i].y = -x + origin.y;
		}
	}
}

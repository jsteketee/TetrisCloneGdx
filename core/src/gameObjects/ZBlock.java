package gameObjects;

public class ZBlock extends Tetromino {
	public ZBlock() {
		this.color = 7;
		int startHeight = GameGrid.HEIGHT;
		int startWidth = Math.round(GameGrid.WIDTH / 2);
		this.block[0] = new Tile(startWidth, startHeight - 1);
		this.block[1] = new Tile(startWidth - 1, startHeight);
		this.block[2] = new Tile(startWidth, startHeight);
		this.block[3] = new Tile(startWidth + 1, startHeight - 1);
	}
}

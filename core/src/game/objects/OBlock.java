package game.objects;

public class OBlock extends Tetromino {
	public OBlock() {
		this.color = 4;
		int startHeight = GameGrid.HEIGHT;
		int startWidth = Math.round(GameGrid.WIDTH / 2);
		this.block[0] = new Tile(startWidth, startHeight - 1);
		this.block[1] = new Tile(startWidth + 1, startHeight - 1);
		this.block[2] = new Tile(startWidth, startHeight);
		this.block[3] = new Tile(startWidth + 1, startHeight);
	}
	@Override
	public void rotateRight() {
	}
	@Override
	public void rotateLeft() {
	}
}

package game.objects;

import java.util.Random;

/**
 * returns a randomly generated Tetromino.
 * 
 * @author jsteketee
 */
public class TetrominoGenerator {
	Random rand = new Random();

	public Tetromino getRandomBlock() {
		Tetromino block = null;
		int i = rand.nextInt(7);
		switch (i) {
			case 0 :
				block = new JBlock();
				break;
			case 1 :
				block = new LBlock();
				break;
			case 2 :
				block = new SBlock();
				break;
			case 3 :
				block = new OBlock();
				break;
			case 4 :
				block = new IBlock();
				break;
			case 5 :
				block = new TBlock();
				break;
			case 6 :
				block = new ZBlock();
				break;
		}
		return block;
	}
}

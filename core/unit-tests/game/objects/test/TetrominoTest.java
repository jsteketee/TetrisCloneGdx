package game.objects.test;

import static org.junit.Assert.*;
import game.objects.IBlock;
import game.objects.Tetromino;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TetrominoTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test() {
		Tetromino IBlock = new IBlock();
		IBlock.rotateRight();
		assertEquals("Wrong final x coordinate:Tile1", 6,
				IBlock.getTiles()[1].getX());
		assertEquals("Wrong final y coordinate:Tile1(y)", 22,
				IBlock.getTiles()[1].getY());

		assertEquals("Wrong final x coordinate:Tile2", 6,
				IBlock.getTiles()[2].getX());
		assertEquals("Wrong final y coordinate:Tile2", 21,
				IBlock.getTiles()[2].getY());

		assertEquals("Wrong final x coordinate:Tile3", 6,
				IBlock.getTiles()[3].getX());
		assertEquals("Wrong final y coordinate:Tile3", 19,
				IBlock.getTiles()[3].getY());
	}

}

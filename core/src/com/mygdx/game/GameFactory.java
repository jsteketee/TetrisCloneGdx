package com.mygdx.game;

import java.util.ArrayList;

import game.objects.GameGrid;
import game.objects.TetrisGame;
import game.objects.Tetromino;

/**
 * GameFactory keeps the current game and contains high level logic for
 * maintaining it.
 * @author jsteketee
 */
public class GameFactory {

	private TetrisGame curGame;
	private boolean linesToClear = false;

	public GameFactory() {}

	public void createGame() {
		TetrisGame game = new TetrisGame();
		curGame = game;
	}

	public TetrisGame retrieveGameState() {
		return curGame;
	}

	public void moveLeft() {
		Tetromino curBlock = curGame.getCurBlock();
		curBlock.translateLeft();
		if (curGame.isCollision()) {
			curBlock.translateRight();
		}
	}

	public void moveRight() {
		Tetromino curBlock = curGame.getCurBlock();
		curBlock.translateRight();
		if (curGame.isCollision()) {
			curBlock.translateLeft();
		}
	}
	public void rotateClockwise() {
		Tetromino curBlock = curGame.getCurBlock();
		curBlock.rotateRight();
		if (curGame.isCollision()) {
			curBlock.rotateLeft();
		}
	}

	public void rotateCounterClockwise() {
		Tetromino curBlock = curGame.getCurBlock();
		curBlock.rotateLeft();
		if (curGame.isCollision()) {
			curBlock.rotateRight();
		}
	}

	/**
	 * Translates the Tetromino down one space. If this is not possible then the
	 * Tetromino is rooted and a new one is generated.
	 * @Return Whether or not a block was rooted on this method call.
	 */
	public boolean moveDown() {
		if (linesToClear) {
			curGame.cycleCurBlock();
			clearFullLines(curGame.getFullLines());
			curGame.getFullLines().clear();
			linesToClear = false;
		}
		else {
			Tetromino curBlock = curGame.getCurBlock();
			curBlock.translateDown();
			if (curGame.isCollision()) {
				curBlock.translateUp();
				curGame.rootBlock();
				curGame.setFullLines(findLines());
				if (!curGame.getFullLines().isEmpty()) {
					linesToClear = true;
				}
				else {
					curGame.cycleCurBlock();
				}
			}
		}
		return linesToClear;
	}

	/**
	 * clears any full lines and updates the positions of tiles on the grid
	 * accordingly.
	 */
	private void clearFullLines(ArrayList<Integer> fullLines) {
		if (!fullLines.isEmpty()) {
			GameGrid grid = curGame.getGrid();
			int shiftDown;
			for (int y = 1; y <= GameGrid.HEIGHT; y++) {
				shiftDown = 0;
				for (int i : fullLines) {
					if (i < y) ++shiftDown;
				}
				if (shiftDown != 0) grid.shiftRow(y, y - shiftDown);
			}
		}
	}
	/**
	 * Returns an arrayList signifying which rows of the grid are to be cleared.
	 * @return fullLine the list of full lines
	 */
	private ArrayList<Integer> findLines() {
		ArrayList<Integer> fullLine = new ArrayList<Integer>();
		GameGrid grid = curGame.getGrid();
		for (int y = 1; y <= GameGrid.HEIGHT; y++) {
			if (grid.isFullRow(y)) fullLine.add(y);
		}
		return fullLine;
	}
}

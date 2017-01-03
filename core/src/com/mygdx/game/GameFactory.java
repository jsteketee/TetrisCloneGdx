package com.mygdx.game;

import java.util.ArrayList;

import game.objects.GameGrid;
import game.objects.TetrisGame;
import game.objects.Tetromino;
import game.objects.Tetromino.Tile;

/**
 * GameFactory keeps the current game and contains implementation independent
 * methods for maintaining it.
 * 
 * @author jsteketee
 */
public class GameFactory {
	private TetrisGame curGame;

	public GameFactory() {
	}

	public void createGame() {
		TetrisGame game = new TetrisGame();
		curGame = game;
	}

	public TetrisGame retrieveGameState() {
		return curGame;
	}

	/**
	 * Translates the Tetromino down one space. If this is not possible then the
	 * Tetromino is rooted and a new one is generated.
	 */
	public void moveDown() {
		Tetromino curBlock = curGame.getCurBlock();
		curBlock.translateDown();
		if (isCollision()) {
			curBlock.translateUp();
			curGame.rootBlock();
			curGame.cycleCurBlock();
			clearFullLines();
		}
	}
	/**
	 * clears any full lines and updates the positions of tiles on the grid
	 * accordingly.
	 */
	private void clearFullLines() {
		ArrayList<Integer> fullLines = findLines();
		if (!fullLines.isEmpty()) {
			GameGrid oldGrid = curGame.getGrid();
			GameGrid newGrid = new GameGrid();
			int rowToFill = 1;
			for (int y = 1; y <= GameGrid.HEIGHT; y++) {
				if (!fullLines.contains(y)) {
					for (int x = 1; x <= GameGrid.WIDTH; x++)
						newGrid.setTileAt(x, rowToFill, oldGrid.getTileAt(x, y));
					rowToFill++;
				}
			}
			curGame.setGrid(newGrid);
		}
	}
	/**
	 * Returns an arrayList signifying which rows of the grid are to be cleared.
	 * 
	 * @return fullLine the list of full lines
	 */
	private ArrayList<Integer> findLines() {
		ArrayList<Integer> fullLine = new ArrayList<Integer>();
		GameGrid grid = curGame.getGrid();
		boolean full;
		for (int y = 1; y <= GameGrid.HEIGHT; y++) {
			full = true;
			for (int x = 1; x <= GameGrid.WIDTH; x++) {
				if (grid.getTileAt(x, y) == 0) {
					full = false;
					break;
				}
			}
			if (full)
				fullLine.add(y);
		}
		return fullLine;
	}

	public void moveLeft() {
		Tetromino curBlock = curGame.getCurBlock();
		curBlock.translateLeft();
		if (isCollision()) {
			curBlock.translateRight();
		}
	}

	public void moveRight() {
		Tetromino curBlock = curGame.getCurBlock();
		curBlock.translateRight();
		if (isCollision()) {
			curBlock.translateLeft();
		}
	}
	public void rotateClockwise() {
		Tetromino curBlock = curGame.getCurBlock();
		curBlock.rotateRight();
		if (isCollision()) {
			curBlock.rotateLeft();
		}
	}

	public void rotateCounterClockwise() {
		Tetromino curBlock = curGame.getCurBlock();
		curBlock.rotateLeft();
		if (isCollision()) {
			curBlock.rotateRight();
		}
	}

	/**
	 * Checks if the moved Tetromino will be on top of a rooted tile or out of
	 * bounds.
	 * 
	 * @return
	 */
	private boolean isCollision() {
		boolean collision = false;
		for (Tile t : curGame.getCurBlock().getTiles()) {
			if (t.getX() < 1 || t.getX() > GameGrid.WIDTH || t.getY() < 1
					|| t.getY() > GameGrid.HEIGHT)
				collision = true;
			else if (curGame.getGrid().getTileAt(t.getX(), t.getY()) != 0)
				collision = true;
		}
		return collision;
	}
}
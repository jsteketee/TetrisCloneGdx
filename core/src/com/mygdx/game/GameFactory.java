package com.mygdx.game;

import game.objects.GameGrid;
import game.objects.TetrisGame;
import game.objects.Tetromino;
import game.objects.Tetromino.Tile;

/**
 * GameFactory keeps the current game and contains methods for maintaining it.
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
	public void enactGravity() {
		Tetromino curBlock = curGame.getCurBlock();
		curBlock.translateDown();
		if (isCollision()) {
			curBlock.translateUp();
			curGame.rootBlock();
			curGame.cycleCurBlock();
		}
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
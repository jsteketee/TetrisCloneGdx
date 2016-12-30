package com.mygdx.game;

import gameObjects.TetrisGame;
import gameObjects.Tetromino;

/**
 * GameFactory keeps the current game and contains methods for maintaining it.
 * @author jsteketee
 */
public class GameFactory
{
	private TetrisGame curGame;
	TetrominoGenerator blocks = new TetrominoGenerator();

	public GameFactory()
	{}

	public TetrisGame createGame()
	{
		TetrisGame game = new TetrisGame(getNextBlock(), getNextBlock());
		curGame = game;
		return game;
	}
	public TetrisGame retrieveGameState()
	{
		return curGame;
	}
	public Tetromino getNextBlock()
	{
		TetrominoGenerator blocks = new TetrominoGenerator();
		return blocks.getRandomBlock();
	}
	/**
	 * Updates the state of the game according to how much time has elapsed
	 * between the last update call.
	 * @param deltaT the time since the last update was performed
	 * @return curGame the current game
	 */
	public TetrisGame enactGravity()
	{
		curGame.getCurBlock().tranlateDown();
		return curGame;
	}
}

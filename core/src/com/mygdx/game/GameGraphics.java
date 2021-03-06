package com.mygdx.game;

import game.objects.GameGrid;
import game.objects.TetrisGame;
import game.objects.Tetromino;
import game.objects.Tetromino.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameGraphics {

	Texture grid;
	Texture[] tiles = new Texture[8];
	Texture gameOver = new Texture("GameOver.png");

	BitmapFont font;

	int gridWidth;
	int gridLocationX = 320;
	int gridLocationY = 50;
	int deltaSquare;

	public GameGraphics() {
		grid = new Texture("grid.png");

		tiles[0] = new Texture("ClearRow.png");
		tiles[1] = new Texture("ITile.png");
		tiles[2] = new Texture("JTile.png");
		tiles[3] = new Texture("LTile.png");
		tiles[4] = new Texture("OTile.png");
		tiles[5] = new Texture("STile.png");
		tiles[6] = new Texture("TTile.png");
		tiles[7] = new Texture("ZTile.png");

		font = new BitmapFont();
		font.setColor(Color.BLACK);

		gridWidth = grid.getWidth();
		deltaSquare = tiles[1].getWidth() + 1;
	}

	public void draw(Batch batch, TetrisGame curGame, boolean animationFlash) {
		drawBackground();
		batch.begin();
		batch.draw(grid, gridLocationX, gridLocationY);
		drawGrid(batch, curGame);
		drawGridNumbers(batch, curGame);
		drawCurShape(batch, curGame);
		drawNextShape(batch, curGame);
		drawScore(batch, curGame);
		if (curGame.isGameOver()) {
			drawGameOver(batch);
		}
		if (animationFlash) {
			for (int y : curGame.getFullLines()) {
				drawTileAt(batch, 1, y, tiles[0]);
			}
		}
		batch.end();
	}
	private void drawScore(Batch batch, TetrisGame curGame) {
		font.draw(batch, "HighScore", gridLocationX - 10 * deltaSquare, gridLocationY
				+ (deltaSquare * GameGrid.HEIGHT) + deltaSquare);
		font.draw(batch, String.valueOf(curGame.getScore()), gridLocationX - 10 * deltaSquare,
				gridLocationY + (deltaSquare * GameGrid.HEIGHT) - deltaSquare);
	}
	private void drawGameOver(Batch batch) {
		batch.draw(gameOver, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	private void drawNextShape(Batch batch, TetrisGame curGame) {
		Tetromino nextBlock = curGame.getNextBlock();
		Tile[] tiles = nextBlock.getTiles();
		for (Tile t : tiles)
			drawTileAt(batch, t.getX() - 8, t.getY(), nextBlock.getColor());
		font.draw(batch, "Next Shape:", gridLocationX - 5 * deltaSquare, gridLocationY
				+ (deltaSquare * GameGrid.HEIGHT) + deltaSquare);
	}
	private void drawBackground() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	private void drawGrid(Batch batch, TetrisGame curGame) {
		for (int x = 1; x <= GameGrid.WIDTH; x++) {
			for (int y = 1; y <= GameGrid.HEIGHT; y++) {
				int color = curGame.getGrid().getTileAt(x, y);
				if (color != 0) {
					this.drawTileAt(batch, x, y, color);
				}
			}
		}
	}
	private void drawCurShape(Batch batch, TetrisGame curGame) {
		Tetromino curBlock = curGame.getCurBlock();
		Tile[] tiles = curBlock.getTiles();
		for (Tile t : tiles) {
			drawTileAt(batch, t.getX(), t.getY(), curBlock.getColor());
		}
	}
	private void drawGridNumbers(Batch batch, TetrisGame curGame) {
		for (int i = 1; i <= GameGrid.HEIGHT; i++)
			font.draw(batch, String.valueOf(i), gridLocationX - deltaSquare, gridLocationY - 5
					+ (deltaSquare) * i);
		for (int i = 1; i <= GameGrid.WIDTH; i++)
			font.draw(batch, String.valueOf(i), gridLocationX - 16 + (deltaSquare) * i,
					gridLocationY - 10);
	}
	private void drawTileAt(Batch batch, int x, int y, int color) {
		batch.draw(tiles[color], (gridLocationX) + (deltaSquare) * (x - 1), (gridLocationY)
				+ (deltaSquare) * (y - 1));
	}
	private void drawTileAt(Batch batch, int x, int y, Texture t) {
		batch.draw(t, (gridLocationX) + (deltaSquare) * (x - 1), (gridLocationY) + (deltaSquare)
				* (y - 1));
	}
	public void disposeTextures() {
		grid.dispose();
		for (int i = 1; i < tiles.length; i++)
			tiles[i].dispose();
	}
}

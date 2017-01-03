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

public class GameGraphics {

	Texture grid;
	Texture[] tiles = new Texture[8];

	BitmapFont font;

	int windowWidth;
	int gridWidth;
	int gridLocationX;
	int gridLocationY;
	int squareWidth;
	final int TILE_MARGIN = 1;

	public GameGraphics() {
		grid = new Texture("grid.png");

		tiles[0] = null;
		tiles[1] = new Texture("ITile.png");
		tiles[2] = new Texture("JTile.png");
		tiles[3] = new Texture("LTile.png");
		tiles[4] = new Texture("OTile.png");
		tiles[5] = new Texture("STile.png");
		tiles[6] = new Texture("TTile.png");
		tiles[7] = new Texture("ZTile.png");

		font = new BitmapFont();
		font.setColor(Color.BLACK);

		windowWidth = Gdx.graphics.getWidth();
		gridWidth = grid.getWidth();
		gridLocationX = windowWidth / 2 - gridWidth / 2 + windowWidth / 4;
		gridLocationY = 50;
		squareWidth = tiles[1].getWidth();
	}

	public void draw(Batch batch, TetrisGame curGame) {
		drawBackground();
		batch.begin();
		batch.draw(grid, gridLocationX, gridLocationY);
		drawGrid(batch, curGame);
		drawGridNumbers(batch, curGame);
		drawCurShape(batch, curGame);
		batch.end();
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
		for (int i = 1; i <= curGame.getGrid().HEIGHT; i++)
			font.draw(batch, String.valueOf(i), gridLocationX - squareWidth,
					gridLocationY - 5 + (squareWidth + TILE_MARGIN) * i);
		for (int i = 1; i <= curGame.getGrid().WIDTH; i++)
			font.draw(batch, String.valueOf(i), gridLocationX - 16
					+ (squareWidth + TILE_MARGIN) * i, gridLocationY - 10);
	}
	private void drawTileAt(Batch batch, int x, int y, int color) {
		batch.draw(tiles[color], (gridLocationX) + (squareWidth + TILE_MARGIN)
				* (x - 1), (gridLocationY) + (squareWidth + TILE_MARGIN)
				* (y - 1));
	}
	public void disposeTextures() {
		grid.dispose();
		for (int i = 1; i < tiles.length; i++)
			tiles[i].dispose();
	}
}

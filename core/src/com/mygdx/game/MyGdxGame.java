package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * ApplicationAdpater for the main screen.
 * @author jsteketee
 */
public class MyGdxGame extends ApplicationAdapter {

	float fallRate = 500;
	float dropRate = 25;
	float inputSeperation = 53;

	float lineClearAnimationFreq = 200;
	float lineClearAnimationDuration = 1000;

	boolean isAnimation = false;

	GameFactory gameEntity;
	GameGraphics graphicsEntity;
	Timer timer;

	SpriteBatch batch;

	@Override
	public void create() {
		gameEntity = new GameFactory();
		gameEntity.createGame();
		graphicsEntity = new GameGraphics();
		batch = new SpriteBatch();
		timer = new Timer(fallRate, dropRate, inputSeperation, lineClearAnimationDuration,
				lineClearAnimationFreq);
	}

	@Override
	public void render() {
		if (isAnimation) {
			graphicsEntity.draw(batch, gameEntity.retrieveGameState(), timer.isAnimationFlash());
			isAnimation = timer.isAnimation();
		}
		else {
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && timer.allowDrop()) {
				isAnimation = gameEntity.moveDown();
				if (isAnimation) timer.startAnimationTimer();
			}
			else if (timer.allowFall()) {
				isAnimation = gameEntity.moveDown();
				if (isAnimation) timer.startAnimationTimer();
			}
			if (timer.allowInput()) {
				if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) gameEntity.moveLeft();
				if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) gameEntity.moveRight();
				if (Gdx.input.isKeyPressed(Input.Keys.UP)) gameEntity.rotateClockwise();
				if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))
					gameEntity.rotateCounterClockwise();
			}
			graphicsEntity.draw(batch, gameEntity.retrieveGameState(), false);
		}
	}
	@Override
	public void dispose() {
		batch.dispose();
		graphicsEntity.disposeTextures();
	}
}

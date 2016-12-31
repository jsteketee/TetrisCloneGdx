package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * ApplicationAdpater for the main screen.
 * 
 * @author jsteketee
 */
public class MyGdxGame extends ApplicationAdapter {

	float fallRate = 1500;
	float dropRate = 25;
	float inputSeperation = 160;

	GameFactory gameEntity;
	GameGraphics graphicsEntity;
	Timer timer;

	SpriteBatch batch;

	public float timeCounter;

	@Override
	public void create() {
		gameEntity = new GameFactory();
		gameEntity.createGame();
		graphicsEntity = new GameGraphics();
		batch = new SpriteBatch();
		timer = new Timer(fallRate, dropRate, inputSeperation);
	}

	@Override
	public void render() {
	
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && timer.allowDrop())
			gameEntity.enactGravity();
		else if (timer.allowFall())
		{
			
		}
			//gameEntity.enactGravity();
		if (timer.allowInput()) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				gameEntity.moveLeft();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				gameEntity.moveRight();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				gameEntity.rotateClockwise();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
				gameEntity.rotateCounterClockwise();
			}
		}
		graphicsEntity.draw(batch, gameEntity.retrieveGameState());
	}
	@Override
	public void dispose() {
		batch.dispose();
		graphicsEntity.disposeTextures();
	}
}

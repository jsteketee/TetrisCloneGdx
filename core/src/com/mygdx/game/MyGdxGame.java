package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * ApplicationAdpater for the main screen.
 * 
 * @author jsteketee
 */
public class MyGdxGame extends ApplicationAdapter {

    int fallRate = 100;
    int inputSeperation = 10;
    
    GameFactory gameEntity;
    GameGraphics graphicsEntity;

    SpriteBatch batch;

    float timeCounter;

    @Override
    public void create() {
	gameEntity = new GameFactory();
	gameEntity.createGame();
	graphicsEntity = new GameGraphics();
	batch = new SpriteBatch();
	timeCounter = System.currentTimeMillis();
    }

    @Override
    public void render() {
	if(System.currentTimeMillis()-timeCounter > fallRate)
	{
	    gameEntity.enactGravity();
	    timeCounter = System.currentTimeMillis();
	}
	if (System.currentTimeMillis()-timeCounter > fallRate)
	
	graphicsEntity.draw(batch, gameEntity.retrieveGameState());
    }

    @Override
    public void dispose() {
	batch.dispose();
	graphicsEntity.disposeTextures();
    }
}

package com.mygdx.game;

public class Timer {

	private float fallRate;
	private float dropRate;
	private float inputSeperation;
	private float lineClearAnimationDur;
	private float lineClearAnimationFreq;
	private long fallTimer, fallAcellTimer, inputTimer, animationTimer, flashTimer, gameStart;
	private float fallAcellCheck = 200;
	private float FALLACELLERATION = (float) -0.000001;
	boolean animationFlash = true;

	public Timer(float fallRate, float dropRate, float inputSeperation,
			float lineClearAnimationDur, float lineClearAnimationFreq) {
		this.fallRate = fallRate;
		this.dropRate = dropRate;
		this.inputSeperation = inputSeperation;
		this.lineClearAnimationDur = lineClearAnimationDur;
		this.lineClearAnimationFreq = lineClearAnimationFreq;
		fallTimer = inputTimer = animationTimer = flashTimer = gameStart = fallAcellTimer = System
				.currentTimeMillis();
	}
	public boolean allowFall() {
		boolean fall = false;
		if (System.currentTimeMillis() - fallTimer > fallRate) {
			fall = true;
			fallTimer = System.currentTimeMillis();
		}
		updateFallRate();
		return fall;
	}
	public boolean allowDrop() {
		boolean drop = false;
		if (System.currentTimeMillis() - fallTimer > dropRate) {
			drop = true;
			fallTimer = System.currentTimeMillis();
		}
		
		return drop;
	}
	public boolean allowInput() {
		boolean input = false;
		if (System.currentTimeMillis() - inputTimer > inputSeperation) {
			input = true;
			inputTimer = System.currentTimeMillis();
		}
		return input;
	}
	public boolean isAnimationFlash() {
		if (System.currentTimeMillis() - flashTimer > lineClearAnimationFreq) {
			if (animationFlash)
				animationFlash = false;
			else animationFlash = true;
			flashTimer = System.currentTimeMillis();
		}
		return animationFlash;
	}
	public boolean isAnimation() {
		boolean animation = true;
		if (System.currentTimeMillis() - animationTimer > lineClearAnimationDur) {
			animation = false;
		}
		return animation;
	}
	public void startAnimationTimer() {
		animationTimer = System.currentTimeMillis();
		animationFlash = true;
	}
	private void updateFallRate()
	{
		if (System.currentTimeMillis() - fallAcellTimer > fallAcellCheck) {
			float elapsedTime = System.currentTimeMillis() - gameStart;
			fallRate = (float) (500*Math.pow(Math.E, elapsedTime*FALLACELLERATION));
			System.out.println(fallRate);
			fallAcellTimer = System.currentTimeMillis();
		}
	}
}

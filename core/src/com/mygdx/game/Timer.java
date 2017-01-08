package com.mygdx.game;

public class Timer {
	private float fallRate;
	private float dropRate;
	private float inputSeperation;
	private float lineClearAnimationDur;
	private float lineClearAnimationFreq;
	private long fallTimer = 0;
	private long inputTimer = 0;
	private long animationTimer = 0;
	private long flashTimer = 0;
	boolean animationFlash = true;

	public Timer(float fallRate, float dropRate, float inputSeperation,
			float lineClearAnimationDur, float lineClearAnimationFreq) {
		this.fallRate = fallRate;
		this.dropRate = dropRate;
		this.inputSeperation = inputSeperation;
		this.lineClearAnimationDur = lineClearAnimationDur;
		this.lineClearAnimationFreq = lineClearAnimationFreq;
	}
	public boolean allowFall() {
		boolean fall = false;
		if (System.currentTimeMillis() - fallTimer > fallRate) {
			fall = true;
			fallTimer = System.currentTimeMillis();
		}
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
}

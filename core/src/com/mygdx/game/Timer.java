package com.mygdx.game;

public class Timer {
	private float fallRate;
	private float dropRate;
	private float inputSeperation;
	private long fallTimer = 0;
	private long inputTimer = 0;

	public Timer(float fallRate, float dropRate, float inputSeperation) {
		this.fallRate = fallRate;
		this.dropRate = dropRate;
		this.inputSeperation = inputSeperation;
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
}

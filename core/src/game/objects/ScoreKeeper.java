package game.objects;

import java.util.ArrayList;

public class ScoreKeeper {

	long score;

	public ScoreKeeper() {
		this.score = 0;
	}
	public long getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score += score;
	}
	public long scoreLineClear(ArrayList<Integer> lines) {
		score += 1000 * (lines.size() * 2);
		if (lines.size() == 4) score += 2000;
		return score;
	}
}

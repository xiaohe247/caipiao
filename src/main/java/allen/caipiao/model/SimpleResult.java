package allen.caipiao.model;

import java.text.DecimalFormat;
import java.text.Format;

public class SimpleResult {

	private Format format = new DecimalFormat("0.00");

	private int goal;

	private double nomalScore;

	private double adjustScore;

	@Override
	public String toString() {
		return goal + "\t" + this.format.format(nomalScore) + "\t"
				+ this.format.format(adjustScore) + "\t"
				+ this.format.format(nomalScore + adjustScore);
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public double getNomalScore() {
		return nomalScore;
	}

	public void setNomalScore(double nomalScore) {
		this.nomalScore = nomalScore;
	}

	public double getAdjustScore() {
		return adjustScore;
	}

	public void setAdjustScore(double adjustScore) {
		this.adjustScore = adjustScore;
	}

}

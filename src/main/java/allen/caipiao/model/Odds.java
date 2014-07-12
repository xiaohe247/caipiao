package allen.caipiao.model;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.Format;

public class Odds {

	private Format doubleFormat = new DecimalFormat("0.00");

	private Format intFormat = new DecimalFormat("00");

	private long id;
	// 让球
	private int adjust;
	// 主队赢赔率
	private double winOdds;

	private double drawOdds;

	private double lossOdds;

	// 主队赢下注比
	private int winPer;

	private int drawPer;

	private int lossPer;

	// 数据来源
	private int sourceId;

	// 更新时间
	private Timestamp updateTime;

	@Override
	public String toString() {
		return this.intFormat.format(this.adjust) + "\t"
				+ this.doubleFormat.format(this.winOdds) + "\t"
				+ this.doubleFormat.format(this.drawOdds) + "\t"
				+ this.doubleFormat.format(this.lossOdds) + "\t"
				+ this.intFormat.format(this.winPer) + "\t"
				+ this.intFormat.format(this.drawPer) + "\t"
				+ this.intFormat.format(this.lossPer) + "\n";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAdjust() {
		return adjust;
	}

	public void setAdjust(int adjust) {
		this.adjust = adjust;
	}

	public double getWinOdds() {
		return winOdds;
	}

	public void setWinOdds(double winOdds) {
		this.winOdds = winOdds;
	}

	public double getDrawOdds() {
		return drawOdds;
	}

	public void setDrawOdds(double drawOdds) {
		this.drawOdds = drawOdds;
	}

	public double getLossOdds() {
		return lossOdds;
	}

	public void setLossOdds(double lossOdds) {
		this.lossOdds = lossOdds;
	}

	public int getWinPer() {
		return winPer;
	}

	public void setWinPer(int winPer) {
		this.winPer = winPer;
	}

	public int getDrawPer() {
		return drawPer;
	}

	public void setDrawPer(int drawPer) {
		this.drawPer = drawPer;
	}

	public int getLossPer() {
		return lossPer;
	}

	public void setLossPer(int lossPer) {
		this.lossPer = lossPer;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}

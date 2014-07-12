package allen.caipiao.model;

public class Match {

	// 杯赛
	private String leagueName;

	// 比赛中文编号
	private String matchNumcn;

	// 比赛编号
	private String matchCode;

	// 主队
	private String homeTeam;
	// 客队
	private String guestTeam;

	// 比赛时间
	private String matchTime;
	
	//正常赔率
	private Odds nomalOdds;
	
	//让球赔率
	private Odds adjustOdds;

	public String toString() {
		String str = this.leagueName + "[" + this.matchCode + ","
				+ this.matchNumcn + "]\t" + this.homeTeam + "--"
				+ this.guestTeam + "\n";
		if(this.nomalOdds == null)
			str += "不支持该投注方法\n";
		else
			str += this.nomalOdds.toString();
		if(this.adjustOdds == null)
			str += "不支持该投注方法\n";
		else
			str	+= this.adjustOdds.toString();
		return str;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public String getMatchNumcn() {
		return matchNumcn;
	}

	public void setMatchNumcn(String matchNumcn) {
		this.matchNumcn = matchNumcn;
	}

	public String getMatchCode() {
		return matchCode;
	}

	public void setMatchCode(String matchCode) {
		this.matchCode = matchCode;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}

	public String getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}

	public Odds getNomalOdds() {
		return nomalOdds;
	}

	public void setNomalOdds(Odds nomalOdds) {
		this.nomalOdds = nomalOdds;
	}

	public Odds getAdjustOdds() {
		return adjustOdds;
	}

	public void setAdjustOdds(Odds adjustOdds) {
		this.adjustOdds = adjustOdds;
	}

}

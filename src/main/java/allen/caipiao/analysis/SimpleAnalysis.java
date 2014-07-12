package allen.caipiao.analysis;

import java.util.ArrayList;
import java.util.List;

import allen.caipiao.model.Match;
import allen.caipiao.model.Odds;
import allen.caipiao.model.SimpleResult;

public class SimpleAnalysis {

	public List<SimpleResult> analy(Match match) {
		double[] nomals = this.score(match.getNomalOdds());
		double[] adjusts = this.score(match.getAdjustOdds());
		int adGoal = match.getAdjustOdds() == null ? 0 :match.getAdjustOdds().getAdjust();
	
		int min = this.min(adGoal);
		int max = this.max(adGoal);
		List<SimpleResult> results = new ArrayList<SimpleResult>();
		for(int i = max; i >= min; i --){
			double s1 = 0;
			double s2 = 0;
			if(nomals != null){
				if(i > 0)
					s1 = nomals[0];
				else if(i == 0)
					s1 = nomals[1];
				else
					s1 = nomals[2];
			}
			
			if(adjusts != null){
				if(i + adGoal > 0)
					s2 = adjusts[0];
				else if(i + adGoal == 0)
					s2 = adjusts[1];
				else
					s2 = adjusts[2];
			}
			
			SimpleResult result = new SimpleResult();
			result.setGoal(i);
			result.setNomalScore(s1);
			result.setAdjustScore(s2);
			results.add(result);
		}
		return results;
	}

	private double[] score(Odds odds) {
		if (odds != null) {
			double[] scores = new double[3];
			scores[0] = odds.getWinOdds() * odds.getWinPer();
			scores[1] = odds.getDrawOdds() * odds.getDrawPer();
			scores[2] = odds.getLossOdds() * odds.getLossPer();
			return scores;
		}
		return null;
	}
	
	private int max(int adGoal){
		if(adGoal > 0)
			return 1;
		else
			return 1 - adGoal;
	}
	
	private int min(int adGoal){
		if(adGoal < 0)
			return -1;
		else
			return -1 - adGoal;
	}
}

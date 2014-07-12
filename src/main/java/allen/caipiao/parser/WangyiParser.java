package allen.caipiao.parser;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import allen.caipiao.PageFetch;
import allen.caipiao.analysis.SimpleAnalysis;
import allen.caipiao.model.Match;
import allen.caipiao.model.Odds;
import allen.caipiao.model.SimpleResult;

public class WangyiParser {

	private String url = "http://caipiao.163.com/order/jczq-hunhe/";

	private PageFetch fetch = new PageFetch();
	
	private SimpleAnalysis analysis = new SimpleAnalysis();

	public void parse() throws Exception {
		Document doc = this.fetch.fetchHtml(url);
		Elements dataBodyEs = doc.getElementsByAttributeValueContaining("class",
				"dataBody");
		Iterator<Element> dataBodyIt = dataBodyEs.iterator();
		while (dataBodyIt.hasNext()) {
			Element dataBodyE = dataBodyIt.next();
			Elements ddEs = dataBodyE.getElementsByTag("dd");
			Iterator<Element> ddIt = ddEs.iterator();
			while(ddIt.hasNext()){
				Element ddE = ddIt.next();
				Match match = this.convert(ddE);
				if(match != null){
					List<SimpleResult> results = this.analysis.analy(match);
					System.out.println(match.toString());
					for(SimpleResult result : results){
						System.out.println(result.toString());
					}
					System.out.println();
				}
			}
		}
	}

	private Match convert(Element ddE){
		if(ddE != null){
			if(ddE.classNames().contains("analyMore")){
				return null;
			}
			if(ddE.hasAttr("isstop") && ddE.attr("isstop").equals("1")){
				return null;
			}
			Match match = new Match();
			
			match.setLeagueName(ddE.attr("leaguename"));
			match.setHomeTeam(ddE.attr("hostname"));
			match.setGuestTeam(ddE.attr("guestname"));
			
			match.setMatchCode(ddE.attr("matchcode"));
			match.setMatchNumcn(ddE.attr("matchnumcn"));
			match.setMatchTime(ddE.attr("endtime"));
			
			
			
			
			Elements goalEs = ddE.getElementsByAttributeValueContaining("class", "co5");
			Element goalE = goalEs.first();
			Element nomalE = goalE.child(0);
			Element adjustE = goalE.child(1);
//			String adjust = adjustE.child(1).text();
//			if(adjust.startsWith("+")){
//				adjust = adjust.substring(1);
//			}
//			nomalOdds.setAdjust(0);
//			adjustOdds.setAdjust(Integer.parseInt(adjust));
			
			Elements oddEs = ddE.getElementsByAttributeValueContaining("class", "co6_1");
			Element oddE = oddEs.first();
			Element nomalOddE = oddE.child(0);
			Element adjustOddE = oddE.child(1);
			
//			String  winOdds = oddE.child(0).child(0).text();
//			String drawOdds = oddE.child(0).child(1).text();
//			String lossOdds = oddE.child(0).child(2).text();
//			nomalOdds.setLossOdds(Double.parseDouble(lossOdds));
//			nomalOdds.setDrawOdds(Double.parseDouble(drawOdds));
//			nomalOdds.setWinOdds(Double.parseDouble(winOdds));
//			
//			String  winOdds2 = oddE.child(1).child(0).text();
//			String drawOdds2 = oddE.child(1).child(1).text();
//			String lossOdds2 = oddE.child(1).child(2).text();
//			adjustOdds.setLossOdds(Double.parseDouble(lossOdds2));
//			adjustOdds.setDrawOdds(Double.parseDouble(drawOdds2));
//			adjustOdds.setWinOdds(Double.parseDouble(winOdds2));
			
			Elements perEs = ddE.getElementsByAttributeValueContaining("class", "co7");
			Element perE = perEs.first();
			Element nomalPerE = perE.child(0);
			Element adjustPerE = perE.child(1);
			
//			String winPer = perE.child(0).child(0).child(0).text();
//			String drawPer = perE.child(0).child(1).child(0).text();
//			String lossPer = perE.child(0).child(2).child(0).text();
//			nomalOdds.setWinPer(Integer.parseInt(winPer.substring(0, winPer.length() - 1)));
//			nomalOdds.setDrawPer(Integer.parseInt(drawPer.substring(0, drawPer.length() - 1)));
//			nomalOdds.setLossPer(Integer.parseInt(lossPer.substring(0, lossPer.length() - 1)));
//			
//			String winPer2 = perE.child(1).child(0).child(0).text();
//			String drawPer2 = perE.child(1).child(1).child(0).text();
//			String lossPer2 = perE.child(1).child(2).child(0).text();
//			adjustOdds.setWinPer(Integer.parseInt(winPer2.substring(0, winPer2.length() - 1)));
//			adjustOdds.setDrawPer(Integer.parseInt(drawPer2.substring(0, drawPer2.length() - 1)));
//			adjustOdds.setLossPer(Integer.parseInt(lossPer2.substring(0, lossPer2.length() - 1)));
			
			
			
			Odds nomalOdds = this.convertOdds(nomalE, nomalOddE, nomalPerE);
			Odds adjustOdds = this.convertOdds(adjustE, adjustOddE, adjustPerE);
			
			match.setNomalOdds(nomalOdds);
			match.setAdjustOdds(adjustOdds);
			
			return match;
		}
		return null;
	}
	
	private Odds convertOdds(Element adjustE, Element oddE, Element perE){
		try{
			Timestamp now = new Timestamp(new Date().getTime());
			int wangyiId = 1;
			Odds odds = new Odds();
			odds.setSourceId(wangyiId);
			odds.setUpdateTime(now);
			//让球
			String adjust = adjustE.text();
			if(adjust.startsWith("+")){
				adjust = adjust.substring(1);
			}
			odds.setAdjust(Integer.parseInt(adjust));
			
			//赔率
			String  winOdds = oddE.child(0).text();
			String drawOdds = oddE.child(1).text();
			String lossOdds = oddE.child(2).text();
			odds.setLossOdds(Double.parseDouble(lossOdds));
			odds.setDrawOdds(Double.parseDouble(drawOdds));
			odds.setWinOdds(Double.parseDouble(winOdds));
			
			// 下注比例
			String winPer = perE.child(0).child(0).text();
			String drawPer = perE.child(1).child(0).text();
			String lossPer = perE.child(2).child(0).text();
			odds.setWinPer(Integer.parseInt(winPer.substring(0, winPer.length() - 1)));
			odds.setDrawPer(Integer.parseInt(drawPer.substring(0, drawPer.length() - 1)));
			odds.setLossPer(Integer.parseInt(lossPer.substring(0, lossPer.length() - 1)));
			return odds;
		}catch(Exception e){
			
		}
		return null;
	}
	
	public static void main(String[] args){
		WangyiParser parser = new WangyiParser();
		try {
			parser.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

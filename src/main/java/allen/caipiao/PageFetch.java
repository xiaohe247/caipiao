package allen.caipiao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageFetch {
	
	public static void main(String[] args){
		PageFetch fetch = new PageFetch();
		try {
			fetch.fetchHtml("http://caipiao.163.com/order/jczq-hunhe/");
//			fetch.fetchHtml("http://blog.csdn.net/yjboy1982/article/details/1693687");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Document fetchHtml(String strURL) throws Exception {
//		String strURL = "http://caipiao.163.com/order/jczq-hunhe/";
		if(strURL != null){
			URL url = new URL(strURL);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			InputStreamReader input = new InputStreamReader(httpConn
					.getInputStream(), "utf-8");
			BufferedReader bufReader = new BufferedReader(input);
			String line = "";
			StringBuilder contentBuf = new StringBuilder();
			while ((line = bufReader.readLine()) != null) {
				contentBuf.append(line);
			}
			String buf = contentBuf.toString();
			 Document doc=null;
	         try{
	            doc = Jsoup.parse(buf);
	            return doc;
	         }
	         catch(Exception e){
	             e.printStackTrace();
	         }
		}
		return null;
	}

}

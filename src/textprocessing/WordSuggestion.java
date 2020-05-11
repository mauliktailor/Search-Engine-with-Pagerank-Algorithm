package textprocessing;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WordSuggestion {
	
	public static void wordSuggestion(String input) throws IOException {
		int count = 0;
		String testinp = input;
		String regex = "^" + testinp + "(.+)";
		Document doc = Jsoup.connect("https://www.investopedia.com/terms/a/artificial-intelligence-ai.asp").get();
		String s = doc.text();
		StringTokenizer token = new StringTokenizer(s);
		System.out.println("WORD SUGGESTIONS: ");
		while (token.hasMoreTokens()) {
			String g = token.nextToken();
			Pattern p = Pattern.compile(regex);
			Matcher match = p.matcher(g);
			if (match.matches()) {
				count+=1;
				System.out.println(g);
			}
		}
		
		if(count <= 0){
			System.out.println("NO SUGGESTION");
		}
	}
}

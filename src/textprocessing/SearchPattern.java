package textprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SearchPattern {

	public static Set<String> words = new HashSet<>();
	static String basePath = "C:\\Users\\Deepti\\Downloads\\FinalProject\\";
	static String rootPath = basePath + "TextOP";
	static String htmlBasePath = basePath + "Tutorial_Point\\swift\\";
	static String pageRankPath = "C:\\Users\\Deepti\\Downloads\\FinalProject\\Tutorial_Point";
	static String wordFile = "Words.txt";

	public static Map<String, Integer> searchWord() throws IOException {
		String line;
		String suggestion = null;
		boolean found;
		Set<String> dict;
		
		System.out.println("\nEnter word to search");
		InputStream fis = new FileInputStream(wordFile);
		BufferedReader dictReader = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
		
		dict = new HashSet<String>();
		while ((line = dictReader.readLine()) != null) {
			dict.add(line);
		}

		dictReader.close();
		dictReader = null;
		fis = null;

		BufferedReader inputreader = new BufferedReader(new InputStreamReader(System.in));
		String mistake = inputreader.readLine();

		Map<String, Integer> patternDetails = new HashMap<String, Integer>();
		while (null != mistake) {
			found = false;
			suggestion = "NO SUGGESTION";

			mistake = SpellChecker.removeTripleRepeat(mistake.toLowerCase());

			if (SpellChecker.checkIfDouble(mistake))
				SpellChecker.checkRepeatLetters(mistake, 0, 0, suggestion, found, dict);
			else 
				SpellChecker.checkVowels(mistake, 0, suggestion, found, dict);
			String x = suggestion;

			if (null == inputreader) {
				break;
			}

			inputreader.close();
			inputreader = null;

			File directory = new File(rootPath);
			String[] filesList = directory.list();

			patternDetails = findPatternExistence(filesList, mistake);

			Searches.searches(x);
			Searches.dispHist();
		}
		MaintainHistory.maintainRecentHistory(mistake);
		WordSuggestion.wordSuggestion(mistake);
		return patternDetails;
	}

	private static Hashtable<String, Integer> findPatternExistence(String[] filesList, String pattern) {
		int temp = 0;
		Hashtable<String, Integer> patternDetails = new Hashtable<String, Integer>();
		for (String file : filesList) {

			int count = 0;
			BoyerMoore bmObject = new BoyerMoore(pattern);

			In in = new In(file);
			String s = in.readAll();
			String original = s.replaceAll("[^\\x00-\\x7f]+", "");
			int offset = bmObject.search(original);
			while (offset != original.length()) {
				count += 1;
				if (offset + pattern.length() >= original.length()) {
					break;
				}

				s = original.substring(offset + pattern.length());
				temp = offset + pattern.length();
				offset = bmObject.search(s) + temp;
			}
			if (count > 0) {
				String txtToHtml = file.replace(".txt", ".html");
				String basePath = htmlBasePath + txtToHtml; 

				patternDetails.put(basePath, count);
			}
		}

		Iterator it = patternDetails.entrySet().iterator();
		ArrayList<String> links = new ArrayList<>();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			links.add((String) pair.getKey());
		}

		ArrayList<String> sorted = PRSortLinks.getSortedLinks(links, pageRankPath);
		Hashtable<String, Integer> sortedLinks = new Hashtable<String, Integer>();
		for(int i=0; i<sorted.size(); i++){
			int tempCount = patternDetails.get(sorted.get(i));
			sortedLinks.put(sorted.get(i), tempCount);
		}
		return sortedLinks;
	}
}

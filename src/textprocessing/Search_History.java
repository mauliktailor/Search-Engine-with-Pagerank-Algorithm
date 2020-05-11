package textprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Search_History {

	static String path = "Recent_History_Data.txt";

	static void sortRecentHistoryFile() throws IOException {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			Stack<String> lines = new Stack<String>();
			String line = br.readLine();
			while (line != null) {
				lines.push(line);
				line = br.readLine();
			}

			while (!lines.empty()) {
				System.out.println(lines.pop());
			}

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Exception in file Search_History: "+e.toString());
				}
			}
		}
	}
}

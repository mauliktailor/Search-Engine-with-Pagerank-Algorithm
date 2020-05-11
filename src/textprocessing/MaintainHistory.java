package textprocessing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MaintainHistory {

	static void maintainRecentHistory(String pattern) throws IOException {
		File file = new File("Recent_History_Data.txt");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));
		if (!pattern.equalsIgnoreCase("NO SUGGESTION")) {
			writer.write(pattern + "\r\n");
		}
		writer.close();
	}
}

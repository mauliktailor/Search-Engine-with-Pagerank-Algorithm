package textprocessing;

import java.util.ArrayList;

public class Searches {

	public static ArrayList<String> l = new ArrayList<String>();

	public static void searches(String word) {
		l.add(word);
	}

	public static void dispHist() {
		try {
			for (int i = l.size() - 1; i >= l.size() - 5; i--) {
				System.out.println("\nSPELL CHECK OPTIONS:" + "\n" + l.get(i));
			}
		} catch (Exception e) {
			System.out.println();
		}
	}
}

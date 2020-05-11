package textprocessing;

import java.io.IOException;
import java.util.Scanner;

public class ACCFinalProject {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		Scanner myObj = new Scanner(System.in);
		System.out.println("Web Search Engine...\n");
		System.out.println("1. Search");
		System.out.println("2. Search History");
		System.out.println("3. Selection Problem");
		System.out.println("4. Exit");
	    System.out.println("\nPlease select an option");

	    String option = myObj.nextLine();
	    System.out.println("Selected option is: " + option);
		switch (Integer.valueOf(option)) {
		  case 1:
		    System.out.println("Searching...");
		    SearchPattern.searchWord();
		    break;
		  case 2:
		    System.out.println("Recent History Details...");
		    Search_History.sortRecentHistoryFile();
		    break;
		  case 3:
		    System.out.println("Selection Problem...");
		    MaximumFrequencyData.showMaxValueFiles();
		    break;
		  case 4:
			  System.out.println("Exit...");
			  break;
		  default:
			  System.out.println("Invalid option...");
			  break;
		}	
	}
}

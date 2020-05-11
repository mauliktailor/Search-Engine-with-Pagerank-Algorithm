package textprocessing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PRSortLinks {
	// get sorted links according to page rank
	// pass the html file directory and the list obtain in the search results.
	public static ArrayList<String> getSortedLinks(ArrayList<String> list, String filePath) {

//		String filePath = "D:\\Test\\www.tutorialspoint.com";
		HashMap<String, Double> pageMap= MakeAdjacency.create(filePath);
//		double d = pageMap.get("D:\\Test\\www.tutorialspoint.com\\add_and_subtract_fractions\\add_or_subtract_fraction_with_different_denominator_advanced.html");
//		System.out.println(d);
		ArrayList<Double> rankList = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i)!= null){
				double rank=1;
				if(pageMap.containsKey(list.get(i)))
					rank = pageMap.get(list.get(i));
				rankList.add(rank);
			}
		}
		ArrayList<Double> tempList= rankList; 
		Collections.sort(rankList);
		ArrayList<String> sortedLinks= new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			double a =rankList.get(i);
			int index = tempList.indexOf(a);
			sortedLinks.add(list.get(index));
		}
		
		return sortedLinks;
	}
	
	public static void main(String[] args) {
		ArrayList<String> links  = new ArrayList<>();
		//links.add("D:\\Test\\www.tutorialspoint.com\\IF\\if_else_if_else_statement.html");
		links.add("C:\\Users\\Deepti\\Downloads\\FinalProject\\Tutorial_Point\\swift\\nested_if_statement.html");
		links.add("C:\\Users\\Deepti\\Downloads\\FinalProject\\Tutorial_Point\\swift\\swift_access_control.html");
		
		ArrayList<String> sorted = getSortedLinks(links, "C:\\Users\\Deepti\\Downloads\\FinalProject\\Tutorial_Point");
		System.out.println(sorted);
	}
}

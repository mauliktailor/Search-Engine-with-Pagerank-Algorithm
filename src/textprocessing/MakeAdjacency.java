package textprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MakeAdjacency {

	public static HashMap<String, Double> create(String filePath) {
//		String filePath = "D:\\Test\\www.tutorialspoint.com";
		File f = new File(filePath);
		ArrayList<String> dirs= listDir(f);
//		System.out.println(dirs);
		ArrayList<String> allLinks = new ArrayList<>();
		for(String path:dirs) {
			File f1 =  new File(path);
			ArrayList<String> htmlFiles = listFiles(f1);
			for(String htmlFile: htmlFiles) {
				ArrayList<String> links = ExtractLinks.extract(htmlFile);
				allLinks = (ArrayList<String>) union(allLinks, links);
			}
		}
		System.out.println("Total Links: "+allLinks.size());
		int[][] adjMtx = new int[allLinks.size()][allLinks.size()];
		
		for(String path:dirs) {
			File f1 =  new File(path);
			ArrayList<String> htmlFiles = listFiles(f1);
			for(String htmlFile: htmlFiles) {
				int i = allLinks.indexOf(htmlFile);
				ArrayList<String> links = ExtractLinks.extract(htmlFile);
				for(int j=0; j<links.size();j++) {
					int k= allLinks.indexOf(links.get(j));
					if(i != -1)
						adjMtx[i][k]=1;
				}
			}
		}
		
		// to check
//		int g=0;
//		for(int i= 0; i<adjMtx[1].length; i++) {
//			if(adjMtx[1][i] == 1) {
//				g++;
//			}
//		}
		PageRank p = new PageRank(allLinks.size());
		p.path = adjMtx;
		p.calc(allLinks.size());

//		for(int i =0 ; i<p.pagerank.length; i++) {
//			System.out.println(p.pagerank[i]);
//		}
		HashMap<String, Double> pageMap = new HashMap<>(); 
		for(int i=0;i< allLinks.size(); i++) {
			pageMap.put(allLinks.get(i), p.pagerank[i]);
		}
		return pageMap;
	}
	//Union of list
	public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }
	// List all directory.
	private static ArrayList<String> listDir(File f) {
		ArrayList<String> files =  new ArrayList<>();
	    for (final File fileEntry : f.listFiles()) {
	    	if(fileEntry.isDirectory()) {
		        files.add(fileEntry.getPath());
	    	}
	    }
	    return files;
	}
	
	// Method to get all the files in the directory
		public static ArrayList<String> listFiles(File folder) {
			ArrayList<String> files =  new ArrayList<>();
		    for (final File fileEntry : folder.listFiles()) {
		    	if(!fileEntry.isDirectory()) {
			        if (fileEntry.getName().contains(".html")) {
			        	files.add(fileEntry.getPath());
			        }
		    	}
		    }
		    return files;
		}
}

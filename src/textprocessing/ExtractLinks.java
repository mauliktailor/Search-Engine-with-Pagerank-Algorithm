package textprocessing;
/* This program extract all links from the html file.
	It has static method extract
	Pass the path of the html file in the Extract method. 
	It returns the List of the links in the html file  
*/
import java.io.File;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import textprocessing.In;

public class ExtractLinks {
	public static void main(String[] args) {
		String path = "D:\\tutorialspoint\\www.tutorialspoint.com\\advanced_excel\\index.html";
		ArrayList<String> l =extract(path);
		System.out.println(l);
		
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
	public static ArrayList<String> extract(String path) {
		ArrayList<String> listLinks = new ArrayList<>();
		In in = new In(path);
		String html = in.readAll();
		Document doc = Jsoup.parse(html);
        Elements links = doc.select("a[href]");
        
//        System.out.println("\nLinks: "+ links.size());
        for (Element link : links) {
        	if(!link.attr("href").contains("javascript")) {
//        		print(" * a: <%s>  (%s)", getFullPath(link.attr("href"), path), trim(link.text(), 35));
        		String s =getFullPath(link.attr("href"), path);
        		listLinks.add(s);
        	}
        }
        return listLinks;
	}
	private static String getFullPath(String attr, String path) {
		// TODO Auto-generated method stub
		if(attr.contains("http")) {
			return attr;
		}
		else {
			String a="";
			String[] folder = path.split("\\\\");
			String[] tempPath = attr.split("/");
			if(tempPath[0].equals("..")) {
				for(int i =0; i<folder.length-2;i++) {
					a+= folder[i]+"/";
				}
				a+= attr.substring(3);
			}
			else {
				for(int i =0; i<folder.length-1;i++) {
					a+= folder[i]+"/";
				}
				a+=attr;
			}

			return a.replace("/", "\\");
		}
	}
	private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
	private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}

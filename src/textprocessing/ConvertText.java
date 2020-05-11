package textprocessing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import textprocessing.In;

public class ConvertText {
	
	public String path;
	public static void convert(String filePath) {
		File f = new File(filePath);
		ArrayList<String> files = listFiles(f);
		
		for(int i=0; i< files.size(); i++) {
			createTextFile(files.get(i));
		}
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
	
	// Method to create the Text files form HTML and save them into folder "Text files"
	private static void createTextFile(String string) {
		// TODO Auto-generated method stub
		In in = new In(string);
		String html = in.readAll();
		Document doc = Jsoup.parse(html);
		
		//Convert HTML to text
		String text = doc.text();
		
		// Store text in file in TextOP directory
		
		// get file name without the .htm  extension
		String[] splitArr =  string.split(".htm");
		String[] folder = splitArr[0].split("\\\\");
		String fileName = folder[folder.length -1];
		
		OutputStream os = null;
		
		//make a directory.
		File theDir = new File("TextOP\\"+folder[folder.length-2]);
		try{
	        theDir.mkdir();
	    } 
	    catch(SecurityException se){}
		File file;
		if(!folder[folder.length-2].equals("www.tutorialspoint.com")) {
			file = new File("TextOP\\"+folder[folder.length-2]+"\\"+fileName+".txt");
		}
		else {
			file = new File("TextOP\\"+fileName+".txt");
		}
		
    
        try{
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
	}
	
	public static void main(String[] args) {
		String filePath = "D:\\Test\\www.tutorialspoint.com";
		File f = new File(filePath);
		convert(filePath);	
		ArrayList<String> dirs= listDir(f);
		for(int i = 0 ; i < dirs.size(); i++) {
			System.out.println(i*100/dirs.size());
			convert(dirs.get(i));
		}
	}

	private static ArrayList<String> listDir(File f) {
		ArrayList<String> files =  new ArrayList<>();
	    for (final File fileEntry : f.listFiles()) {
	    	if(fileEntry.isDirectory()) {
		        files.add(fileEntry.getPath());
	    	}
	    }
	    return files;
	}
}


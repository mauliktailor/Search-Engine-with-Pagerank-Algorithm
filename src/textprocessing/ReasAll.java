package textprocessing;

public class ReasAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "C:\\Users\\Deepti\\Downloads\\FinalProject\\TextOP\\5g\\5g_advancement.txt";
		In in = new In(path);
		String s = in.readAll();
		System.out.println(s);
	}

}

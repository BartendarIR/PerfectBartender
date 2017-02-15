import java.io.File;
import java.io.FileNotFoundException;

public class main {

	public static void main(String[] args) throws FileNotFoundException {

		File folder = new File("Document/");
		File[] listOfFiles = folder.listFiles();
		String s;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				s=file.getName();
				if(!s.equals("stopword.txt")){
					

					Data data = new Data("Document/"+s);
				}
			}
		}

		
	}
}

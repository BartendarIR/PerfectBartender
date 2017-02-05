import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
//This is the raw data I will be parsing the each raw data to a Hash map. so we know how often each words has appeared in the document.

public class Data {
	private static List<String> list;
	private static String drinkName;
	private static String body = "";

	public Data(String filename) throws FileNotFoundException {
		list = Data.init(filename);
		splitName();
		//System.out.println(list.size());
		// Removing the stop words
		removeStopWord(list);
		// cleaning up specific words like 's and quotes
		remove( "'");
		remove( "”");
		remove( "“");
		//System.out.println(list.size());

		// prints the list
		printList();
	}

	// Parses through the drink name

	private void splitName() {
		String[] temp = new String[2];
		temp = drinkName.split(":");
		drinkName = temp[1];
		drinkName = drinkName.trim();
	}
	// Initial import of the file to make get the text into the list

	@SuppressWarnings("resource")
	public static List<String> init(String filename) throws FileNotFoundException {
		Scanner input;
		List<String> list = new ArrayList<String>();
		String line = null;

		input = new Scanner(new File(filename));
		while (input.hasNextLine()) {
			line = input.nextLine();
			body += line + "\n";
			if (drinkName == null) {
				drinkName = line;
			}
			StringTokenizer strings = new StringTokenizer(line, "[ $*+=-\\?!.,:;()\\/]#&0123456789");
			while (strings.hasMoreElements()) {
				//user is not using realuger single quote i am replacing here also before removing stop words.
				list.add(strings.nextToken().toLowerCase().replace("’", "'"));
			}
		}
		return list;

	}

	@SuppressWarnings("resource")
	private static void removeStopWord(List<String> list) throws FileNotFoundException {
		String line = null;
		Scanner input;
		input = new Scanner(new File("Document/stopword.txt"));

		while (input.hasNextLine()) {
			line = input.nextLine();
			for (int x = 0; x < list.size(); x++) {
				if (line.equals(list.get(x))) {
					list.remove(x);
				}
			}

		}
	}

	private static void printList() {
		for (int x = 0; x < list.size(); x++) {
			System.out.println(list.get(x));
		}
	}

	private static void remove( String s) {
		String temp;
		for (int x = 0; x < list.size(); x++) {
			if (((String) list.get(x)).contains(s)) {
				temp = ((String) list.get(x)).replaceAll(s, "");
				list.remove(x);
				list.add(x, temp);
			}
		}
	}

	public String getDrinkName() {
		return drinkName;
	}

	public String getBody() {
		return body;
	}

	public List<String> getList() {
		return list;
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
//This is the raw data I will be parsing the each raw data to a Hash map. so we know how often each words has appeared in the document.

public class Data {
	private List<String> list;
	private String drinkName;
	private String body = "";
	private Map<String, Integer> map;

	public Data(String filename) throws FileNotFoundException {
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
			StringTokenizer strings = new StringTokenizer(line, "[ –%$*+=-\\?!.,:;()\\/]#&0123456789:");
			while (strings.hasMoreElements()) {
				// user is not using realuger single quote i am replacing here
				// also before removing stop words.
				list.add(strings.nextToken().toLowerCase().replace("’", "'"));
			}
		}
		input.close();
		
		splitName();
		// System.out.println(list.size());
		// Removing the stop words
		removeStopWord(list);
		// cleaning up specific words like 's and quotes
		remove("'",list);
		remove("”",list);
		remove("“",list);
		// System.out.println(list.size());

		// prints the list
		//printList(list);

		map=mapGen(list);
		
		//printMap(map);
		//System.out.println(drinkName);
	}

	public  void printMap() {
		for ( Map.Entry<String , Integer>entry : map.entrySet()) {
			
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}		
	}

	// Parses through the drink name
	private Map<String, Integer> mapGen(List<String> list) {
		map = new HashMap<String, Integer>();
		for (String s : list) {
			if (s.equals(""))
			{
				continue;
			}
			else if (!map.containsKey(s)) { // first time we've seen this string
				map.put(s, 1);
			} else {
				int count = map.get(s);
				map.put(s, count + 1);
			}
		}
		return map;
	}

	private void splitName() {
		String[] temp = new String[2];
		temp = drinkName.split(":");
		drinkName = temp[1];
		drinkName = drinkName.trim();
		drinkName = drinkName.substring(0, drinkName.length()-2);
	}
	// Initial import of the file to make get the text into the list



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

	private  void printList(List<String> list) {
		for (int x = 0; x < list.size(); x++) {
			System.out.println(list.get(x));
		}
	}

	private  void remove(String s, List<String> list) {
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

	public Map<String, Integer> getMap() {
	
		return map;
	}
}

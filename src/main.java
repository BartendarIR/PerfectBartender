import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

public class main {
	private static List<Data> data;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		data = new ArrayList<Data>();
		File folder = new File("Document/");
		File[] listOfFiles = folder.listFiles();
		String s;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				s = file.getName();
				if (!s.equals("stopword.txt")) {
					data.add(new Data("Document/" + s));
				}
			}
		}
		Scanner sc = new Scanner(System.in);
		String query = sc.nextLine();
		search(query);
		System.out.println("test");
	}

	public static void search(String query) throws InterruptedException {
		int sum;
		List<Sets> list = new ArrayList<Sets>();
		// List<String> queryS = new ArrayList<String>();
		// need to tokenize the query testing with only 1 word query.

		for (Data d : data) {
			sum = 0;
			Map<String, Integer> map = d.getMap();
			// System.out.println(d.getDrinkName());

			StringTokenizer strings = new StringTokenizer(query);
			while (strings.hasMoreElements()) {
				String s =strings.nextToken();
				for (Map.Entry<String, Integer> entry : map.entrySet()) {
					if (StringUtils.getJaroWinklerDistance(s, entry.getKey()) > .8)
						sum += entry.getValue();
				}
				
			}
			
			Sets x = new Sets(d, sum);
			list.add(x);
			Collections.sort(list);

		}
		for (int i = 0; i <= 5; i++) {
			Sets l = list.get(i);
			System.out.println(l.getData().getDrinkName() + "  " + l.getValue());
			// System.out.println(l.getData().getBody());
		}
	}
}

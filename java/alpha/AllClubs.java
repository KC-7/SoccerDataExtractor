package alpha;
import static alpha.DataScraper.END;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AllClubs {
	
	private final static String urlString = "http://www.ussoccerda.com/all-clubs";
	private final static String[] posCriteria = {"<tbody>", "img", "club_directory_list", "<td>"};
	private final static String[] negCriteria = {"<//tbody>", END, END, "</td>"};
	private final static String[] regexs = {"<.*?>", "\\s{2,}"};
	
	public static void main(String[] args) throws Exception {
		long initTime = System.currentTimeMillis();
		String data = new DataScraper(urlString, posCriteria, negCriteria, regexs, System.lineSeparator()).scrapeData();
		System.out.println(CSV.getCSV(aggregateData(data)));
		System.out.println(System.currentTimeMillis() - initTime);
	}
	
	private static List<List<String>> aggregateData(String data) throws Exception {
		List<String> ClubName = new ArrayList<String>();
		List<String> City = new ArrayList<String>();
		List<String> State = new ArrayList<String>();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes()))); 
		String line;
		int lineIndex = 0;
		while ((line = in.readLine()) != null) {
			if (line.startsWith("Boys") || line.startsWith("Girls")) {
				lineIndex = 0;
			} else {
				if (lineIndex == 0) {
					ClubName.add(line);
				} else if (lineIndex == 1) {
					String[] split = line.split(", ");
					City.add(split[0]);
					State.add(getStateCode(split[1].replace(".","").toUpperCase()));
				}
				lineIndex++;
			}
		}
		return Arrays.asList(ClubName, City, State);
	}
	
	private static String getStateCode(String state) {
		Map<String, String> states = new HashMap<String, String>();
		states.put("ARIZ","AZ");
		states.put("BC", "BC");
		states.put("CALIF","CA");
		states.put("COLO","CO");
		states.put("CONN","CT");
		states.put("DC","DC");
		states.put("FLA","FL");
		states.put("GA","GA");
		states.put("ILL","IL");
		states.put("IND","IN");
		states.put("KAN","KS");
		states.put("MD","MD");
		states.put("MASS","MA");
		states.put("MICH","MI");
		states.put("MINN","MN");
		states.put("MO","MO");
		states.put("NH","NH");
		states.put("NJ","NJ");
		states.put("NY","NY");
		states.put("NC","NC");
		states.put("OHIO","OH");
		states.put("ORE","OR");
		states.put("PA","PA");
		states.put("QUEBEC", "QC");
		states.put("RI","RI");
		states.put("SC","SC");
		states.put("TEXAS","TX");
		states.put("UTAH","UT");
		states.put("VA","VA");
		states.put("WASH","WA");
		return states.get(state);
	}
	
}

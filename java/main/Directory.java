package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.jaunt.component.Table;

public class Directory {

	public static String directory() throws JauntException {

		UserAgent userAgent = new UserAgent();
		userAgent.visit("http://ussoccerda.com/all-clubs");
		Table table = userAgent.doc.getTable("<table id=club_directory_list>");
		
		List<String> names = new ArrayList<String>();
		List<String> cities = new ArrayList<String>();
		List<String> states = new ArrayList<String>();
		
		names.add("ClubName");
		cities.add("ClubCity");
		states.add("ClubStateCode");
		
		Elements nameCol = table.getCol(1);
		Elements locationCol = table.getCol(2);
		
		for(int i = 2; i < nameCol.size(); i++) {
			String[] locationData = locationCol.getElement(i).innerText().trim().split(", ", 2);
			Map<String, String> stateMap = getStateMap();
			names.add(nameCol.getElement(i).innerText().trim());
			cities.add(locationData[0]);
			try {
				states.add(stateMap.get(locationData[1]));
			} catch (Exception e) {
				states.add("");
			}
		}
		
		List<List<String>> dataColumns = new ArrayList<List<String>>();
		dataColumns.add(names);
		dataColumns.add(cities);
		dataColumns.add(states);
		
		return CSV.getCSV(dataColumns);
		
	}

	private static Map<String, String> getStateMap() {
		Map<String, String> stateMap = new HashMap<String, String>();
		stateMap.put("Ala.", "AL");
		stateMap.put("Alaska", "AK");
		stateMap.put("Ariz.", "AZ");
		stateMap.put("Ark.", "AR");
		stateMap.put("B.C.", "BC"); //
		stateMap.put("Calif.", "CA");
		stateMap.put("Colo.", "CO");
		stateMap.put("Conn.", "CT");
		stateMap.put("D.C.", "DC");
		stateMap.put("Del.", "DE");
		stateMap.put("Fla.", "FL");
		stateMap.put("Fla", "FL"); //
		stateMap.put("Ga.", "GA");
		stateMap.put("Hawaii", "HI");
		stateMap.put("Idaho", "ID");
		stateMap.put("Ill.", "IL");
		stateMap.put("Ind.", "IN");
		stateMap.put("Iowa", "IA");
		stateMap.put("Kan.", "KS"); //
		stateMap.put("Kans.", "KS");
		stateMap.put("Ky.", "KY");
		stateMap.put("La.", "LA");
		stateMap.put("Maine", "ME");
		stateMap.put("Md.", "MD");
		stateMap.put("Mass.", "MA");
		stateMap.put("Mich.", "MI");
		stateMap.put("Minn.", "MN");
		stateMap.put("Miss.", "MS");
		stateMap.put("Mo.", "MO");
		stateMap.put("Mont.", "MT");
		stateMap.put("Nebr.", "NE");
		stateMap.put("Nev.", "NV");
		stateMap.put("N.H.", "NH");
		stateMap.put("N.J.", "NJ");
		stateMap.put("N.M.", "NM");
		stateMap.put("N.Y.", "NY");
		stateMap.put("N.C.", "NC");
		stateMap.put("N.D.", "ND");
		stateMap.put("Ohio", "OH");
		stateMap.put("Okla.", "OK");
		stateMap.put("Ore.", "OR");
		stateMap.put("Pa.", "PA");
		stateMap.put("Quebec","QC"); //
		stateMap.put("R.I.", "RI");
		stateMap.put("S.C.", "SC");
		stateMap.put("S.D.", "SD");
		stateMap.put("Tenn.", "TN");
		stateMap.put("Tex.", "TX");
		stateMap.put("Texas", "TX"); // 
		stateMap.put("Utah", "UT");
		stateMap.put("Vt.", "VT");
		stateMap.put("Va.", "VA");
		stateMap.put("VA.", "VA"); // 
		stateMap.put("Wash.", "WA");
		stateMap.put("W.Va.", "WV");
		stateMap.put("Wis.", "WI");
		stateMap.put("Wyo.", "WY");
		return stateMap;
	}

}

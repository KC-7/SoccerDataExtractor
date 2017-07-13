package main;

import java.util.ArrayList;
import java.util.List;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class Staff {

	public static String staff() throws JauntException {
		
		
		UserAgent userAgent = new UserAgent();
		userAgent.visit("http://afcl.ussoccerda.com/club-staff");
		Element div = userAgent.doc.findFirst("<div class=BoardTrinket>");
		
		List<String> firstNames = new ArrayList<String>();
		List<String> lastNames = new ArrayList<String>();
		List<String> roles = new ArrayList<String>();
		List<String> emailAddresses = new ArrayList<String>();
		List<String> phoneNumbers = new ArrayList<String>();
		
		Elements spans = div.findEach("<span class>");
		for (Element span : spans) {
			String text = span.innerText().trim();
			switch (span.getAt("class").trim()) {
			case "bm-name":
				String[] nameData = text.split(" ",2);
				firstNames.add(nameData[0]);
				lastNames.add(nameData[1]);
				break;
			case "bm-role":
				roles.add(text);
				break;
			case "bm-email":
				emailAddresses.add(text);
				break;
			case "bm-phone":
				if (phoneNumbers.size() == firstNames.size() - 1) {
					phoneNumbers.add(text);
				} else {
					int lastIndex = phoneNumbers.size() - 1;
					if (!phoneNumbers.get(lastIndex).contains("cell")) {
						phoneNumbers.set(lastIndex, text);
					}
				}
				break;
			}
		}
		
		List<List<String>> dataColumns = new ArrayList<List<String>>();
		dataColumns.add(firstNames);
		dataColumns.add(lastNames);
		dataColumns.add(roles);
		dataColumns.add(emailAddresses);
		dataColumns.add(phoneNumbers);
		return CSV.getCSV(dataColumns);
		
		
	}

}

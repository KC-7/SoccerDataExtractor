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
		List<String> cellPhoneNumbers = new ArrayList<String>();
		List<String> homePhoneNumbers = new ArrayList<String>();
		List<String> workPhoneNumbers = new ArrayList<String>();
		
		firstNames.add("StaffFirstName");
		lastNames.add("StaffLastName");
		roles.add("StaffRole");
		emailAddresses.add("StaffEmailAddress");
		cellPhoneNumbers.add("StaffCellPhoneNumber");
		homePhoneNumbers.add("StaffHomePhoneNumber");
		workPhoneNumbers.add("StaffWorkPhoneNumber");
		
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
				String cleanNumber = text.replaceAll("\\D", "");		
				String type = text.replaceAll("[^A-z]", "");
				switch (type) {
				case "cell":
					cellPhoneNumbers.add(cleanNumber);
					homePhoneNumbers.add("");
					workPhoneNumbers.add("");
					break;
				case "home":
					homePhoneNumbers.add(cleanNumber);
					cellPhoneNumbers.add("");
					workPhoneNumbers.add("");
					break;
				case "work":
					workPhoneNumbers.add(cleanNumber);
					homePhoneNumbers.add("");
					cellPhoneNumbers.add("");
					break;
				}
				break;
			}
		}
		
		List<List<String>> dataColumns = new ArrayList<List<String>>();
		dataColumns.add(firstNames);
		dataColumns.add(lastNames);
		dataColumns.add(roles);
		dataColumns.add(emailAddresses);
		dataColumns.add(cellPhoneNumbers);
		dataColumns.add(homePhoneNumbers);
		dataColumns.add(workPhoneNumbers);
		return CSV.getCSV(dataColumns);
		
		
	}

}

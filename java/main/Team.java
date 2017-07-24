package main;

import java.util.ArrayList;
import java.util.List;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.jaunt.component.Table;

public class Team {

	public static String team() throws JauntException {

		
		UserAgent userAgent = new UserAgent();
		userAgent.visit("http://afcl.ussoccerda.com/sam/teams/index.php?team=1642601");
		Element tableElem = userAgent.doc.findFirst("<div class=double-col-right>").findFirst("<table class=rosterTable>");
		Table table = userAgent.doc.getTable(tableElem);
		
		List<String> firstNames = new ArrayList<String>();
		List<String> lastNames = new ArrayList<String>();
		List<String> roles = new ArrayList<String>();
		List<String> emailAddresses = new ArrayList<String>();
		
		firstNames.add("StaffFirstName");
		lastNames.add("StaffLastName");
		roles.add("StaffRole");
		emailAddresses.add("StaffEmailAddress");
		
		Elements col = table.getCol(1);
		
		for (int i = 2; i < col.size(); i++) {
			Element cell = col.getElement(i);
			String[] nameData = cell.findFirst("<h1 class=rosterModalName>").innerText().trim().split(" ",2);
			firstNames.add(nameData[0]);
			lastNames.add(nameData[1]);
			roles.add(cell.findFirst("<h1 class=rosterModalRole>").innerText().trim());
			emailAddresses.add(cell.findFirst("<a href>").getAt("href").replace("mailto:", ""));
		}
		
		List<List<String>> dataColumns = new ArrayList<List<String>>();
		dataColumns.add(firstNames);
		dataColumns.add(lastNames);
		dataColumns.add(roles);
		dataColumns.add(emailAddresses);
		return CSV.getCSV(dataColumns);
		
		
	}

}

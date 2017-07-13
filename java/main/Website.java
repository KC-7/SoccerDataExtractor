package main;

import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.jaunt.component.Hyperlink;

public class Website {
	
	public static String website() throws JauntException {
		
		
		UserAgent userAgent = new UserAgent();
		userAgent.visit("http://afcl.ussoccerda.com/club-staff");
		Hyperlink link = userAgent.doc.getHyperlink("Official Club Website");
		
		return link.getHref();

		
	}

}

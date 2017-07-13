package alpha;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class DataScraper {
	
	public final static String END = "</html>";
	private String urlString;
	private String[] posCriteria, negCriteria;
	private String[] regexs;
	private String append;
	
	public DataScraper(String urlString, String[] posCriteria, String[] negCriteria, String[] regexs, String append) {
		this.urlString = urlString;
		this.posCriteria = posCriteria;
		this.negCriteria = negCriteria;
		this.regexs = regexs;
		this.append = append;
	}
	
	public String scrapeData() throws Exception {
		final URL url = new URL(urlString);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));  
        StringBuilder sb = new StringBuilder();
        String line;
        
        boolean[] flags = new boolean[posCriteria.length];
        
        while ((line = in.readLine()) != null) {
        	
        	for (int i = 0; i < flags.length; i++) {
        		if (line.contains(posCriteria[i])) flags[i] = true;
        		else if (line.contains(negCriteria[i])) flags[i] = false;
        	}
        	
        	boolean flagsMet = true;
        	for (boolean b : flags) {
        		flagsMet = b && flagsMet;
        	}
        	if (flagsMet) {
        		if (regexs != null) {
	        		for (String regex : regexs) {
	        			line = line.replaceAll(regex, "");
	        		}
        		}
        		if (!line.isEmpty()) {
	        		sb.append(line);
	        		sb.append(append);
        		}
        	}
        	
        }
        
        in.close();
        return sb.toString();
	}

}
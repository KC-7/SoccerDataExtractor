package main;
import java.util.List;

public class CSV {
	
	public static String getCSV(List<List<String>> dataColumns) {
		
		final StringBuilder sb = new StringBuilder();
		for (int row = 0; row < dataColumns.get(0).size(); row++) {
			for (int col = 0; col < dataColumns.size(); col++) {
				sb.append(dataColumns.get(col).get(row));
				if(col < dataColumns.size() - 1) sb.append(", ");
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

}

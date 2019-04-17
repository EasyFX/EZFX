	package Loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class AttributesFilter {
	private static List<String> attributesFilters = new ArrayList<>();
	
	public static void loadAttributesFilters(String FilterPath) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(FilterPath)));
			String Line;
			while ((Line = bufferedReader.readLine()) != null) {
				attributesFilters.add(Line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static boolean contains(String string) {
			return attributesFilters.contains(string);
	}
	
}

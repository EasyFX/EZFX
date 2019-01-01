package Loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class MethodFilter {
	private static List<String> methodFilters = new ArrayList<>();
	
	public static void loadMethodFilters(String FilterPath) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(FilterPath)));
			String Line;
			while ((Line = bufferedReader.readLine()) != null) {
				methodFilters.add(Line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static boolean contains(String string) {
			return methodFilters.contains(string);
	}
	
}

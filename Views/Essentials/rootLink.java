// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Essentials;

import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class rootLink {

	static HashMap<String,Parent> rootMap = new HashMap<String,Parent>();
	
	public Parent getRoot(String target) throws Exception {
		
		if(rootMap.containsKey(target)) {
			return rootMap.get(target);
		}
		else {
			rootMap.put(target, FXMLLoader.load(getClass().getResource(target)));
			return rootMap.get(target);
		}
	}
}

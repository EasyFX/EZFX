// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Graphics;

import java.io.File;
import java.util.HashMap;

import javafx.scene.image.Image;

public class imageHandler {

	private imageHandler() {
	}
	
	private static imageHandler INSTANCE;
	
	public static imageHandler getInstance() {
		return INSTANCE == null ? INSTANCE = new imageHandler() : INSTANCE;
	}
	
	static HashMap<String,Image> imgMap = new HashMap<String,Image>();
	
	public Image getImage(String target){
		
		if(imgMap.containsKey(target)) {
			return imgMap.get(target);
		}
		else {
			try{
				imgMap.put(target, new Image(new File(target).toURI().toString()));
			}
			catch(Exception e) {
				System.out.println("ERROR 1A: Missing image files");
			}
			return imgMap.get(target);
		}
	}
}

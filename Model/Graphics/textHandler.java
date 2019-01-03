// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Graphics;

import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class textHandler {
	
	private textHandler() {
	
	}
	
	private static textHandler INSTANCE;
	
	public static textHandler getInstance() {
		return INSTANCE == null ? INSTANCE = new textHandler() : INSTANCE;
	}
	
	public void setText(Text[] set, String[] strings){
		for(int i = 0 ; i < set.length ; i++) {
			set[i].setText(strings[i]);
		}
	}

	public void setText(TextField[] set, String[] strings){
		for(int i = 0 ; i < set.length ; i++) {
			set[i].setText(strings[i]);
		}
	}
	
	public void clearText(Text ... set){
		for(Text t : set) {
			t.setText("");
		}
	}
	
	public void clearText(TextField ... set){
		for(TextField t : set) {
			t.setText("");
		}
	}



}


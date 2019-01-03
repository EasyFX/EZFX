// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Graphics;

import Animations.AnimationFactory;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class buttonHandler {

	static AnimationFactory factory = new AnimationFactory();

	public static void glowButton(Button b) {
		if(b.getOpacity() == 0) {
			b.setStyle("-fx-background-color: #111111");
			b.setTextFill(Color.WHITE);
			factory.buildScale(b, 300, 0, 1, 1, 1.2, 1.2, 0.1, 0.1, 1, false).play();
		}
	}
	
	public static void unglowButton(Button b) {
		if(b.getOpacity() == 0) {
			b.setStyle("-fx-background-color: #000000");
			b.setTextFill(Color.rgb(255, 196, 100));
			factory.buildScale(b, 300, 0, 1.2, 1.2, 1, 1, 0.1, 0.1, 1, false).play();
		}
	}
	
	public static void setButton(boolean fade, Button ... set) {
		for(Button b : set) {
			b.setCursor(Cursor.OPEN_HAND);
			b.setDisable(false);
			if(fade) factory.buildFade(b, 1000, 0, 0.0, 1.0, 0.1, 1, false).play();
		}
	}
	
	public static void unsetButton(boolean fade, Button ... set) {
		for(Button b : set) {
			b.setCursor(Cursor.DEFAULT);
			b.setDisable(true);
			if(fade) factory.buildFade(b, 1000, 0, 1.0, 0.0, 0.1, 1, false).play();
		}
	}
}
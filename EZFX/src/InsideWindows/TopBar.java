package InsideWindows;

import App.App;
import Utils.Constants;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class TopBar extends HBox {

	public TopBar() {
		setBackground(new Background(new BackgroundFill(Color.rgb(83, 83, 83), null, null)));

		
		setTranslateX(0);
		setTranslateY(0);
		
		
		prefWidthProperty().bind(App.Window.widthProperty());
		
		
		setMinHeight(Constants.HEIGHT/10);
		setPrefHeight(Constants.HEIGHT/6);
		setMaxHeight(Constants.HEIGHT/4);
		
		
		
	}
	
}

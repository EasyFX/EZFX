package InsideWindows;

import App.App;
import Utils.Constants;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Canvas extends Pane {

	private double xOffset, yOffset;

	public Canvas(int xoffset, int yoffset) {
		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		this.xOffset = xoffset;
		this.yOffset = yoffset;

		setTranslateX(Constants.WIDTH / 6 + xOffset);
		setTranslateY(Constants.HEIGHT / 6 + yOffset);


		App.Window.widthProperty().addListener(nmb ->{
			setPrefWidth(App.Window.getWidth()- FirstScene.attributesPanel.getPrefWidth()
				- FirstScene.entityExplorer.getPrefWidth() - 2 * xOffset);
		});
		
		App.Window.heightProperty().addListener(nmb -> {
			setPrefHeight(App.Window.getHeight() - FirstScene.topBar.getPrefHeight() - 2 * yOffset -39);
				
		});
		
		
	}

}

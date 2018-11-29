package InsideWindows;

import App.App;
import Utils.Constants;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AttributesPanel extends VBox {

	public AttributesPanel() {
		setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

		setTranslateX(App.Window.getWidth()-Constants.WIDTH / 6);
		setTranslateY(FirstScene.topBar.getPrefHeight());

		App.Window.widthProperty().addListener(nmb->{
			setTranslateX(App.Window.getWidth()-Constants.WIDTH / 6);
		});

		setMinWidth(Constants.WIDTH / 10);
		setPrefWidth(Constants.WIDTH / 6);
		setMaxWidth(Constants.WIDTH / 4);

		prefHeightProperty().bind(App.Window.heightProperty());
	}

}

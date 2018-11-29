package InsideWindows;

import App.App;
import Utils.Constants;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EntityExplorer extends VBox {

	private TreeView<HBox> ItemTree;
	
	public EntityExplorer() {
		ItemTree = new TreeView<>();
		getChildren().add(ItemTree);
		setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		setupDimensions();
		
	}

	
	
	private void setupDimensions() {
		setTranslateX(0);
		setTranslateY(FirstScene.topBar.getPrefHeight());
		
		setMinWidth(Constants.WIDTH / 10);
		setPrefWidth(Constants.WIDTH / 6);
		setMaxWidth(Constants.WIDTH / 4);
		
		prefHeightProperty().bind(App.Window.heightProperty());
	}
}

package FirstScene;

import App.App;
import Utils.Constants;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Canvas extends Pane {

	public Group Items = new Group();
	private double xOffset = Constants.CANVAS_X_NUDGE, yOffset = Constants.CANVAS_Y_NUDGE;
	
	public Canvas() {
		getChildren().addAll(Items);
		
		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

		setTranslateX(Constants.WIDTH / 6 + xOffset);
		setTranslateY(Constants.HEIGHT / 6 + yOffset);
		Items.setTranslateX(-(Constants.WIDTH / 6 + xOffset));
		Items.setTranslateY(-(Constants.HEIGHT / 6 + yOffset));
		
		VBox vbox = new VBox();
		vbox.setTranslateX(getTranslateX());
		vbox.setTranslateY(getTranslateY());
		vbox.setMaxHeight(getPrefHeight());
		vbox.setMaxWidth(getPrefWidth());

		App.Window.widthProperty().addListener(nmb ->{
			setPrefWidth(App.Window.getWidth()- FirstScene.attributesPanel.getPrefWidth()
					- FirstScene.entityExplorer.getPrefWidth() - 2 * xOffset);
			vbox.setMaxWidth(getWidth() - (vbox.getTranslateX()- getTranslateX()));
		});
		
		App.Window.heightProperty().addListener(nmb -> {
			setPrefHeight(App.Window.getHeight() - FirstScene.topBar.getPrefHeight() - 2 * yOffset -39);
			vbox.setMaxHeight(getHeight() - (vbox.getTranslateY()- getTranslateY()));
		});
		
		
		FirstScene.hierarchyTree.setMainBox(vbox);
		addItem(vbox);
	}

	public void addItem(Node node) {
		Items.getChildren().add(node);
	}

	public void deleteItem(Node node) {
		Items.getChildren().remove(node);
	}
	
	public boolean isInside(double x,double y) {
		if( x < getTranslateX() || x > (getTranslateX() + getWidth()) || y < getTranslateY() || y > (getTranslateY() + getHeight())) {
			return false;
		}
		return true;
	}
}

package FirstScene;

import App.App;
import Utils.Constants;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Control;
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
		setTranslateY(Constants.TOP_BAR_HEIGHT + yOffset);
		Items.setTranslateX(-(Constants.WIDTH / 6 + xOffset));
		Items.setTranslateY(-(Constants.TOP_BAR_HEIGHT + yOffset));
		
		VBox vbox = new VBox();
		vbox.setTranslateX(getTranslateX());
		vbox.setTranslateY(getTranslateY());
		vbox.setMaxHeight(Constants.HEIGHT);
		vbox.setMaxWidth(Constants.WIDTH);
	
		App.Window.widthProperty().addListener(nmb ->{
			setPrefWidth(App.Window.getWidth()- FirstScene.attributesPanel.getPrefWidth()
					- FirstScene.entityExplorer.getPrefWidth() - 2 * xOffset);
		});
		
		App.Window.heightProperty().addListener(nmb -> {
			setPrefHeight(App.Window.getHeight() - FirstScene.topBar.getPrefHeight() - 2 * yOffset -39);
		});
		
		
		FirstScene.hierarchyTree.setMainBox(vbox);
		addItem(vbox);
		
	}

	public void addItem(Node node) {
		Items.getChildren().add(node);
	}

	public void deleteItem(Node node) {
		if(node.getParent() instanceof Pane)
			((Pane)node.getParent()).getChildren().remove(node);
		else if(!(node.getParent() instanceof Control))
			((Group)node.getParent()).getChildren().remove(node);
	}
	
	public boolean isInside(double x,double y) {
		if( x < getTranslateX() || x > (getTranslateX() + getWidth()) || y < getTranslateY() || y > (getTranslateY() + getHeight())) {
			return false;
		}
		return true;
	}
	
}

package InsideWindows;

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

	public static Group Items = new Group();
	private double xOffset, yOffset;
	
	public Canvas(int xoffset, int yoffset) {
		getChildren().addAll(Items);
		
		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		this.xOffset = xoffset;
		this.yOffset = yoffset;

		setTranslateX(Constants.WIDTH / 6 + xOffset);
		setTranslateY(Constants.HEIGHT / 6 + yOffset);
		Items.setTranslateX(-(Constants.WIDTH / 6 + xOffset));
		Items.setTranslateY(-(Constants.HEIGHT / 6 + yOffset));
		
		VBox vbox = new VBox();
		//vbox.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		vbox.setTranslateX(getTranslateX());
		vbox.setTranslateY(getTranslateY());

		App.Window.widthProperty().addListener(nmb ->{
			setPrefWidth(App.Window.getWidth()- FirstScene.attributesPanel.getPrefWidth()
					- FirstScene.entityExplorer.getPrefWidth() - 2 * xOffset);
			vbox.setPrefWidth(getPrefWidth());
		});
		
		App.Window.heightProperty().addListener(nmb -> {
			setPrefHeight(App.Window.getHeight() - FirstScene.topBar.getPrefHeight() - 2 * yOffset -39);
			vbox.setPrefHeight(getPrefHeight());
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

package InsideWindows;

import App.App;
import Utils.Constants;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class TopBar extends HBox {

	private double ButtonCount;
	
	public TopBar(int ButtonCount) {
		this.ButtonCount = ButtonCount;
		setupDimensions();
		setupItems();
		
		
	}
	
	private void setupItems() {
		Button Save = createButton("Save");
		Button Load = createButton("Open");
		
		Save.setOnMouseReleased(event->{
			FirstScene.treeWriter.writeTree(FirstScene.canvas.Items);
		});
		Load.setOnMouseReleased(event ->{
			FirstScene.treeReader.LoadTree(FirstScene.canvas.Items);
		});
		
		getChildren().addAll(Save,Load);
	}
	
	
	private void setupDimensions() {
		setBackground(new Background(new BackgroundFill(Color.rgb(83, 83, 83), null, null)));
		
		setTranslateX(0);
		setTranslateY(0);
		
		prefWidthProperty().bind(App.Window.widthProperty());
		
		setMinHeight(Constants.HEIGHT/10);
		setPrefHeight(Constants.HEIGHT/6);
		setMaxHeight(Constants.HEIGHT/4);		
	}
	
	private Button createButton(String ButtonText){
		Button button = new Button(ButtonText);
		button.setPrefWidth(getPrefWidth() / ButtonCount);
		button.setPrefHeight(getPrefHeight());
		
		return button;
	}
	
}

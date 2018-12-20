package InsideWindows;


import App.App;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class BluePrintScene extends Scene {

	
	public static Group TheSecondOne = new Group();

	public BluePrintScene() {
		super(TheSecondOne, App.Window.getWidth(), App.Window.getHeight());
		if(FirstScene.entityExplorer.Chosen == null) return;
		
		TheSecondOne.getChildren().add(new Button(((Button)FirstScene.entityExplorer.Chosen).getText()));
		
		
	}
	

}

// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Controller;

import java.util.HashMap;

import Essentials.rootLink;
import Graphics.imageHandler;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.application.Application;

public class Driver extends Application{
	
	//Retrieve FXML instances
	private rootLink rLink = new rootLink();
	
	//<<Flyweight>> Prevent over-creation of scenes
	private static HashMap<Parent,Scene> sceneMap = new HashMap<>();
	public static Stage stage;
	
	public static void switchScene(Parent root) {
		if(sceneMap.containsKey(root)) {
			stage.setScene(sceneMap.get(root));
		}
		else {
			sceneMap.put(root, new Scene(root));
			stage.setScene(sceneMap.get(root));
		}
	}
	
	//Initial controller that gets reverted to when logging in/out
	static StartController controller = null;
	
	
	@Override
	public void start(Stage stage){
		Driver.stage = stage;
		
		Parent root = null;
		
		try {
			root = rLink.getRoot("./Start.fxml");
		}
		catch(Exception e) {
			System.out.println("Error A3: FXML fail");
		}
		
		Scene scene = new Scene(root);
		sceneMap.put(root, scene);
		
		stage.getIcons().add(imageHandler.getInstance().getImage("Views/icons/icon_EZFX.png"));

		stage.setScene(scene);
		stage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}



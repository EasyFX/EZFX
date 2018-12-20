package Scene;

import java.util.ArrayList;
import java.util.List;

import App.App;
import InsideWindows.BluePrintScene;
import javafx.scene.Group;
import javafx.scene.Scene;

public class SceneManager {
	private static List<Scene> scenes = new ArrayList<>();
	private static SceneManager sceneManager;
	public static Scene CurrentScene;
	
	private SceneManager() {
		
	}	
	
	public void changeScene(int sceneNumber) {
		if(sceneNumber > 0 || sceneNumber < scenes.size()) {
			if(scenes.get(sceneNumber-1) instanceof BluePrintScene) {
				scenes.get(sceneNumber-1).setRoot(new Group());
				scenes.set(sceneNumber - 1, new BluePrintScene());
			}
			App.mainWindowEventHandler.setUpEventHandlers(CurrentScene,scenes.get(sceneNumber-1));
			CurrentScene = scenes.get(sceneNumber-1);
			App.Window.setScene(CurrentScene);
			App.Window.sizeToScene();
		}
	}
	
	public void addScene(Scene scene) {
		scenes.add(scene);
	}
	
	
	
	public static SceneManager getSceneManager() {
		if(sceneManager == null)
			sceneManager = new SceneManager();
		
		return sceneManager;
	}
}

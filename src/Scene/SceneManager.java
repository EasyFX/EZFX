package Scene;

import java.util.ArrayList;
import java.util.List;

import App.App;
import BlueprintScene.BluePrintScene;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;

@SuppressWarnings("unchecked")
public class SceneManager {
	private static List<Scene> scenes = new ArrayList<>();
	private static SceneManager sceneManager;
	public static Scene CurrentScene;
	private int BLueprintSceneIndex=0;
	
	private SceneManager() {
		
	}	
	
	public void changeScene(int sceneNumber) {
		if(sceneNumber > 0 || sceneNumber < scenes.size()) {
			if(scenes.get(sceneNumber-1) instanceof BluePrintScene) {
				scenes.get(sceneNumber-1).setRoot(new Group());
				scenes.set(sceneNumber - 1, new BluePrintScene(null));
			}
			App.mainWindowEventHandler.setUpEventHandlers(CurrentScene,scenes.get(sceneNumber-1));
			CurrentScene = scenes.get(sceneNumber-1);
			App.Window.setScene(CurrentScene);
			App.Window.sizeToScene();
		}
	}
	
	public void addScene(Scene scene) {
		scenes.add(scene);
		if(scene instanceof BluePrintScene)
			BLueprintSceneIndex = scenes.size()-1;
	}
	
	public void refreshBlueprint(EventType<? extends Event> eventType) {
		scenes.get(BLueprintSceneIndex).setRoot(new Group());
		scenes.set(BLueprintSceneIndex, new BluePrintScene((EventType<Event>) eventType));		
	}
	
	public static SceneManager getSceneManager() {
		if(sceneManager == null)
			sceneManager = new SceneManager();
		
		return sceneManager;
	}

	public void newBlueprint(EventType<? extends Event> eventType) {
		scenes.get(BLueprintSceneIndex).setRoot(new Group());
		scenes.set(BLueprintSceneIndex, new BluePrintScene((EventType<Event>) eventType));		
		App.mainWindowEventHandler.setUpEventHandlers(CurrentScene,scenes.get(BLueprintSceneIndex-1));
		CurrentScene = scenes.get(BLueprintSceneIndex);
		App.Window.setScene(CurrentScene);
		App.Window.sizeToScene();
	}
}

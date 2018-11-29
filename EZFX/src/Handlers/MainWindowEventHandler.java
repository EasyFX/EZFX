package Handlers;

import App.App;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainWindowEventHandler {

	private EventHandler<KeyEvent> KeyHandler = keyEvent -> {
		if (keyEvent.getCode() == KeyCode.ESCAPE) {
			App.Window.close();
		}
	};

	public void setUpEventHandlers(Scene oldScene, Scene newScene) {
		if (oldScene != null)
			RemoveEventHandlers(oldScene);

		AddEventHandlers(newScene);
	}

	private void RemoveEventHandlers(Scene scene) {
		scene.setOnKeyPressed(null);
	}

	private void AddEventHandlers(Scene scene) {
		scene.setOnKeyPressed(KeyHandler);
	}

}

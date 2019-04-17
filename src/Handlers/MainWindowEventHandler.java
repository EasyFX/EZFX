package Handlers;

import App.App;
import FirstScene.AttributesPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MainWindowEventHandler {

	private EventHandler<KeyEvent> KeyHandler = keyEvent -> {
		if (keyEvent.getCode() == KeyCode.ESCAPE) {
			App.Window.close();
		}
		if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.F) {
			AttributesPanel.searchField.requestFocus();
		}
	};

	private EventHandler<MouseEvent> MouseHandler = mouseEvent -> {
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
		scene.addEventFilter(KeyEvent.KEY_PRESSED, KeyHandler);
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, MouseHandler);
	}

}

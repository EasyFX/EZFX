package Handlers;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class NodeKeyHandler {
	private static EventHandler<KeyEvent> DeleteEvent = KeyEvent -> {
		if (KeyEvent.getCode() == KeyCode.DELETE) {
			
		}
	};

	private static Map<String, EventHandler<KeyEvent>> KeyEventMap = new HashMap<>();

	static {
		KeyEventMap.put("Delete", DeleteEvent);
	}

	public static void AddKeyEvent(Node node, String Event) {

		node.addEventFilter(KeyEvent.KEY_PRESSED, KeyEventMap.get(Event));

	}

}

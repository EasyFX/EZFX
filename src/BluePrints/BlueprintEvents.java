package BluePrints;

import BlueprintScene.BluePrintScene;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public abstract class BlueprintEvents {
	
	public static EventHandler<Event> ADD = E -> {
		Object[] objects = null;
		try {
			Blueprint.currentBluePrint.getOutputObjects()[0].getClass().getMethod("setText", new String().getClass())
					.invoke(Blueprint.currentBluePrint.getOutputObjects()[0],
							"" + (Integer
									.parseInt((String) Blueprint.currentBluePrint.getInputObjects()[0].getClass()
											.getMethod("getText", (Class<?>[]) objects)
											.invoke(Blueprint.currentBluePrint.getInputObjects()[0], objects))
									+ Integer.parseInt((String) Blueprint.currentBluePrint.getInputObjects()[1]
											.getClass().getMethod("getText", (Class<?>[]) objects)
											.invoke(Blueprint.currentBluePrint.getInputObjects()[1], objects))));
		} catch (Exception e) {
			e.printStackTrace();
		}

	};

	
	public static EventHandler<Event> TWICE = E -> {
		Object[] objects = null;
		try {
			Blueprint.currentBluePrint.getOutputObjects()[0].getClass().getMethod("setText", new String().getClass())
					.invoke(Blueprint.currentBluePrint.getOutputObjects()[0],
							"" + (Integer
									.parseInt((String) Blueprint.currentBluePrint.getInputObjects()[0].getClass()
											.getMethod("getText", (Class<?>[]) objects)
											.invoke(Blueprint.currentBluePrint.getInputObjects()[0], objects))
									*2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	};
	
	public static EventHandler<Event> SUBTRACT = E -> {
		BluePrintScene.NodeToBlue.setOnMousePressed(event -> {
			((TextField) Blueprint.currentBluePrint.getOutputObjects()[0]).setText("" + (Integer
					.parseInt((((TextField) Blueprint.currentBluePrint.getInputObjects()[0]).getText()))
					- Integer.parseInt((((TextField) Blueprint.currentBluePrint.getInputObjects()[1]).getText()))));
		});
	};

	public static EventHandler<Event> MULTIPLY = E -> {
		BluePrintScene.NodeToBlue.setOnMousePressed(event -> {
			((TextField) Blueprint.currentBluePrint.getOutputObjects()[0]).setText("" + (Integer
					.parseInt((((TextField) Blueprint.currentBluePrint.getInputObjects()[0]).getText()))
					* Integer.parseInt((((TextField) Blueprint.currentBluePrint.getInputObjects()[1]).getText()))));
		});
	};

	public static EventHandler<Event> DIVIDE = E -> {
		BluePrintScene.NodeToBlue.setOnMousePressed(event -> {
			((TextField) Blueprint.currentBluePrint.getOutputObjects()[0]).setText("" + (Integer
					.parseInt((((TextField) Blueprint.currentBluePrint.getInputObjects()[0]).getText()))
					/ Integer.parseInt((((TextField) Blueprint.currentBluePrint.getInputObjects()[1]).getText()))));
		});
	};

	public static EventHandler<Event> SIZE_UP = E -> {

	};

	public static EventHandler<Event> SIZE_DOWN = E -> {

	};
}

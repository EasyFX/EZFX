package BluePrints;

import BlueprintScene.BluePrintScene;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class BlueprintBuilder {

	private EventHandler<Event> Event;
	private String EventName;
	private Image EventImage;
	private int InputCount, OutputCount;
	private Color BackgroundColor;


	public BlueprintBuilder(String eventName, Image eventImage, EventHandler<Event> event, int inputCount,
			int outputCount, Color backgrouncolor) {
		Event = event;
		EventName = eventName;
		EventImage = eventImage;
		InputCount = inputCount;
		OutputCount = outputCount;
		BackgroundColor = backgrouncolor;
	}

	public Blueprint build() {
		return new Blueprint(EventName, EventImage, Event, InputCount, OutputCount, BackgroundColor,BluePrintScene.eventType,BluePrintScene.NodeToBlue);
	}
	
	public HBox toEntry() {
		return new HBox(new ImageView(EventImage), new Label(EventName));
	}
}

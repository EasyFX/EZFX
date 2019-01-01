package BluePrints;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;

public class Blueprint {

	private boolean isDraging = false;
	private double X1, Y1, X2, Y2;
	private static int ID = 0;

	private int BlueprintID;
	private EventHandler<Event> Event;
	private String EventName;
	private Image EventImage;
	private int InputCount, OutputCount;
	private Color BackgroundColor;
	private StackPane stackPane;
	private Rectangle rectangle = new Rectangle();
	private Object[] InputObjects, OutputObjects;
	private boolean Changed = false;
	private Group group;

	public Blueprint(String eventName, Image eventImage, EventHandler<Event> event, int inputCount, int outputCount,
			Color backgrouncolor) {
		ID++;
		BlueprintID = ID;
		Event = event;
		EventName = eventName;
		EventImage = eventImage;
		InputCount = inputCount;
		OutputCount = outputCount;
		BackgroundColor = backgrouncolor;
		InputObjects = new Object[InputCount];
		OutputObjects = new Object[OutputCount];

		rectangle.setFill(BackgroundColor);

		stackPane = new StackPane();
		group = new Group(stackPane);

		HBox hBox = new HBox();

		VBox Input = new VBox();
		for (int i = 0; i < InputCount; i++) {
			Circle circle = makeDOT("Input", i);
			Input.getChildren().add(circle);
			circle.setFill(Color.BLACK);
			circle.setStroke(Color.BLACK);
		}
		Input.setSpacing(5);
		Input.setAlignment(Pos.CENTER);

		Label EventNameLabel = new Label(eventName);
		EventNameLabel.setFont(new Font(20));
		EventNameLabel.setAlignment(Pos.CENTER);

		VBox Output = new VBox();
		for (int i = 0; i < OutputCount; i++) {
			Circle circle = makeDOT("Output", i);
			Output.getChildren().add(circle);
			circle.setFill(Color.BLACK);
			circle.setStroke(Color.BLACK);
		}
		Output.setSpacing(5);
		Output.setAlignment(Pos.CENTER);

		hBox.setSpacing(5);
		hBox.setAlignment(Pos.CENTER);

		hBox.getChildren().addAll(Input, EventNameLabel, Output);

		rectangle.setStrokeWidth(5);
		rectangle.setStroke(BackgroundColor.darker());

		hBox.translateXProperty().addListener(listener -> {
			rectangle.setTranslateX(hBox.getTranslateX());
		});
		hBox.translateYProperty().addListener(listener -> {
			rectangle.setTranslateY(hBox.getTranslateY());
		});

		hBox.widthProperty().addListener(listener -> {
			if (!Changed)
				rectangle.setWidth(hBox.getWidth());
		});
		hBox.heightProperty().addListener(listener -> {
			if (!Changed) {
				rectangle.setHeight(hBox.getHeight());
				Changed = true;
			}
		});

		stackPane.getChildren().addAll(rectangle, hBox);

	}

	public EventHandler<Event> getEvent() {
		return Event;
	}

	public Group getBlueprint() {
		return group;
	}

	public String getEventName() {
		return EventName;
	}

	public void setEventName(String eventName) {
		EventName = eventName;
	}

	public Image getEventImage() {
		return EventImage;
	}

	public void setEventImage(Image eventImage) {
		EventImage = eventImage;
	}

	public int getInputCount() {
		return InputCount;
	}

	public void setInputCount(int inputCount) {
		InputCount = inputCount;
	}

	public int getOutputCount() {
		return OutputCount;
	}

	public void setOutputCount(int outputCount) {
		OutputCount = outputCount;
	}

	public Color getBackgroundColor() {
		return BackgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		BackgroundColor = backgroundColor;
	}

	public StackPane getStackPane() {
		return stackPane;
	}

	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}

	public void setEvent(EventHandler<Event> event) {
		Event = event;
	}

	public int getBlueprintID() {
		return BlueprintID;
	}

	private Circle makeDOT(String string, int i) {
		Circle circle = new Circle(5);
		circle.setId(BlueprintID + string + i);
		circle.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
			X1 = event.getSceneX();
			Y1 = event.getSceneY();
			isDraging = true;
		});
		circle.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
			if(!(event.getPickResult().getIntersectedNode() instanceof Circle))
				return;
			
			X2 = event.getSceneX();
			Y2 = event.getSceneY();
			isDraging = false;
			Line line = new Line(X1, Y1, X2, Y2);
			group.getChildren().add(line);
			line.setTranslateX(line.getTranslateX() - group.getTranslateX());
			line.setTranslateY(line.getTranslateY() - group.getTranslateY());

			line.setSmooth(true);
			line.setStrokeWidth(5);
			line.setStrokeLineCap(StrokeLineCap.ROUND);
			
			line.toBack();
			
			if (string.equals("Input")) {
				line.getStrokeDashArray().addAll(15.0);
				line.setStroke(Color.DARKORANGE);
			}

		});

		return circle;
	}
}

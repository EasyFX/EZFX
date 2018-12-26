package InsideWindows;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

import javax.tools.ToolProvider;

import App.App;
import Utils.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AttributesPanel extends VBox {
	public static ListView<HBox> Attributes = new ListView<>();
	public HBox searchBox;
	public static TextField searchField = new TextField();

	public AttributesPanel() {
		getChildren().add(Attributes);

		setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

		setTranslateX(App.Window.getWidth() - Constants.WIDTH / 6);
		setTranslateY(FirstScene.topBar.getPrefHeight());

		App.Window.widthProperty().addListener(nmb -> {
			setTranslateX(App.Window.getWidth() - Constants.WIDTH / 6);
		});

		Attributes.prefWidthProperty().bind(widthProperty());
		Attributes.prefHeightProperty().bind(heightProperty());

		setMinWidth(Constants.WIDTH / 10);
		setPrefWidth(Constants.WIDTH / 6);
		setMaxWidth(Constants.WIDTH / 4);

		prefHeightProperty().bind(App.Window.heightProperty());

	}

	public void setAttributes(Node node, String... attributes) {
		ObservableList<HBox> list = FXCollections.observableArrayList();
		searchBox = new HBox(new Label("Search "));
		searchBox.getChildren().add(searchField);
		list.add(searchBox);

		for (String attribute : attributes) {
			TextField textfield = new TextField();
			Label label = new Label(attribute);
			textfield.setOnKeyPressed(Event -> {
				if (Event.getCode() == KeyCode.ENTER) {
					textfield.deselect();

					try {

						for (Method method : node.getClass().getMethods()) {
							if (method.getName().equals(attribute)) {
								Class<?>[] params = method.getParameterTypes();
								if (params.length == 1) {
									String name = params[0].getName()
											.split("\\.")[params[0].getName().split("\\.").length - 1];
									Object arg0 = getObject(name, textfield);
									if (arg0 != null)
										method.invoke(node, arg0);
									return;
								}
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			list.add(new HBox(label, textfield));
		}
		Attributes.setItems(list);

		searchField.setOnKeyTyped(Event -> {
			ObservableList<HBox> filteredAttributes = FXCollections.observableArrayList();
			String filter = searchField.getText().toString();
			boolean first = true;
			for (HBox hBox : list) {
				if (first) {
					first = false;
					filteredAttributes.add(searchBox);
					continue;
				}

				if (Pattern.compile(filter, Pattern.CASE_INSENSITIVE)
						.matcher(((Label) hBox.getChildren().get(0)).getText()).find())
					filteredAttributes.add(hBox);
				Attributes.setItems(filteredAttributes);
			}
		});
	}

	private Object getObject(String name, TextField textfield) {
		if (name.equals("double")) {
			return Double.parseDouble(textfield.getText());
		} else if (name.equals("integer")) {
			return Integer.parseInt(textfield.getText());
		} else if (name.equals("float")) {
			return Float.parseFloat(textfield.getText());
		} else if (name.equals("String")) {
			return textfield.getText().toString();
		} else if (name.equals("EventHandler")) {
			return new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

				}
			};
		}
		return null;
	}

}

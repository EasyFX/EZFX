package FirstScene;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

import App.App;
import Loaders.AttributesFilter;
import Scene.SceneManager;
import Utils.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AttributesPanel extends VBox {
	public static ListView<HBox> Attributes = new ListView<>();
	public HBox searchBox;
	public static TextField searchField = new TextField();
	private ObservableList<HBox> filteredAttributes = FXCollections.observableArrayList();

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
			if (AttributesFilter.contains(attribute))
				continue;
			Label label = new Label(attribute);
			if (!attribute.startsWith("setOn")) {
				TextField textfield = new TextField();
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
			} else {
				Button button = new Button("To Blueprint");
				button.setFont(new Font(9));
				button.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
					SceneManager.getSceneManager().changeScene(2);
				});
				button.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
					SceneManager.getSceneManager().changeScene(2);
				});
				list.add(new HBox(label, button));
			}
		}

		if (searchField.getText().isEmpty())
			Attributes.setItems(list);
		else {
			filteredAttributes = FXCollections.observableArrayList();
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

		}

		searchField.setOnKeyTyped(Event -> {
			filteredAttributes = FXCollections.observableArrayList();
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
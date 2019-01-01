package BlueprintScene;

import App.App;
import Utils.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NodeExplorer extends VBox {

	private static ObservableList<HBox> NodeList = FXCollections.observableArrayList();
	public static ListView<HBox> NodeListView = new ListView<>();
	private static TextField searchField = new TextField();

	public NodeExplorer() {
		setupList();
		setupDimensions();
		setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
	}

	private void setupList() {

		getChildren().add(NodeListView);

		NodeListView.prefWidthProperty().bind(widthProperty());
		NodeListView.prefHeightProperty().bind(heightProperty());

		NodeListView.setItems(NodeList);
		searchField.setText("");

		NodeList.addListener((ListChangeListener<HBox>) listener -> {
			NodeListView.refresh();
		});

	}

	private void setupDimensions() {
		setTranslateX(0);
		setTranslateY(BluePrintScene.topBar.getPrefHeight());

		setMinWidth(Constants.WIDTH / 10);
		setPrefWidth(Constants.WIDTH / 6);
		setMaxWidth(Constants.WIDTH / 4);

		setPrefHeight((App.Window.getHeight() - BluePrintScene.topBar.getPrefHeight()));
		App.Window.heightProperty().addListener(Change -> {
			setPrefHeight((App.Window.getHeight() - BluePrintScene.topBar.getPrefHeight()));
		});
	}

	public void addNode(String string, Image image, Node node) {
		NodeList.add(new HBox(new ImageView(image), new Label(string + " " + node.getId().replace("BID", ""))));
	}

	public void removeNode(Node node) {
		HBox hBoxToRemove = null;
		for (HBox hBox : NodeList) {
			for (Node node2 : hBox.getChildren())
				if (node2.equals(node)) {
					hBoxToRemove = hBox;
					break;
				}
			if (hBoxToRemove != null)
				break;

		}
		NodeList.remove(hBoxToRemove);
	}

	static {
		HBox searchBox = new HBox(new Label("Search "), searchField);
		NodeList.add(searchBox);
		searchField.setOnKeyTyped(event -> {
			if (searchField.getText().isEmpty()) {
				NodeListView.setItems(NodeList);
				return;
			}
			ObservableList<HBox> FilteredList = FXCollections.observableArrayList();
			FilteredList.add(searchBox);
			boolean first = true;
			for (HBox hBox : NodeList) {
				if (first) {
					first = false;
					continue;
				}
				if (((Label) hBox.getChildren().get(1)).getText().contains(searchField.getText())) {
					FilteredList.add(hBox);
				}
			}
			NodeListView.setItems(FilteredList);
		});
	}
}

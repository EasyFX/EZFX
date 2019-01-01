package BlueprintScene;

import App.App;
import BluePrints.BlueprintCollection;
import Utils.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class BlueprintExplorer extends VBox {

	private static ObservableList<HBox> BlueprintList = FXCollections.observableArrayList();
	public static ListView<HBox> BlueprintListView = new ListView<>();

	public static ObservableList<HBox> BlueprintsAdded = FXCollections.observableArrayList();

	public BlueprintExplorer() {
		setupList();
		setupDimensions();
		setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
	}

	private void setupList() {

		getChildren().add(BlueprintListView);

		BlueprintListView.prefWidthProperty().bind(widthProperty());
		BlueprintListView.prefHeightProperty().bind(heightProperty());

		BlueprintListView.setItems(BlueprintList);

		BlueprintList.addListener((ListChangeListener<HBox>) listener -> {
			BlueprintListView.refresh();
		});

		HBox hBox = BlueprintCollection.BP_Add.toEntry();
		BlueprintList.add(hBox);
		hBox.setOnMouseReleased(event -> {
			BlueprintsAdded.add(hBox);
			StackPane stackPane = BlueprintCollection.BP_Add.getBlueprint();
			stackPane.setTranslateX(event.getSceneX());
			stackPane.setTranslateY(event.getSceneY());
			BluePrintScene.blueprintRoot.getChildren().add(stackPane);
		});

	}

	private void setupDimensions() {

		setTranslateX(App.Window.getWidth() - Constants.WIDTH / 6);
		setTranslateY(BluePrintScene.topBar.getPrefHeight());

		App.Window.widthProperty().addListener(nmb -> {
			setTranslateX(App.Window.getWidth() - Constants.WIDTH / 6);
		});

		BlueprintListView.prefWidthProperty().bind(widthProperty());
		BlueprintListView.prefHeightProperty().bind(heightProperty());

		setMinWidth(Constants.WIDTH / 10);
		setPrefWidth(Constants.WIDTH / 6);
		setMaxWidth(Constants.WIDTH / 4);

		prefHeightProperty().bind(App.Window.heightProperty());
	}

}

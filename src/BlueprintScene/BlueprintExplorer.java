package BlueprintScene;

import App.App;
import BluePrints.Blueprint;
import BluePrints.BlueprintCollection;
import Utils.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
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

	static {
		setupActualListItems();
	}
	
	private void setupList() {

		getChildren().add(BlueprintListView);

		BlueprintListView.prefWidthProperty().bind(widthProperty());
		BlueprintListView.prefHeightProperty().bind(heightProperty());
	}

	
	
	private static void setupActualListItems() {

		BlueprintListView.setItems(BlueprintList);

		BlueprintList.addListener((ListChangeListener<HBox>) listener -> {
			BlueprintListView.refresh();
		});

		HBox hBox = BlueprintCollection.BP_ADD_BUILDER.toEntry();
		BlueprintList.add(hBox);
		hBox.setOnMouseReleased(event -> {
			Blueprint blueprint = BlueprintCollection.BP_ADD_BUILDER.build();
			BlueprintsAdded.add(hBox);
			Group group = blueprint.getBlueprint();
			group.setTranslateX(event.getSceneX());
			group.setTranslateY(event.getSceneY());
			BluePrintScene.blueprintRoot.getChildren().add(group);
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

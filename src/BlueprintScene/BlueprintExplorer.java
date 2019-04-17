package BlueprintScene;

import App.App;
import BluePrints.Blueprint;
import BluePrints.BlueprintBuilder;
import BluePrints.BlueprintCollection;
import Scene.SceneManager;
import Utils.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class BlueprintExplorer extends VBox {

	private static ObservableList<HBox> BlueprintList = FXCollections.observableArrayList();
	private Button Done = new Button("Done");

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

		getChildren().addAll(Done, BlueprintListView);

		BlueprintListView.prefWidthProperty().bind(widthProperty());
		BlueprintListView.prefHeightProperty().bind(heightProperty());

		Done.setTextAlignment(TextAlignment.CENTER);

		Done.setOnMousePressed(event -> {
			Blueprint.connectBlueprints();
			SceneManager.getSceneManager().changeScene(1);
		});
	}

	private static void setupActualListItems() {

		BlueprintListView.setItems(BlueprintList);

		BlueprintList.addListener((ListChangeListener<HBox>) listener -> {
			BlueprintListView.refresh();
		});

		addBlueprint(BlueprintCollection.BP_ADD_BUILDER);
		addBlueprint(BlueprintCollection.BP_SUBTRACT_BUILDER);
		addBlueprint(BlueprintCollection.BP_MULTIPLY_BUILDER);
		addBlueprint(BlueprintCollection.BP_DIVIDE_BUILDER);
		addBlueprint(BlueprintCollection.SIZE_UP_BUILDER);
		addBlueprint(BlueprintCollection.SIZE_DOWN_BUILDER);
		addBlueprint(BlueprintCollection.BP_TWICE_BUILDER);
		
		
	}

	private static void addBlueprint(BlueprintBuilder blueprintBuilder) {
		HBox hBox = blueprintBuilder.toEntry();
		BlueprintList.add(hBox);
		hBox.setOnMouseReleased(event -> {
			Blueprint blueprint = blueprintBuilder.build();
			BlueprintsAdded.add(hBox);
			Group group = blueprint.getBlueprint();
			group.setTranslateX(event.getSceneX());
			group.setTranslateY(event.getSceneY());
			BluePrintScene.blueprintRoot.getChildren().add(group);
		});
	}

	private void setupDimensions() {

		setTranslateX(App.Window.getWidth() - Constants.WIDTH / 6 - 18);
		setTranslateY(BluePrintScene.topBar.getPrefHeight());

		App.Window.widthProperty().addListener(nmb -> {
			setTranslateX(App.Window.getWidth() - Constants.WIDTH / 6 - 18);
		});

		BlueprintListView.prefWidthProperty().bind(widthProperty());
		BlueprintListView.prefHeightProperty().bind(heightProperty());

		setMinWidth(Constants.WIDTH / 10);
		setPrefWidth(Constants.WIDTH / 6);
		setMaxWidth(Constants.WIDTH / 4);

		Done.setPrefWidth(getPrefWidth());

		prefHeightProperty().bind(App.Window.heightProperty());

	}

}

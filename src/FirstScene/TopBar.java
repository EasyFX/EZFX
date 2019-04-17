package FirstScene;

import App.App;
import SaveLoadTree.Exporter;
import Utils.Constants;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class TopBar extends HBox {

	private double ButtonCount;

	public TopBar(int ButtonCount) {
		this.ButtonCount = ButtonCount;
		setupDimensions();
		setupItems();

	}

	private void setupItems() {
		Button Save = createButton("Save");
		Button Load = createButton("Open");
		Button Export = createButton("Export Code");
		Button ExportAndCompile = createButton("Export Code \n And Compile");
		Button ExportAndRun = createButton("Run");

		Save.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
			FirstScene.treeWriter.writeTree(FirstScene.canvas.Items);
		});
		Load.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
			FirstScene.treeReader.LoadTree(FirstScene.canvas.Items);
		});
		Export.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
			Exporter.Export();
		});
		ExportAndCompile.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
			Exporter.ExportAndCompile();
		});
		ExportAndRun.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
			Exporter.Run();
		});
	}

	private void setupDimensions() {
		setBackground(new Background(new BackgroundFill(Color.rgb(83, 83, 83), null, null)));

		setTranslateX(0);
		setTranslateY(0);

		prefWidthProperty().bind(App.Window.widthProperty());

		setMinHeight(Constants.HEIGHT / 10);
		setPrefHeight(Constants.TOP_BAR_HEIGHT);
		setMaxHeight(Constants.HEIGHT / 7);
	}

	private Button createButton(String ButtonText) {
		Button button = new Button(ButtonText);
		button.setPrefWidth(getPrefWidth() / ButtonCount);
		button.setPrefHeight(getPrefHeight());

		getChildren().add(button);

		return button;
	}

}

package InsideWindows;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HierarchyTree extends VBox {

	public static ObservableList<TreeItem<HBox>> Items = FXCollections.observableArrayList();
	public static TreeView<HBox> Hierarchy = new TreeView<>();

	public Pane mainBox;

	private TreeItem<HBox> Root = new TreeItem<>(),basePane;
	public TreeItem<HBox> selected;
	
	private Map<String, Integer> count = new HashMap<>();
	private Map<Node, TreeItem<HBox>> map = new HashMap<>();

	public HierarchyTree() {
		setupTree();
		setupDimensions();
		selected = Root;
	}

	private void setupTree() {
		HBox box = new HBox();
		box.getChildren().addAll(new Label("Hierarchy Tree"));
		Root = new TreeItem<>(box);
		Root.getChildren().addAll(Items);
		Root.setExpanded(true);
		Hierarchy = new TreeView<>(Root);
		getChildren().add(Hierarchy);
		Hierarchy.prefHeightProperty().bind(prefHeightProperty());
		Hierarchy.prefWidthProperty().bind(prefWidthProperty());

		Hierarchy.setOnMousePressed(event -> {
			selected = Hierarchy.getSelectionModel().getSelectedItem();
			if (selected == null)
				selected = basePane;
		});

	}

	private void setupDimensions() {
		setTranslateX(0);
		setTranslateY(FirstScene.entityExplorer.getPrefHeight() + FirstScene.topBar.getPrefHeight());
		FirstScene.entityExplorer.heightProperty().addListener(Change -> {
			setTranslateY(FirstScene.entityExplorer.getHeight() + FirstScene.topBar.getPrefHeight());
			setPrefHeight(FirstScene.entityExplorer.getPrefHeight());
		});

		setMinWidth(Constants.WIDTH / 10);
		setPrefWidth(Constants.WIDTH / 6);
		setMaxWidth(Constants.WIDTH / 4);

	}

	public TreeItem<HBox> AddItem(String string, Image image, Node node) {
		HBox box = new HBox();
		if (count.containsKey(string)) {
			count.put(string, count.get(string) + 1);
		} else {
			count.put(string, 1);
		}
		box.getChildren().addAll(new ImageView(image), new Label(string + "" + count.get(string)));
		TreeItem<HBox> treeItem = new TreeItem<>(box);
	
		selected.getChildren().add(treeItem);
		map.put(node, treeItem);

		box.setOnMousePressed(event -> {
			if (event.getButton() == MouseButton.SECONDARY) {
				FirstScene.canvas.deleteItem(node);
				FirstScene.hierarchyTree.DeleteItem(node);
				FirstScene.attributesPanel.setAttributes(null, new String[0]);
			}
			if (event.getButton() == MouseButton.PRIMARY) {
				if(node instanceof Pane) 
					mainBox = (Pane)node;
				Method[] field = node.getClass().getMethods();
				List<String> strings = new ArrayList<>();
				for (Method field2 : field) {
					strings.add(field2.getName());
				}
				Collections.sort(strings);
				String[] strings2 = new String[strings.size()];
				strings.toArray(strings2);
				FirstScene.attributesPanel.setAttributes(node,strings2);
			}
			
		});
		treeItem.setExpanded(true);
		return treeItem;
	}

	public void DeleteItem(Node node) {

		map.get(node).getParent().getChildren().remove(map.get(node));
		map.remove(node);

	}

	public void setMainBox(Pane node) {
		mainBox = node;
		basePane = AddItem("Root Vbox ", new Image("File:msg.png"), node);
		selected = basePane;
		
	}

}

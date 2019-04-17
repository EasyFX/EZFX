package FirstScene;

import App.App;
import SaveLoadTree.TreeReader;
import SaveLoadTree.TreeWriter;
import Utils.Constants;
import Utils.UserData;
import javafx.scene.Group;
import javafx.scene.Scene;

public class FirstScene extends Scene {

	public static TreeWriter treeWriter;
	public static TreeReader treeReader;
	public static TopBar topBar;
	public static EntityExplorer entityExplorer;
	public static AttributesPanel attributesPanel;
	public static Canvas canvas;
	public static HierarchyTree hierarchyTree;
	public static Group TheFirstOne = new Group();

	private static Scene scene;

	public FirstScene() {
		super(TheFirstOne, App.Window.getWidth(), App.Window.getHeight());
		setFill(Constants.FIRST_SCENE_BACKGROUND_COLOR);
		treeWriter = new TreeWriter(UserData.EZ_TREE_PATH + "/" + UserData.EZ_TREE_FILENAME);
		treeReader = new TreeReader(UserData.EZ_TREE_PATH + "/" + UserData.EZ_TREE_FILENAME);
		topBar = new TopBar(Constants.TOP_BAR_BUTTON_COUNT);
		entityExplorer = new EntityExplorer();
		hierarchyTree = new HierarchyTree();
		attributesPanel = new AttributesPanel();
		canvas = new Canvas();
		TheFirstOne.getChildren().addAll(canvas, topBar, entityExplorer, hierarchyTree, attributesPanel);

		scene = this;
	}

	public static Scene getScene() {
		return scene;
	}
}

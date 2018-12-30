package InsideWindows;

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

	public FirstScene() {
		super(TheFirstOne, App.Window.getWidth(), App.Window.getHeight());
		setFill(Constants.FIRST_SCENE_BACKGROUND_COLOR);
		treeWriter = new TreeWriter(UserData.PATH);
		treeReader = new TreeReader(UserData.PATH);
		topBar = new TopBar(Constants.TOP_BAR_BUTTON_COUNT);
		entityExplorer = new EntityExplorer();
		hierarchyTree = new HierarchyTree();
		attributesPanel = new AttributesPanel();
		canvas = new Canvas();
		TheFirstOne.getChildren().addAll(topBar, entityExplorer, hierarchyTree , attributesPanel, canvas);

	}

}

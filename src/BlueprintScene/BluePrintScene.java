package BlueprintScene;

import java.lang.reflect.Method;

import App.App;
import FirstScene.FirstScene;
import FirstScene.TopBar;
import Utils.Constants;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

@SuppressWarnings("deprecation")
public class BluePrintScene extends Scene {

	public static Node BlueNode;
	public static Group TheSecondOne = new Group();
	public static TopBar topBar;
	public static NodeExplorer nodeExplorer;
	public static BlueprintExplorer blueprintExplorer;
	public static Group blueprintRoot;

	private static boolean setTextInvoked = false;

	public BluePrintScene() {
		super(TheSecondOne, App.Window.getWidth(), App.Window.getHeight());
		setupNode();
		if (BlueNode == null)
			return;
		topBar = new TopBar(Constants.TOP_BAR_BUTTON_COUNT);
		nodeExplorer = new NodeExplorer();
		blueprintExplorer = new BlueprintExplorer();
		blueprintRoot = new Group();
		
		TheSecondOne.getChildren().addAll(topBar, nodeExplorer, blueprintExplorer, BlueNode, blueprintRoot);	
	}

	private void setupNode() {
		if (FirstScene.entityExplorer.Chosen == null)
			return;

		try {
			Node node = (Node) FirstScene.entityExplorer.Chosen.getClass().newInstance();
			node.setId(FirstScene.entityExplorer.Chosen.getId());
			node.setTranslateX(Constants.WIDTH / 2);
			node.setTranslateY(Constants.HEIGHT / 2);

			for (Method method : node.getClass().getMethods()) {
				if (method.getName().equals("setText")) {
					method.invoke(node, node.getClass().getSimpleName()
							+ (node.getId() != null ? " " + node.getId().replace("BID", "") : ""));
					setTextInvoked = true;
					break;
				}
			}
			if (!setTextInvoked) {
				for (Method method : node.getClass().getMethods()) {
					if (method.getName().equals("setBackground")) {
						System.out.println("Hello1");
						method.invoke(node, new Background(new BackgroundFill(Color.RED, null, null)));
					}
					if (method.getName().equals("setPrefWidth")) {
						System.out.println("Hello2");
						method.invoke(node, 50);
					}
					if (method.getName().equals("setPrefHeight")) {
						System.out.println("Hello3");
						method.invoke(node, 50);
					}
				}
			}
			BlueNode = node;
			BlueNode.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
				if (event.isPrimaryButtonDown()) {
					BlueNode.setTranslateX(event.getSceneX() - ((Button) BlueNode).getWidth() / 2);
					BlueNode.setTranslateY(event.getSceneY() - ((Button) BlueNode).getHeight() / 2);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

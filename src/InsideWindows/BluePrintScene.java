package InsideWindows;

import java.lang.reflect.Method;

import App.App;
import Utils.Constants;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

@SuppressWarnings("deprecation")
public class BluePrintScene extends Scene {

	public static Group TheSecondOne = new Group();

	private static boolean setTextInvoked = false;

	public BluePrintScene() {
		super(TheSecondOne, App.Window.getWidth(), App.Window.getHeight());
		if (FirstScene.entityExplorer.Chosen == null)
			return;

		try {
			Node node = (Node) FirstScene.entityExplorer.Chosen.getClass().newInstance();
			TheSecondOne.getChildren().add(node);
			node.setTranslateX(Constants.WIDTH / 2);
			node.setTranslateY(Constants.HEIGHT / 2);

			for (Method method : node.getClass().getMethods()) {
				if (method.getName().equals("setText")) {
					method.invoke(node, node.getClass().getSimpleName());
					setTextInvoked = true;
					break;
				}
			}
			if (!setTextInvoked) {
				System.out.println(node.getClass().getSimpleName());
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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

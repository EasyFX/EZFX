package SaveLoadTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import BlueprintScene.BluePrintScene;
import FirstScene.EntityExplorer;
import FirstScene.FirstScene;
import Loaders.ItemLoader;
import Scene.SceneManager;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

@SuppressWarnings("deprecation")
public class TreeReader {

	String Path;

	public TreeReader(String Path) {
		this.Path = Path;
	}

	public void LoadTree(Parent root) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(Path)));
			read(bufferedReader, root);
			root.setTranslateX(0);
			FirstScene.hierarchyTree.loadHierarchyTree(root);
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void read(BufferedReader bufferedReader, Parent root) {
		try {
			Stack<Parent> nodes = new Stack<>();
			nodes.add(root);
			Parent currentRoot = nodes.pop();

			String Line = null;
			while ((Line = bufferedReader.readLine()) != null) {
				if (Line.trim().startsWith("<")) {
					String Line2 = Line.trim().split(" ")[0].substring(1);
					Node node = (Node) Class.forName(Line2).newInstance();

					TreeItem<HBox> treeItem = addItems(ItemLoader.LookUpName(node), ItemLoader.LookUpImage(node), node);

					if (currentRoot instanceof Pane)
						((Pane) currentRoot).getChildren().add(node);
					else
						((Group) currentRoot).getChildren().add(node);

					if (node instanceof Parent && !(node instanceof Control)) {

						nodes.add(currentRoot);
						currentRoot = (Parent) node;
						if (treeItem != null)
							FirstScene.hierarchyTree.selected = treeItem;

					}
					setAttributes(node, Line);
				} else if (Line.endsWith(">")) {
					Parent parent = nodes.pop();
					if (parent instanceof Pane)
						currentRoot = (Pane) parent;
					else
						currentRoot = parent;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setAttributes(Node node, String Line) {
		String[] fields = Line.trim().split(" ");
		for (int i = 1; i < fields.length - 1; i++) {
			for (Method method : node.getClass().getMethods()) {
				if (check(method, fields[i])) {
					try {
						Object object = getObject(method.getParameterTypes()[0].getSimpleName(),
								fields[i].split("'")[1].replace("~", " "));
						if (object != null)
							method.invoke(node, object);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private Object getObject(String name, String Text) {
		if (name.equals("double")) {
			return Double.parseDouble(Text);
		} else if (name.equals("integer")) {
			return Integer.parseInt(Text);
		} else if (name.equals("float")) {
			return Float.parseFloat(Text);
		} else if (name.equals("String")) {
			return Text;
		} else if (name.equals("EventHandler")) {
			return new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

				}
			};
		}
		return null;
	}

	private boolean check(Method method, String field) {
		if (!field.contains("''") && method.getName().equals(field.split("'")[0].replace("=", ""))
				&& method.getName().startsWith("set") && method.getParameterCount() == 1
				&& !method.getParameterTypes()[0].getSimpleName().equals("Insets")
				&& !method.getParameterTypes()[0].getSimpleName().equals("Font"))
			return true;
		return false;

	}

	public TreeItem<HBox> addItems(String string, Image image, Node node) {
		Node newNode = node;
		newNode.setId("" + (++EntityExplorer.ID));

		// FirstScene.canvas.addItem(newNode);
		if (FirstScene.hierarchyTree.selected != null) {
			FirstScene.hierarchyTree.mainBox.setTranslateY(1);
			if (FirstScene.hierarchyTree.mainBox instanceof Pane)
				((Pane) FirstScene.hierarchyTree.mainBox).getChildren().add(newNode);
			else if (!(FirstScene.hierarchyTree.mainBox instanceof Control))
				((Group) FirstScene.hierarchyTree.mainBox).getChildren().add(newNode);
			HBox.setHgrow(newNode, Priority.ALWAYS);
		}

		TreeItem<HBox> treeItem = FirstScene.hierarchyTree.AddItem(string, image, newNode);

		final Node self = newNode;
		newNode.setOnMousePressed(event2 -> {
			Method[] field = self.getClass().getMethods();
			List<String> strings = new ArrayList<>();
			for (Method field2 : field) {
				strings.add(field2.getName());
			}
			Collections.sort(strings);
			String[] strings2 = new String[strings.size()];
			strings.toArray(strings2);
			FirstScene.attributesPanel.setAttributes(self, strings2);
			FirstScene.entityExplorer.Chosen = self;
		});
		newNode.setOnKeyPressed(KeyEvent -> {
			if (KeyEvent.getCode() == KeyCode.DELETE) {
				FirstScene.canvas.deleteItem(self);
				FirstScene.hierarchyTree.DeleteItem(self);
				FirstScene.attributesPanel.setAttributes(null, new String[0]);
			}
		});
		try {
			if(BluePrintScene.nodeExplorer == null) {
				FirstScene.entityExplorer.Chosen = newNode ;
				SceneManager.getSceneManager().refreshBlueprint();
			}
			Node BlueprintNode = node.getClass().newInstance();
			BlueprintNode.setId(newNode.getId() + "BID");
			BluePrintScene.nodeExplorer.addNode(string,image,BlueprintNode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (node instanceof Parent)
			return treeItem;
		else
			return null;
	}

}

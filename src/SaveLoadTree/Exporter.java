package SaveLoadTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import BluePrints.Blueprint;
import FirstScene.FirstScene;
import Loaders.AttributesFilter;
import Utils.Constants;
import Utils.UserData;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Control;

public class Exporter {

	private static List<String> list = new ArrayList<>();
	private static Map<Blueprint, String> map = new HashMap<>();
	private static Map<Blueprint, ArrayList<String>> mapInputs = new HashMap<>();
	private static Map<Blueprint, ArrayList<String>> mapOutputs = new HashMap<>();
	private static String first = "";

	private static int ID = 0, C = 0;

	static {
		list.add("import javafx.application.Application;");
		list.add("import javafx.scene.Scene;");
		list.add("import javafx.stage.Stage;");
		list.add("import javafx.scene.Node;");
		list.add("import javafx.scene.text.TextAlignment;");
		list.add("import javafx.geometry.Pos;");
		list.add("import javafx.scene.control.ContentDisplay;");
		list.add("import javafx.event.Event;");
		list.add("import javafx.scene.input.KeyEvent;");
		list.add("import javafx.scene.input.MouseEvent;");
	}

	public static void ExportAndCompile() {
		try {
			Export();
			Runtime.getRuntime().exec("javac " + UserData.EXPORT_PATH + "/" + UserData.EXPORT_FILENAME).waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Run() {
		try {
			Runtime.getRuntime().exec("java -classpath " + UserData.EXPORT_PATH + " Code");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Export() {
		getImports(FirstScene.canvas.Items);
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(
					new FileWriter(new File(UserData.EXPORT_PATH + "/" + UserData.EXPORT_FILENAME)));

			for (String string : list) {
				bufferedWriter.write(string);
				bufferedWriter.newLine();
			}
			bufferedWriter.newLine();

			bufferedWriter.write("public class Code extends Application{");
			bufferedWriter.newLine();
			bufferedWriter.newLine();

			bufferedWriter.write("public static void main(String[] args){launch(args);}");
			bufferedWriter.newLine();
			bufferedWriter.newLine();

			bufferedWriter.write("@Override");
			bufferedWriter.newLine();

			bufferedWriter.write("public void start(Stage primaryStage) throws Exception {");
			bufferedWriter.newLine();

			WriteNodes(FirstScene.canvas.Items, bufferedWriter, null, true);

			bufferedWriter.newLine();

			WriteEvents(bufferedWriter);

			bufferedWriter.newLine();
			bufferedWriter.write("Scene scene1 = new Scene(" + first + ",1280,720);");
			bufferedWriter.newLine();

			bufferedWriter.write("primaryStage.setScene(scene1);");
			bufferedWriter.newLine();

			bufferedWriter.write("primaryStage.sizeToScene();");
			bufferedWriter.newLine();

			bufferedWriter.write("primaryStage.show();");
			bufferedWriter.newLine();

			bufferedWriter.write("}");
			bufferedWriter.newLine();
			bufferedWriter.newLine();

			bufferedWriter.write("}");
			bufferedWriter.newLine();

			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void getImports(Node node) {

		if (!list.contains("import " + node.getClass().getName() + ";")) {
			list.add("import " + node.getClass().getName() + ";");
		}
		if (node instanceof Parent && !(node instanceof Control)) {
			for (Node parent : ((Parent) node).getChildrenUnmodifiable()) {
				getImports(parent);
			}
		}

	}

	private static void WriteNodes(Node node, BufferedWriter bufferedWriter, String Parent, boolean isFirst) {
		try {
			String className = node.getClass().getSimpleName();
			String instanceName = className.toLowerCase()
					+ (node.getId() == null || node.getId().equals("null") ? ++ID : node.getId());
			if (isFirst)
				first = instanceName;
			bufferedWriter.write(className + " " + instanceName + " = new " + className + "();");
			bufferedWriter.newLine();

			if (Parent != null) {
				bufferedWriter.write(Parent + ".getChildren().add(" + instanceName + ");");
				bufferedWriter.newLine();
				bufferedWriter.newLine();
			}

			writeAttributes(node, instanceName, bufferedWriter);

			if (node instanceof Parent && !(node instanceof Control)) {
				for (Node node2 : ((Parent) node).getChildrenUnmodifiable()) {
					WriteNodes(node2, bufferedWriter, instanceName, false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeAttributes(Node node, String instanceName, BufferedWriter bufferedWriter) {
		try {
			for (Method method : node.getClass().getMethods()) {
				if (check(method) && checkSetters(method, node)) {
					Object[] objects = null;
					if ((method.invoke(node, objects) + "").equals("[]")
							|| (method.invoke(node, objects) + "").equals(""))
						continue;
					if (method.getName().equals("getId")) {
						bufferedWriter.write(instanceName + "." + method.getName().replace("get", "set") + "("
								+ method.invoke(node, objects) + " + \"\");");
						bufferedWriter.newLine();
					} else if (method.getName().equals("getTextAlignment")) {
						bufferedWriter.write(instanceName + "." + method.getName().replace("get", "set")
								+ "(TextAlignment." + method.invoke(node, objects) + ");");
						bufferedWriter.newLine();
					} else if (method.getName().equals("getText")) {
						bufferedWriter.write(instanceName + "." + method.getName().replace("get", "set") + "(\""
								+ method.invoke(node, objects) + "\");");
						bufferedWriter.newLine();
					} else if (method.getName().equals("getAlignment")) {
						bufferedWriter.write(instanceName + "." + method.getName().replace("get", "set") + "(Pos."
								+ method.invoke(node, objects) + ");");
						bufferedWriter.newLine();
					} else if (method.getName().equals("getContentDisplay")) {
						bufferedWriter.write(instanceName + "." + method.getName().replace("get", "set")
								+ "(ContentDisplay." + method.invoke(node, objects) + ");");
						bufferedWriter.newLine();
					} else {
						bufferedWriter.write(instanceName + "." + method.getName().replace("get", "set") + "("
								+ method.invoke(node, objects) + ");");
						bufferedWriter.newLine();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean check(Method method) {
		if (method.getModifiers() != 4 && method.getModifiers() != 2 && method.getName().startsWith("get")
				&& method.getParameterCount() == 0 && !method.getName().contains("getOn")
				&& !AttributesFilter.contains(method.getName()))
			return true;
		return false;
	}

	private static boolean checkSetters(Method method, Node node) {
		for (Method method2 : node.getClass().getMethods()) {
			if (method.getName().replace("get", "set").equals(method2.getName()))
				return true;
		}
		return false;
	}

	private static void WriteEvents(BufferedWriter bufferedWriter) {
		try {
			for (Blueprint blueprint : Blueprint.blueprints.keySet()) {
				map.put(blueprint, "node" + blueprint.getNodeToBlue().getId());
				String name = "node" + blueprint.getNodeToBlue().getId() + "_" + C;
				bufferedWriter.write("Node " + name + " = " + first + ".lookup(\"#"
						+ blueprint.getNodeToBlue().getId() + "\");");
				bufferedWriter.newLine();

				if (!mapInputs.containsKey(blueprint)) {
					mapInputs.put(blueprint, new ArrayList<>());
				}
				if (!mapOutputs.containsKey(blueprint)) {
					mapOutputs.put(blueprint, new ArrayList<>());
				}

				for (Node node : blueprint.getInputObjects()) {
					mapInputs.get(blueprint).add("input" + blueprint.getNodeToBlue().getId() + "_" + node.getId());
					bufferedWriter.write("Node input" + blueprint.getNodeToBlue().getId() + "_" + node.getId() + " = "
							+ first + ".lookup(\"#" + node.getId() + "\");");
					bufferedWriter.newLine();
				}

				for (Node node : blueprint.getOutputObjects()) {
					mapOutputs.get(blueprint).add("output" + blueprint.getNodeToBlue().getId() + "_" + node.getId());
					bufferedWriter.write("Node output" + blueprint.getNodeToBlue().getId() + "_" + node.getId() + " = "
							+ first + ".lookup(\"#" + node.getId() + "\");");
					bufferedWriter.newLine();
				}

				bufferedWriter.newLine();
				bufferedWriter.write(getEventString(blueprint,name));
				bufferedWriter.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getEventString(Blueprint blueprint,String name) {
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(new File(Constants.BLUEPRINT_EVENT_PATH)));
			String string = "", Line = "";
			while ((Line = bufferedReader.readLine()) != null) {
				if (Line.equals(blueprint.getEventName())) {
					bufferedReader.readLine();
					while ((Line = bufferedReader.readLine()) != null && !Line.equals("~")) {
						String t = captialiseFirst(blueprint.getEventType().getSuperType().getName());
						String e = "Event";
						string += Line.replaceAll("\\?\\?n\\?\\?", name)
								.replaceAll("\\?\\?ef\\?\\?", t + e + "." + blueprint.getEventType().getName())
								.replaceAll("\\?\\?o\\?\\?",
										blueprint.getOutputCount() == 0 ? "" : mapOutputs.get(blueprint).get(0))
								.replaceAll("\\?\\?i0\\?\\?",
										blueprint.getInputCount() == 0 ? "" : mapInputs.get(blueprint).get(0))
								.replaceAll("\\?\\?i1\\?\\?",
										blueprint.getInputCount() == 0 ? "" : mapInputs.get(blueprint).get(1));
						string += "\n";
					}
					break;
				}
			}

			bufferedReader.close();
			return string;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String captialiseFirst(String input) {
		if (input == null)
			return "";
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}

}

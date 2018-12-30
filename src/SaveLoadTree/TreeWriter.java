package SaveLoadTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Control;

public class TreeWriter {
	
	private String Path;
	private List<String> methodFilters = new ArrayList<>();

	public TreeWriter(String Path) {
		this.Path = Path;
		loadMethodFilters("./saveFilters.filters");
	}
	
	public void writeTree(Parent root) {
		try {
			double x = root.getTranslateX(),y=root.getTranslateY();
			
			root.setTranslateX(0);
			root.setTranslateY(0);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(Path)));
			writeTree(root, bufferedWriter, 0);
			bufferedWriter.close();
			root.setTranslateX(x);
			root.setTranslateY(y);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeTree(Parent root, BufferedWriter bufferedWriter, int indent) {
		try {
			write(bufferedWriter, root.getClass().getName(), indent, root, false);
			for (Node node : root.getChildrenUnmodifiable()) {
				if (node instanceof Parent && !(node instanceof Control))
					writeTree((Parent) node, bufferedWriter, indent + 1);
				else {
					write(bufferedWriter, node.getClass().getName(), indent + 1, node, true);
				}
			}
			bufferedWriter.write(indent(bufferedWriter, ">", indent));
			bufferedWriter.newLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String indent(BufferedWriter bufferedWriter, String string, int indent) {
		String indented = "";
		try {
			for (int i = 0; i < indent; i++) {
				bufferedWriter.write("\t");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		indented += string;
		return indented;
	}

	private void write(BufferedWriter bufferedWriter, String string, int indent, Node node, boolean isLeaf) {
		try {
			for (int i = 0; i < indent; i++) {
				bufferedWriter.write("\t");
			}
			bufferedWriter.write("<" + string + " ");
			writeAttributes(node, bufferedWriter);
			if (isLeaf)
				bufferedWriter.write(">");
			bufferedWriter.newLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeAttributes(Node node, BufferedWriter bufferedWriter) {
		try {
			for (Method method : node.getClass().getMethods()) {
				if (check(method)) {
					bufferedWriter.write(method.getName().replace("get", "set")
							+ ("='" + method.invoke(node, (Object[]) null) + "'").replace(" ", "~") + " ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	private boolean check(Method method) {
		if (method.getName().startsWith("get") && method.getParameterCount() == 0 && !method.getName().contains("getOn")
				&& !methodFilters.contains(method.getName()))
			return true;
		return false;
	}

	private void loadMethodFilters(String FilterPath) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(FilterPath)));
			String Line;
			while ((Line = bufferedReader.readLine()) != null) {
				methodFilters.add(Line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

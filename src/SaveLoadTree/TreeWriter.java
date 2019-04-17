package SaveLoadTree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;

import Loaders.MethodFilter;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Control;

public class TreeWriter {

	private String Path;

	public TreeWriter(String Path) {
		this.Path = Path;
	}

	public void writeTree(Parent root) {
		try {

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(Path)));
			writeTree((Parent) root, bufferedWriter, 0);
			bufferedWriter.close();

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
				&& !MethodFilter.contains(method.getName()))
			return true;
		return false;
	}

}

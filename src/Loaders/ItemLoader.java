package Loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import InsideWindows.FirstScene;
import javafx.scene.Node;
import javafx.scene.image.Image;

@SuppressWarnings("deprecation")
public abstract class ItemLoader {

	private static class ItemDescription {
		private String Name, Section;
		private Image Image;

		private ItemDescription(String name, Image image, String section) {
			Name = name;
			Image = image;
			Section = section;
		}

		public String getName() {
			return Name;
		}

		public Image getImage() {
			return Image;
		}

		public String getSection() {
			return Section;
		}

	}

	private static Map<String, ItemDescription> LookUpTable = new HashMap<>();

	public static void LoadItems(String Path) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(Path)));
			String Line = null;
			while ((Line = bufferedReader.readLine()) != null) {
				if (Line.isEmpty())
					continue;
				String[] fields = Line.split(" ");
				Image image = new Image("File:" + fields[1]);
				FirstScene.entityExplorer.AddItem(fields[0], image, (Node) Class.forName(fields[2]).newInstance(),
						fields[3]);
				LookUpTable.put(fields[2], new ItemDescription(fields[0], image, fields[3]));
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void LoadSections(String Path) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(Path)));
			String Line = null;

			while ((Line = bufferedReader.readLine()) != null) {
				if (Line.isEmpty())
					continue;
				FirstScene.entityExplorer.AddSection(Line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String LookUpName(Node node) {
		return LookUpTable.get(node.getClass().getName()).getName();
	}

	public static Image LookUpImage(Node node) {
		return LookUpTable.get(node.getClass().getName()).getImage();
	}

	public static String LookUpSection(Node node) {
		return LookUpTable.get(node.getClass().getName()).getSection();
	}

}

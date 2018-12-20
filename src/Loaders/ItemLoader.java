package Loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import InsideWindows.FirstScene;
import javafx.scene.Node;
import javafx.scene.image.Image;

@SuppressWarnings("deprecation")
public class ItemLoader {

	public static void LoadItems(String Path) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(Path)));
			String Line = null;
			while ((Line = bufferedReader.readLine()) != null) {
				if (Line.isEmpty())
					continue;
				String[] fields = Line.split(" ");
				FirstScene.entityExplorer.AddItem(fields[0], new Image("File:" + fields[1]),
						(Node) Class.forName(fields[2]).newInstance(), fields[3]);
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

}

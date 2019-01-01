package BluePrints;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BlueprintCollection {

	public static Blueprint BP_Add = new Blueprint("Add", new Image("File:msg.png"), event -> {
		System.out.println("test");
	}, 2, 1, Color.LIGHTBLUE);


}

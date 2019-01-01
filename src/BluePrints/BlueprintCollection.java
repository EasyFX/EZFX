package BluePrints;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BlueprintCollection {
	
	public static BlueprintBuilder BP_ADD_BUILDER = new BlueprintBuilder("Add", new Image("File:msg.png"), event -> {
		System.out.println("Add");
	}, 2, 1, Color.LIGHTBLUE),
			BP_;


}

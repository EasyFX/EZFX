package BluePrints;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BlueprintCollection {

	public static BlueprintBuilder BP_ADD_BUILDER = new BlueprintBuilder("Add", new Image("File:msg.png"),
			BlueprintEvents.ADD, 2, 1, Color.LIGHTBLUE),
			 BP_TWICE_BUILDER = new BlueprintBuilder("twice", new Image("File:msg.png"),
						BlueprintEvents.TWICE, 1, 1, Color.LIGHTBLUE),
			BP_SUBTRACT_BUILDER = new BlueprintBuilder("Subtract", new Image("File:msg.png"), BlueprintEvents.SUBTRACT, 2, 1,
					Color.LIGHTBLUE),
			BP_MULTIPLY_BUILDER = new BlueprintBuilder("Multiply", new Image("File:msg.png"), BlueprintEvents.MULTIPLY, 2, 1,
					Color.LIGHTBLUE),
			BP_DIVIDE_BUILDER = new BlueprintBuilder("Divide", new Image("File:msg.png"), BlueprintEvents.DIVIDE, 2, 1,
					Color.LIGHTBLUE),
			SIZE_UP_BUILDER=new BlueprintBuilder("Size Up",new Image("File:msg.png"),BlueprintEvents.SIZE_UP,0,1,Color.LIGHTBLUE),
			SIZE_DOWN_BUILDER=new BlueprintBuilder("Size Down",new Image("File:msg.png"),BlueprintEvents.SIZE_DOWN,0,1,Color.LIGHTBLUE);


	
}

// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Animations;


import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;

public class shapeManipulation {

	static AnimationFactory factory = new AnimationFactory();

	public static void movePivot(Node node, double x, double y){
		node.getTransforms().add(new Translate(-x,-y));
		node.setTranslateX(x); node.setTranslateY(y);
	}
   
	public static void glowColor(Shape s, Color from, Color to) {
		factory.buildFill(s, 1000, 0, from, to, 1, false);
	}
}
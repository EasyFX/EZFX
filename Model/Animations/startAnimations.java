// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Animations;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javafx.animation.SequentialTransition;

public class startAnimations {
	
	////StartController
	//A: Glow effect + E-Z-F-X flash + twist main
	//UndoA: Revert A to a single black pane
	
	//B: Load in login option + bottom bar
	//UndoB: Load out login options
	
	//Profile: Comes after A, loads in profile information in place of login options + templates on side + setting button
	//UndoProfile: Loads out profile

	private static AnimationFactory factory = new AnimationFactory();
	
	public static SequentialTransition getLoginSequenceA(Rectangle[] set, ImageView[] setI){
		
		for(ImageView img : setI){
			img.setOpacity(0);
		}
		
		return AnimationFactory.buildSequential(
			factory.buildFade(setI[4], 1000, 0, 0.0, 1.0, 0.1, 1, false),
			AnimationFactory.buildParallel(			
				factory.buildRotate(setI[4], 600, 0, 360, null, 2, false),
				factory.buildFade(set[0], 1000, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildScale(set[0], 1000, 1, 1, 1, 0.33333, 1, 1, 1, 1, false)
			),
			AnimationFactory.buildParallel(	
				factory.buildFade(setI[4], 600, 0, 1.0, 0.0, 0.1, 1, false),
				factory.buildFade(set[1], 600, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[2], 600, 0, 0.0, 1.0, 0.1, 1, false)
			),
			AnimationFactory.buildParallel(
					factory.buildTranslate(set[1], 500, 0, 0, 0, 0, 512, 1, 1, 1, false),
					factory.buildTranslate(set[2], 500, 0, 0, 0, 100, 0, 1, 1, 1, false),
					AnimationFactory.buildSequential(
						factory.buildFade(setI[0], 140, 0, 0.0, 1.0, 0.1, 1, false),
						factory.buildFade(setI[1], 140, 0, 0.0, 1.0, 0.1, 1, false),
						factory.buildFade(setI[2], 140, 0, 0.0, 1.0, 0.1, 1, false),
						factory.buildFade(setI[3], 140, 0, 0.0, 1.0, 0.1, 1, false)
					)
			),
			AnimationFactory.buildParallel(
					factory.buildTranslate(set[1], 500, 0, 0, 512, 100, 512, 1, 1, 1, false),
					factory.buildTranslate(set[2], 500, 0, 100, 0, 100, 512, 1, 1, 1, false),
					AnimationFactory.buildSequential(
							AnimationFactory.buildPause(200),
							factory.buildFade(setI[0], 200, 0, 1.0, 0.1, 0.1, 1, false),
							factory.buildFade(setI[1], 200, 0, 1.0, 0.1, 0.1, 1, false),
							factory.buildFade(setI[2], 200, 0, 1.0, 0.1, 0.1, 1, false),
							factory.buildFade(setI[3], 200, 0, 1.0, 0.1, 0.1, 1, false)
					)
			),
			AnimationFactory.buildParallel(	
				factory.buildFade(set[1], 600, 0, 1.0, 0.0, 0.1, 1, false),
				factory.buildFade(set[2], 600, 0, 1.0, 0.0, 0.1, 1, false)
			),
			factory.buildRotate(set[0], 300, 500, 30, null, 1, false),
			AnimationFactory.buildParallel(
				factory.buildFade(set[3], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildRotate(set[3], 200, 500, -30, null, 1, false)
			)
		);
	}
	
	public static SequentialTransition getQuickSequenceA(Rectangle[] set, ImageView[] setI, int delay){
		
		return new SequentialTransition(
				factory.buildScale(set[0], 600, 0, 1, 1, 0.3333, 1, 1, 0.1, 1, false),
				factory.buildRotate(set[0], 300, 500, 30, null, 1, false),
				AnimationFactory.buildSequential(
						AnimationFactory.buildPause(200),
						factory.buildFade(setI[0], 200, 0, 0.0, 0.1, 0.1, 1, false),
						factory.buildFade(setI[1], 200, 0, 0.0, 0.1, 0.1, 1, false),
						factory.buildFade(setI[2], 200, 0, 0.0, 0.1, 0.1, 1, false),
						factory.buildFade(setI[3], 200, 0, 0.0, 0.1, 0.1, 1, false)
				),
				AnimationFactory.buildParallel(
						factory.buildFade(set[3], 200, 0, 0.0, 1.0, 0.1, 1, false),
						factory.buildRotate(set[3], 200, 500, -30, null, 1, false)
				)
			);
		
	}
	
	public static SequentialTransition getUndoSequenceA(Rectangle[] set, ImageView[] setI, int delay){
		
		return new SequentialTransition(
			AnimationFactory.buildPause(delay),
			AnimationFactory.buildParallel(
					factory.buildFade(set[3], 300, 0, 1.0, 0.0, 0.1, 1, false),
					factory.buildRotate(set[3], 200, 0, 30, null, 1, false)
			),
			factory.buildRotate(set[0], 100, 0, -30, null, 1, false),
			AnimationFactory.buildSequential(
					factory.buildFade(setI[0], 200, 0, 0.1, 0.0, 1.0, 1, false),
					factory.buildFade(setI[1], 200, 0, 0.1, 0.0, 1.0, 1, false),
					factory.buildFade(setI[2], 200, 0, 0.1, 0.0, 1.0, 1, false),
					factory.buildFade(setI[3], 200, 0, 0.1, 0.0, 1.0, 1, false)
			)
		);
	}
	
	public static SequentialTransition getLoginSequenceB(Rectangle[] setR, Button[] setB, int delay){

		return AnimationFactory.buildSequential(
			AnimationFactory.buildPause(delay),
			AnimationFactory.buildParallel(	
				factory.buildFade(setR[0], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildRotate(setR[0], 355, 500, 60, null, 1, false)
			),
			AnimationFactory.buildParallel(	
				factory.buildFade(setR[1], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildRotate(setR[1], 355, 500, 60, null, 1, false)
			),
			AnimationFactory.buildParallel(	
				factory.buildFade(setR[2], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildRotate(setR[2], 355, 500, 60, null, 1, false)
			),
			factory.buildFade(setB[0], 400, 0, 0.0, 1.0, 0.1, 1, false),
			factory.buildFade(setB[1], 300, 0, 0.0, 1.0, 0.1, 1, false),
			factory.buildFade(setB[2], 300, 0, 0.0, 1.0, 0.1, 1, false)
		);
	}
	
	public static SequentialTransition getUndoSequenceB(Rectangle[] setR, Button[] setB) {
		return new SequentialTransition(
			AnimationFactory.buildParallel(
				factory.buildFade(setB[0], 200, 0, 1.0, 0.0, 0.1, 1, false),
				factory.buildFade(setB[1], 200, 0, 1.0, 0.0, 0.1, 1, false),
				factory.buildFade(setB[2], 200, 0, 1.0, 0.0, 0.1, 1, false)
			),
			AnimationFactory.buildParallel(	
					factory.buildRotate(setR[0], 400, 500, -60, null, 1, false),
					factory.buildFade(setR[0], 700, 0, 1.0, 0.0, 0.1, 1, false)
			),
			AnimationFactory.buildParallel(	
					factory.buildRotate(setR[1], 400, 500, -60, null, 1, false),
					factory.buildFade(setR[1], 700, 0, 1.0, 0.0, 0.1, 1, false)
			),
			AnimationFactory.buildParallel(	
					factory.buildRotate(setR[2], 400, 500, -60, null, 1, false),
					factory.buildFade(setR[2], 700, 0, 1.0, 0.0, 0.1, 1, false)
			)
		);
	}
	
	
	public static SequentialTransition getProfileSequence(Node[] set, Text[] setT) {
		
		return new SequentialTransition(
			AnimationFactory.buildPause(2000),
			AnimationFactory.buildParallel(
					factory.buildFade(set[0], 600, 0, 0.0, 1.0, 0.1, 1, false),
					factory.buildRotate(set[0], 555, 500, -30, null, 1, false)
			),
			factory.buildFade(set[1], 600, 0, 0.0, 1.0, 0.1, 1, false),
			factory.buildFade(setT[0], 200, 0, 0.0, 1.0, 0.1, 1, false),
			factory.buildFade(setT[1], 200, 0, 0.0, 1.0, 0.1, 1, false)
		);
	}
	
	public static SequentialTransition getUndoProfileSequence(Node[] set,  Text[] setT) {
		
		return new SequentialTransition(
			factory.buildFade(set[1], 600, 0, 1.0, 0.0, 0.1, 1, false),
			factory.buildFade(setT[0], 200, 0, 1.0, 0.0, 0.1, 1, false),
			factory.buildFade(setT[1], 200, 0, 1.0, 0.0, 0.1, 1, false),
			AnimationFactory.buildParallel(
				factory.buildFade(set[0], 600, 0, 1.0, 0.0, 0.1, 1, false),
				factory.buildRotate(set[0], 555, 500, 30, null, 1, false)
			)
		);
	}

}








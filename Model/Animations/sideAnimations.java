package Animations;

import javafx.animation.SequentialTransition;
import javafx.scene.Node;

public class sideAnimations {

	private static AnimationFactory factory = new AnimationFactory();

	public static SequentialTransition getLoginSequence(Node[] set, int delay) {
		
		return new SequentialTransition(
			AnimationFactory.buildPause(delay),
			AnimationFactory.buildParallel(
				factory.buildFade(set[0], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[1], 200, 0, 0.0, 1.0, 0.1, 1, false)
			),
			AnimationFactory.buildParallel(
				factory.buildFade(set[2], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[3], 200, 0, 0.0, 1.0, 0.1, 1, false)
			),
			AnimationFactory.buildParallel(
				factory.buildFade(set[4], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[5], 200, 0, 0.0, 1.0, 0.1, 1, false)
			)
		);
	}
	
	public static SequentialTransition getRegisterSequence(Node[] set, int delay) {
		
		return new SequentialTransition(
			AnimationFactory.buildParallel(
					AnimationFactory.buildPause(delay),
				factory.buildFade(set[0], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[1], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[2], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[3], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[4], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[5], 200, 0, 0.0, 1.0, 0.1, 1, false)
			),
			AnimationFactory.buildParallel(
				factory.buildFade(set[6], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[7], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[8], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[9], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[10], 200, 0, 0.0, 1.0, 0.1, 1, false)

			),
			AnimationFactory.buildParallel(
				factory.buildFade(set[11], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[12], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[13], 200, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[14], 200, 0, 0.0, 1.0, 0.1, 1, false)
			)
		);
	}
}

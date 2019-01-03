package Animations;

import javafx.animation.SequentialTransition;
import javafx.scene.Node;

public class sideAnimations {

	private static AnimationFactory factory = new AnimationFactory();

	public static SequentialTransition getLoginSequence(Node[] set, int delay) {
		
		return new SequentialTransition(
			AnimationFactory.buildPause(delay),
			AnimationFactory.buildParallel(
				factory.buildFade(set[0], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[1], 400, 0, 0.0, 1.0, 0.1, 1, false)
			),
			AnimationFactory.buildParallel(
				factory.buildFade(set[2], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[3], 400, 0, 0.0, 1.0, 0.1, 1, false)
			),
			AnimationFactory.buildParallel(
				factory.buildFade(set[4], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[5], 400, 0, 0.0, 1.0, 0.1, 1, false)
			)
		);
	}
	
	public static SequentialTransition getRegisterSequence(Node[] set, int delay) {
		
		return new SequentialTransition(
			AnimationFactory.buildParallel(
					AnimationFactory.buildPause(delay),
				factory.buildFade(set[0], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[1], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[2], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[3], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[4], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[5], 400, 0, 0.0, 1.0, 0.1, 1, false)
			),
			AnimationFactory.buildParallel(
				factory.buildFade(set[6], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[7], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[8], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[9], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[10], 400, 0, 0.0, 1.0, 0.1, 1, false)

			),
			AnimationFactory.buildParallel(
				factory.buildFade(set[11], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[12], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[13], 400, 0, 0.0, 1.0, 0.1, 1, false),
				factory.buildFade(set[14], 400, 0, 0.0, 1.0, 0.1, 1, false)
			)
		);
	}
}

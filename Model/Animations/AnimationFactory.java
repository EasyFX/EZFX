// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Animations;


import javafx.scene.Node;
import javafx.util.Duration;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import javafx.animation.Transition;
import javafx.animation.FillTransition;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.SequentialTransition;

public class AnimationFactory {

	public static SequentialTransition buildSequential(Transition ... set) {
		return new SequentialTransition(set);
	}
	
	public static ParallelTransition buildParallel(Transition ... set) {
		return new ParallelTransition(set);
	}
	
	public static PauseTransition buildPause(int time) {
		return new PauseTransition(Duration.millis(time));
	}
	
	public FadeTransition buildFade(Node s, int time, int delay, double from, double to, double by, int cycles, boolean autoReverse) {
		FadeTransition ft = new FadeTransition(new Duration(time),s);
		ft.setToValue(to);
		ft.setDelay(new Duration(delay));
		ft.setByValue(by);
		ft.setFromValue(from);
		ft.setCycleCount(cycles);
		ft.setAutoReverse(autoReverse);
		return ft;
	}
	
	public TranslateTransition buildTranslate(Node s, int time, int delay, double fromX, double fromY, double toX, double toY,
									double byX, double byY, int cycles, boolean autoReverse) {
		TranslateTransition tt = new TranslateTransition(new Duration(time),s);
		tt.setFromX(fromX);
		tt.setFromY(fromY);
		tt.setToX(toX);
		tt.setToY(toY);
		tt.setByX(byX);
		tt.setByY(byY);
		tt.setDelay(new Duration(delay));
		tt.setCycleCount(cycles);
		tt.setAutoReverse(autoReverse);
		return tt;
	}
	
	public RotateTransition buildRotate(Node s, int time, int delay, double angle, double[] axis, int cycles, boolean autoReverse) {
		RotateTransition rt = new RotateTransition(new Duration(time),s);
		rt.setByAngle(angle);
		rt.setDelay(new Duration(delay));
		rt.setAxis(axis == null ? null : new Point3D(axis[0],axis[1],axis[2]));
		rt.setCycleCount(cycles);
		rt.setAutoReverse(autoReverse);
		return rt;
	}
	
	public ScaleTransition buildScale(Node s, int time, int delay, double fromX, double fromY, double toX, double toY,
								double byX, double byY, int cycles, boolean autoReverse) {
		ScaleTransition st = new ScaleTransition(new Duration(time),s);
		st.setFromX(fromX);
		st.setFromY(fromY);
		st.setToX(toX);
		st.setToY(toY);
		st.setByX(byX);
		st.setByY(byY);
		st.setDelay(new Duration(delay));
		st.setCycleCount(cycles);
		st.setAutoReverse(autoReverse);
		return st;
	}
	
	public FillTransition buildFill(Shape s, int time, int delay, Color from, Color to, int cycles, boolean autoReverse) {
		FillTransition ft = new FillTransition(new Duration(time),s , from, to);
		
		ft.setDelay(new Duration(delay));
		ft.setCycleCount(cycles);
		ft.setAutoReverse(autoReverse);
		return ft;
	
	}
}

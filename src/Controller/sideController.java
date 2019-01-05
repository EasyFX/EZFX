	package Controller;

import DB_Assets.passwordManager;
import Essentials.rootLink;
import Graphics.buttonHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class sideController {

	private rootLink rLink = new rootLink();
	
	private static passwordManager PM = passwordManager.getinstance();
	
	
	public passwordManager getPM() {
		return PM;
	}
	
	public rootLink getLink() {
		return rLink;
	}
	
	@FXML
	private void buttonHoverOn(Event event) {
		Button btn = (Button) event.getSource();
		buttonHandler.glowButton(btn);
	}
	
	@FXML
	private void buttonHoverOff(Event event) {
		Button btn = (Button) event.getSource();
		buttonHandler.unglowButton(btn);
	}
	
	@FXML
	private void goBack(ActionEvent event) {
		
		Parent root = null;
		try {
			root = rLink.getRoot("./Start.fxml");
		} catch (Exception e) {
			System.out.println("Error A3: FXML fail");
			e.printStackTrace();
		}
		
		Driver.switchScene(root);
		Driver.controller.reConstruct(0);
		
	}
	
}

	package Controller;

import Essentials.rootLink;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;

public class sideController {

	private rootLink rLink = new rootLink();

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

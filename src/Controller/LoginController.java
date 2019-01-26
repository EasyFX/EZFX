// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import Animations.sideAnimations;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextField;

public class LoginController extends sideController implements Initializable {

	@FXML
	Rectangle mainbar;
	@FXML
	Text text_username;
	@FXML
	Text text_password;
	@FXML
	Text text_error;
	@FXML
	TextField input_username;
	@FXML
	TextField input_password;
	@FXML
	Button b_login;
	@FXML
	Button b_back;
	
	private DBController DBC = DBController.getInstance();
	
	
	@FXML
	private void login(ActionEvent event) {
		
		String user = input_username.getText();
		String pass = input_password.getText();
		
		if(user.isEmpty() || pass.isEmpty()) {
			text_error.setText("Fields can not be empty");
			text_error.setOpacity(1);
		}
		
		else {
			
			if(DBC.checkLogin(DBC.select(DBC.setupConnection(), "*", "user", null), user, pass)) {
				
				Parent root = null;
				try {
					root = getLink().getRoot("./Start.fxml");
				} catch (Exception e) {
					System.out.println("Error A3: FXML fail");
					e.printStackTrace();
				}
				Driver.switchScene(root);
				Driver.controller.reConstruct(1);
			}	
			else {
				text_error.setText("Username or Password incorrrect");
				text_error.setOpacity(1);
			}
		}	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Node[] set = {text_username,text_password,input_username,input_password, b_login, b_back};
		sideAnimations.getLoginSequence(set,3000).play();
		
	}
}

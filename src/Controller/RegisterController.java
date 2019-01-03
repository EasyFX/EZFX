// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import Animations.sideAnimations;
import DB_Assets.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterController extends sideController implements Initializable{

	@FXML
	private Text text_username;
	@FXML
	private Text text_password;
	@FXML
	private Text text_password1;
	@FXML
	private Text text_password2;
	@FXML
	private Text text_error;
	@FXML
	private Text text_email;
	@FXML
	private Text text_path;	
	
	@FXML
	private TextField input_username;
	@FXML
	private TextField input_password;
	@FXML
	private TextField input_password1;
	@FXML
	private TextField input_email;
	@FXML
	private TextField input_path;
	
	@FXML
	private CheckBox news;
	
	@FXML
	private Button b_register;
	@FXML
	private Button b_back;
	@FXML
	private Button b_browse;
		
	private DBController DBC = DBController.getInstance();

	@FXML
	private void register(ActionEvent event) {
		DBC.setupConnection();
		
		String user = input_username.getText();
		String pass1 = input_password.getText();
		String pass2 = input_password1.getText();
		String email = input_email.getText();
		
		if(user.isEmpty() || pass1.isEmpty() || pass2.isEmpty() || email.isEmpty()) {
			text_error.setText("Fields can not be empty");
			text_error.setOpacity(1);
		}
		
		else if(!pass1.equals(pass2)) {
			text_error.setText("Passwords do not match");
			text_error.setOpacity(1);
		}
		
		else {
			
			User.getInstance(user, email, input_path.getText(), pass1);
			
			//Parent root = null;
			//try {
			//	root = rLink.getRoot("./Start.fxml");
			//} catch (Exception e) {
			//	System.out.println("Error A3: FXML fail");
			//	e.printStackTrace();
			//}
			//Driver.switchScene(root);
			//Driver.controller.reConstruct(1);
			
		}
	}
	
	@FXML
	private void browse(ActionEvent event) {
		
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);

        input_path.setText(f.getSelectedFile().getPath());
        
	}
	
	@FXML
	private void newsletter(ActionEvent event) {
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Node[] set = {text_username,text_password,text_password1,text_password2,
				text_email,text_path,input_username,input_password,input_password1,input_email,input_path,
				news,b_register,b_back,b_browse};
		
		sideAnimations.getRegisterSequence(set,2000).play();
		
	}
	
}






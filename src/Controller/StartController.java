// (c) 2019 EasyFX
// This code is licensed under MIT license (see LICENSE.txt for details)

package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Animations.startAnimations;
import Animations.shapeManipulation;

import Essentials.rootLink;

import Graphics.textHandler;
import Graphics.imageHandler;
import Graphics.buttonHandler;

import javafx.event.Event;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;

public class StartController implements Initializable{
	
	@FXML
	private Rectangle mainBar;
	@FXML
	private Rectangle mainBarGlow;
	@FXML
	private Rectangle mainBarGlow2;
	@FXML
	private Rectangle optionBar1;
	@FXML
	private Rectangle optionBar2;
	@FXML
	private Rectangle optionBar3;
	@FXML
	private Button b_guest;
	@FXML
	private Button b_login;
	@FXML
	private Button b_reg;
	@FXML
	private Button b_back;
	@FXML
	private ImageView img_E;
	@FXML
	private ImageView img_Z;
	@FXML
	private ImageView img_F;
	@FXML
	private ImageView img_X;
	@FXML
	private ImageView icon_settings;
	@FXML
	private Rectangle sideBar;
	@FXML
	private Rectangle topBar;
	@FXML
	private Circle profile_circle;
	@FXML
	private Text text_name;
	@FXML
	private Text text_email;
	
	private rootLink rLink = new rootLink();
	private imageHandler imgHandler = imageHandler.getInstance();
	private textHandler txtHandler = textHandler.getInstance();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		setConstructor();
		
		//Packing up sets:
		Rectangle[] glowBarSet = {mainBar,mainBarGlow,mainBarGlow2,sideBar};
		Rectangle[] optionsSet = {optionBar1,optionBar2,optionBar3};
		Button[] buttonSet = {b_guest,b_login,b_reg,b_back};
		ImageView[] imgSet = {img_E,img_Z,img_F,img_X,icon_settings};
		
		//Minor permanent adjustments:
		profile_circle.setStroke(null);
		
		//Setting images:
		img_E.setImage(imgHandler.getImage("Views/img/logo_Letter_E.png"));
		img_Z.setImage(imgHandler.getImage("Views/img/logo_Letter_Z.png"));
		img_F.setImage(imgHandler.getImage("Views/img/logo_Letter_F.png"));
		img_X.setImage(imgHandler.getImage("Views/img/logo_Letter_X.png"));
		icon_settings.setImage(imgHandler.getImage("Views/icons/icon_settings.png"));
		
		//Buttons:
		buttonHandler.unsetButton(false, buttonSet);
		
		//Pivoting:
		shapeManipulation.movePivot(optionBar1,100,100);
		shapeManipulation.movePivot(optionBar2,100,100);
		shapeManipulation.movePivot(optionBar3,100,100);
		
		//Loading sequence from Model:
		startAnimations.getLoginSequenceA(glowBarSet, imgSet).play();
		startAnimations.getLoginSequenceB(optionsSet,buttonSet,5500).play();
		buttonHandler.setButton(false,b_guest,b_login,b_reg);
	}
	
	@FXML //click button Guest
	private void guestLoggingIn(ActionEvent event) {
		Rectangle[] optionsSet = {optionBar1,optionBar2,optionBar3};
		Button[] buttonSet = {b_guest,b_login,b_reg};

		Node[] profileSet = {topBar,profile_circle};
		Text[] profileText = {text_name,text_email};
		String[] fillerText = {"Name: Guest","Email: N/A"};
		
		hideLogins(optionsSet,buttonSet);
		showProfile(profileSet,profileText);
		txtHandler.setText(profileText, fillerText);
		
		buttonHandler.unsetButton(true, buttonSet);
		buttonHandler.setButton(true,b_back);
	}
	
	@FXML //click button "back" after a succesful guest or non guest login
	private void loggingOut(ActionEvent event) {
		Rectangle[] optionsSet = {optionBar1,optionBar2,optionBar3};
		Button[] buttonSet = {b_guest,b_login,b_reg,b_back};

		Node[] profileSet = {topBar,profile_circle};
		Text[] profileText = {text_name,text_email};
		
		hideProfile(profileSet,profileText);
		showLogins(optionsSet,buttonSet);
		
		buttonHandler.setButton(false, buttonSet);
		buttonHandler.unsetButton(true,b_back);
		
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
	
	@FXML //click button "register"
	private void register(ActionEvent event) {
		Rectangle[] optionsSet = {optionBar1,optionBar2,optionBar3};
		Button[] buttonSet = {b_guest,b_login,b_reg,b_back};
		Rectangle[] glowBarSet = {mainBar,mainBarGlow,mainBarGlow2,sideBar};
		ImageView[] imgSet = {img_E,img_Z,img_F,img_X,icon_settings};
		
		Parent root = null;
		try {
			root = rLink.getRoot("./Register.fxml");
		} catch (Exception e) {
			System.out.println("Error A3: FXML fail");
			e.printStackTrace();
		}
		final Parent root2 = root;
		
		hideLogins(optionsSet,buttonSet);
		
		SequentialTransition trans = startAnimations.getUndoSequenceA(glowBarSet, imgSet, 800);
		trans.setOnFinished(e -> Driver.switchScene(root2));
		trans.play();
	}
	
	@FXML //click button "login"
	private void login(ActionEvent event) {
		Rectangle[] optionsSet = {optionBar1,optionBar2,optionBar3};
		Button[] buttonSet = {b_guest,b_login,b_reg,b_back};
		Rectangle[] glowBarSet = {mainBar,mainBarGlow,mainBarGlow2,sideBar};
		ImageView[] imgSet = {img_E,img_Z,img_F,img_X,icon_settings};

		Parent root = null;
		try {
			root = rLink.getRoot("./Login.fxml");
		} catch (Exception e) {
			System.out.println("Error A3: FXML fail");
			e.printStackTrace();
		}
		final Parent root2 = root;
		
		hideLogins(optionsSet,buttonSet);
		
		SequentialTransition trans = startAnimations.getUndoSequenceA(glowBarSet, imgSet, 500);
		trans.setOnFinished(e -> Driver.switchScene(root2));
		trans.play();
	}

	//give Driver reference to current controller
	private void setConstructor() {		
		Driver.controller = this;
	}
	
	//Methods for calls rom outside the class
	
	public void reConstruct(int status) {
		Rectangle[] glowBarSet = {mainBar,mainBarGlow,mainBarGlow2,sideBar};
		ImageView[] imgSet = {img_E,img_Z,img_F,img_X,icon_settings};
		Rectangle[] optionsSet = {optionBar1,optionBar2,optionBar3};
		Button[] buttonSet = {b_guest,b_login,b_reg,b_back};
		Node[] profileSet = {topBar,profile_circle};
		Text[] profileText = {text_name,text_email};
		
		startAnimations.getQuickSequenceA(glowBarSet, imgSet, 2000).play();
		if(status==0) {
			showLogins(optionsSet,buttonSet);
		}
		else {			
			showProfile(profileSet,profileText);
			
			String[] fillerText = {"Name: Guest","Email: N/A"};
			txtHandler.setText(profileText, fillerText);
			
			buttonHandler.setButton(true,b_back);
		}
	}
	
	private void showLogins(Rectangle[] optionsSet, Button[] buttonSet) {
		startAnimations.getLoginSequenceB(optionsSet,buttonSet,0).play();
	}
	
	private void showProfile(Node[] profileSet, Text[] profileText) {
		startAnimations.getProfileSequence(profileSet,profileText).play();
	}
	
	//Undoing previous methods
	private void hideLogins(Rectangle[] optionsSet, Button[] buttonSet) {
		startAnimations.getUndoSequenceB(optionsSet,buttonSet).play();
	}
	private void hideProfile(Node[] profileSet, Text[] profileText) {
		startAnimations.getUndoProfileSequence(profileSet,profileText).play();
	}

	
}





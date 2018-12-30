package App;

import java.awt.Toolkit;

import Handlers.MainWindowEventHandler;
import InsideWindows.BluePrintScene;
import InsideWindows.FirstScene;
import Loaders.ItemLoader;
import Scene.SceneManager;
import Utils.Constants;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	public static Stage Window;
	public static MainWindowEventHandler mainWindowEventHandler;

	private SceneManager sceneManager = SceneManager.getSceneManager();

	@Override
	public void start(Stage window) throws Exception {
		Window = window;
		setup();
		window.show();
	}

	private void setup() {
		setupWindow();
		mainWindowEventHandler = new MainWindowEventHandler();
		setupLayout();
		ItemLoader.LoadSections("./Sections.in");
		ItemLoader.LoadItems("./Items.in");
	}

	private void setupWindow() {
		Window.setMinWidth(Constants.MINWIDTH);
		Window.setWidth(Constants.WIDTH);
		Window.setMaxWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());

		Window.setMinHeight(Constants.MINHEIGHT);
		Window.setHeight(Constants.HEIGHT);
		Window.setMaxHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight());

		Window.setResizable(true);
	}

	private void setupLayout() {
		sceneManager.addScene(new FirstScene());
		sceneManager.changeScene(1);
		sceneManager.addScene(new BluePrintScene());
	}

	public static void main(String[] args) {
		launch(args);
	}
}

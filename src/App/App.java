package App;

import java.awt.Toolkit;

import BlueprintScene.BluePrintScene;
import FirstScene.FirstScene;
import Handlers.MainWindowEventHandler;
import Loaders.AttributesFilter;
import Loaders.ItemLoader;
import Loaders.MethodFilter;
import Scene.SceneManager;
import Utils.Constants;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	public static Stage Window;
	public static MainWindowEventHandler mainWindowEventHandler;

	private SceneManager sceneManager = SceneManager.getSceneManager();

	static {
		MethodFilter.loadMethodFilters(Constants.METHOD_FILTER_PATH);
		AttributesFilter.loadAttributesFilters(Constants.ATTRIBUTES_FILTER_PATH);
	}

	@Override
	public void start(Stage window) throws Exception {
		Window = window;
		setup();
		window.show();
	}

	private void setup() {
		Window.setTitle(Constants.SOFTWARE_TITLE);
		setupWindow();
		mainWindowEventHandler = new MainWindowEventHandler();
		setupLayout();
		ItemLoader.LoadSections(Constants.SECTIONS_INPUT_PATH);
		ItemLoader.LoadItems(Constants.ITEMS_INPUT_PATH);
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
		sceneManager.addScene(new BluePrintScene(null));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
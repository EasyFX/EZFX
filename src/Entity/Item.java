package Entity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javafx.scene.Node;

public class Item extends Node {
	private Node node;
	private int id;
	private static int ID = 1;

	private String OnMousePressed, OnMouseReleased, OnKeyPressed, OnKeyReleased;

	public Item(Node node) {
		this.node = node;
		id = ID;
		ID++;
	}

	public void setOnMousePressed(String onMousePressed) {
		OnMousePressed = onMousePressed;
	}

	public void setOnMouseReleased(String onMouseReleased) {
		OnMouseReleased = onMouseReleased;
	}

	public void setOnKeyPressed(String onKeyPressed) {
		OnKeyPressed = onKeyPressed;
	}

	public void setOnKeyReleased(String onKeyReleased) {
		OnKeyReleased = onKeyReleased;
	}

	public void save() {
		try {
			BufferedWriter bufferewriter = new BufferedWriter(
					new FileWriter(new File("./OutputFile/Listeners/Listeners" + id + ".in")));
			if (OnMousePressed != null)
				bufferewriter.write(OnMousePressed);
			if (OnMouseReleased != null)
				bufferewriter.write(OnMouseReleased);
			if (OnKeyPressed != null)
				bufferewriter.write(OnKeyPressed);
			if (OnKeyReleased != null)
				bufferewriter.write(OnKeyReleased);
			node.applyCss();
			bufferewriter.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	
	}
}

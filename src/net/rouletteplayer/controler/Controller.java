package net.rouletteplayer.controler;

import net.rouletteplayer.exceptions.CornerDoesNotExistsException;
import net.rouletteplayer.model.Model;
import net.rouletteplayer.view.MainWindow;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Controller {

	private Model model;
	private MainWindow view;

	public Controller(Model model) {
		this.model = model;
	}

	public void keyPressed(KeyEvent evt) {
		PointerInfo inf = MouseInfo.getPointerInfo();
		switch (evt.getKeyCode()) {
			case KeyEvent.VK_F1:
				view.showHelp();
				break;
			case KeyEvent.VK_F2:
				model.setLeftTopCornerPosition(inf.getLocation());
				break;
			case KeyEvent.VK_F3:
				model.setRightBottomCornerPosition(inf.getLocation());
				break;
			case KeyEvent.VK_F4:
				try {
					model.play();
				} catch (CornerDoesNotExistsException e) {
					e.printStackTrace();
				}
				break;
		}
	}

	public void setView(MainWindow view) {
		this.view = view;
	}
}

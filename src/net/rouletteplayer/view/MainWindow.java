package net.rouletteplayer.view;

import net.rouletteplayer.controler.Controller;
import net.rouletteplayer.model.Config;
import net.rouletteplayer.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {


	public MainWindow(Model model, final Controller controller) {
		try {
			controller.setView(this);

			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle(Config.MAIN_WINDOW_NAME);
			this.setResizable(false);

			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(
					(int) (screen.getWidth() / 2 - 250),
					(int) (screen.getHeight() / 2 - 250));

			this.add(new JLabel("Welcome in automatic Roulette Player"));
			this.setSize(150, 200);

			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent evt) {
					controller.keyPressed(evt);
				}
			});

			this.pack();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
	}

	public void showHelp() {
		this.showMessageDialog("Controls: \n"
				+ "F1                              Help\n"
				+ "F2                              Set left top corner position\n"
				+ "F3                              Set right bottom corner position\n"
				+ "F4                              Start to play\n");
	}

	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this,message);
	}
}
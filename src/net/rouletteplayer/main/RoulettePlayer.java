package net.rouletteplayer.main;


import net.rouletteplayer.controler.Controller;
import net.rouletteplayer.model.Config;
import net.rouletteplayer.model.Model;
import net.rouletteplayer.view.MainWindow;

import javax.swing.*;

public class RoulettePlayer {

	public static void main(String[] args) {
		final Model model = new Model(Config.MARTINGALE_STRATEGY);
		final Controller controller = new Controller(model);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainWindow(model, controller).setVisible(true);
			}
		});
	}
}

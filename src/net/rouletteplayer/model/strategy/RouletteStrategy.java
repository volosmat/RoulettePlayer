package net.rouletteplayer.model.strategy;

import net.rouletteplayer.model.ScreenRecognizer;
import net.rouletteplayer.model.coordinates.Coordinates;

import java.awt.*;
import java.awt.event.InputEvent;

public abstract class RouletteStrategy {

	protected Runtime runtime = Runtime.getRuntime();
	protected Robot robot;
	protected ScreenRecognizer winRecognizer;

	protected int totalWon = 0;

	public RouletteStrategy() {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	protected void moveAndPressMouse(Point point, int count, int sleep) {
		for (int i=0; i<count; i++) {
			this.robot.mouseMove(point.x, point.y);
			this.robot.mousePress(InputEvent.BUTTON1_MASK);
			this.robot.mouseRelease(InputEvent.BUTTON1_MASK);
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public abstract int bet(Coordinates coordinates);

	public abstract void updateBet(Coordinates coordinates, int won);

	public void spin(Coordinates coordinates) {
		moveAndPressMouse(coordinates.getQuickSpin(), 1, 2500);
	}

	public int checkWinAmount(ScreenRecognizer winAmountRecognizer) {
		return winAmountRecognizer.recognizeScreen();
	}

	public int getTotalWon() {
		return totalWon;
	}
}

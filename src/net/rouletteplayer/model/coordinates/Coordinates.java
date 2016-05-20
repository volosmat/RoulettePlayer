package net.rouletteplayer.model.coordinates;

import java.awt.*;

public abstract class Coordinates {
	// Relative coordinates of items in game window
	protected Point winRelativeX = new Point(449,790);
	protected Point winRelativeY = new Point(632,652);
	protected Point quickSpinRelativeX = new Point(378,868);
	protected Point quickSpinRelativeY = new Point(566,652);
	protected Point clearBetRelativeX = new Point(584,868);
	protected Point clearBetRelativeY = new Point(571,652);

	protected Point leftTopCorner;
	protected Point rightBottomCorner;

	protected Point quickSpin;
	protected Point clearBet;
	protected Point winAmount;

	public Coordinates(Point leftTopCorner, Point rightBottomCorner) {
		this.leftTopCorner = leftTopCorner;
		this.rightBottomCorner = rightBottomCorner;
	}

	protected abstract void recalculate();

	public void setRightBottomCorner(Point rightBottomCorner) {
		this.rightBottomCorner = rightBottomCorner;
		this.recalculate();
	}

	public void setLeftTopCorner(Point leftTopCorner) {
		this.leftTopCorner = leftTopCorner;
		this.recalculate();
	}

	public Point getQuickSpin() {
		return quickSpin;
	}

	public Point getClearBet() {
		return clearBet;
	}

	public Point getWinAmount() {
		return winAmount;
	}
}

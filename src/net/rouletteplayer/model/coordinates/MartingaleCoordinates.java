package net.rouletteplayer.model.coordinates;

import java.awt.*;

public class MartingaleCoordinates extends Coordinates {
	// Relative coordinates of items in game window
	private Point redNumbersRelativeX = new Point(422,868);
	private Point redNumbersRelativeY = new Point(371,652);
	private Point blackNumbersRelativeX = new Point(490,868);
	private Point blackNumbersRelativeY = new Point(414,652);
	private Point euroChipRelativeX = new Point(831,868);
	private Point euroChipRelativeY = new Point(614,652);

	private Point euroChip;
	private Point redNumbers;
	private Point blackNumbers;


	public MartingaleCoordinates(Point leftTopCorner, Point rightBottomCorner) {
		super(leftTopCorner, rightBottomCorner);
		this.recalculate();
	}

	protected void recalculate() {
		int winWidth = this.rightBottomCorner.x - this.leftTopCorner.x;
		int winLength = this.rightBottomCorner.y  - this.leftTopCorner.y;
		int x,y;

		x = Math.round(this.leftTopCorner.x + winWidth*((float)this.winRelativeX.x/(float)this.winRelativeX.y));
		y = Math.round(this.leftTopCorner.y + winLength*((float)this.winRelativeY.x/(float)this.winRelativeY.y));
		this.winAmount = new Point(x,y);

		x = Math.round(this.leftTopCorner.x + winWidth*((float)this.quickSpinRelativeX.x/(float)this.quickSpinRelativeX.y));
		y = Math.round(this.leftTopCorner.y + winLength*((float)this.quickSpinRelativeY.x/(float)this.quickSpinRelativeY.y));
		this.quickSpin = new Point(x,y);

		x = Math.round(this.leftTopCorner.x + winWidth*((float)this.clearBetRelativeX.x/(float)this.clearBetRelativeX.y));
		y = Math.round(this.leftTopCorner.y + winLength*((float)this.clearBetRelativeY.x/(float)this.clearBetRelativeY.y));
		this.clearBet = new Point(x,y);

		x = Math.round(this.leftTopCorner.x + winWidth*((float)this.redNumbersRelativeX.x/(float)this.redNumbersRelativeX.y));
		y = Math.round(this.leftTopCorner.y + winLength*((float)this.redNumbersRelativeY.x/(float)this.redNumbersRelativeY.y));
		this.redNumbers = new Point(x,y);

		x = Math.round(this.leftTopCorner.x + winWidth*((float)this.blackNumbersRelativeX.x/(float)this.blackNumbersRelativeX.y));
		y = Math.round(this.leftTopCorner.y + winLength*((float)this.blackNumbersRelativeY.x/(float)this.blackNumbersRelativeY.y));
		this.blackNumbers = new Point(x,y);

		x = Math.round(this.leftTopCorner.x + winWidth*((float)this.euroChipRelativeX.x/(float)this.euroChipRelativeX.y));
		y = Math.round(this.leftTopCorner.y + winLength*((float)this.euroChipRelativeY.x/(float)this.euroChipRelativeY.y));
		this.euroChip = new Point(x,y);
	}

	public Point getBlackNumbers() {
		return blackNumbers;
	}

	public Point getEuroChip() {
		return euroChip;
	}

	public Point getRedNumbers() {
		return redNumbers;
	}

}

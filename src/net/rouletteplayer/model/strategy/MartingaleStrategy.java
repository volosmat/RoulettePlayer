package net.rouletteplayer.model.strategy;

import net.rouletteplayer.model.coordinates.Coordinates;
import net.rouletteplayer.model.coordinates.MartingaleCoordinates;

import java.awt.*;

public class MartingaleStrategy extends RouletteStrategy {

	private Point actualNumbers;
	private int bet = 1;

	@Override
	public int bet(Coordinates coordinates) {
		MartingaleCoordinates martingaleCoordinates = (MartingaleCoordinates) coordinates;
		if (this.actualNumbers == null) {
			this.actualNumbers = martingaleCoordinates.getBlackNumbers();
		}

		// bet to actual numbers
		moveAndPressMouse(this.actualNumbers, this.bet, 300);

		return this.bet;
	}

	@Override
	public void updateBet(Coordinates coordinates, int won) {
		MartingaleCoordinates martingaleCoordinates = (MartingaleCoordinates) coordinates;

		if (won != 0 || this.bet == 16) {
			this.switchNumbers(martingaleCoordinates);
			this.bet = 1;
			this.totalWon++;
			moveAndPressMouse(coordinates.getClearBet(), 1, 2000);
		} else {
			this.bet *= 2;
		}

	}

	private void switchNumbers(MartingaleCoordinates coordinates) {
		if (this.actualNumbers == coordinates.getBlackNumbers()) {
			this.actualNumbers = coordinates.getRedNumbers();
		} else {
			this.actualNumbers = coordinates.getBlackNumbers();
		}
	}

}

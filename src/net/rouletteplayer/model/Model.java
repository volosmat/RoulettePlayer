package net.rouletteplayer.model;

import net.rouletteplayer.exceptions.CornerDoesNotExistsException;
import net.rouletteplayer.exceptions.FactoryNotExistsException;
import net.rouletteplayer.model.coordinates.Coordinates;
import net.rouletteplayer.model.factory.coordinates.CoordinatesFactory;
import net.rouletteplayer.model.strategy.RouletteStrategy;

import java.awt.*;

public class Model {

	private Coordinates coordinates;
	private CoordinatesFactory coordinatesFactory;
	private RouletteStrategy rouletteStrategy;
	private ScreenRecognizer winAmountRecognizer;

	private int strategy;
	private Point leftTopCorner;
	private Point rightBottomCorner;

	public Model(int strategy) {
		this.strategy = strategy;
	}

	private void recalculate() throws CornerDoesNotExistsException {
		if (this.rightBottomCorner == null || this.leftTopCorner == null) {
			throw new CornerDoesNotExistsException("Top left or bottom right corner was not set.");
		}

		try {
			this.coordinatesFactory = CoordinatesFactory.getFactory(this.strategy);
		} catch (FactoryNotExistsException e) {
			e.printStackTrace();
		}

		this.coordinates = this.coordinatesFactory.createCoordinates(this.leftTopCorner, this.rightBottomCorner);
		this.rouletteStrategy = this.coordinatesFactory.getRouletteStrategy();
		this.winAmountRecognizer =
				new ScreenRecognizer(
						this.coordinates.getWinAmount().x,
						this.coordinates.getWinAmount().y,
						Config.SCREEN_WIDTH,
						Config.SCREEN_HEIGHT,
						Config.SCREEN_FILENAME,
						Config.TEXT_FILENAME
				);
	}

	public void setLeftTopCornerPosition(Point point) {
		this.leftTopCorner = point;
	}

	public void setRightBottomCornerPosition(Point point) {
		this.rightBottomCorner = point;
	}

	/**
	 * Main game loop (bet, spin ...)
	 */
	public void play() throws CornerDoesNotExistsException {
		this.recalculate();

		while (rouletteStrategy.getTotalWon() < 10) {
			int bet = this.rouletteStrategy.bet(this.coordinates);
			this.rouletteStrategy.spin(this.coordinates);
			int win = this.rouletteStrategy.checkWinAmount(this.winAmountRecognizer);
			this.rouletteStrategy.updateBet(this.coordinates, win);

			System.out.println("Bet: " + bet + " Won: " + win);
			System.out.println("Total won: " + this.rouletteStrategy.getTotalWon());
		}

		// stop ocr engine
		System.out.println("Your total profit is: " + rouletteStrategy.getTotalWon() + " EUR");
	}
}

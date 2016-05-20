package net.rouletteplayer.model.factory.coordinates;

import net.rouletteplayer.exceptions.FactoryNotExistsException;
import net.rouletteplayer.model.Config;
import net.rouletteplayer.model.coordinates.Coordinates;
import net.rouletteplayer.model.strategy.RouletteStrategy;

import java.awt.*;

public abstract class CoordinatesFactory {

	protected RouletteStrategy rouletteStrategy;

	public abstract Coordinates createCoordinates(Point rightTopCorner, Point leftBottomCorner);

	public RouletteStrategy getRouletteStrategy() {
		return this.rouletteStrategy;
	}

	public static CoordinatesFactory getFactory(int strategy) throws FactoryNotExistsException {
		if (strategy == Config.MARTINGALE_STRATEGY) {
			return new MartingaleCoordinatesFactory();
		}

		throw new FactoryNotExistsException("For this strategy do not exists any factory");
	}
}
package net.rouletteplayer.model.factory.coordinates;


import net.rouletteplayer.model.coordinates.Coordinates;
import net.rouletteplayer.model.coordinates.MartingaleCoordinates;
import net.rouletteplayer.model.strategy.MartingaleStrategy;

import java.awt.*;

public class MartingaleCoordinatesFactory extends CoordinatesFactory {

	public MartingaleCoordinatesFactory() {
		this.rouletteStrategy = new MartingaleStrategy();
	}

	@Override
	public Coordinates createCoordinates(Point rightTopCorner, Point leftBottomCorner) {
		return new MartingaleCoordinates(rightTopCorner, leftBottomCorner);
	}
}

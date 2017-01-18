package lille1.petit.antoine.hunt;

import java.awt.Color;
import java.util.Random;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Environment;
import lille1.petit.antoine.core.Position;

public class Wall extends Agent {

	public Wall(final Environment environment, final Random rand, final Position pos) {
		super(environment, rand, pos);
		color = Color.BLACK;
	}

	@Override
	public void update() {
	}

	@Override
	public void decide() {
	}

}

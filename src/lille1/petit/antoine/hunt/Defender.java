package lille1.petit.antoine.hunt;

import java.awt.Color;
import java.util.Random;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Environment;
import lille1.petit.antoine.core.Position;
import lille1.petit.antoine.core.PropertiesReader;

public class Defender extends Agent {

	private int life;

	public Defender(Environment environment, Random rand, Position pos) {
		super(environment, rand, pos);
		color = Color.ORANGE;
		life = PropertiesReader.defenderLife;
	}

	@Override
	public void update() {
		if (life == 0) {
			this.environment.removeAt(this.position);
		}
	}

	@Override
	public void decide() {
			life--;
	}

}

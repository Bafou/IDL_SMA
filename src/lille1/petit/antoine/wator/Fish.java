package lille1.petit.antoine.wator;

import java.awt.Color;
import java.util.Random;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Environment;
import lille1.petit.antoine.core.Position;

public class Fish implements Agent {
	
	protected Position position;
	
	protected int breedTime; 
	
	protected Environment environment;
	
	protected Random rand;
	
	public Fish (final Environment env, final Random random) {
		rand = random;
		environment = env;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public int getBreedTime() {
		return breedTime;
	}

	public void setBreedTime(final int breedTime) {
		this.breedTime = breedTime;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(final Environment environment) {
		this.environment = environment;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void decide() {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}


}

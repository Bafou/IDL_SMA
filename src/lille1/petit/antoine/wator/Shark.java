package lille1.petit.antoine.wator;

import java.awt.Color;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Environment;
import lille1.petit.antoine.core.Position;

public class Shark implements Agent {
	
	protected Position position;

	protected int breedTime;
	
	protected int starveTime;
	
	protected Environment environment;

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public Position getPosition() {
		return null;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public int getBreedTime() {
		return breedTime;
	}

	public void setBreedTime(int breedTime) {
		this.breedTime = breedTime;
	}

	public int getStarveTime() {
		return starveTime;
	}

	public void setStarveTime(int starveTime) {
		this.starveTime = starveTime;
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
		return Color.RED;
	}


}

package lille1.petit.antoine.wator;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import lille1.petit.antoine.core.Environment;
import lille1.petit.antoine.core.Position;
import lille1.petit.antoine.core.PropertiesReader;

public class Fish extends WaterAnimal {
	
	protected int breedTime; 
	
	protected Random rand;
	
	public Fish (final Environment env, final Random random, final Position pos, final SMAWator smaW) {
		super (env, random, pos, smaW);
		rand = random;
		environment = env;
		age = 0;
		breedTime = PropertiesReader.breedAgeFish;
		color = Color.GREEN;
	}

	public int getBreedTime() {
		return breedTime;
	}

	public void setBreedTime(final int breedTime) {
		this.breedTime = breedTime;
	}


	@Override
	public void update() {
		if (age >= breedTime) 
			color= Color.BLUE;
	}

	@Override
	public void decide() {
		super.decide();
		List<Position> freeSpots = getFreeSpotsAround();
		
		boolean move = false;
		Position p = null;
		
		if(!freeSpots.isEmpty()){
			p = freeSpots.get(rand.nextInt(freeSpots.size()));
			move = true;
		}
		
		if(move){
			int previousX = position.getPosX(),
					previousY = position.getPosY();
			moveTo(p);

			if(age % breedTime  == 0){
				giveBirth(previousX, previousY);
			}
		}
	}

	@Override
	protected void giveBirth(int x, int y) {
		Fish fish = new Fish(environment, rand, new Position(x, y),smaWator);
		environment.getAgentTab()[x][y] = fish;
		smaWator.addAgent(fish);
	}

	@Override
	public boolean isShark() {
		return false;
	}

	@Override
	public boolean isFish() {
		return true;
	}


}

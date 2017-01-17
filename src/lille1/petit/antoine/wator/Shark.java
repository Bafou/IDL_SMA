package lille1.petit.antoine.wator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lille1.petit.antoine.core.Environment;
import lille1.petit.antoine.core.Position;
import lille1.petit.antoine.core.PropertiesReader;

public class Shark extends WaterAnimal {
	
	protected int breedTime;
	
	protected int starveTime;
	
	protected int starve;
	
	public Shark (final Environment env, final Random random, final Position pos, final SMAWator smaW) {
		super(env, random, pos, smaW);
		rand = random;
		environment = env;
		starve = 0;
		breedTime = PropertiesReader.breedAgeShark;
		starveTime = PropertiesReader.starveTime;
		color = Color.PINK;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
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
		if (age >= breedTime) color = Color.RED;

	}

	@Override
	public void decide() {
		super.decide();

		if(starve == starveTime){
			environment.removeAt(position);
			smaWator.removeAgent(this);
			return;
		}
		
		boolean move = false, eat = false;
		Position p = null;
		
		List<Position> spotsWithFish = getSpotsWithFish();
		if(!spotsWithFish.isEmpty()){
			p = spotsWithFish.get(rand.nextInt(spotsWithFish.size()));
			move = true;
			eat = true;

		} else {
			List<Position> freeSpots = getFreeSpotsAround();
			if(!freeSpots.isEmpty()){
				p = freeSpots.get(rand.nextInt(freeSpots.size()));
				move = true;
				eat = false;
				
			}
		}
		
		starve++;

		if(move){
			if(eat){
				eatFishAt(p);
				starve = 0;	
			}
			int previousX = position.getPosX(),
					previousY = position.getPosY();
			moveTo(p);

			if(age % breedTime == 0){
				giveBirth(previousX, previousY);
			}

		}
		
	}
	
	private List<Position> getSpotsWithFish(){
		List<Position> spotsWithFish = new ArrayList<Position>();
		for(int i=position.getPosX()-1;i<=position.getPosX()+1;i++){
			for(int j= position.getPosY() - 1;j<=position.getPosY() + 1;j++){
				Position p = new Position(i, j);
				
				if(environment.isInside(p)){
					WaterAnimal animal = (WaterAnimal) environment.getAgentAt(p);
					if(animal != null && animal.isFish())
						spotsWithFish.add(p);
				}
			}
		}
		return spotsWithFish;
	}

	
	private void eatFishAt(Position p){
		Fish fishToBeEaten = (Fish) environment.getAgentAt(p);
		environment.removeAt(p);
		smaWator.removeAgent(fishToBeEaten);
	}

	@Override
	protected void giveBirth(int x, int y) {
		Shark shark = new Shark(environment, rand, new Position(x, y), smaWator);
		environment.getAgentTab()[x][y] = shark;
		smaWator.addAgent(shark);
	}

	@Override
	public boolean isShark() {
		return true;
	}

	@Override
	public boolean isFish() {
		return false;
	}


}

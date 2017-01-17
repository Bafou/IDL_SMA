package lille1.petit.antoine.wator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Environment;
import lille1.petit.antoine.core.Position;

public abstract class WaterAnimal extends Agent {

	protected int age;
	protected SMAWator smaWator;

	public WaterAnimal(final Environment e, final Random rand, final Position pos, final SMAWator smaW) {
			super(e, rand, pos);
			smaWator = smaW;
			age = 0;
		}

	protected List<Position> getFreeSpotsAround() {
		List<Position> freeSpots = new ArrayList<Position>();
		for (int i = position.getPosX() - 1; i <= position.getPosX() + 1; i++) {
			for (int j = position.getPosY() - 1; j <= position.getPosY() + 1; j++) {
				Position p = new Position(i, j);
				if (environment.isToric()) {
					adaptePosToToricEnv(p);
				}
				if (environment.isInside(p) && environment.getAgentAt(p) == null) {
					freeSpots.add(p);
				}
			}
		}

		return freeSpots;
	}
	
	private void adaptePosToToricEnv(Position p){
		if(p.getPosX() == -1)
			p.setPosX( p.getPosX() + environment.getAgentTab().length);
		else if(p.getPosX() == environment.getAgentTab().length)	
			p.setPosX(p.getPosX() % environment.getAgentTab().length);
		if(p.getPosY() == -1)
			p.setPosY(p.getPosY() + environment.getAgentTab()[0].length);
		else if(p.getPosY() == environment.getAgentTab()[0].length)	
			p.setPosY(p.getPosY() % environment.getAgentTab()[0].length);
	}

	protected abstract void giveBirth(int x, int y);

	public boolean isNewBorn() {
		return age == 0;
	}

	@Override
	public void decide() {
		age++;
	}


	public abstract boolean isShark();

	public abstract boolean isFish();

}

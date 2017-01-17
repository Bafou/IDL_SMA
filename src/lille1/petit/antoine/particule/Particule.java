package lille1.petit.antoine.particule;

import java.awt.Color;
import java.util.Random;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Environment;
import lille1.petit.antoine.core.Position;
import lille1.petit.antoine.core.PropertiesReader;

public class Particule extends Agent {

	

	/** Direction sur l'axe X */
	private int stepX;

	/** Direction sur l'axe Y */
	private int stepY;

	
	private boolean hasChange;

	

	public int getStepX() {
		return stepX;
	}

	public void setStepX(final int stepX) {
		this.stepX = stepX;
	}

	public int getStepY() {
		return stepY;
	}

	public void setStepY(final int stepY) {
		this.stepY = stepY;
	}

	public boolean isHasChange() {
		return hasChange;
	}

	public void setHasChange(boolean asChange) {
		this.hasChange = asChange;
	}

	public Particule(final Environment environment, final Random rand, final Position pos) {
		super(environment, rand, pos);
		this.environment = environment;

		int r = rand.nextInt(200);
		int g = rand.nextInt(200);
		int b = rand.nextInt(200);
		this.color = new Color(r, g, b);
		
		setDirection();
	}

	public void decide() {
		int nextX = position.getPosX() + stepX;
		int nextY = position.getPosY() + stepY;
		if (environment.isToric()) {
			if (nextX < 0) {
				nextX = environment.getAgentTab().length + stepX;
			} else {
				nextX = nextX % environment.getAgentTab().length;
			}
			if (nextY < 0) {
				nextY = environment.getAgentTab()[0].length + stepY;
			} else {
				nextY = nextY % environment.getAgentTab()[0].length;
			}
		}

		if (!environment.isToric()) {
			if (nextX >= environment.getAgentTab().length || nextX < 0) {
				stepX = -stepX;
				hasChange = true;
				if (PropertiesReader.trace) System.out.println("Agent," + position.getPosX() + ","+ position.getPosY() + "," + (nextX <0 ? "Mur gauche": "Mur droit"));
			}
			if (nextY >= environment.getAgentTab()[0].length || nextY < 0) {
				stepY = -stepY;
				hasChange = true;
				if (PropertiesReader.trace) System.out.println("Agent," + position.getPosX() + ","+ position.getPosY() + "," + (nextY <0 ? "Mur haut": "Mur bas"));
			}
		}
		if (!hasChange) {
			Agent agent = environment.getAgentTab()[nextX][nextY];
			if (agent != null) {
				if (agent instanceof Particule) {
					final Particule particule = (Particule) agent;
					swapStep(particule);
					hasChange = true;
					if (PropertiesReader.trace) System.out.println("Agent," + position.getPosX() + ","+ position.getPosY() + "," + "Autre agent (" + agent.getPosition().posX + "; " + agent.getPosition().posY + ")");
				}
			}
		}
	}

	private void swapStep(final Particule particule) {
		int footOtherAgentX = particule.getStepX();
		int footOtherAgentY = particule.getStepY();
		particule.setStepX(stepX);
		particule.setStepY(stepY);
		particule.setHasChange(true);
		this.stepX = footOtherAgentX;
		this.stepY = footOtherAgentY;
//		particule.setStepX(stepX);
//		particule.setStepY(stepY);
//		particule.setHasChange(true);
//		this.stepX = -stepX;
//		this.stepY = -stepY;
	}

	@Override
	public void update() {
		if (!hasChange) {
			move();
		}
		hasChange = false;
	}

	public void move() {
		int nextPosX = position.posX + stepX;
		int nextPosY = position.posY + stepY;
		if (environment.isToric() && nextPosX < 0) {
			nextPosX = environment.getAgentTab().length + stepX;
		} else {
			nextPosX = nextPosX % environment.getAgentTab().length;
		}
		if (environment.isToric() && nextPosY < 0) {
			nextPosY = environment.getAgentTab()[0].length + stepY;
		} else {
			nextPosY = nextPosY % environment.getAgentTab()[0].length;
		}
		moveTo(new Position(nextPosX, nextPosY));
	}

	private void setDirection() {
		switch (rand.nextInt(8)) {
		case 0:
			stepX = -1;
			stepY = -1;
			break;
		case 1:
			stepX = -1;
			stepY = 0;
			break;
		case 2:
			stepX = 0;
			stepY = -1;
			break;
		case 3:
			stepX = 1;
			stepY = -1;
			break;
		case 4:
			stepX = 0;
			stepY = 1;
			break;
		case 5:
			stepX = 1;
			stepY = 0;
			break;
		case 6:
			stepX = 1;
			stepY = 1;
			break;
		case 7:
			stepX = -1;
			stepY = 1;
			break;
		}

	}
}

package lille1.petit.antoine.particule;

import java.awt.Color;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Environment;

public class Particule implements Agent {

	/** Position sur l'axe X */
	protected int posX;

	/** Position sur l'axe Y */
	protected int posY;

	/** Direction sur l'axe X */
	private int stepX;

	/** Direction sur l'axe Y */
	private int stepY;

	/** Couleur de l'agent */
	private Color color;
	
	private boolean hasChange;

	/** Environnement contenant cet agent */
	private Environment environment;

	public int getPosX() {
		return posX;
	}

	public void setPosX(final int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(final int posY) {
		this.posY = posY;
	}

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

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}
	

	public boolean isHasChange() {
		return hasChange;
	}

	public void setHasChange(boolean asChange) {
		this.hasChange = asChange;
	}


	public void decide() {
		int nextX = posX + stepX;
		int nextY = posY + stepY;

		if (!environment.isToric()) {
			if (nextX >= environment.getAgentTab().length) {
				stepX = -stepX;
				hasChange = true;
			}
			if (nextY >= environment.getAgentTab()[0].length) {
				stepY = -stepY;
				hasChange = true;
			}
		}
		Agent agent = environment.getAgentTab()[nextX][nextY];
		if (agent != null) {
			if (agent instanceof Particule) {
				final Particule particule = (Particule) agent;
				swapStep(particule);
				hasChange = true;
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
	}

	@Override
	public void update() {
		if (!hasChange) {
			move();
		}
		hasChange = false;
	}
	
	public void move() {
		
	}
}

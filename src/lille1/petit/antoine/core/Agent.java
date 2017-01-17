package lille1.petit.antoine.core;
import java.awt.Color;
import java.util.Random;

public abstract class Agent {

	/** Couleur de l'agent */
	protected Color color;
	
	/** Environnement contenant cet agent */
	protected Environment environment;

	protected Random rand;
	
	protected Position position;
	
	public Agent (final Environment environment, final Random rand, final Position pos) {
		this.environment = environment;
		this.rand = rand;
		position = pos;
		environment.getAgentTab()[position.getPosX()][position.getPosY()] = this;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

	protected void moveTo(final Position p) {
		int newX = p.getPosX(), newY = p.getPosY();
		environment.move(position.getPosX(), position.getPosY(), newX, newY);

		position.setPosX(newX);
		position.setPosY(newY);
	}

	public abstract void update();
	
	public abstract void decide();
	
}

package lille1.petit.antoine.hunt;

import java.awt.Color;
import java.util.Random;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Environment;
import lille1.petit.antoine.core.Position;
import lille1.petit.antoine.core.PropertiesReader;

public class Hunter extends Agent {

	protected Avatar avatarToFollow;
	private Dijkstra dijkstra;
	private int tick;
	protected Position nextMove;
	
	public Hunter(Environment environment, final Random rand, final Position pos, final Dijkstra dijkstra) {
		super(environment, rand, pos);
		this.dijkstra = dijkstra;
		this.color = Color.RED;
		tick = 0;
	}

	@Override
	public void update() {
		if (tick % PropertiesReader.hunterSpeed == 0) {
			move();
		}

	}

	@Override
	public void decide() {
		if (tick % PropertiesReader.hunterSpeed == 0) {
			nextMove = dijkstra.getNextMove(position);
		} else {
//			this.needToFreeze = true;
		}
		tick++;
	}

	
	public void move() {
		int dirX = nextMove.getPosX();
		int dirY = nextMove.getPosY();
		int nextPosX = position.posX + dirX;
		int nextPosY = position.posY + dirY;
		if (environment.isToric() && nextPosX < 0) {
			nextPosX = environment.getAgentTab().length + dirX;
		} else {
			nextPosX = nextPosX % environment.getAgentTab().length;
		}
		if (environment.isToric() && nextPosY < 0) {
			nextPosY = environment.getAgentTab()[0].length + dirY;
		} else {
			nextPosY = nextPosY % environment.getAgentTab()[0].length;
		}
		
		agentCollisionReaction(nextPosX, nextPosY);
	
	}
	
	public void agentCollisionReaction(int nextPosX, int nextPosY) {
		Agent collided = environment.getAgentAt(new Position(nextPosX, nextPosY));
		if (collided instanceof Hunter) {
			return; // ne transperce pas un alli�
		} else if (collided instanceof Wall) {
			return; // don't move
		} else if (collided instanceof Agent) {
			System.out.println("Ennemi marche sur joueur");
		}
		moveTo(new Position(nextPosX, nextPosY));
	}
}

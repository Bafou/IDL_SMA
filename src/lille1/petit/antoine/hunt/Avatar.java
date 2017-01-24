package lille1.petit.antoine.hunt;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Environment;
import lille1.petit.antoine.core.Position;
import lille1.petit.antoine.core.PropertiesReader;

public class Avatar extends Agent implements KeyListener {

	private Dijkstra dijkstra;
	protected int dirX;
	protected int dirY;
	protected int defendersEated;
	protected int invicibility;
	protected int tick;

	public Avatar(final Environment environment, final Random rand, final Position pos, final Dijkstra dij) {
		super(environment, rand, pos);
		color = Color.BLUE;
		defendersEated = 0;
		invicibility = 0;
		dijkstra = dij;
		dijkstra.compute(this.position, invicibility > 0);
		tick = 0;
	}

	@Override
	public void update() {
		if (tick % PropertiesReader.avatarSpeed == 0) {
			dijkstra.compute(this.position, invicibility > 0);
			if (invicibility <= 0) {
				color = Color.BLUE;
			}
			invicibility--;

			int nextX = position.getPosX() + dirX;
			int nextY = position.getPosY() + dirY;
			if (environment.isToric()) {
				if (nextX < 0) {
					nextX = environment.getAgentTab().length + dirX;
				} else {
					nextX = nextX % environment.getAgentTab().length;
				}
				if (nextY < 0) {
					nextY = environment.getAgentTab()[0].length + dirY;
				} else {
					nextY = nextY % environment.getAgentTab()[0].length;
				}
			}

			if (!environment.isToric()) {
				if (nextX >= environment.getAgentTab().length || nextX < 0) {
					dirX = 0;
				}
				if (nextY >= environment.getAgentTab()[0].length || nextY < 0) {
					dirY = 0;
				}
			}

			move();
			dirX = 0;
			dirY = 0;
		}
		tick++;
	}

	public void agentCollisionReaction(int nextPosX, int nextPosY) {
		Agent collided = environment.getAgentAt(new Position(nextPosX, nextPosY));
		if (collided instanceof Defender) {
			environment.removeAt(collided.getPosition());
			defendersEated++;
			setInvicibleColor();
			invicibility = 5;
		} else if (collided instanceof Hunter) {
			if (invicibility <= 0) {
				System.out.println("Joueur marche sur ennemi");
			}
		} else if (collided instanceof Winner) {
			// End the game
		} else if (collided instanceof Wall) {
			return; // don't move
		}
		moveTo(new Position(nextPosX, nextPosY));
	}

	private void setInvicibleColor() {
		color = Color.YELLOW;
	}

	public void move() {
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

	@Override
	public void decide() {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_NUMPAD7:// 7
			dirX = -1;
			dirY = -1;
			break;
		case KeyEvent.VK_NUMPAD8:// 8
			dirX = 0;
			dirY = -1;
			break;
		case KeyEvent.VK_NUMPAD9:// 9
			dirX = 1;
			dirY = -1;
			break;
		case KeyEvent.VK_NUMPAD4:// 4
			dirX = -1;
			dirY = 0;
			break;
		case KeyEvent.VK_NUMPAD6:// 6
			dirX = 1;
			dirY = 0;
			break;
		case KeyEvent.VK_NUMPAD1:// 1
			dirX = -1;
			dirY = 1;
			break;
		case KeyEvent.VK_NUMPAD2:// 2
			dirX = 0;
			dirY = 1;
			break;
		case KeyEvent.VK_NUMPAD3:// 3
			dirX = 1;
			dirY = 1;
			break;
		default:
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

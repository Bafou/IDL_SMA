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
	
	protected int[][] dijkstraTab;
	protected int dirX;
	protected int dirY;

	public Avatar(final Environment environment, final Random rand, final Position pos) {
		super(environment, rand, pos);
		color = Color.BLUE;
		dijkstraTab = new int[environment.getAgentTab().length][environment.getAgentTab()[0].length];
	}

	@Override
	public void update() {
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
				dirX = -dirX;
			}
			if (nextY >= environment.getAgentTab()[0].length || nextY < 0) {
				dirY = -dirY;
			}
		}
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
		case 103://7
			dirX = -1;
			dirY = -1;
			break;
		case 104://8
			dirX = 0;
			dirY = -1;
			break;
		case 105://9
			dirX = 1;
			dirY = -1;
			break;
		case 100://4
			dirX = -1;
			dirY = 0;
			break;
		case 102://6
			dirX = 1;
			dirY = 0;
			break;
		case 97://1
			dirX = -1;
			dirY = 1;
			break;
		case 98://2
			dirX = 0;
			dirY = 1;
			break;
		case 99://1
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

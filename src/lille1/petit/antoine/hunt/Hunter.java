package lille1.petit.antoine.hunt;

import java.awt.Color;
import java.util.Random;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Environment;
import lille1.petit.antoine.core.Position;

public class Hunter extends Agent {

	protected Avatar avatarToFollow;
	protected int[][] dijkstraTab;
	
	public Hunter(final Environment environment, final Random rand, final Position pos,final Avatar avatar) {
		super(environment, rand, pos);
		color = Color.RED;
		dijkstraTab = new int[environment.getAgentTab().length][environment.getAgentTab()[0].length];
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void decide() {
		// TODO Auto-generated method stub

	}

}

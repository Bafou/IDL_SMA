package lille1.petit.antoine.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {
	
	private Agent[][] agentTab;
	
	private List<Agent> listAgent;
	
	private List<Position> freePosition;;  
	
	private boolean isToric;
	

	public boolean isToric() {
		return isToric;
	}

	public void setToric(final boolean isToric) {
		this.isToric = isToric;
	}

	public Agent[][] getAgentTab() {
		return agentTab;
	}

	public void setAgentTab(final Agent[][] agentTab) {
		this.agentTab = agentTab;
	}
	
	public Environment (int sizeX, int sizeY, boolean isToric) {
		this.isToric = isToric;
		this.agentTab = new Agent[sizeX][sizeY];
		this.listAgent = new ArrayList<Agent>();
		this.freePosition = new ArrayList<Position>();
		initFreePosition(sizeX, sizeY);
	}
	
	public Position getNextFreePosition() {
		Random rand = new Random();
		int pos = rand.nextInt(this.freePosition.size());
		return this.freePosition.remove(pos);
	}
	
	private void initFreePosition(int sizeX, int sizeY) {
		for (int x=0; x < sizeX; x++) {
			for (int y=0; y < sizeY; y++) {
				Position pos = new Position(x, y);
				this.freePosition.add(pos);
			}
		}
	}
	
	private class Position {
		
		public int posX;
		
		public int posY;
		
		public Position (int X, int Y) {
			posX = X;
			posY = Y;
		}
	}

}

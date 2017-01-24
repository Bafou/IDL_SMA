package lille1.petit.antoine.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {

	private Agent[][] agentTab;

	private List<Position> freePosition;;

	private boolean isToric;

	private Random rand;
	
	private SMA sma;

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

	public Environment(final SMA sma, final Random rand) {
		this.isToric = PropertiesReader.toric;
		this.agentTab = new Agent[PropertiesReader.gridSizeX][PropertiesReader.gridSizeY];
		this.freePosition = new ArrayList<Position>();
		initFreePosition(PropertiesReader.gridSizeX, PropertiesReader.gridSizeY);
		this.rand = rand;
		this.sma = sma;
	}

	public Position getNextFreePosition() {
		int pos = rand.nextInt(this.freePosition.size());
		return this.freePosition.remove(pos);
	}

	private void initFreePosition(int sizeX, int sizeY) {
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				final Position pos = new Position(x, y);
				this.freePosition.add(pos);
			}
		}
	}
	
	public void removeAt(final Position pos ) {
		sma.removeAgent(agentTab[pos.getPosX()][pos.getPosY()]);
		agentTab[pos.getPosX()][pos.getPosY()] = null;
	}
	
	public Agent getAgentAt (final Position pos) {
		return agentTab[pos.getPosX()][pos.getPosY()];
	}
	
	public void move(int previousX, int previousY, int newX, int newY){
		agentTab[newX][newY] = agentTab[previousX][previousY];
		agentTab[previousX][previousY] = null;
	}

	public boolean isInside(final Position pos) {
		return (pos.getPosX() >=0) && (pos.getPosX() < agentTab.length) && (pos.getPosY() >=0) && (pos.getPosY() < agentTab[0].length);
	}
}

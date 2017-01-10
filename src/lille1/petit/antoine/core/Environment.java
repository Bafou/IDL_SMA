package lille1.petit.antoine.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {

	private Agent[][] agentTab;

	private List<Position> freePosition;;

	private boolean isToric;

	private SMA sma;
	
	private Random rand;

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
		this.sma = sma;
		this.isToric = PropertiesReader.toric;
		this.agentTab = new Agent[PropertiesReader.gridSizeX][PropertiesReader.gridSizeY];
		this.freePosition = new ArrayList<Position>();
		initFreePosition(PropertiesReader.gridSizeX, PropertiesReader.gridSizeY);
		this.rand = rand;
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

}

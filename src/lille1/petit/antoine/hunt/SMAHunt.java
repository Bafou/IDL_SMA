package lille1.petit.antoine.hunt;

import java.util.ArrayList;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Position;
import lille1.petit.antoine.core.PropertiesReader;
import lille1.petit.antoine.core.SMA;

public class SMAHunt extends SMA {

	protected Avatar avatar;
	protected boolean asWinner = false;
	protected boolean asEnded = false;

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public SMAHunt() {
		super();
		initAgent();
	}

	private void initAgent() {
		agentList = new ArrayList<Agent>();
		Dijkstra dijkstra = new Dijkstra(PropertiesReader.gridSizeX, PropertiesReader.gridSizeY, this);
		Position position = environment.getNextFreePosition();
		Avatar avatar = new Avatar(environment, rand, position, dijkstra,this);
		agentList.add(avatar);
		this.avatar = avatar;
		int nbWall = PropertiesReaderHunt.percentageWall * PropertiesReader.gridSizeX * PropertiesReader.gridSizeY / 100;
		for (int i = 0; i < nbWall; i++) {
			position = environment.getNextFreePosition();
			agentList.add(new Wall(environment, rand, position));
		}
		for (int i = 0; i < PropertiesReaderHunt.nbDefender; i++) {
			position = environment.getNextFreePosition();
			agentList.add(new Defender(environment, rand, position));
		}
		for (int i = 0; i < PropertiesReaderHunt.nbHunter; i++) {
			position = environment.getNextFreePosition();
			agentList.add(new Hunter(environment, rand, position, dijkstra, this));
		}
	}

	public void addWinner() {
		Position position = environment.getNextFreePosition();
		Winner winner = new Winner(environment, rand, position);
		addAgent(winner);
		asWinner = true;
	}
	
	public boolean asWinner() {
		return asWinner;
	}

	@Override
	protected void writeOutput() {
	}

	protected void initOutput() {
	}

	@Override
	protected void actionTurn(int tick) {
		environment.refreshFreePosition();
		int nbDefender = rand.nextInt(PropertiesReaderHunt.nbDefender) + 1;
		if (tick % PropertiesReaderHunt.defenderLife == 0) {
			for (int i = 0; i < nbDefender; i++) {
				Position position = environment.getNextFreePosition();
				addAgent(new Defender(environment, rand, position));
			}
		}

	}

	@Override
	protected boolean asEnded() {
		return asEnded;
	}

	public void endGame() {
		asEnded = true;
	}
}

package lille1.petit.antoine.hunt;

import java.util.ArrayList;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Position;
import lille1.petit.antoine.core.PropertiesReader;
import lille1.petit.antoine.core.SMA;

public class SMAHunt extends SMA {
	
	protected Avatar avatar;
	
	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public SMAHunt () {
		super();
		initAgent();
	}
	
	private void initAgent(){
		agentList = new ArrayList<Agent>();
		Dijkstra dijkstra= new Dijkstra(PropertiesReader.gridSizeX, PropertiesReader.gridSizeY, this);
		Position position = environment.getNextFreePosition();
		Avatar avatar = new Avatar(environment, rand, position, dijkstra);
		agentList.add(avatar);
		this.avatar = avatar;
		for (int i = 0; i < 15;i++) {
			position = environment.getNextFreePosition();
			agentList.add(new Wall(environment, rand, position));
		}
		for (int i = 0; i < 4;i++) {
			position = environment.getNextFreePosition();
			agentList.add(new Defender(environment, rand, position));
		}
		position = environment.getNextFreePosition();
		agentList.add(new Hunter(environment, rand, position, dijkstra));
		
	}
	
	
	@Override
	protected void writeOutput(){
	}
	
	protected void initOutput(){
	}

}

package lille1.petit.antoine.particule;

import java.util.ArrayList;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Position;
import lille1.petit.antoine.core.PropertiesReader;
import lille1.petit.antoine.core.SMA;

public class SMAParticule extends SMA {
	
	public SMAParticule () {
		super();
		initAgent();
	}
	
	private void initAgent(){
		agentList = new ArrayList<Agent>();
	
		for(int i = 0; i < PropertiesReaderParticule.nbParticles && i < PropertiesReader.gridSizeX*PropertiesReader.gridSizeY; i++){
			Position position = environment.getNextFreePosition();
			agentList.add(new Particule(environment, rand,position));
		}
	}

	@Override
	protected void writeOutput() {
				
	}

	@Override
	protected void initOutput() {
		
	}

	@Override
	protected void actionTurn(int tick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean asEnded() {
		return false;
	}

}

package lille1.petit.antoine.particule;

import java.util.ArrayList;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.PropertiesReader;
import lille1.petit.antoine.core.SMA;

public class SMAParticule extends SMA {
	
	public SMAParticule () {
		super();
		initAgent();
	}
	
	private void initAgent(){
		agentList = new ArrayList<Agent>();
	
		for(int i = 0; i < PropertiesReader.nbParticles && i < PropertiesReader.gridSizeX*PropertiesReader.gridSizeY; i++){
			agentList.add(new Particule(environment, rand));
		}
	}

}

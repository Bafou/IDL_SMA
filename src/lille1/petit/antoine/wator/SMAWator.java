package lille1.petit.antoine.wator;

import java.util.ArrayList;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.PropertiesReader;
import lille1.petit.antoine.core.SMA;
import lille1.petit.antoine.particule.Particule;

public class SMAWator extends SMA {
	
	public SMAWator () {
		super();
		initAgent();
	}
	
	private void initAgent(){
		agentList = new ArrayList<Agent>();
		int i= 0;
		for(; i < PropertiesReader.nbParticles && i < PropertiesReader.gridSizeX*PropertiesReader.gridSizeY; i++){
			agentList.add(new Fish(environment, rand));
		}
		for(int j = 0; j < PropertiesReader.nbParticles && i + j < PropertiesReader.gridSizeX * PropertiesReader.gridSizeY; j++) {
			agentList.add(new Shark(environment, rand));
		}
	}

}

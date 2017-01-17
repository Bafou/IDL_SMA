package lille1.petit.antoine.wator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.Position;
import lille1.petit.antoine.core.PropertiesReader;
import lille1.petit.antoine.core.SMA;

public class SMAWator extends SMA {
	
	public SMAWator () {
		super();
		initAgent();
	}
	
	private void initAgent(){
		agentList = new ArrayList<Agent>();
		int i= 0;
		for(; i < PropertiesReader.nbFish && i < PropertiesReader.gridSizeX*PropertiesReader.gridSizeY; i++){
			Position position = environment.getNextFreePosition();
			agentList.add(new Fish(environment, rand, position, this));
		}
		for(int j = 0; j < PropertiesReader.nbShark && i + j < PropertiesReader.gridSizeX * PropertiesReader.gridSizeY; j++) {
			Position position = environment.getNextFreePosition();
			agentList.add(new Shark(environment, rand, position, this));
		}
	}
	
	private int getNbSharks(){
		int nbSharks = 0;
		for(Agent agent : agentList){
			WaterAnimal animal = (WaterAnimal) agent;
			if(animal.isShark())
				nbSharks++;
		}
		
		return nbSharks;
	}
	
	
	@Override
	protected void writeOutput(){
		int nbSharks = getNbSharks();
		String toBeWritten = tick + "," + (agentList.size()-nbSharks) + "," + nbSharks + "\n";
		try {
			outputWriter.write(toBeWritten);
			outputWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void initOutput(){
		try {
			outputWriter = new BufferedWriter(new FileWriter(output));
			outputWriter.write("time,nb fishs,nb sharks\n");
		} catch (IOException e) {
			// Do nothing
			e.printStackTrace();
		}
	}

}

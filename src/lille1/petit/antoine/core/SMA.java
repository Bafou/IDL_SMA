package lille1.petit.antoine.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public abstract class SMA extends Observable {

	protected List<Agent> agentList;

	protected List<Agent> toBeRemovedAgentList;

	protected List<Agent> toBeAddedAgentList;

	protected Environment environment;

	protected Random rand;
	
	protected int tick;
	
	protected BufferedWriter outputWriter;
	
	protected String output;

	public List<Agent> getAgentList() {
		return agentList;
	}

	public void setAgentList(List<Agent> agentList) {
		this.agentList = agentList;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public void addAgent(final Agent agent) {
		toBeAddedAgentList.add(agent);
	}

	public void removeAgent(final Agent agent) {
		toBeRemovedAgentList.add(agent);
	}

	public SMA() {
		rand = new Random();
		if (PropertiesReader.seed != 0) {
			rand.setSeed(PropertiesReader.seed);
		}
		this.environment = new Environment(this, rand);
		this.toBeRemovedAgentList = new ArrayList<Agent>();
		this.toBeAddedAgentList = new ArrayList<Agent>();
		this.tick = 0;
		this.output = "src/lille1/petit/antoine/wator/result.csv";
	}

	public void run() throws IOException {
		initOutput();
		setChanged();
		notifyObservers();
		long startTimeTotal = System.currentTimeMillis();
		while (PropertiesReader.nbTicks == 0 || tick < PropertiesReader.nbTicks) {
			long startTime = System.currentTimeMillis();
			switch (PropertiesReader.sheduling) {
			case 0:
				Collections.shuffle(agentList, rand);
			case 1:
				for (Agent agent : agentList) {
					if (!toBeRemovedAgentList.contains(agent)) {
						agent.decide();
						agent.update();
					}
				}
				break;
			case 2:
				int indexRand = rand.nextInt(agentList.size());
				agentList.get(indexRand).decide();
				agentList.get(indexRand).update();
				break;
			}

			agentList.addAll(toBeAddedAgentList);
			agentList.removeAll(toBeRemovedAgentList);
			toBeAddedAgentList.clear();
			toBeRemovedAgentList.clear();
			
			writeOutput();
			
			tick++;

			if (PropertiesReader.refresh != 0 && (tick == 0 || tick % PropertiesReader.refresh == 0)) {
				setChanged();
				notifyObservers();
			}

			long endTime = System.currentTimeMillis();
			long duration = (endTime - startTime);
			if (PropertiesReader.trace) {
				System.out.println("Tick " + tick + "," + duration);
			}

			try {
				if (PropertiesReader.delay > duration) {
					Thread.sleep(PropertiesReader.delay - duration);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long endTimeTotal = System.currentTimeMillis();
		long durationTotal = (endTimeTotal - startTimeTotal);
		System.out.println("Total time : " + durationTotal + " ms");
		outputWriter.close();

	}
	
	protected abstract void initOutput();
	
	protected abstract void writeOutput();

}

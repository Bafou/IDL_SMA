package lille1.petit.antoine.core;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public abstract class SMA extends Observable {

	protected List<Agent> agentList;

	protected Environment environment;

	protected Random rand;

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

	public SMA() {
		rand = new Random();
		if (PropertiesReader.seed != 0) {
			rand.setSeed(PropertiesReader.seed);
		}
		this.environment = new Environment(this, rand);
	}

	public void run() {
		int tick = 0;
		long startTimeTotal = System.currentTimeMillis();
		while (PropertiesReader.nbTicks == 0 || tick < PropertiesReader.nbTicks) {
			long startTime = System.currentTimeMillis();
			switch (PropertiesReader.sheduling) {
			case 0:
				Collections.shuffle(agentList, rand);
			case 1:
				for (int i = 0; i < agentList.size(); i++) {
					agentList.get(i).decide();
					agentList.get(i).update();
				}
				break;
			case 2:
				int indexRand = rand.nextInt(agentList.size());
				agentList.get(indexRand).decide();
				agentList.get(indexRand).update();
				break;
			}

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

	}

}

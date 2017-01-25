package lille1.petit.antoine.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader implements KeyListener {

	public static int gridSizeX = 20;
	public static int gridSizeY = 20;
	public static int canvasSizeX = 1200;
	public static int canvasSizeY = 1200;
	public static int boxSize = 50;
	public static int delay = 40;
	public static int sheduling = 0;
	public static int nbTicks = 0;
	public static boolean grid = true;
	public static boolean trace = false;
	public static int seed = 0;
	public static int refresh = 1;
	public static int nbParticles = 20;
	public static boolean toric = false;
	
	public static int nbShark = 20;
	public static int nbFish = 100;
	public static int breedAgeShark = 10;
	public static int breedAgeFish = 2;
	public static int starveTime = 3;

	public static int nbDefender = 4;
	public static int nbHunter = 2;
	public static int defenderLife = 200;
	public static int avatarSpeed = 4;
	public static int hunterSpeed = 8;
	public static int invicibilityTime = 20;
	public static int percentageWall = 20;
	
	
	private static PropertiesReader INSTANCE = new PropertiesReader();
	

	private PropertiesReader () {
		
	}
	
	public static PropertiesReader getInstance() {
		return INSTANCE;
	}

	public static void initProperties(final String path) {

		final Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(path);

			prop.load(input);

			gridSizeX = Integer.parseInt(prop.getProperty("gridSizeX"));
			gridSizeY = Integer.parseInt(prop.getProperty("gridSizeY"));
			canvasSizeX = Integer.parseInt(prop.getProperty("canvasSizeX"));
			canvasSizeY = Integer.parseInt(prop.getProperty("canvasSizeY"));
			boxSize = Integer.parseInt(prop.getProperty("boxSize"));
			delay = Integer.parseInt(prop.getProperty("delay"));
			sheduling = Integer.parseInt(prop.getProperty("sheduling"));
			nbTicks = Integer.parseInt(prop.getProperty("nbTicks"));
			grid = Boolean.parseBoolean(prop.getProperty("grid"));
			trace = Boolean.parseBoolean(prop.getProperty("trace"));
			seed = Integer.parseInt(prop.getProperty("seed"));
			refresh = Integer.parseInt(prop.getProperty("refresh"));
			nbParticles = Integer.parseInt(prop.getProperty("nbParticles"));
			toric = Boolean.parseBoolean(prop.getProperty("toric"));
			
			nbShark = Integer.parseInt(prop.getProperty("nbShark"));
			nbFish = Integer.parseInt(prop.getProperty("nbFish"));
			breedAgeShark = Integer.parseInt(prop.getProperty("breedAgeShark"));
			breedAgeFish = Integer.parseInt(prop.getProperty("breedAgeFish"));
			starveTime = Integer.parseInt(prop.getProperty("starveTime"));
			
			nbDefender = Integer.parseInt(prop.getProperty("nbDefender"));
			nbHunter = Integer.parseInt(prop.getProperty("nbHunter"));
			defenderLife = Integer.parseInt(prop.getProperty("defenderLife"));
			avatarSpeed = Integer.parseInt(prop.getProperty("avatarSpeed"));
			hunterSpeed = Integer.parseInt(prop.getProperty("hunterSpeed"));
			invicibilityTime = Integer.parseInt(prop.getProperty("invicibilityTime"));
			percentageWall = Integer.parseInt(prop.getProperty("percentageWall"));

		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			if (avatarSpeed > 1)
				avatarSpeed--;
			break;
		case KeyEvent.VK_Z:
			avatarSpeed++;
			break;
		case KeyEvent.VK_O:
			if (hunterSpeed > 1)
				hunterSpeed--;
			break;
		case KeyEvent.VK_P:
			hunterSpeed++;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

package lille1.petit.antoine.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	public static int gridSizeX=20;
	public static int gridSizeY=20;
	public static int canvasSizeX=1200;
	public static int canvasSizeY=1200;
	public static int boxSize=50;
	public static int delay=40;
	public static int sheduling=0;
	public static int nbTicks=0;
	public static boolean grid=true;
	public static boolean trace=false;
	public static int seed=0;
	public static int refresh=1;
	public static int nbParticles=20;
	public static boolean toric=false;
	public static int nbShark = 20;
	public static int nbFish = 100;
	public static int breedAgeShark = 10;
	public static int breedAgeFish = 2;
	public static int starveTime = 3;
	
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

}

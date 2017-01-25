package lille1.petit.antoine.wator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReaderWator {
	
	
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

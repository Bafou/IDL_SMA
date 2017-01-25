package lille1.petit.antoine.particule;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReaderParticule {

	public static int nbParticles = 20;
	
	
	public static void initProperties(final String path) {

		final Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(path);

			prop.load(input);

			nbParticles = Integer.parseInt(prop.getProperty("nbParticles"));

			

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

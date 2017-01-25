package lille1.petit.antoine.hunt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReaderHunt implements KeyListener {

	public static int nbDefender = 4;
	public static int nbHunter = 2;
	public static int defenderLife = 200;
	public static int avatarSpeed = 4;
	public static int hunterSpeed = 8;
	public static int invicibilityTime = 20;
	public static int percentageWall = 20;

	private static PropertiesReaderHunt INSTANCE = new PropertiesReaderHunt();

	private PropertiesReaderHunt() {
	}

	public static void initProperties(final String path) {
		final Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(path);

			prop.load(input);
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

	public static PropertiesReaderHunt getInstance() {
		return INSTANCE;
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

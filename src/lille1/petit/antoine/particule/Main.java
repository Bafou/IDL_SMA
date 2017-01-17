package lille1.petit.antoine.particule;

import java.io.IOException;

import javax.swing.JScrollPane;

import lille1.petit.antoine.core.PropertiesReader;
import lille1.petit.antoine.particule.view.GUIHelper;
import lille1.petit.antoine.particule.view.View;

public class Main {
	
	public static void main(String[] args) {
		
		PropertiesReader.initProperties("src/lille1/petit/antoine/particule/Particules.properties");
		SMAParticule smaParticule = new SMAParticule();

		View view = new View();
		smaParticule.addObserver(view);
		
		JScrollPane scrollPane = new JScrollPane(view);
		GUIHelper.showOnFrame(scrollPane,"S.M.A");
		
		try {
			smaParticule.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

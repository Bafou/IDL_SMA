package lille1.petit.antoine.particule;

import javax.swing.JScrollPane;

import lille1.petit.antoine.core.PropertiesReader;
import lille1.petit.antoine.particule.view.GUIHelper;
import lille1.petit.antoine.particule.view.View;

public class Main {
	
	public static void main(String[] args) {
		
		PropertiesReader.initProperties();
		SMAParticule smaParticule = new SMAParticule();

		View view = new View();
		smaParticule.addObserver(view);
		
		JScrollPane scrollPane = new JScrollPane(view);
		GUIHelper.showOnFrame(scrollPane,"S.M.A");
		
		smaParticule.run();
		
	}

}

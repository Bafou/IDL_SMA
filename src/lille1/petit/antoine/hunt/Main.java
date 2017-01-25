package lille1.petit.antoine.hunt;

import java.io.IOException;

import javax.swing.JScrollPane;

import lille1.petit.antoine.core.PropertiesReader;
import lille1.petit.antoine.core.view.GUIHelper;
import lille1.petit.antoine.core.view.View;

public class Main {
	
public static void main(String[] args) {
		
		PropertiesReader.initProperties("src/lille1/petit/antoine/hunt/Hunt.properties");
		PropertiesReaderHunt.initProperties("src/lille1/petit/antoine/hunt/Hunt.properties");
		SMAHunt smaHunt = new SMAHunt();

		View view = new View();
		view.setFocusable(true);
		smaHunt.addObserver(view);
		view.addKeyListener(smaHunt.getAvatar());
		view.addKeyListener(PropertiesReaderHunt.getInstance());
		
		JScrollPane scrollPane = new JScrollPane(view);
		GUIHelper.showOnFrame(scrollPane,"S.M.A");
		scrollPane.addKeyListener(smaHunt.getAvatar());
		try {
			smaHunt.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

}

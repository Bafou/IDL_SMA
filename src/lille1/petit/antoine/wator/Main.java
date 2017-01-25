package lille1.petit.antoine.wator;

import java.io.IOException;

import javax.swing.JScrollPane;

import lille1.petit.antoine.core.PropertiesReader;
import lille1.petit.antoine.core.view.GUIHelper;
import lille1.petit.antoine.core.view.View;

public class Main {
	
public static void main(String[] args) {
		
		PropertiesReader.initProperties("src/lille1/petit/antoine/wator/Wator.properties");
		PropertiesReaderWator.initProperties("src/lille1/petit/antoine/wator/Wator.properties");
		SMAWator smaWator = new SMAWator();

		View view = new View();
		smaWator.addObserver(view);
		
		JScrollPane scrollPane = new JScrollPane(view);
		GUIHelper.showOnFrame(scrollPane,"S.M.A");
		
		try {
			smaWator.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

}

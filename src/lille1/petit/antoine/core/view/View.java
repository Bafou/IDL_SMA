package lille1.petit.antoine.core.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lille1.petit.antoine.core.Agent;
import lille1.petit.antoine.core.PropertiesReader;
import lille1.petit.antoine.core.SMA;

public class View extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected SMA sma;

	protected int gridSizeX;

	protected int gridSizeY;

	protected int boxSize;

	protected boolean grid;

	@Override
	public void update(final Observable observable, final Object arg) {
		sma = (SMA) observable;
		this.invalidate();
		this.repaint();
	}

	public View() {

		// get the property value and print it out
		gridSizeX = PropertiesReader.gridSizeX;
		gridSizeY = PropertiesReader.gridSizeY;
		boxSize = PropertiesReader.boxSize;
		grid = PropertiesReader.grid;

		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(gridSizeX * boxSize, gridSizeY * boxSize));
	}

	public void paint(final Graphics g) {
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.GRAY);

		if (grid) {
			int gridDivisionX = gridSizeX;
			int gridDivisionY = gridSizeY;
			for (int i = 1; i < gridDivisionX; i++) {
				int x = i * (boxSize);
				g2.drawLine(x, 0, x, PropertiesReader.boxSize * PropertiesReader.gridSizeX);
			}
			for (int i = 1; i < gridDivisionY; i++) {
				int y = i * (boxSize);
				g2.drawLine(0, y, PropertiesReader.canvasSizeY * PropertiesReader.gridSizeY, y);
			}
		}

		List<Agent> agentlist = new ArrayList<Agent>(sma.getAgentList());
		for (final Agent agent : agentlist) {
			g2.setColor(agent.getColor());
			g2.fillOval(agent.getPosition().getPosX()*boxSize, agent.getPosition().getPosY()*boxSize, boxSize, boxSize);
		}

		Toolkit.getDefaultToolkit().sync();
	}

}
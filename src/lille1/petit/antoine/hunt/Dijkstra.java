package lille1.petit.antoine.hunt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lille1.petit.antoine.core.Position;

public class Dijkstra {

	private int[][] cells;
	private Set<Position> unvisited;
	private Set<Position> visited;
	private SMAHunt sma;
	private List<Position> surroundings;
	private boolean negateValue;

	public Dijkstra(int sizeX, int sizeY, SMAHunt sma) {
		this.sma = sma;
		cells = new int[sizeX][sizeY];
		unvisited = new HashSet<Position>();
		visited = new HashSet<Position>();
		surroundings = new ArrayList<Position>();
		surroundings.add(new Position(1, 0));
		surroundings.add(new Position(1, 1));
		surroundings.add(new Position(0, 1));
		surroundings.add(new Position(-1, 1));
		surroundings.add(new Position(-1, 0));
		surroundings.add(new Position(-1, -1));
		surroundings.add(new Position(0, -1));
		surroundings.add(new Position(1, -1));
		negateValue = false;
	}

	public int getCellValue(Position p) {
		return cells[p.getPosX()][p.getPosY()];
	}

	public Position getNextMove(Position p) {

		int value = cells[p.getPosX()][p.getPosY()];
		if (negateValue) {
			value = 0;
		}
		Position nextMove = new Position(0, 0);
		for (Position surrounding : surroundings) {
			int x = p.getPosX() + surrounding.getPosX();
			int y = p.getPosY() + surrounding.getPosY();
			if (x >= 0 && x < cells.length && y >= 0 && y < cells[0].length) {
				int nextValue = cells[x][y];
				if (negateValue) {
					if (nextValue >= value && nextValue != cells.length * cells[x].length) {
						nextMove = surrounding;
						value = nextValue;
					}
				} else {
					if (nextValue < value) {
						nextMove = surrounding;
						value = nextValue;
					}
				}
			}
		}

		return nextMove;
	}

	public void compute(Position avatar, boolean negateValue) {
		this.negateValue = negateValue;
		unvisited.clear();
		visited.clear();

		Position currentCell;
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				cells[x][y] = cells.length * cells[x].length;
				if ((sma.getEnvironment().getAgentTab()[x][y] != null
						&& sma.getEnvironment().getAgentTab()[x][y] instanceof Wall)) {
					visited.add(new Position(x, y));
				}
			}
		}

		currentCell = new Position(avatar.getPosX(), avatar.getPosY());

		cells[avatar.getPosX()][avatar.getPosY()] = 0;
		visitNeighborhood(currentCell);

		while (unvisited.size() != 0) {
			int lowestValue = cells.length * cells[0].length;
			for (Position p : unvisited) {
				int pValue = cells[p.getPosX()][p.getPosY()];
				if (pValue < lowestValue) {
					currentCell = p;
					lowestValue = pValue;
				}
			}
			if (lowestValue == cells.length * cells[0].length) {
				break;
			}
			visitNeighborhood(currentCell);
		}
	}

	private void visitNeighborhood(Position currentCell) {
		int currentValue = cells[currentCell.getPosX()][currentCell.getPosY()];

		visitNeighbor(currentCell.getPosX() + 1, currentCell.getPosY(), currentValue);
		visitNeighbor(currentCell.getPosX() + 1, currentCell.getPosY() + 1, currentValue);
		visitNeighbor(currentCell.getPosX(), currentCell.getPosY() + 1, currentValue);
		visitNeighbor(currentCell.getPosX() - 1, currentCell.getPosY() + 1, currentValue);
		visitNeighbor(currentCell.getPosX() - 1, currentCell.getPosY(), currentValue);
		visitNeighbor(currentCell.getPosX() - 1, currentCell.getPosY() - 1, currentValue);
		visitNeighbor(currentCell.getPosX(), currentCell.getPosY() - 1, currentValue);
		visitNeighbor(currentCell.getPosX() + 1, currentCell.getPosY() - 1, currentValue);

		unvisited.remove(currentCell);
		visited.add(currentCell);
	}

	private void visitNeighbor(int x, int y, int value) {
		Position p = new Position(x, y);
		if (x >= 0 && y >= 0 && x < cells.length && y < cells[0].length && !visited.contains(p)) {
			unvisited.add(p);
			if (cells[x][y] > value + 1) {
				cells[x][y] = value + 1;
			}
		}
	}

}

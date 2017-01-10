package lille1.petit.antoine.core;

public class Position {
	
	public int posX;
	
	public int posY;
	
	public Position (final int X, final int Y) {
		posX = X;
		posY = Y;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(final int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(final int posY) {
		this.posY = posY;
	}

}

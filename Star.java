import java.awt.*;

public class Star {
	
	public final int STAR_SIZE = 2;
	
	private Point point;
	private int secs; // ����� �������� ������
	private Color color;	
	private long startTime; // ��� ������� ������� ��������
	private boolean alive;
	
	public Star(int x, int y, int secs, Color color) {
		
		point = new Point(x, y);
		this.secs = secs;
		this.color = color;
		startTime = System.currentTimeMillis(); // ������ ������� �������� ������		
		alive = true;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean isAlive() {
		if (System.currentTimeMillis() > startTime + (secs * 1000)) {
			setAlive(false);
			return false;
		}
		return alive;
	}
	
	
	private void setAlive(boolean b) {
		alive = b;
	}
	
}

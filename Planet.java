import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Planet {
		
	private String name; // �������� �������
	private int id; // ����� �� �������
	private double radius = 1.0; // ������ ������� 
	private double radiusOrbit = 5.0; // ������ ������ 
	private double velocity = 1.0; // �������� 
	private Color color; // ����
	private int direction = 0; // �����������
	private double angle = 0; // ����
	private int moonsNumber = 0; // ���-�� ��� � �������
	
	// ������ ���
	private ArrayList<Moon> moons = null;
	
	/**
	 * ����������� ������� �������
	 * @param n - ��������
	 * @param id - ����� �� �������
	 * @param rad - ������
	 * @param radOrbit - ������ ������
	 * @param vel - ��������
	 * @param dir - �����������
	 * @param color - ����
	 */
	public Planet(String n, int id, double rad, double radOrbit, double vel, int dir, Color color)  {			
		this(n, id, rad, radOrbit, vel, dir, color, 0);
	}
	
	/**
	 * ����������� ������� �������
	 * @param n - ��������
	 * @param id - ����� �� �������
	 * @param rad - ������
	 * @param radOrbit - ������ ������
	 * @param vel - ��������
	 * @param dir - �����������
	 * @param m - ���-�� ���
	 */
	public Planet(String n, int id, double rad, double radOrbit, double vel, int dir, Color color, int m) {
		
		this.name = n;
		this.id = id;
		this.radius = rad;
		this.radiusOrbit = radOrbit;
		this.velocity = vel;
		this.direction = dir;
		this.color = color;
		this.moonsNumber = m;
		
		if (moonsNumber > 0) {
			moons = new ArrayList<Moon>();
			
			for (int i = 0; i < moonsNumber; i++) {
				/** ��������� �������� ���� - ��� ����� � ������� - ��� ������� (�����. 2 - �.�. � ������ ������� �������� ����������� �� 2)
				 *   �������� ����� - �������� = (���-�� ��� �� ������� * �� �����. �������� ���� - i * �����. �������� ����,
				 *   	��� i = 0, 1, 2... <= ���-�� ��� �� �������   
				 */				
				double moonVelocity = (moonsNumber * Moon.VELOCITY_MIN) - (i * Moon.VELOCITY_MIN);
				moons.add(new Moon(rad + 4, i+1, moonVelocity));
			}			
		}				
	}
	
	public Color getColor() {
		return color;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double a) {
		this.angle = a;
	}
	
	public double getRadiusOrbit() {
		return radiusOrbit;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	public ArrayList<Moon> getMoons() 	{
		return moons;
	}

}

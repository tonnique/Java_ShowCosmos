import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Planet {
		
	private String name; // Название планеты
	private int id; // Норер по порядку
	private double radius = 1.0; // Радиус планеты 
	private double radiusOrbit = 5.0; // радиус орбиты 
	private double velocity = 1.0; // скорость 
	private Color color; // цвет
	private int direction = 0; // направление
	private double angle = 0; // угол
	private int moonsNumber = 0; // кол-во лун у планеты
	
	// список лун
	private ArrayList<Moon> moons = null;
	
	/**
	 * Конструктор создает Планету
	 * @param n - название
	 * @param id - номер по порядку
	 * @param rad - радиус
	 * @param radOrbit - радиус орбиты
	 * @param vel - скорость
	 * @param dir - направление
	 * @param color - цвет
	 */
	public Planet(String n, int id, double rad, double radOrbit, double vel, int dir, Color color)  {			
		this(n, id, rad, radOrbit, vel, dir, color, 0);
	}
	
	/**
	 * Конструктор создает Планету
	 * @param n - название
	 * @param id - номер по порядку
	 * @param rad - радиус
	 * @param radOrbit - радиус орбиты
	 * @param vel - скорость
	 * @param dir - направление
	 * @param m - кол-во лун
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
				/** Вычисляем скорость луны - чем ближе к планете - тем быстрее (миним. 2 - т.е. с каждой орбитой скорость удваивается на 2)
				 *   Алгоритм такой - скорость = (Кол-во лун на планете * на миним. скорость луны - i * миним. скорость луны,
				 *   	где i = 0, 1, 2... <= кол-во лун на планете   
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

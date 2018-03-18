import java.awt.Color;
import java.util.Random;

public class Moon {

		/** Задаем минимальную скорость. Скорость луны будет вычисляться. 
		 * Чем ближе радиус орбиты луны к планете, тем ближе скорость движения луны
		 */
		public static final double VELOCITY_MIN = 2;
		// Мин. растояние орбиты луны от планеты
		private final int OrbitRadiusDistance = 7;
	
		private int id; // номер по порядку		
		private final double radius = 2;				
		private double radiusOrbit = 7.0;
		private double velocity = VELOCITY_MIN;
		private final Color color = Color.YELLOW;
		
		private double angle = 0;
		private Random rand = new Random();
		
		/**
		 * Конструктор создает Луну		
		 * @param radOrbit - определяет радиус орбиты Луны
		 * @param id - номер по порядку
		 * @param vel - скорость движения луны
		 */
		public Moon(double radOrbit, int id, double vel) {			
			radiusOrbit = radOrbit;
			this.id = id;
			velocity = vel;			
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
			// вычисляем радиус исходя из того, 
			// что у нас могут быть несколько лун и у каждой своя орбита
			 return radiusOrbit + id * OrbitRadiusDistance;
		}
				
		public double getRadius() {
			return radius;
		}
		
		public double getVelocity() {
			return velocity;
		}		
}

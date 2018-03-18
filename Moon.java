import java.awt.Color;
import java.util.Random;

public class Moon {

		/** ������ ����������� ��������. �������� ���� ����� �����������. 
		 * ��� ����� ������ ������ ���� � �������, ��� ����� �������� �������� ����
		 */
		public static final double VELOCITY_MIN = 2;
		// ���. ��������� ������ ���� �� �������
		private final int OrbitRadiusDistance = 7;
	
		private int id; // ����� �� �������		
		private final double radius = 2;				
		private double radiusOrbit = 7.0;
		private double velocity = VELOCITY_MIN;
		private final Color color = Color.YELLOW;
		
		private double angle = 0;
		private Random rand = new Random();
		
		/**
		 * ����������� ������� ����		
		 * @param radOrbit - ���������� ������ ������ ����
		 * @param id - ����� �� �������
		 * @param vel - �������� �������� ����
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
			// ��������� ������ ������ �� ����, 
			// ��� � ��� ����� ���� ��������� ��� � � ������ ���� ������
			 return radiusOrbit + id * OrbitRadiusDistance;
		}
				
		public double getRadius() {
			return radius;
		}
		
		public double getVelocity() {
			return velocity;
		}		
}

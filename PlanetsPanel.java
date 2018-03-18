import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JPanel;

public class PlanetsPanel extends JPanel {
	
	private final int SUN_SIZE = 50; // ������ ������
		
	private int width; // ������ ������
    private int height; // ������ ������
    private int centerX; // ����� ������
    private int centerY; // ����� ������
    
    private Thread runner; 
    
    private Random rand = new Random();
    
    private List<Star> stars; // � ���� ������ �� ������ ������
    
    private Planet[] planets;  // �������  
    //private Color[] colors; 
    		
    
    /** ����������� ������� ������
     *  
     * @param w - ������ ������
     * @param h - ������ ������
     */
	public PlanetsPanel (int w, int h) { 
		width = w; // ������
		height = h;	// ������
		
		// ����� ������
	    centerX = w / 2 ;
	    centerY = h / 2;		
		
		initPlanets();
		
		// ������� ������ � ��������� � ���� ������
		stars = new ArrayList<>();
		addStars();
		
		// ������ ��������
		startAnimation();		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawPanel(g);
	}
	
	public void startAnimation() {
		runner = new Thread() {
			
			@Override
			public void run() {
				try {
					while(!Thread.interrupted()) {
						repaint();						
						Thread.sleep(30);
					}
				}
				catch(InterruptedException ex) {
					ex.printStackTrace();
				}
			}		
			
		};
		runner.start();		
	}
	
	
	private void drawPanel(Graphics g) {
		
		// ������������� ���� ���� ������ 
		this.setBackground(Color.BLACK);
		
		/* ������� �� ������ ������, ������� ��� �� ����� 		
		* ������� ����� while ���� 
		int starCount = stars.size();
		int j = 0;
		while (j < starCount) {
			
			if (!stars.get(j).isAlive()) {
				stars.remove(j);
				starCount--;
			}
			j++;
		}*/
		
		// ������� �� ������ ������, ������� ��� �� �����
		// ������� ����� for ����
		for (int i = 0; i < stars.size(); i++) {
			if (!stars.get(i).isAlive()) {
				stars.remove(i);				
			}
		}
		
		// ���� � ������ ��� < 50 ����� - ��������� �����
		if (stars.size() < 50) {
			addStars();
		} 
		
		// ���� ������ ����� - ������������ ��
		for (Star s : stars) {
			if (s.isAlive()) {
				g.setColor(s.getColor());
				g.fillRect(s.getPoint().x, s.getPoint().y, s.STAR_SIZE, s.STAR_SIZE);
			}
		}
		
		
		// ������ ������
		g.setColor(Color.YELLOW);
		g.fillOval(width/2 - 25, height / 2 - 25, SUN_SIZE, SUN_SIZE);
		
		// ������������ ������� 
		for(Planet p : planets) {
			g.setColor(p.getColor());
			// ��������� ����� ���������� x ��� �������
			int Vx = (int)((double) centerX  + Math.cos(p.getAngle() * Math.PI / 180) * p.getRadiusOrbit());
			Vx = (int)((double)Vx - p.getRadius());

			// ��������� ����� ���������� y ��� �������
			int Vy = (int)((double) centerY  - Math.sin(p.getAngle() * Math.PI / 180 * (double)(p.getDirection() == 0 ? 1 : -1 )) 
					* p.getRadiusOrbit());
			Vy = (int)((double)Vy - p.getRadius());
			
			// ������ �������
			g.fillOval(Vx, Vy, (int)p.getRadius() * 2, (int)p.getRadius() * 2);
			// ���������� ����� ����
			double angle = p.getAngle() + p.getVelocity();
			if (angle > 360) { angle = 0;}
			p.setAngle(angle);
			
			
			// ��������� ���� �� � ������� ����
			if (p.getMoons() != null && p.getMoons().size() > 0) {

				// ������������ ���� � �������
				for (Moon m : p.getMoons()) {
				
					// ���������� ������ ������� �� x � � - ������������ ����� ������ �� ��������� ���������� �������� ����
					double planetX = Vx + p.getRadius();
					double planetY = Vy + p.getRadius();
								
					// ------- ������ ���� -- ��� ������� ----------------
					// �.�. �� ������ ���������� �� �� ������, ���������� �������� ���������� ������� ������ �� �������� ������ 
					// �.� ������� �������� ������ ���� � �������� ������� �������� �������
					// ��� �������� ����� ��� ��������� ������ ���� - ��� ������� 
					// double delta = (m.getRadiusOrbit() * 2 - p.getRadius() * 2) / 2;
					
					// x ���������� ��� ��������� ���������� ������ - ��� ������� 
					//int orbitX = (int)(Vx - delta );
										
					// y ���������� ��� ��������� ���������� ������	- ��� �������
					//int orbitY = (int)(Vy - delta );
					
																			 	
					//g.setColor(Color.WHITE);
					//g.drawOval(orbitX, orbitY, (int)m.getRadiusOrbit() * 2, (int)m.getRadiusOrbit() * 2);
					
					// ------- ������ ���� -- ��� ������� END ----------------
					
					// ��������� ���������� �������� ���� �� �
					double moonX = planetX + Math.cos(m.getAngle() * Math.PI / 180) * m.getRadiusOrbit();
					moonX = moonX - m.getRadius();
					
					// ��������� ���������� �������� ���� �� y
					double moonY = planetY - Math.sin(m.getAngle() * Math.PI / 180 ) * m.getRadiusOrbit();
					moonY = moonY - m.getRadius();
								
					// ������������ ����
					g.setColor(Color.YELLOW);
					g.fillOval((int)moonX, (int)moonY, (int)m.getRadius() * 2, (int)m.getRadius() * 2);
					
					// ��������� ����� ���� ����
					double MoonAngle = m.getAngle() + m.getVelocity();
					if (MoonAngle > 360) { MoonAngle = 0;}
					m.setAngle(MoonAngle);
				
				}				
			}
		}		
	} 

	
	// ������� ������ � ���������
	private void initPlanets() {
		 	planets = new Planet[] {
		 			new Planet("Mercury", 2, 3.0, 50.0, 4.0, 0, Color.GRAY),
		 			new Planet("Venera", 1, 5.0, 75.0, 3.0, 0, Color.CYAN), 
		 			new Planet("Earth", 3, 6.0, 120.0, 2.0, 1, Color.BLUE, 1),
		 			//new Planet("Earth", 3, 16.0, 150.0, 2.0, 1, Color.BLUE, 1),
		 			new Planet("Mars", 4, 5, 170.0, 1.7, 0, Color.RED, 2),
		 			//new Planet("Mars", 4, 35, 135.0, 3.5, 0, Color.RED, 2),
		 			new Planet("Jupiter", 4, 15.0, 270.0, 1.5, 0, Color.ORANGE, 8), 
		 			new Planet("Saturn", 5, 12.0, 380.0, 1.3, 0, Color.MAGENTA), 		 			
		 	};
	}
	
	
	// ����� ���������� ��������� ������� ��������� ����
	private Color getRandomColor() {
        Random generator = new Random();
        int r = generator.nextInt(256);
        int g = generator.nextInt(256);
        int b = generator.nextInt(256);
        return new Color(r, g, b);        
    }

	// ����� ��������� ������ � ������ �����
	private void addStars() {
		
		/* � ������ ��������� stars.size ����� ����� �� �����, ����� ����� ���������� ����� ������ - 
		 * � ��������� �� ������, ����� �� ��������� < 50 ��� ����� ������, 
		 * ��� �� �������� ����� ������, � ��� ��� ������� � ������, � ������� � ��� 50, �������
		 * ���������� ���������� ����� ����� �� (50 - ���-�� ���������) �� 250  */ 
		int starsCount = 50 - stars.size() + rand.nextInt(250);
		
		for (int i = 0; i < starsCount; i++) {
			
			// ���� ������
			//int rCol = rand.nextInt(colors.length-1);
			Color starColor = getRandomColor();
			
			// ����� ��������
            int rS = 3 + rand.nextInt(5); 
            // ����������
            int rX = rand.nextInt(width); // ���������� �� X
            int rY = rand.nextInt(height); // ���������� �� Y
            
            // �������� ������ �� �������� ������������ ����������, ����� �������� � ����
            stars.add(new Star(rX, rY, rS, starColor));
            //stars.add(new Star(rX, rY, rS, colors[rCol]));           
            
		}		
	}
	
}

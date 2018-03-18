import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JPanel;

public class PlanetsPanel extends JPanel {
	
	private final int SUN_SIZE = 50; // радиус солнца
		
	private int width; // ширина панели
    private int height; // высота панели
    private int centerX; // центр экрана
    private int centerY; // центр экрана
    
    private Thread runner; 
    
    private Random rand = new Random();
    
    private List<Star> stars; // с этом списке мы храним звезды
    
    private Planet[] planets;  // планеты  
    //private Color[] colors; 
    		
    
    /** конструктор создает панель
     *  
     * @param w - ширина панели
     * @param h - высота панели
     */
	public PlanetsPanel (int w, int h) { 
		width = w; // ширина
		height = h;	// высота
		
		// центр экрана
	    centerX = w / 2 ;
	    centerY = h / 2;		
		
		initPlanets();
		
		// создаем список и добавляем в него звезды
		stars = new ArrayList<>();
		addStars();
		
		// начать анимацию
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
		
		// устанавливаем цвет фона черный 
		this.setBackground(Color.BLACK);
		
		/* Удаляем из списка звезды, которые уже не живые 		
		* Выриант через while цикл 
		int starCount = stars.size();
		int j = 0;
		while (j < starCount) {
			
			if (!stars.get(j).isAlive()) {
				stars.remove(j);
				starCount--;
			}
			j++;
		}*/
		
		// Удаляем из списка звезды, которые уже не живые
		// Вариант через for цикл
		for (int i = 0; i < stars.size(); i++) {
			if (!stars.get(i).isAlive()) {
				stars.remove(i);				
			}
		}
		
		// если в списке уже < 50 звезд - добавляем новые
		if (stars.size() < 50) {
			addStars();
		} 
		
		// Если звезда живая - отрисовываем ее
		for (Star s : stars) {
			if (s.isAlive()) {
				g.setColor(s.getColor());
				g.fillRect(s.getPoint().x, s.getPoint().y, s.STAR_SIZE, s.STAR_SIZE);
			}
		}
		
		
		// рисуем солнце
		g.setColor(Color.YELLOW);
		g.fillOval(width/2 - 25, height / 2 - 25, SUN_SIZE, SUN_SIZE);
		
		// отрисовываем планеты 
		for(Planet p : planets) {
			g.setColor(p.getColor());
			// вычисляем новую координату x для планеты
			int Vx = (int)((double) centerX  + Math.cos(p.getAngle() * Math.PI / 180) * p.getRadiusOrbit());
			Vx = (int)((double)Vx - p.getRadius());

			// вычисляем новую координату y для планеты
			int Vy = (int)((double) centerY  - Math.sin(p.getAngle() * Math.PI / 180 * (double)(p.getDirection() == 0 ? 1 : -1 )) 
					* p.getRadiusOrbit());
			Vy = (int)((double)Vy - p.getRadius());
			
			// рисуем планету
			g.fillOval(Vx, Vy, (int)p.getRadius() * 2, (int)p.getRadius() * 2);
			// определяем новый угол
			double angle = p.getAngle() + p.getVelocity();
			if (angle > 360) { angle = 0;}
			p.setAngle(angle);
			
			
			// проверяем есть ли у планеты луны
			if (p.getMoons() != null && p.getMoons().size() > 0) {

				// отрисовываем луны у планеты
				for (Moon m : p.getMoons()) {
				
					// координаты центра планеты по x и у - относительно этого центра мы вычисляем координаты движения луны
					double planetX = Vx + p.getRadius();
					double planetY = Vy + p.getRadius();
								
					// ------- ОРБИТА ЛУНЫ -- ДЛЯ ОТЛАДКИ ----------------
					// т.к. мы рисуем окружность не из центра, необходимо сместить координату радиуса орбиты на значение дельта 
					// т.е разница диаметра орбиты луны и диаметра планеты деленная пополам
					// это значение нужно для отрисовки орбиты луны - для отладки 
					// double delta = (m.getRadiusOrbit() * 2 - p.getRadius() * 2) / 2;
					
					// x координата для отрисовки окружности орбиты - для отладки 
					//int orbitX = (int)(Vx - delta );
										
					// y координата для отрисовки окружности орбиты	- для отладки
					//int orbitY = (int)(Vy - delta );
					
																			 	
					//g.setColor(Color.WHITE);
					//g.drawOval(orbitX, orbitY, (int)m.getRadiusOrbit() * 2, (int)m.getRadiusOrbit() * 2);
					
					// ------- ОРБИТА ЛУНЫ -- ДЛЯ ОТЛАДКИ END ----------------
					
					// вычисляем координаты движения луны по х
					double moonX = planetX + Math.cos(m.getAngle() * Math.PI / 180) * m.getRadiusOrbit();
					moonX = moonX - m.getRadius();
					
					// вычисляем координаты движения луны по y
					double moonY = planetY - Math.sin(m.getAngle() * Math.PI / 180 ) * m.getRadiusOrbit();
					moonY = moonY - m.getRadius();
								
					// отрисовываем луну
					g.setColor(Color.YELLOW);
					g.fillOval((int)moonX, (int)moonY, (int)m.getRadius() * 2, (int)m.getRadius() * 2);
					
					// вычисляем новый угол луны
					double MoonAngle = m.getAngle() + m.getVelocity();
					if (MoonAngle > 360) { MoonAngle = 0;}
					m.setAngle(MoonAngle);
				
				}				
			}
		}		
	} 

	
	// Создаем массив с планетами
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
	
	
	// метод возвращает случайным образом выбранный цвет
	private Color getRandomColor() {
        Random generator = new Random();
        int r = generator.nextInt(256);
        int g = generator.nextInt(256);
        int b = generator.nextInt(256);
        return new Color(r, g, b);        
    }

	// метод добавляет звезды в список звезд
	private void addStars() {
		
		/* в начале программы stars.size будет равно но затем, когда будут проверятся живые звезды - 
		 * и удаляться из списка, когда их останется < 50 нам нужно учесть, 
		 * что мы добавлям новые звезды, к тем что имеются в списке, и минимум у нас 50, поэтому
		 * генерируем количество новых звезд от (50 - кол-во имеющихся) до 250  */ 
		int starsCount = 50 - stars.size() + rand.nextInt(250);
		
		for (int i = 0; i < starsCount; i++) {
			
			// Цвет звезды
			//int rCol = rand.nextInt(colors.length-1);
			Color starColor = getRandomColor();
			
			// время свечения
            int rS = 3 + rand.nextInt(5); 
            // координаты
            int rX = rand.nextInt(width); // координата по X
            int rY = rand.nextInt(height); // координата по Y
            
            // создавая Звезду мы передаем конструктору координаты, время свечения и цвет
            stars.add(new Star(rX, rY, rS, starColor));
            //stars.add(new Star(rX, rY, rS, colors[rCol]));           
            
		}		
	}
	
}

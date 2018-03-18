import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PlanetsFrame extends JFrame {
	
	private int width;
	private int height;

	public PlanetsFrame() {
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height - 40;
		setSize(width, height);
		setTitle("Planets");
		
		PlanetsPanel planetsCanvas = new PlanetsPanel(width, height);
		Container c = getContentPane();
		c.add(planetsCanvas);
		
	}

}

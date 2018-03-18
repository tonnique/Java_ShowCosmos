import java.awt.EventQueue;

import javax.swing.JFrame;

public class LaunchApp {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				PlanetsFrame frame = new PlanetsFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);				
			}
		});

	}

}

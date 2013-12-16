package donut;

import javax.swing.JFrame;


public class Donut extends JFrame {


	public Donut() {
		
		add(new Board());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(360, 310);
		setLocationRelativeTo(null);
		setTitle("Donut");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Donut();
	}
}

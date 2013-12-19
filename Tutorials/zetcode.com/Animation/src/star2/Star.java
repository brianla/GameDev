package star2;

import javax.swing.JFrame;

public class Star extends JFrame {

	public Star() {
		
		add(new Board());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(280, 240);
		setLocationRelativeTo(null);
		setTitle("Star");
		setResizable(false);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Star();
	}
}

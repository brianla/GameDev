package rtype;

import javax.swing.JFrame;

public class RType extends JFrame {
	
	public RType() {
		
		add(new Board());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setTitle("R - Type");
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new RType();
	}
}

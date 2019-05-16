import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.Timer;


public class CrazyButton {
	final JButton btnPressMe;
	
	Timer timer;
	
	int randomWidth;
	int randomHeight;
	static int counter = 1;
	static int animationSpeed = 10;
	
	public CrazyButton(JDialog dialog, final Dimension screenDim) {
		
		//BUTTON SETUP//
		btnPressMe = new JButton("PRESS ME!!");
		btnPressMe.setBounds(651, 304, 158, 70);
		dialog.setLayout(null);
		dialog.add(btnPressMe);
		
		//Action for Swing Timer
		final Action paintTimer = new AbstractAction() { 
			
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				int X = (int) Math.pow(randomWidth, 2);
				int Y = (int) Math.pow(randomHeight, 2);
				
				int eulerLength = (int) Math.sqrt(X + Y);
				Point newPosition = checkNewPosition(eulerLength);
				btnPressMe.setLocation(newPosition);
				}
			};
		
		//Add Button Listener//
		btnPressMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Random rand = new Random();
				
				randomWidth = rand.nextInt(screenDim.width - btnPressMe.getSize().width);
				randomHeight = rand.nextInt(screenDim.height - btnPressMe.getSize().height);
				timer = new Timer(animationSpeed, paintTimer);
				timer.start();
				}
		});
	}
	private Point checkNewPosition(int eulerLength) {
		if( (btnPressMe.getBounds().x == randomWidth) && (btnPressMe.getBounds().y == randomHeight) )
			timer.stop();
		
		int newLocationY = randomHeight;
		int newLocationX = randomWidth;
		
		int normalizer = 50;
		
		if(btnPressMe.getBounds().x < randomWidth) {
			int diff = randomWidth - btnPressMe.getBounds().x;
			newLocationX = (int) Math.ceil(btnPressMe.getBounds().x + diff/normalizer);
			
		}
		else if(btnPressMe.getBounds().x > randomWidth) {
			int diff = btnPressMe.getBounds().x - randomWidth;
			newLocationX = (int) Math.ceil(btnPressMe.getBounds().x - diff/normalizer);
		}
		
		if(btnPressMe.getBounds().y < randomHeight) {
			int diff = randomHeight - btnPressMe.getBounds().y;
			newLocationY = (int) Math.ceil(btnPressMe.getBounds().y + diff/normalizer); 
		}
		else if(btnPressMe.getBounds().y > randomHeight) {
			int diff = btnPressMe.getBounds().y - randomHeight;
			newLocationY = (int) Math.ceil(btnPressMe.getBounds().y - diff/normalizer);
		}
		
		Point point = new Point(newLocationX, newLocationY);
		return point;
	}
}

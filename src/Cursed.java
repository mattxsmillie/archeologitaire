package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cursed extends JPanel implements MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	private JFrame myFrame;
	private int WIDTH;
	private int HEIGHT;
	private int xOffset = 0;
	private int yOffset = 0;
	private int x;
	private int y;
	
	private int radius = 50;//radius of mouse "lantern"
	
	private Point mouseLocation;
	
	
	public Cursed(JFrame frame) {
		super();
		myFrame = frame;
		WIDTH = frame.getWidth();
		HEIGHT = frame.getHeight();
		x = getX();
		y = getY();
		
		
		//methodology borrowed from Steve Harper's dolpin code
		int borderWidth = (WIDTH - frame.getContentPane().getWidth())/2;
		xOffset = borderWidth;
        yOffset = HEIGHT - frame.getContentPane().getHeight()-borderWidth; // assume side border = bottom border; ignore title bar
		
        mouseLocation = new Point(0, 0);

		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		if(mouseLocation.getX() > x && mouseLocation.getX() < WIDTH && mouseLocation.getY() > y && mouseLocation.getY() < HEIGHT) {
			g.setColor(Color.BLACK); //draw rectangles
			g.fillRect(x, y, WIDTH, (int) mouseLocation.getY() - radius);
			g.fillRect(x, (int) mouseLocation.getY() + radius, (int) WIDTH, HEIGHT - (int) mouseLocation.getY());
			g.fillRect(x, y, (int) mouseLocation.getX() - radius, HEIGHT);
			g.fillRect((int) mouseLocation.getX() + radius, y, WIDTH - (int) mouseLocation.getX(), HEIGHT);
		}else{
			g.fillRect(x, y, WIDTH, HEIGHT);
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		Point p = arg0.getLocationOnScreen();
		p.setLocation(p.getX() - myFrame.getX()- xOffset, p.getY() - myFrame.getY() - yOffset);
		//int px = (int) (p.getX()); int py = (int) (p.getY());
		mouseLocation.setLocation(p);
		repaint();
	}
	
	//main method for testing purposes
	public static void main(String[] args) {
		JFrame thisFrame = new JFrame();
		thisFrame.setTitle("Cursed");
		thisFrame.setSize(700, 400); //width, height
		thisFrame.setVisible(true);
		thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Cursed myPanel = new Cursed(thisFrame);
		thisFrame.add(myPanel);
	}
}

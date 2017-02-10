import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.*;
import javax.swing.*;

public class GamePiece extends JPanel {
	
	public GamePiece(){
		this.setPreferredSize(new Dimension(103,103));
		this.setMaximumSize(new Dimension(105,105));
		this.setMinimumSize(new Dimension(100,100));
		
	}
	
	public void paint(Graphics g){
		
		g.setColor(Color.RED);
		g.fillOval(2, 2, 100, 100);
	}
	
}

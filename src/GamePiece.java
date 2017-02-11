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
	
	private Integer pieceNumber;
	
	
	public GamePiece(int imageNumber){
		pieceNumber = imageNumber;
		this.setPreferredSize(new Dimension(103,103));
		this.setMaximumSize(new Dimension(105,105));
		this.setMinimumSize(new Dimension(100,100));
	}

	public void paint(Graphics g){

		if(pieceNumber!=null){
			if(pieceNumber == 1){
				g.setColor(Color.GREEN);
				g.fillRect(2, 2, 95, 95);			
			}else if(pieceNumber == 2){
				g.setColor(Color.BLUE);
				g.fillOval(2, 2, 100, 100);				
			}else{
				g.setColor(Color.RED);
				g.fillOval(2, 2, 100, 100);
			}
		}
	}
	
}

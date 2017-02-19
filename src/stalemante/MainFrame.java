package stalemante;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JTabbedPane;

import gridsystem.GridView;

import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;


public class MainFrame {
	
	JScrollPane scrollpane;
	JFrame frame;
	
	JTabbedPane rulesPane;
	JFrame rulesFrame;
	
	public MainFrame(GameSystem gameSystem, GridView grid){
		frame = new JFrame("Stalemate (Main Window)");
		
		restartGame(gameSystem, grid);
	}
	
	public void restartGame(GameSystem gameSystem, GridView grid){
		
		frame.getContentPane().removeAll();
		scrollpane = new JScrollPane(grid);
		JComponent toolbar = new Toolbar(gameSystem);
		JFrame frame = setupFrame(new int[] {800,800});
		frame.add(scrollpane,BorderLayout.CENTER);
		frame.add(toolbar,BorderLayout.NORTH);
		frame.revalidate();
		frame.repaint();
		
	}
	
	private JFrame setupFrame(int[] windowSize){
		frame.setVisible(true);
		frame.setSize(windowSize[0],windowSize[1]);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(4500,5500));
		frame.setMinimumSize(new Dimension(300,350));
		return frame;
	}
	
}

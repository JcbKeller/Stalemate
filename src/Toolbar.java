import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Toolbar extends JPanel{
	private JButton button1;
	private JButton button2;
	private JLabel label;
	
	public Toolbar(){
		super(new GridBagLayout());
		button1 = new JButton("TestButton1");
		button2 = new JButton("TestButton2");
		label = new JLabel("TestLabel");
		
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You clicked Button 1");
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You clicked Button 2");
			}
		});
		GridBagConstraints c = new GridBagConstraints();		
		c.gridx = 0;
		c.gridy = 1;
		this.add(button1,c);
		c.gridx = 1;
		c.gridy = 1;
		this.add(button2,c);
		this.add(label);
	    this.setBackground(Color.LIGHT_GRAY);
		
	}
}


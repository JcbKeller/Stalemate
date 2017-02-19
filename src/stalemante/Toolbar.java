package stalemante;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Toolbar extends JPanel{
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JLabel label;
	private RulesWindow rulesWindow;
	
	public Toolbar(GameSystem gameSystem){
		super(new GridBagLayout());
		button1 = new JButton("Show Rules");
		button2 = new JButton("Undo Move");
		button4 = new JButton("Start New Game");
		button3 = new JButton("Switch Teams");
		label = new JLabel("Options Menu");

		rulesWindow = new RulesWindow();
		
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Attempting to show rules");
				rulesWindow.rulesFrame.setVisible(true);

			}
		});
		
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Undoing Last Move");
				gameSystem.undoLastMove();
			}
		});
		
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Switching Team Turn");
				gameSystem.changeTurns();
			}
		});
		
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Starting a new game");
				Stalemate.resetGame();
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();		
		c.gridx = 0;
		c.gridy = 1;
		this.add(button1,c);
		c.gridx = 1;
		c.gridy = 1;
		this.add(button2,c);
		c.gridx = 2;
		c.gridy = 1;
		this.add(button3,c);
		c.gridx = 3;
		c.gridy = 1;
		this.add(button4,c);
		
		this.add(label);

		this.setBackground(Color.LIGHT_GRAY);

	}
}


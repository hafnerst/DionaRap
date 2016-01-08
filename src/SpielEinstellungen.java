import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.fhwgt.dionarap.model.data.MTConfiguration;


public class SpielEinstellungen extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	Hauptfenster fenster;
	private JTextField delayStart, delayShoot, delayStep, zeilen, spalten, obstacles, opponents;
	private JCheckBox randomWaitTime, opponentsAvoidObstacles, opponentsAvoidOpponents;
	private JButton ubernehmen, abbruch;
	
	public SpielEinstellungen(Hauptfenster f, MTConfiguration levelConv)
	{
		super(f, "Spieleinstellungen");
		fenster = f;
		
		this.setLayout(new GridLayout(11, 2));
		this.add(new JLabel("Wartezeit der Gegner zu Beginn:"));
		this.add(delayStart= new JTextField());
		this.add(new JLabel("Verzögerung eines Schusses:"));
		this.add(delayShoot= new JTextField());
		this.add(new JLabel("Wartezeit eines Gegners vor jedem Schritt:    "));
		this.add(delayStep= new JTextField());
		this.add(new JLabel(""));
		this.add(randomWaitTime= new JCheckBox("Zufällige Wartezeit der Gegner"));
		this.add(new JLabel(""));
		this.add(opponentsAvoidObstacles= new JCheckBox("Gegner meiden Kollision mit Hindernis"));
		this.add(new JLabel(""));
		this.add(opponentsAvoidOpponents= new JCheckBox("Gegner meiden Kollision mit anderen Gegnern"));
		this.add(new JLabel("Anzahl Zeilen des Spielfeldes:"));
		this.add(zeilen= new JTextField());
		this.add(new JLabel("Anzahl Spalten des Spielfeldes:"));
		this.add(spalten= new JTextField());
		this.add(new JLabel("Anzahl Hindernisse:"));
		this.add(obstacles= new JTextField());
		this.add(new JLabel("Anzahl der Gegner:"));
		this.add(opponents= new JTextField());
		this.add(ubernehmen = new JButton("Übernehmen"));
		this.add(abbruch = new JButton("Abbruch"));
		
		ubernehmen.setActionCommand("ubernehmen");
		abbruch.setActionCommand("abbruch");
		
		ubernehmen.addActionListener(this);
		abbruch.addActionListener(this);
		
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		
		
	}
}

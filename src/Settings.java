import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;

import de.fhwgt.dionarap.model.data.MTConfiguration;

public class Settings extends JFrame
{
	private static final long serialVersionUID = 1L;
	Hauptfenster fenster;
	private JTable table;
	
	private JTextField delayStart, delayShoot, delayStep, zeilen, spalten, obstacles, opponents;
	private JCheckBox randomWaitTime, opponentsAvoidObstacles, opponentsAvoidOpponents;
	private JButton ubernehmen, abbruch;
	private JLabel wartezeit_gegner;
	private JPanel gesamt;
	

	public Settings(Hauptfenster f, MTConfiguration levelConv) 
	{
		fenster = f;
		gesamt = new JPanel();
		table = new JTable();
		
		createTable();
		
		gesamt.add(table);
		gesamt.setPreferredSize(new Dimension(400,400));
		gesamt.setVisible(true);
		
		this.add(gesamt);
		this.setVisible(true);
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	public void createTable()
	{	
		table.setLayout(new GridLayout(11, 2));
		wartezeit_gegner = new JLabel("Wartezeit der Gegner zu Beginn:");
		wartezeit_gegner.setPreferredSize(new Dimension(50,50));
		table.add(wartezeit_gegner);
		table.add(delayStart= new JTextField());
		table.add(new JLabel("Verzögerung eines Schusses:"));
		table.add(delayShoot= new JTextField());
		table.add(new JLabel("Wartezeit eines Gegners vor jedem Schritt:    "));
		table.add(delayStep= new JTextField());
		table.add(new JLabel(""));
		table.add(randomWaitTime= new JCheckBox("Zufällige Wartezeit der Gegner"));
		table.add(new JLabel(""));
		table.add(opponentsAvoidObstacles= new JCheckBox("Gegner meiden Kollision mit Hindernis"));
		table.add(new JLabel(""));
		table.add(opponentsAvoidOpponents= new JCheckBox("Gegner meiden Kollision mit anderen Gegnern"));
		table.add(new JLabel("Anzahl Zeilen des Spielfeldes:"));
		table.add(zeilen= new JTextField());
		table.add(new JLabel("Anzahl Spalten des Spielfeldes:"));
		table.add(spalten= new JTextField());
		table.add(new JLabel("Anzahl Hindernisse:"));
		table.add(obstacles= new JTextField());
		table.add(new JLabel("Anzahl der Gegner:"));
		table.add(opponents= new JTextField());
		table.add(ubernehmen = new JButton("Übernehmen"));
		table.add(abbruch = new JButton("Abbruch"));
		
		
		table.setVisible(true);
	}
}

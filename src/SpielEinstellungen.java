import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import de.fhwgt.dionarap.model.data.MTConfiguration;
import de.fhwgt.dionarap.model.data.*;


public class SpielEinstellungen extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	Hauptfenster fenster;
	DionaRapModel model;
	Grid grid;
	int x_flaeche, y_flaeche, opponentAnz, obstacleAnz;
	Spielfeld feld;
	
	private JTextField obstacles, opponents, spalten, zeilen;
	private JCheckBox randomWaitTime, opponentsAvoidObstacles, opponentsAvoidOpponents;
	private JButton ubernehmen, abbruch;
	private JSlider delayStart, delayShoot, delayStep;
	
	public SpielEinstellungen(Hauptfenster f, MTConfiguration levelConf)
	{
		super(f, "Spieleinstellungen");
		fenster = f;
		model = fenster.getModel();
		feld = fenster.getSpielfeld();
		grid = model.getGrid();
		x_flaeche = grid.getGridSizeX();
		y_flaeche = grid.getGridSizeY();
		opponentAnz = model.getOpponentCount();
		obstacleAnz = feld.getObstacleCount();
		
		//Markierungen an JSlider
		Hashtable labelTable = new Hashtable();
		labelTable.put(new Integer(0), new JLabel("0"));
		labelTable.put(new Integer(2000),new JLabel("2000"));
		labelTable.put(new Integer(4000), new JLabel("4000"));
		labelTable.put(new Integer(6000),new JLabel("6000"));
		labelTable.put(new Integer(8000), new JLabel("8000"));
		labelTable.put(new Integer(10000),new JLabel("10000"));
		
		this.setLayout(new GridLayout(11, 2));
		this.add(new JLabel("Wartezeit der Gegner zu Beginn:"));
		this.add(delayStart= new JSlider(0,10000));
		delayStart.setMinorTickSpacing(500);
		delayStart.setMajorTickSpacing(2000);
		delayStart.setPaintTicks(true);
		delayStart.setPaintLabels(true);
		delayStart.setLabelTable(labelTable);
		delayStart.setValue(levelConf.getOpponentStartWaitTime());
		
		this.add(new JLabel("Verzögerung eines Schusses:"));
		this.add(delayShoot= new JSlider(0,10000));
		delayShoot.setMinorTickSpacing(500);
		delayShoot.setMajorTickSpacing(2000);
		delayShoot.setPaintTicks(true);
		delayShoot.setPaintLabels(true);
		delayShoot.setLabelTable(labelTable);
		delayShoot.setValue(levelConf.getShotWaitTime());
		
		this.add(new JLabel("Wartezeit eines Gegners vor jedem Schritt:    "));
		this.add(delayStep= new JSlider(0,10000));
		delayStep.setMinorTickSpacing(500);
		delayStep.setMajorTickSpacing(2000);
		delayStep.setPaintTicks(true);
		delayStep.setPaintLabels(true);
		delayStep.setLabelTable(labelTable);
		delayStep.setValue(levelConf.getOpponentStartWaitTime());
		
		this.add(new JLabel(""));
		this.add(randomWaitTime= new JCheckBox("Zufällige Wartezeit der Gegner"));
		randomWaitTime.setSelected(levelConf.isRandomOpponentWaitTime());
		
		this.add(new JLabel(""));
		this.add(opponentsAvoidObstacles= new JCheckBox("Gegner meiden Kollision mit Hindernis"));
		opponentsAvoidObstacles.setSelected(levelConf.isAvoidCollisionWithObstacles());
		
		this.add(new JLabel(""));
		this.add(opponentsAvoidOpponents= new JCheckBox("Gegner meiden Kollision mit anderen Gegnern"));
		opponentsAvoidOpponents.setSelected(levelConf.isAvoidCollisionWithOpponent());
		
		this.add(new JLabel("Anzahl Zeilen des Spielfeldes:"));
		this.add(zeilen= new JTextField(Integer.toString(x_flaeche)));
		
		this.add(new JLabel("Anzahl Spalten des Spielfeldes:"));
		this.add(spalten= new JTextField(Integer.toString(y_flaeche)));
		
		this.add(new JLabel("Anzahl Hindernisse:"));
		this.add(obstacles= new JTextField(Integer.toString(obstacleAnz)));
		
		this.add(new JLabel("Anzahl der Gegner:"));
		this.add(opponents= new JTextField(Integer.toString(opponentAnz)));
		
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

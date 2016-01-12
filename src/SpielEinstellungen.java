import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import de.fhwgt.dionarap.model.data.MTConfiguration;
import de.fhwgt.dionarap.model.data.*;

/**
 * 
 * Klasse SpielEinstellungen dient zur Darstellung und Änderung der in der Config hinterlegten Einstellungen.
 *
 */

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
	
/**
 * Erzeugt eine Instanz der Klasse SpielEinstellungen.
 * @param f das aktuelle Hauptfenster des Spiels
 * @param levelConf die ausgewählte Config
 */
	
	public SpielEinstellungen(Hauptfenster f, MTConfiguration levelConf)
	{
		super(f, "Spieleinstellungen");
		fenster = f;
		model = fenster.getModel();
		feld = fenster.getSpielfeld();
		
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
		
		this.add(new JLabel("Verzögerung eines Schusses:"));
		this.add(delayShoot= new JSlider(0,10000));
		delayShoot.setMinorTickSpacing(500);
		delayShoot.setMajorTickSpacing(2000);
		delayShoot.setPaintTicks(true);
		delayShoot.setPaintLabels(true);
		delayShoot.setLabelTable(labelTable);
		
		this.add(new JLabel("Wartezeit eines Gegners vor jedem Schritt:    "));
		this.add(delayStep= new JSlider(0,10000));
		delayStep.setMinorTickSpacing(500);
		delayStep.setMajorTickSpacing(2000);
		delayStep.setPaintTicks(true);
		delayStep.setPaintLabels(true);
		delayStep.setLabelTable(labelTable);
		
		this.add(new JLabel(""));
		this.add(randomWaitTime= new JCheckBox("Zufällige Wartezeit der Gegner"));
		
		
		this.add(new JLabel(""));
		this.add(opponentsAvoidObstacles= new JCheckBox("Gegner meiden Kollision mit Hindernis"));
		
		
		this.add(new JLabel(""));
		this.add(opponentsAvoidOpponents= new JCheckBox("Gegner meiden Kollision mit anderen Gegnern"));
		
		
		this.add(new JLabel("Anzahl Zeilen des Spielfeldes:"));
		this.add(zeilen= new JTextField(Integer.toString(x_flaeche)));
		
		this.add(new JLabel("Anzahl Spalten des Spielfeldes:"));
		this.add(spalten= new JTextField(Integer.toString(y_flaeche)));
		
		this.add(new JLabel("Anzahl Hindernisse:"));
		this.add(obstacles= new JTextField(Integer.toString(obstacleAnz)));
		
		this.add(new JLabel("Anzahl der Gegner:"));
		this.add(opponents= new JTextField(Integer.toString(opponentAnz)));
		
		readConfig(levelConf);
		
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
	
/**
 * Liest die aktuelle angegebene MTConfiguration aus.
 * @param levelConf die ausgewählte MTConfiguration
 */
	
	private void readConfig(MTConfiguration levelConf) 
	{
		grid = model.getGrid();
		x_flaeche = grid.getGridSizeX();
		y_flaeche = grid.getGridSizeY();
		opponentAnz = model.getOpponentCount();
		obstacleAnz = feld.getObstacleCount();
		
		delayStart.setValue(levelConf.getOpponentStartWaitTime());
		delayShoot.setValue(levelConf.getShotWaitTime());
		delayStep.setValue(levelConf.getOpponentStartWaitTime());
		randomWaitTime.setSelected(levelConf.isRandomOpponentWaitTime());
		opponentsAvoidObstacles.setSelected(levelConf.isAvoidCollisionWithObstacles());
		opponentsAvoidOpponents.setSelected(levelConf.isAvoidCollisionWithOpponent());
		zeilen.setText(Integer.toString(x_flaeche));
		spalten.setText(Integer.toString(y_flaeche));
		obstacles.setText(Integer.toString(obstacleAnz));
		opponents.setText(Integer.toString(opponentAnz));
	}
	
/**
 * Beschreibt die neue MTConfiguration.
 */
	
	private void writeConfig()
	{
		MTConfigurationComp newConfig = new MTConfigurationComp();
		
		newConfig.setOpponentStartWaitTime(delayStart.getValue());
		newConfig.setShotWaitTime(delayShoot.getValue());
		newConfig.setOpponentWaitTime(delayStep.getValue());
		newConfig.setRandomOpponentWaitTime(randomWaitTime.isSelected());
		newConfig.setAvoidCollisionWithObstacles(opponentsAvoidObstacles.isSelected());
		newConfig.setAvoidCollisionWithOpponent(opponentsAvoidOpponents.isSelected());
		newConfig.setZeilen(Integer.parseInt(zeilen.getText()));
		newConfig.setSpalten(Integer.parseInt(spalten.getText()));
		newConfig.setObstacleCount(Integer.parseInt(obstacles.getText()));
		newConfig.setOpponentCount(Integer.parseInt(opponents.getText()));
		
		fenster.startNewGame();
		new Hauptfenster(newConfig);
	}
	
/**
 * Dient zur Auswertung welcher Button gewählt wurde und was daraus resultiert.
 */
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("ubernehmen")) 
		{
			writeConfig();
			this.dispose();
		}
		else if (e.getActionCommand().equals("abbruch")) 
		{
			this.dispose();
		}
	}
}

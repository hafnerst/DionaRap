import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

/**
 * 
 * Klasse MenuBar dient zur Erzeugung einer Menübar mit ihren Komponenten.
 *
 */

public class MenuBar extends JMenuBar implements ActionListener, ItemListener
{
	private static final long serialVersionUID = 1L;
	Hauptfenster hf;
	JMenu menuHilfe, menuAnsicht, menuPosToolbar, menuLookFeel, menuKonfig, menuAuswahlLevel;
	JMenuItem anzToolbar, anzNavigator, spielEinstellungen;
	JRadioButtonMenuItem toolbarNord, toolbarSued, levelEinf, levelMit, levelSchw, levelToed;
	Vector<JRadioButtonMenuItem> lookAndFeelMenuItem;
	LookAndFeelInfo[] lfList;
	int indexLetzterLaF;
	
/**
 * Erzeugt eine Instanz der Klasse MenuBar.
 * @param fenster das Hauptfenster des Spiels
 */
	
	MenuBar(Hauptfenster fenster)
	{
		hf = fenster;
		indexLetzterLaF = 0;
		
		//Hinzufügen von Menüs
		menuHilfe = new JMenu("Hilfe");
		menuAnsicht = new JMenu("Ansicht");
		menuPosToolbar = new JMenu("Position Toolbar");
		menuLookFeel = new JMenu("Look and Feel");
		menuKonfig = new JMenu("Konfigurierung");
		menuAuswahlLevel = new JMenu("Levelauswahl");
		this.add(menuHilfe);
		this.add(menuAnsicht);
		this.add(menuKonfig);
		
		// Hilfe -> Spielbeschreibung
		JMenuItem beschreibung = new JMenuItem("Spielbeschreibung");
		menuHilfe.add(beschreibung);
		
		// Ansicht -> Toolbar anzeigen		
		anzToolbar = new JCheckBoxMenuItem("Toolbar anzeigen");
		anzToolbar.setSelected(true);
		menuAnsicht.add(anzToolbar);

		// Ansicht -> Position Toolbar	
		menuAnsicht.add(menuPosToolbar);
		menuPosToolbar.add(toolbarNord = new JRadioButtonMenuItem("Oben"));
		menuPosToolbar.add(toolbarSued = new JRadioButtonMenuItem("Unten"));
		toolbarNord.setSelected(true);
		
		// Ansicht -> Navigator anzeigen
		anzNavigator = new JCheckBoxMenuItem("Navigator anzeigen");
		anzNavigator.setSelected(true);
		menuAnsicht.add(anzNavigator);
		
		//Ansicht -> LookAndFeel
		menuAnsicht.add(menuLookFeel);
		lfList = UIManager.getInstalledLookAndFeels();
		lookAndFeelMenuItem = new Vector<JRadioButtonMenuItem>();
		
		//Hinzufügen der installierten LookAndFeels zum Untermenü
		for(int i = 0; i < lfList.length; i++) {
			lookAndFeelMenuItem.add(new JRadioButtonMenuItem(lfList[i].getName()));
			lookAndFeelMenuItem.get(i).setActionCommand(lfList[i].getName());
			lookAndFeelMenuItem.get(i).addActionListener(this);
			menuLookFeel.add(lookAndFeelMenuItem.get(i));
		}
		lookAndFeelMenuItem.get(0).setSelected(true);
		
		// Konfigurierung -> Level einlesen
		menuAuswahlLevel.add(levelEinf = new JRadioButtonMenuItem("Einfach"));
		menuAuswahlLevel.add(levelMit = new JRadioButtonMenuItem("Mittel"));
		menuAuswahlLevel.add(levelSchw = new JRadioButtonMenuItem("Schwer"));
		menuAuswahlLevel.add(levelToed = new JRadioButtonMenuItem("Toedlich"));
		menuAuswahlLevel.setEnabled(false);
		levelEinf.setSelected(true);
		
		menuKonfig.add(menuAuswahlLevel);
		
		// Konfigurierung -> Spieleinstellungen
		spielEinstellungen = new JMenuItem("Spieleinstellungen");
		menuKonfig.add(spielEinstellungen);
		
		
		//Action Commands
		beschreibung.setActionCommand("Spielbeschreibung");
		spielEinstellungen.setActionCommand("Einstellungen");
		
		//Hinzufügen zum Listener
		beschreibung.addActionListener(this);
		anzToolbar.addItemListener(this);
		toolbarNord.addItemListener(this);
		toolbarSued.addItemListener(this);
		anzNavigator.addItemListener(this);
		spielEinstellungen.addActionListener(this);
		levelEinf.addItemListener(this);
		levelMit.addItemListener(this);
		levelSchw.addItemListener(this);
		levelToed.addItemListener(this);
	}
	
/**
 * Dient zur Auswertung, welche Komponente in MenuBar gedrückt wurde und was daraus resultieren soll.
 */
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Spielbeschreibung"))
		{
			new BeschreibungDialog(hf);
		}
		else if(e.getActionCommand().equals("Einstellungen"))
		{
			new SpielEinstellungen(hf, hf.getLevel());
		}
		else {
			//Look and Feels abfragen
			for (int i = 0; i < lfList.length; i++) 
			{
				if( e.getActionCommand().equals(lfList[i].getName()) ) 
				{
					try 
					{
						//System.out.println(lfInfo[i].getClassName());
						UIManager.setLookAndFeel(lfList[i].getClassName());
						//Nach festlegen des LookAndFeel müssen die Bäume frisch aufgebaut werden
						SwingUtilities.updateComponentTreeUI(hf);
						SwingUtilities.updateComponentTreeUI(hf.getNavigator());
						lookAndFeelMenuItem.get(indexLetzterLaF).setSelected(false);
						indexLetzterLaF = i;
					}
					catch (Exception ex) 
					{
						JOptionPane.showMessageDialog(this, "Fehler beim laden des Look and Feel", "MessageBox", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
	
/**
 * Dient zur Auswertung, ob und welches Item in der MenuBar sich verändert hat und was daraus resultieren soll.
 */

	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		if(source == anzToolbar)
		{
			if(e.getStateChange() == ItemEvent.DESELECTED)
			{
				menuPosToolbar.setEnabled(false);
				hf.anzToolbar(false);
				
			}
			else if(e.getStateChange() == ItemEvent.SELECTED)
			{
				menuPosToolbar.setEnabled(true);
				hf.anzToolbar(true);
			}
		}
		else if(source == toolbarNord)
		{
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				toolbarSued.setSelected(false);
				hf.posToolbar(true);
			}
		}
		else if(source == toolbarSued)
		{
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				toolbarNord.setSelected(false);
				hf.posToolbar(false);
			}
		}
		else if(source == anzNavigator)
		{
			if(e.getStateChange() == ItemEvent.DESELECTED)
			{
				hf.anzNavigator(false);
			}
			else if(e.getStateChange() == ItemEvent.SELECTED)
			{
				hf.anzNavigator(true);
			}
		}
		else if(source == levelEinf)
		{
			hf.startNewGame();
			new Hauptfenster("level_einfach.xml");
		}
		
		else if(source == levelMit)
		{
			hf.startNewGame();
			new Hauptfenster("level_mittel.xml");
			
		}
		
		else if(source == levelSchw)
		{
			hf.startNewGame();
			new Hauptfenster("level_schwer.xml");
			
		}
		
		else if(source == levelToed)
		{
			hf.startNewGame();
			new Hauptfenster("level_toedlich.xml");
		}
	}	
}

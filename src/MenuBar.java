import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.File;
import java.net.URL;

import javax.swing.*; 
import de.fhwgt.dionarap.model.data.*;
import de.fhwgt.dionarap.controller.*;

public class MenuBar extends JMenuBar implements ActionListener, ItemListener
{
	private static final long serialVersionUID = 1L;
	Hauptfenster hf;
	JMenu menuPosToolbar;
	JMenuItem anzToolbar;
	JRadioButtonMenuItem toolbarNord, toolbarSued;
	
	MenuBar(Hauptfenster fenster)
	{
		hf = fenster;
		
		//Hinzufügen von Menüs
		JMenu menuHilfe = new JMenu("Hilfe");
		JMenu menuAnsicht = new JMenu("Ansicht");
		menuPosToolbar = new JMenu("Position Toolbar");
		JMenu menuKonfig = new JMenu("Konfigurierung");
		this.add(menuHilfe);
		this.add(menuAnsicht);
		this.add(menuKonfig);
		
		JMenuItem beschreibung = new JMenuItem("Spielbeschreibung");
		menuHilfe.add(beschreibung);
		
		anzToolbar = new JCheckBoxMenuItem("Toolbar anzeigen");
		anzToolbar.setSelected(true);
		menuAnsicht.add(anzToolbar);
		
		menuAnsicht.add(menuPosToolbar);
		menuPosToolbar.add(toolbarNord = new JRadioButtonMenuItem("Oben"));
		menuPosToolbar.add(toolbarSued = new JRadioButtonMenuItem("Unten"));
		toolbarNord.setSelected(true);
		
		
		//Action Commands
		beschreibung.setActionCommand("Spielebeschreibung");
		
		//Hinzufügen zum Listener
		beschreibung.addActionListener(this);
		anzToolbar.addItemListener(this);
		toolbarNord.addItemListener(this);
		toolbarSued.addItemListener(this);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		new BeschreibungDialog(hf);
	}

	@Override
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
	}
	
	

}

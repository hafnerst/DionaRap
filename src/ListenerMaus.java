import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.Player;

/**
 * Programm:	  DionaRap
 * Ueberschrift:  Erstellung des Mauslisteners
 * Beschreibung:  Zeigt, wie die Steuerung des Spielers mit der Maus erfolgt
 * Organisation:  Hochschule Ravensburg-Weingarten
 * @author Daniel Landler-Gaertner und Steffen Hafner
 * @version 1.0
 */

public class ListenerMaus extends MouseAdapter implements ActionListener
{
	Hauptfenster hf;
	DionaRapModel model;
	DionaRapController controller;
	Player spieler;
	int x_klick, y_klick;
	JMenuItem letztesThema;
	JPopupMenu popup;
	JMenu menu;
	JMenuItem alien, dracula, helsing, luke, spacewars, squarehead, vader;
	
	
	ListenerMaus(Hauptfenster fenster)
	{
		hf = fenster;
		model = hf.getModel();
		controller = hf.getSteuerung();
		
		popup = new JPopupMenu();
		
		menu = new JMenu("Thema");
		
		alien = new JRadioButtonMenuItem("alien");
		alien.addActionListener(this);
		menu.add(alien);
		
		dracula = new JRadioButtonMenuItem("dracula");
		dracula.addActionListener(this);
		menu.add(dracula);
		
		helsing = new JRadioButtonMenuItem("helsing");
		helsing.addActionListener(this);
		menu.add(helsing);
		
		luke = new JRadioButtonMenuItem("luke");
		luke.addActionListener(this);
		menu.add(luke);
		
		spacewars = new JRadioButtonMenuItem("spacewars");
		spacewars.addActionListener(this);
		menu.add(spacewars);
		
		squarehead = new JRadioButtonMenuItem("squarehead");
		squarehead.setSelected(true);
		squarehead.addActionListener(this);
		menu.add(squarehead);
		letztesThema = squarehead;
		
		vader = new JRadioButtonMenuItem("vader");
		vader.addActionListener(this);
		menu.add(vader);
		
		popup.add(menu);
	}
	
	public void mousePressed(MouseEvent e) 
	{
		if(e.getButton()==MouseEvent.BUTTON1)
		{
			int ret_val = 0;
			spieler = model.getPlayer();
			x_klick = (e.getX()/50);
			y_klick = (e.getY()/50);
			ret_val = klick_richtung();
		
		if(ret_val != 0)
		{
			if(ret_val != 5)
			{
				controller.movePlayer(ret_val);
			}
			else
			{
				if(controller.shoot() == false)
				{
					Toolbar tool = hf.getToolbar();
					tool.startBlinkThread();
				}
			}
		}
		}
		else if(e.getButton()==MouseEvent.BUTTON3)
		{
			popup.show(e.getComponent(),e.getX(), e.getY());
		}
	}
	
	private int klick_richtung()
	{
		int x = spieler.getX();
		int y = spieler.getY();
		
		if(x == x_klick && y == y_klick)
		{
			return 5;
		}
		else if(x == x_klick && y-1 == y_klick)
		{
			return 8;
		}
		else if(x+1 == x_klick && y-1 == y_klick)
		{
			return 9;
		}
		else if(x+1 == x_klick && y == y_klick)
		{
			return 6;
		}
		else if(x+1 == x_klick && y+1 == y_klick)
		{
			return 3;
		}
		else if(x == x_klick && y+1 == y_klick)
		{
			return 2;
		}
		else if(x-1 == x_klick && y+1 == y_klick)
		{
			return 1;
		}
		else if(x-1 == x_klick && y == y_klick)
		{
			return 4;
		}
		else if(x-1 == x_klick && y-1 == y_klick)
		{
			return 7;
		}
	return 0;
	}

	public void actionPerformed(ActionEvent e) 
	{
		letztesThema.setSelected(false);
		Spielfeld feld = hf.getSpielfeld();
		String thema = e.getActionCommand();
		letztesThema = (JMenuItem) e.getSource();
		letztesThema.setSelected(true);
		hf.setThema(thema);
		feld.setPawns();
	}
	
}
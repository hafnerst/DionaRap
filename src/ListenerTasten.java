import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import de.fhwgt.dionarap.controller.DionaRapController;

/**
 * Programm:	  DionaRap
 * Ueberschrift:  Erstellung des Tastenlisteners
 * Beschreibung:  Zeigt, wie die Steuerung mit dem Nummern-Tasten funktioniert
 * Organisation:  Hochschule Ravensburg-Weingarten
 * @author Daniel Landler-Gaertner und Steffen Hafner
 * @version 1.0
 */

public class ListenerTasten implements KeyListener 
{

	public void keyTyped(KeyEvent event) {
		
		Hauptfenster hf = (Hauptfenster)event.getSource();
		char eingabe = event.getKeyChar();
		DionaRapController controller = hf.getSteuerung();
		
		if(eingabe>='1'&&eingabe<='9')
		{
			int dir = Integer.parseInt(""+eingabe);
			if(dir==5)
			{
				if(controller.shoot() == false)
				{
					Toolbar tool = hf.getToolbar();
					tool.startBlinkThread();
				}
			}
			else
			{
				controller.movePlayer(dir);
			}
		}
	}

	public void keyPressed(KeyEvent event) 
	{	
	}

	public void keyReleased(KeyEvent event) 
	{	
	}

}

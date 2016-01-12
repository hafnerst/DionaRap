import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import de.fhwgt.dionarap.controller.DionaRapController;

/**
 * 
 * Klasse ListenerTasten dient zum implementieren eines KeyListener.
 *
 */

public class ListenerTasten implements KeyListener 
{

/**
 * Dient zur Auswertung, ob und welche Taste gedrückt wurde.
 */
	
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
	
/**
 * Dient zur Auswertung, ob und welche Taste gedrückt gehalten wird.
 */

	public void keyPressed(KeyEvent event) 
	{	
	}
	
/**
 * Dient zur Auswertung, ob und welche Taste losgelassen wird.
 */

	public void keyReleased(KeyEvent event) 
	{	
	}

}

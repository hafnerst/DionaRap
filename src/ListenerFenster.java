import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * 
 * Klasse ListenerFenster dient zur Implementierung eines ComponentListener
 *
 */

public class ListenerFenster implements ComponentListener
{

/**
 * Dient zur Auswertung, ob die ausgewählte Komponente versteckt ist.
 * 
 */
	
	public void componentHidden(ComponentEvent event) 
	{

	}
	
/**
 * Dient zur Auswertung, ob die ausgewählte Komponente bewegt wurde.
 * 
 */

	public void componentMoved(ComponentEvent event) 
	{
		Hauptfenster hf = (Hauptfenster)event.getSource();
		Navigator nav = hf.getNavigator();
		nav.setLocation(hf.getX()+hf.getWidth()+20, hf.getY());
	}
	
/**
 * Dient zur Auswertung, ob die ausgewählte Komponente verkleinert/vergrößert wurde.
 */
	
	public void componentResized(ComponentEvent event) 
	{
		
	}
	
/**
 * Dient zur Auswertung, ob die ausgewählte Komponente gezeigt wurde.
 */

	public void componentShown(ComponentEvent event) 
	{
		
	}

}

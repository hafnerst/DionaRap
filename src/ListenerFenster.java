import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Programm:	  DionaRap
 * Ueberschrift:  Erstellung des Fensterlisteners
 * Beschreibung:  Zeigt, wie das Kindfenster dem Hauptfenster folgt
 * Organisation:  Hochschule Ravensburg-Weingarten
 * @author Daniel Landler-Gaertner und Steffen Hafner
 * @version 1.0
 */

public class ListenerFenster implements ComponentListener
{

	public void componentHidden(ComponentEvent event) 
	{

	}

	public void componentMoved(ComponentEvent event) 
	{
		Hauptfenster hf = (Hauptfenster)event.getSource();
		Navigator nav = hf.getNavigator();
		nav.setLocation(hf.getX()+hf.getWidth()+20, hf.getY());
	}
	
	public void componentResized(ComponentEvent event) 
	{
		
	}

	public void componentShown(ComponentEvent event) 
	{
		
	}

}

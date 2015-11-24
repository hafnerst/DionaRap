import java.awt.Polygon;

import javax.swing.*; 

/**
 * Die Klasse Navigator erstellt ein Kindfenster vom Hauptfenster und setzt deren Eigenschaften
 * @author Daniel Landler-G�rtner und Steffen Hafner
 *
 */
public class Navigator extends JWindow
{
	// Diese Variable wird ben�tigt, da JWindow das Interface Serializable implementiert
	private static final long serialVersionUID = 1L;
	Polygon polygon = new Polygon();
	int b_size = 75;
	
	public Navigator(JFrame hauptfenster)
	{
		//Ruft Konstruktor von JWindow auf
		super(hauptfenster);
		setVisible(true);
				
		//Hinzuf�gen einer Tastatur und �bergeben des aktuell verwendeten Objekts
		add(new Tastatur());
		
		//Kindfenster relativ zum Hauptfenster positionieren
		setLocation(hauptfenster.getX()+hauptfenster.getWidth()+20, hauptfenster.getY());
		
		pack();
		
		createPolygon(b_size);
		setShape(polygon);
	}
	private void createPolygon(int b_size)
	{
		polygon.addPoint(0,b_size);
		polygon.addPoint(0,(b_size)*2);
		polygon.addPoint(b_size,(b_size)*3);
		polygon.addPoint((b_size)*2,(b_size)*3);
		polygon.addPoint((b_size)*3,(b_size)*2);
		polygon.addPoint((b_size)*3,b_size);
		polygon.addPoint((b_size)*2,0);
		polygon.addPoint(b_size,0);
	}
}

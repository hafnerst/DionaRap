import javax.swing.*; 

/**
 * Die Klasse Navigator erstellt ein Kindfenster vom Hauptfenster und setzt deren Eigenschaften
 * @author Daniel Landler-Gärtner und Steffen Hafner
 *
 */
public class Navigator extends JWindow
{
	// Diese Variable wird benötigt, da JWindow das Interface Serializable implementiert
	private static final long serialVersionUID = 1L;
	
	public Navigator(JFrame hauptfenster)
	{
		//Ruft Konstruktor von JWindow auf
		super(hauptfenster);
		setVisible(true);
				
		//Hinzufügen einer Tastatur und übergeben des aktuell verwendeten Objekts
		add(new Tastatur());

		//Kindfenster relativ zum Hauptfenster positionieren
		setLocation(hauptfenster.getX()+hauptfenster.getWidth()+20, hauptfenster.getY());
		pack();
	}
}

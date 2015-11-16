import java.awt.*;
import javax.swing.*; 
import de.fhwgt.dionarap.model.data.*;
import de.fhwgt.dionarap.controller.*;

/**
 * Programm:	  DionaRap
 * �berschrift:   Erstellung des Hauptfensters
 * Beschreibung:  Zeigt das Erstellen eines Hauptfensters und enth�lt die main Methode 
 * Organisation:  Hochschule Ravensburg-Weingarten
 * @author Daniel Landler-G�rtner und Steffen Hafner
 * @version 1.0
 */

public class Hauptfenster extends JFrame
{
	// Diese Variable wird ben�tigt, da JFrame das Interface Serializable implementiert
	private static final long serialVersionUID = 1L;
	DionaRapModel spiel = new DionaRapModel();
	DionaRapController steuerung = new DionaRapController(spiel);
	Spielfeld feld = new Spielfeld(spiel);
	Navigator nav;
	
	public Hauptfenster()
	{
		//Operation fuer Close-Button
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Fenstereigenschaften setzen
		setTitle("DionaRap");
		setVisible(true);
		
		//Spielfeld im Zentrum des Hauptfensters anlegen
		add(feld, BorderLayout.CENTER);
		//Objekte automatisch anordnen
		pack();
		setResizable(false);
		//Erst packen und dann in die Mitte setzen, davor falsche Gr��e 
		setLocationRelativeTo(null);
		nav = new Navigator(this);
		
		addComponentListener(new ListenerFenster());
		spiel.addModelChangedEventListener(new ListenerModel(feld));
		addKeyListener(new ListenerTasten());
	}
	
	public DionaRapController getSteuerung()
	{
		return steuerung;
	}
	
	public Navigator getNavigator()
	{
		return nav;
	}
	
	public static void main(String[] args) 
	{
		new Hauptfenster();
	}
}
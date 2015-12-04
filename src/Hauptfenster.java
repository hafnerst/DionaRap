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
	JPanel flaeche = new JPanel();
	DionaRapModel spiel = new DionaRapModel();
	DionaRapController steuerung = new DionaRapController(spiel);
	Spielfeld feld = new Spielfeld(spiel);
	int anz_gegner = spiel.getOpponentCount();
	Toolbar toolbar;
	Navigator nav;
	JMenuBar menue;
	
	public Hauptfenster()
	{
		//Operation fuer Close-Button
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Fenstereigenschaften setzen
		setTitle("DionaRap");
		
		//Spielfeld im Zentrum des Hauptfensters anlegen
		flaeche.add(feld, BorderLayout.CENTER);	
		add(flaeche);
		
		toolbar = new Toolbar();
		add(toolbar, BorderLayout.NORTH);
		
		menue = new MenuBar(this);
		setJMenuBar(menue);
		
		//Objekte automatisch anordnen
		pack();

		setResizable(false);
		
		//Erst packen und dann in die Mitte setzen, davor falsche Groesse 
		setLocationRelativeTo(null);
		setVisible(true);
		
		nav = new Navigator(this);
		
		addComponentListener(new ListenerFenster());
		spiel.addModelChangedEventListener(new ListenerModel(feld));
		addKeyListener(new ListenerTasten());
		requestFocus();
	}
	
	public DionaRapController getSteuerung()
	{
		return steuerung;
	}
	
	public Navigator getNavigator()
	{
		return nav;
	}
	
	public Toolbar getToolbar()
	{
		return toolbar;
	}
	
	public void anzToolbar(boolean sichtbar)
	{
		toolbar.setVisible(sichtbar);
		pack();
	}
	
	public void posToolbar(boolean richtung)
	{
		if(richtung == true)
		{
			add(toolbar, BorderLayout.NORTH);
		}
		else
		{
			add(toolbar, BorderLayout.SOUTH);
		}
		pack();
	}
		
	public static void main(String[] args) 
	{
		new Hauptfenster();
	}
}
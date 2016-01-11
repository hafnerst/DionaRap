import java.awt.*;
import javax.swing.*; 
import de.fhwgt.dionarap.model.data.*;
import de.fhwgt.dionarap.model.objects.Ammo;
import de.fhwgt.dionarap.controller.*;
import de.fhwgt.dionarap.levelreader.LevelReader;

/**
 * Programm:	  DionaRap
 * Ueberschrift:   Erstellung des Hauptfensters
 * Beschreibung:  Zeigt das Erstellen eines Hauptfensters und enthaelt die main Methode 
 * Organisation:  Hochschule Ravensburg-Weingarten
 * @author Daniel Landler-Gaertner und Steffen Hafner
 * @version 1.0
 */

public class Hauptfenster extends JFrame
{
	// Diese Variable wird ben�tigt, da JFrame das Interface Serializable implementiert
	private static final long serialVersionUID = 1L;
	String pfad = System.getProperty("user.dir")+"/levels/";
	String thema = "squarehead";
	JPanel flaeche;
	DionaRapModel spiel;
	DionaRapController steuerung;
	Spielfeld feld;
	int anz_gegner;
	Toolbar toolbar;
	Navigator nav;
	MenuBar menue;
	Settings einstellungen;
	ListenerModel listenerModel;
	ListenerFenster listenerFenster;
	ListenerTasten listenerTasten;
	ListenerMaus listenerMaus;
	int control = 0;
	MTConfigurationComp tmpComp;
	
	MTConfigurationComp levelConf;
	LevelReader levelReader;
	LevelReader.Level aktLevel;
	
	public Hauptfenster()
	{
		//Level Reader starten
		levelReader = new LevelReader();
		
		//Startlevel einlesen
		aktLevel = levelReader.readLevel(pfad + "level_einfach.xml");
		
		flaeche = new JPanel();
		spiel = aktLevel.getModel();
		steuerung = new DionaRapController(spiel);
		feld = new Spielfeld(spiel, this);
		anz_gegner = spiel.getOpponentCount();
		
		//Controller Konfigurationsdatei übergeben
		steuerung.setMultiThreaded(aktLevel.getConfig());
		
		erstelleHauptfenster();
	}
	
	public Hauptfenster(String level)
	{
		//Level Reader starten
		levelReader = new LevelReader();
		
		//Startlevel einlesen
		aktLevel = levelReader.readLevel(pfad + level);
		
		flaeche = new JPanel();
		spiel = aktLevel.getModel();
		steuerung = new DionaRapController(spiel);
		feld = new Spielfeld(spiel, this);
		anz_gegner = spiel.getOpponentCount();
		
		//Controller Konfigurationsdatei übergeben
		steuerung.setMultiThreaded(aktLevel.getConfig());
		
		erstelleHauptfenster();
	}
	
	public Hauptfenster(MTConfigurationComp newLevel)
	{		
		flaeche = new JPanel();
		spiel = new DionaRapModel(newLevel.getZeilen(), newLevel.getSpalten(), newLevel.getOpponentCount(), newLevel.getObstacleCount());
		tmpComp = newLevel;
		steuerung = new DionaRapController(spiel);
		feld = new Spielfeld(spiel, this);
		anz_gegner = spiel.getOpponentCount();
		control = 1;
		spiel.setShootAmount(3);
		//Controller Konfigurationsdatei übergeben
		steuerung.setMultiThreaded(newLevel);
		erstelleHauptfenster();
	}
	
	void erstelleHauptfenster() 
	{
		
		//Operation fuer Close-Button
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Fenstereigenschaften setzen
		setTitle("DionaRap");
		
		//Spielfeld im Zentrum des Hauptfensters anlegen
		flaeche.add(feld, BorderLayout.CENTER);	
		flaeche.add(feld, BorderLayout.CENTER);
		listenerMaus = new ListenerMaus(this);
		flaeche.addMouseListener(listenerMaus);
		add(flaeche);
		
		toolbar = new Toolbar(this);
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
		
		listenerFenster = new ListenerFenster();
		addComponentListener(listenerFenster);
		listenerModel = new ListenerModel(feld);
		spiel.addModelChangedEventListener(listenerModel);
		listenerTasten = new ListenerTasten();
		addKeyListener(listenerTasten);
		requestFocus();
	}
	
	public void startNewGame()
	{
		nav.dispose();
		this.dispose();
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
	
	public MenuBar getMenu()
	{
		return menue;
	}
	
	public DionaRapModel getModel()
	{
		return spiel;
	}
	

	public Spielfeld getSpielfeld()
	{
		return feld;
	}
	
	public String getThema()
	{
		return thema;
	}
	
	public MTConfiguration getLevel()
	{
		if(control == 0)
		{
			return aktLevel.getConfig();
		}
		else
		{
			return tmpComp;
		}
	}
	
	public void setThema(String thema_neu)
	{
		thema = thema_neu;
	}
	
	public void anzNavigator(boolean sichtbar)
	{
		nav.setVisible(sichtbar);
		pack();
	}
	
	public void anzToolbar(boolean sichtbar)
	{
		toolbar.setVisible(sichtbar);
		pack();
		setLocationRelativeTo(null);
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
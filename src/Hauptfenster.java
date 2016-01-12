import java.awt.*;
import javax.swing.*; 
import de.fhwgt.dionarap.model.data.*;
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

/**
 * Hauptfenster ist verantwortlich für die Implementierung des gesammten Spiels mit seinen Komponenten.
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
	
/**
 * Erzeugt eine Instanz der Klasse Hauptfenster und implementiert den LevelReader, Spielfläche, Spiel, Steuerung und das Spielfeld. 	
 */
	
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
	
/**
 * Erzeugt eine Instanz der Klasse Hauptfenster, wenn zum starten ein individuelles Level gewählt wurde.	
 * @param level das Level, das für das neue Spiel gewählt wurde
 */
	
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
	
/**
 * Erzeugt eine Instanz der Klasse Hauptfenster, wenn die Spieleinstellungen manuell angepasst wurden.	
 * @param newLevel die neue modifizierte MTConifgurationComp Datei
 */
	
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
	
/**
 * Erstellt die restlichen Komponenten für das Spiel, die bei den Hauptfenster() noch nicht erzeugt wurden.
 */
	
	private void erstelleHauptfenster() 
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
	
/**
 * Startet ein neues Spiel, indem sie den vorhanden Navigator und das Hauptfenster schließt.
 */
	
	public void startNewGame()
	{
		nav.dispose();
		this.dispose();
	}
	
/**
 * Dient als getMethode um auf die Steuerung zugreifen zu können.
 * @return steuerung der aktuelle DionaRapController
 */
	
	public DionaRapController getSteuerung()
	{
		return steuerung;
	}
	
/**
 * Dient als getMehtode um auf den Navigator zugreifen zu können.
 * @return nav der aktuelle Navigator
 */
	
	public Navigator getNavigator()
	{
		return nav;
	}
	
/**
 * Dient als getMethode um auf die Toolbar zugreifen zu können.
 * @return toolbar die aktuelle Toolbar
 */
	
	public Toolbar getToolbar()
	{
		return toolbar;
	}
	
/**
 * Dient als getMethode um auf die MenuBar zugreifen zu können.
 * @return menue die aktuelle MenuBar
 */
	
	public MenuBar getMenu()
	{
		return menue;
	}
	
/**
 * Dient als getMethode um auf das DionaRapModel zugreifen zu können.
 * @return spiel das aktuelle DionaRapModel
 */
	
	public DionaRapModel getModel()
	{
		return spiel;
	}
	
/**
 * Dient als getMethode um auf das Spielfeld zugreifen zu können.
 * @return feld das aktuelle Spielfeld
 */

	public Spielfeld getSpielfeld()
	{
		return feld;
	}
	
/**
 * Dient als getMethode um auf das Thema zugreifen zu können.
 * @return thema das gewählte Thema für das Spiel
 */
	
	public String getThema()
	{
		return thema;
	}
	
/**
 * Dient als getMethode um auf das aktuell gewählte Level zugreifen zu können.
 * @return aktLevel.getConfig() oder tmpComp die Config des aktuellen Levels oder die MTConfigurationComp 
 */
	
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
	
/**
 * Dient zum ändern des Themas.
 * @param thema_neu das neu gewählte Thema als String
 */
	
	public void setThema(String thema_neu)
	{
		thema = thema_neu;
	}
	
/**
 * Dient zum (un)sichtbar machen des Navigators.
 * @param sichtbar ein Boolean-Wert, der angibt, ob sichtbar oder nicht
 */
	
	public void anzNavigator(boolean sichtbar)
	{
		nav.setVisible(sichtbar);
		pack();
	}
	
/**
 * Dient zum (un)sichtbar machen der Toolbar.
 * @param sichtbar ein Boolean-Wert, der angibt, ob sichtbar oder nicht
 */
	
	public void anzToolbar(boolean sichtbar)
	{
		toolbar.setVisible(sichtbar);
		pack();
		setLocationRelativeTo(null);
	}
	
/**
 * Dient zur Änderung der Ausrichtung der Toolbar.
 * @param richtung ein Boolean-Wert, der bei true die Toolbar auf North setzt und bei false auf South setzt
 */
	
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
	
/**
 * Die main-Funktion, die das Programm startet
 * @param args ein Standard Attribut
 */
		
	public static void main(String[] args) 
	{
		new Hauptfenster();
	}
}
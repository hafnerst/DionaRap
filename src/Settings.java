import java.util.*;
import javax.swing.JFrame;
import javax.swing.JTable;
import de.fhwgt.dionarap.model.data.MTConfiguration;

/**
 * 
 * Klasse Settings dient zur Darstellung der Spieleinstellungen in einer Tabelle.
 *
 */

public class Settings extends JFrame
{
	Hauptfenster hf;
	MTConfiguration conf;
	HashMap map;
	JTable tabelle;
	private static final long serialVersionUID = 1L;
	
/**
 * Erzeugt eine Instanz der Klasse Settings und stellt ein Fenster dar.
 * @param fenster das aktuelle Hauptfenster des Spiels
 * @param config die aktuelle ausgewählte Conifg
 */
	
	public Settings(Hauptfenster fenster, MTConfiguration config)
	{
		hf = fenster;
		conf = config;
		
		this.setVisible(true);
		
		tabelleSetzen();
	}
	
/**
 * Setzt die Werte der Tabelle mithilfe einer HashMap.
 */
	
	public void tabelleSetzen()
	{
		map = new HashMap();
		
		map.put("1", conf.isAlgorithmAStarActive());
		map.put("2", conf.isAvoidCollisionWithObstacles());
		map.put("3", conf.isAvoidCollisionWithOpponent());
		map.put("4", conf.getOpponentMinimumWaitTime());
		map.put("5", conf.isShotGetsOwnThread());
		map.put("6", conf.getOpponentStartWaitTime());
		map.put("7", conf.getOpponentWaitTime());
		map.put("8", conf.getShotWaitTime());
		map.put("9", conf.isRandomOpponentWaitTime());
		map.put("10", conf.isDynamicOpponentWaitTime());
		
		Object [] [] zeilenDaten = {
			{"AlgorithmAStarActive", (boolean)map.get("1")},
			{"AvoidCollisionWithObstacles", (boolean)map.get("2")},
			{"AvoidCollisionWithOpponent", (boolean)map.get("3")},
			{"MinimumTime", (int)map.get("4")},
			{"ShotGetsOwnThread", (boolean)map.get("5")},
			{"OpponentStartWaitTime", (int)map.get("6")},
			{"OpponentWaitTime", (int)map.get("7")},
			{"ShotWaitTime", (int)map.get("8")},
			{"RandomOpponentWaitTime", (boolean)map.get("9")},
			{"DynamicOpponentWaitTime", (boolean)map.get("10")}
		};
		Object [] spaltenDaten = { "", "" };
		
		tabelle = new JTable(zeilenDaten, spaltenDaten);
		tabelle.setVisible(true);
		
		this.add(tabelle);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}

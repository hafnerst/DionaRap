import java.awt.*;
import javax.swing.*;

/**
 * Programm:	  DionaRap
 * Ueberschrift:  Erstellung der Tastatur
 * Beschreibung:  Zeigt das Anlegen der Tasten im Kindfenster
 * Organisation:  Hochschule Ravensburg-Weingarten
 * @author Daniel Landler-Gaertner und Steffen Hafner
 * @version 1.0
 */

public class Tastatur extends JPanel
{
	// Diese Variable wird ben�tigt, da JPanel das Interface Serializable implementiert
	private static final long serialVersionUID = 1L;
	private JButton[] tasten = new JButton[9];
	
	public Tastatur()
	{
		String pfad = System.getProperty("user.dir")+"/images/navigator/";
		
		//3 Zeilen, 3 Spalten und 0 Abstand zwischen Elementen
		setLayout(new GridLayout(3,3,0,0));
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		for(int i=9; i>0;i--)
		{
			tasten[i-1] = new JButton();
			tasten[i-1].setActionCommand(Integer.toString(i));
			tasten[i-1].setIcon(new ImageIcon(pfad+"taste"+i+".gif"));
			tasten[i-1].setPreferredSize(new Dimension(75,75));
			add(tasten[i-1]);
			
			if(i!=5)
			{
				tasten[i-1].addActionListener(new ListenerBewegung());
			}
			else
			{
				tasten[i-1].addActionListener(new ListenerWaffe());
			}
		}
	}
}

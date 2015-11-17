import java.awt.*;
import javax.swing.*;

/**
 * Die Klasse Tastatur erzeugt die Buttons f�r den Navigator in einem GridLayout
 * @author Daniel Landler-G�rtner und Steffen Hafner
 *
 */
public class Tastatur extends JPanel
{
	// Diese Variable wird ben�tigt, da JPanel das Interface Serializable implementiert
	private static final long serialVersionUID = 1L;
	private JButton[] tasten = new JButton[9];
	
	public Tastatur()
	{
		//3 Zeilen, 3 Spalten und 0 Abstand zwischen Elementen
		setLayout(new GridLayout(3,3,0,0));
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		//Setzen des roten Rahmens
		setBorder(BorderFactory.createLineBorder(Color.red));
		
		for(int i=9; i>0;i--)
		{
			tasten[i-1] = new JButton(Integer.toString(i));
			tasten[i-1].setPreferredSize(new Dimension(50,50));
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

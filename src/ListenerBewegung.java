import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import de.fhwgt.dionarap.controller.*;
/**
 * Klasse ListenerBewegung dient zum implementieren eines ActionListener.
 *
 */

public class ListenerBewegung implements ActionListener
{	
/**
 * Dient zur Auswertung von Benutzereingaben der gewählten Komponente.
 * 
 */
	
	public void actionPerformed(ActionEvent event) 
	{
		JButton tmp_taste = (JButton) event.getSource();
		String beschriftung = tmp_taste.getActionCommand();
		
		Navigator nav = (Navigator)tmp_taste.getTopLevelAncestor();
		Hauptfenster hf = (Hauptfenster)nav.getParent();
		DionaRapController controller = hf.getSteuerung();
		
		controller.movePlayer(Integer.parseInt(beschriftung));
	}
}
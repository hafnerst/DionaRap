import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.fhwgt.dionarap.controller.DionaRapController;

/**
 * 
 * Klasse ListenerWaffe dient zum imlementieren eines ActionListener.
 *
 */

public class ListenerWaffe implements ActionListener 
{
	
/**
 * Dient zur Auswertung, ob die ausgewählte Komponente gedrückt wurde.
 */

	public void actionPerformed(ActionEvent event) 
	{
		JButton tmp_taste = (JButton) event.getSource();
		
		Navigator nav = (Navigator)tmp_taste.getTopLevelAncestor();
		Hauptfenster hf = (Hauptfenster)nav.getParent();
		DionaRapController controller = hf.getSteuerung();
		
		if(controller.shoot() == false)
		{
			Toolbar tool = hf.getToolbar();
			tool.startBlinkThread();
		}
	}
}

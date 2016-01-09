import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.fhwgt.dionarap.controller.DionaRapController;

public class ListenerWaffe implements ActionListener 
{

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

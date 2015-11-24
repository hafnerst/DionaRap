import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import de.fhwgt.dionarap.controller.*;

public class ListenerBewegung implements ActionListener
{	
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
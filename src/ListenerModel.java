import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.*;

public class ListenerModel implements DionaRapListener
{
	Spielfeld sfeld;
	JOptionPane dialogfenster = new JOptionPane();
	String pfad = System.getProperty("user.dir")+"/images/";
	int eingabe;
	Hauptfenster hf;
	
	public ListenerModel(Spielfeld feld)
	{
		sfeld = feld;
		hf = (Hauptfenster) sfeld.getTopLevelAncestor();
	}

	public void modelChanged(DionaRapChangedEvent event) 
	{
		sfeld.deletePawns();
		sfeld.setPawns();
		
		DionaRapModel model = (DionaRapModel)event.getSource();
		Toolbar toolbar = hf.getToolbar();
		
		int stand = model.getScore();		
		toolbar.punktestand.setText(Integer.toString(stand));
		
		int anz_gegner = hf.anz_gegner;
		int akt_gegner = anz_gegner-model.getOpponentCount();
		int fortschritt = (int)(((double)akt_gegner/(double)anz_gegner)*100);
		toolbar.balken.setValue(fortschritt);
	}

	public void statusChanged(GameStatusEvent event) 
	{
		DionaRapModel model = (DionaRapModel)event.getSource();
		
		
		Toolbar toolbar = hf.getToolbar();
		toolbar.neu_spiel.setEnabled(true);
		
		Object[] optionen = {"Abbrechen", "Neues Spiel"};
		
		if(model.isGameOver())
		{
			eingabe = JOptionPane.showOptionDialog(dialogfenster,"Game over!","Spielende",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(pfad+"gameover.gif"), optionen, optionen[1]);
		}
		if(model.isGameWon())
		{
			eingabe = JOptionPane.showOptionDialog(dialogfenster,"Gewonnen!","Spielende",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(pfad+"gewonnen.gif"), optionen, optionen[1]);
		}
		
		if (eingabe == 1)
		{
			hf.dispose();
			Navigator nav = (Navigator)hf.getNavigator();
			nav.dispose();
			new Hauptfenster();
		}
	}
}
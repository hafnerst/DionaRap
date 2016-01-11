import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.*;

public class ListenerModel implements DionaRapListener
{
	Spielfeld sfeld;
	String pfad;
	int eingabe;
	Hauptfenster hf;
	Toolbar toolbar;
	
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
		toolbar = hf.getToolbar();
		
		//Anpassung des Punktestands
		int stand = model.getScore();		
		toolbar.punktestandT.setText(Integer.toString(stand));
		
		//Anpassung des Fortschittsbalken
		int anz_gegner = hf.anz_gegner;
		int akt_gegner = anz_gegner-model.getOpponentCount();
		int fortschritt = (int)(((double)akt_gegner/(double)anz_gegner)*100);
		toolbar.fortschrittPB.setValue(fortschritt);
		
		//Anpassung der Munitionsanzeige
		
		toolbar.deleteMunition();
		toolbar.setMunition();
	}

	public void statusChanged(GameStatusEvent event) 
	{
		DionaRapModel model = (DionaRapModel)event.getSource();
		String thema = hf.getThema();
		MenuBar menu = hf.getMenu();
		menu.menuAuswahlLevel.setEnabled(true);
		pfad = System.getProperty("user.dir")+"/images/"+thema;
		
		
		Toolbar toolbar = hf.getToolbar();
		toolbar.spiel_neuB.setEnabled(true);
		
		Object[] optionen = {"Abbrechen", "Neues Spiel"};
		
		if(model.isGameOver())
		{
			eingabe = JOptionPane.showOptionDialog(hf,"Game over!","Spielende",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(pfad+"/loss.gif"), optionen, optionen[1]);
		}
		if(model.isGameWon())
		{
			eingabe = JOptionPane.showOptionDialog(hf,"Gewonnen!","Spielende",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(pfad+"/win.gif"), optionen, optionen[1]);
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
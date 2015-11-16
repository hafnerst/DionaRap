import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.*;

public class ListenerModel implements DionaRapListener
{
	Spielfeld sfeld;
	
	public ListenerModel(Spielfeld feld)
	{
		sfeld = feld;
	}

	public void modelChanged(DionaRapChangedEvent event) 
	{
		sfeld.deletePawns();
		sfeld.setPawns();
	}

	public void statusChanged(GameStatusEvent event) 
	{

	}
}
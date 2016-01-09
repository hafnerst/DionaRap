import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import de.fhwgt.dionarap.controller.DionaRapController;

public class ListenerTasten implements KeyListener 
{

	public void keyTyped(KeyEvent event) {
		
		Hauptfenster hf = (Hauptfenster)event.getSource();
		char eingabe = event.getKeyChar();
		DionaRapController controller = hf.getSteuerung();
		
		if(eingabe>='1'&&eingabe<='9')
		{
			int dir = Integer.parseInt(""+eingabe);
			if(dir==5)
			{
				if(controller.shoot() == false)
				{
					Toolbar tool = hf.getToolbar();
					tool.startBlinkThread();
				}
			}
			else
			{
				controller.movePlayer(dir);
			}
		}
	}

	public void keyPressed(KeyEvent event) 
	{	
	}

	public void keyReleased(KeyEvent event) 
	{	
	}

}

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.Player;

public class ListenerMaus extends MouseAdapter
{
	Hauptfenster hf;
	DionaRapModel model;
	DionaRapController controller;
	Player spieler;
	int xKlick, yKlick;
	
	ListenerMaus(Hauptfenster fenster)
	{
		hf = fenster;
		model = hf.getModel();
		controller = hf.getSteuerung();
	}
	
	public void mousePressed(MouseEvent e) 
	{
		int ret_val = 0;
		spieler = model.getPlayer();
		xKlick = (e.getX()/50);
		yKlick = (e.getY()/50);
		ret_val = klickRichtung();
		
		if(ret_val !=0 )
		{
			if (ret_val == 5)
			{
				controller.shoot();
			}
			else
			{
				controller.movePlayer(ret_val);
			}
		}
	}
	
	private int klickRichtung()
	{
		int x = spieler.getX();
		int y = spieler.getY();
		
		if(x == xKlick && y == yKlick)
		{
			return 5;
		}
		else if(x == xKlick && y-1 == yKlick)
		{
			return 8;
		}
		else if(x+1 == xKlick && y-1 == yKlick)
		{
			return 9;
		}
		else if(x == xKlick && y-1 == yKlick)
		{
			return 8;
		}
		else if(x+1 == xKlick && y == yKlick)
		{
			return 6;
		}
		else if(x+1 == xKlick && y+1 == yKlick)
		{
			return 3;
		}
		else if(x == xKlick && y+1 == yKlick)
		{
			return 2;
		}
		else if(x-1 == xKlick && y+1 == yKlick)
		{
			return 1;
		}
		else if(x-1 == xKlick && y == yKlick)
		{
			return 4;
		}
		else if(x-1 == xKlick && y-1 == yKlick)
		{
			return 7;
		}
		return 0;
	}
	
}
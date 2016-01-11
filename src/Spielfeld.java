import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.data.Grid;
import de.fhwgt.dionarap.model.objects.AbstractPawn;
import de.fhwgt.dionarap.model.objects.Ammo;
import de.fhwgt.dionarap.model.objects.Destruction;
import de.fhwgt.dionarap.model.objects.Obstacle;
import de.fhwgt.dionarap.model.objects.Opponent;
import de.fhwgt.dionarap.model.objects.Player;
import de.fhwgt.dionarap.model.objects.Vortex;

/**
 * Die Klasse Spielfeld erzeugt f�r das Hauptfenster ein Spielfeld mit Hilfe eines GridLayouts
 * @author Daniel Landler-G�rtner und Steffen Hafner
 *
 */
public class Spielfeld extends JPanel
{
	// Diese Variable wird ben�tigt, da JPanel das Interface Serializable implementiert
	private static final long serialVersionUID = 1L;
	DionaRapModel model;
	JLabel[][] felder;	
	Hauptfenster hf;
	int x_fl, y_fl;
	int obstacleCount;
	
	public Spielfeld(DionaRapModel spiel, Hauptfenster fenster)
	{
		hf = fenster;
		model = spiel;
		
		Grid spielflaeche = model.getGrid();
		x_fl = spielflaeche.getGridSizeX();
		y_fl = spielflaeche.getGridSizeY();
		
		setSpielflaeche(x_fl, y_fl);

		setPawns();
	}
	
	public void setSpielflaeche(int x_flaeche, int y_flaeche)
	{
		felder = new JLabel[x_flaeche][y_flaeche];
		//10 Zeilen, 10 Spalten und 0 Abstand zwischen Elementen
		setLayout(new GridLayout(y_flaeche,x_flaeche,0,0));
		
		//Erstellen des Spielfelds
		for (int i=0; i<y_flaeche; i++)
		{
			for(int j=0; j<x_flaeche; j++)
			{
				felder[j][i] = new JLabel();
				felder[j][i].setPreferredSize(new Dimension(50,50));
				felder[j][i].setOpaque(true);
				if((i+j)%2==0)
				{
					felder[j][i].setBackground(Color.LIGHT_GRAY);
					felder[j][i].setForeground(Color.white);
					felder[j][i].setHorizontalAlignment(SwingConstants.CENTER);
				}
				else
				{
					felder[j][i].setBackground(Color.white);
					felder[j][i].setForeground(Color.LIGHT_GRAY);
					felder[j][i].setHorizontalAlignment(SwingConstants.CENTER);
				}
				add(felder[j][i]);
			}
		}
	}
	
	public void setPawns()
	{
		obstacleCount = 0;
		String thema = hf.getThema();
		AbstractPawn[] figuren = model.getAllPawns();
		String pfad = System.getProperty("user.dir")+"/images/"+thema;
		
		Player spieler = model.getPlayer();
		String richtung = Integer.toString(spieler.getViewDirection());

		for(int i=0; i<figuren.length; i++)
		{
			if(figuren[i] instanceof Opponent)
			{
				felder[figuren[i].getX()][figuren[i].getY()].setIcon(new ImageIcon(pfad+"/opponent.gif"));
			}
			else if(figuren[i] instanceof Obstacle)
			{
				felder[figuren[i].getX()][figuren[i].getY()].setIcon(new ImageIcon(pfad+"/obstacle.gif"));
				obstacleCount++;
			}
			else if(figuren[i] instanceof Player)
			{
				felder[figuren[i].getX()][figuren[i].getY()].setIcon(new ImageIcon(pfad+"/player"+richtung+".gif"));
			}
			else if(figuren[i] instanceof Vortex)
			{
				felder[figuren[i].getX()][figuren[i].getY()].setIcon(new ImageIcon(pfad+"/vortex.gif"));
			}
			else if(figuren[i] instanceof Destruction)
			{
				felder[figuren[i].getX()][figuren[i].getY()].setIcon(new ImageIcon(pfad+"/destruction.gif"));
			}
			else if(figuren[i] instanceof Ammo)
			{
				felder[figuren[i].getX()][figuren[i].getY()].setIcon(new ImageIcon(pfad+"/ammo.png"));
			}
		}
	}
	
	public void deletePawns()
	{
		for(int i=0; i<y_fl; i++)
		{
			for(int j=0; j<x_fl; j++)
			{
				felder[j][i].setIcon(null);
			}
		}
	}
	
	public int getObstacleCount()
	{
		return obstacleCount;
	}
}
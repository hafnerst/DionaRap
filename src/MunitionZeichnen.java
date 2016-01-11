import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Programm:	  DionaRap
 * Ueberschrift:  Erstellung der Zeichnenklasse der Munition
 * Beschreibung:  Zeigt, wie die Bilder mit Graphics2D in ein Panel gezeichnet wird
 * Organisation:  Hochschule Ravensburg-Weingarten
 * @author Daniel Landler-Gaertner und Steffen Hafner
 * @version 1.0
 */

public class MunitionZeichnen extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private Image munitionImage;
	
	public MunitionZeichnen(Hauptfenster hf) 
	{
		String pfad = System.getProperty("user.dir")+"/images/"+hf.getThema()+"/ammo.png";
		munitionImage = this.getToolkit().getImage(pfad).getScaledInstance(24, 24, Image.SCALE_DEFAULT);
	}
	
	public void paint(Graphics g)
	{
		Graphics2D munitionsGrafik = (Graphics2D) g;
		munitionsGrafik.drawImage(munitionImage, 0, 0, this);
	}
}
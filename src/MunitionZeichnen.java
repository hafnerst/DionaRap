import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * 
 * Klasse MunitionZeichnen dient zur Erzeugung einer Instanz von MunitionZeichnen.
 *
 */

public class MunitionZeichnen extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private Image munitionImage;
	
/**
 * Greift auf die Bilddatei zu und legt eine Image Komponente davon an.
 * @param hf das aktuelle Hauptfenster des Spiels
 */
	
	public MunitionZeichnen(Hauptfenster hf) 
	{
		String pfad = System.getProperty("user.dir")+"/images/"+hf.getThema()+"/ammo.png";
		munitionImage = this.getToolkit().getImage(pfad).getScaledInstance(24, 24, Image.SCALE_DEFAULT);
	}
	
/**
 * Zeichnet das ausgewählte Munitionsbild mit Java2D.
 */
	
	public void paint(Graphics g)
	{
		Graphics2D munitionsGrafik = (Graphics2D) g;
		munitionsGrafik.drawImage(munitionImage, 0, 0, this);
	}
}
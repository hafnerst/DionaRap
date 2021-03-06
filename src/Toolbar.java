import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import de.fhwgt.dionarap.model.data.DionaRapModel;

import java.awt.event.ActionListener;

/**
 * 
 * Klasse Toolbar erzeugt eine TToolBar mit seinen Komponenten für das Hauptfenster.
 *
 */
public class Toolbar extends JToolBar
{
	private static final long serialVersionUID = 1L;
	String pfad = System.getProperty("user.dir")+"/images/squarehead/ammo.png";
	JPanel gesamtP = new JPanel();
	JButton spiel_neuB;
	JTextField punktestandT;
	JProgressBar fortschrittPB;
	Hauptfenster hf;
	DionaRapModel spiel;
	JPanel munitionP;
	ImageIcon munitionI;
	JLabel munitionL;
	JLabel[] muni;
	JPanel[] munitionBilder;
	Settings einstellungen;
	int mun_menge = 0;
	TitledBorder mun;
	
	Thread blinkThread;
	MunitionBlink munitionBlink;
	
/**
 * Erzeugt eine Instanz der Klasse Toolbar und setzt deren Eigenschaften.
 * @param haupt das aktuelle Hauptfenster des Spiels
 */

	public Toolbar(Hauptfenster haupt)
	{	
		hf = haupt;
		spiel = (DionaRapModel) hf.getModel();
		
		gesamtP.setLayout(new GridLayout(1,5,0,0));
		gesamtP.setPreferredSize(new Dimension(500,60));
		
		JPanel spiel_neuP = new JPanel();
		spiel_neuP.setPreferredSize(new Dimension(100,60));
		spiel_neuP.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gesamtP.add(spiel_neuP);
		
		spiel_neuB = new JButton("Neues Spiel");
		spiel_neuB.setEnabled(false);
		spiel_neuB.setFont(new Font(null, Font.PLAIN, 11));
		spiel_neuB.addActionListener(new ActionListener()
		{
			
		/**
		 * Dient zur Auswertung der gedrückten Taste und was daraus resultiert.
		 */
			
			public void actionPerformed(ActionEvent event)
			{
				JButton tmp_taste = (JButton) event.getSource();
				Hauptfenster hf = (Hauptfenster) tmp_taste.getTopLevelAncestor();
				Navigator nav = (Navigator) hf.getNavigator();
				hf.startNewGame();
				new Hauptfenster();
			}
		});
		spiel_neuP.add(spiel_neuB, gbc);
		
		//Panel für "Punktestand" und Textfeld "Punktestand" anlegen
		JPanel punkteP = new JPanel();
		punkteP.setBorder(BorderFactory.createTitledBorder("Punktestand"));
		TitledBorder punkteTB = (TitledBorder) punkteP.getBorder();
		punkteTB.setTitleFont(new Font(null, Font.PLAIN, 11));
		punkteP.setToolTipText("Punktestand");
	
		punktestandT = new JTextField("0");
		punktestandT.setColumns(7);
		punktestandT.setFont(new Font(null, Font.PLAIN, 11));
		punktestandT.setEditable(false);
		punkteP.add(punktestandT);
		gesamtP.add(punkteP);
		
		//Panel für "Munition" anlegen
		munitionP = new JPanel();
        munitionBilder = new MunitionZeichnen[3];
        for (int i = 0; i < munitionBilder.length; i++){
            munitionBilder[i] = new MunitionZeichnen(hf);
            munitionBilder[i].setAlignmentX(LEFT);
            munitionBilder[i].setPreferredSize(new Dimension(24, 24));
        }
        setMunition();
        gesamtP.add(munitionP);
		
		//Panel für "Fortschritt" anlegen und Fortschritsbalken "Balken" anlegen
		JPanel fortschrittP = new JPanel();
		fortschrittP.setBorder(BorderFactory.createTitledBorder("Spielfortschritt"));
		TitledBorder fortschrittTB = (TitledBorder) fortschrittP.getBorder();
		fortschrittTB.setTitleFont(new Font(null, Font.PLAIN, 11));
		fortschrittP.setToolTipText("Spielfortschritt");
		gesamtP.add(fortschrittP);
		
		fortschrittPB = new JProgressBar(0, 100);
		fortschrittPB.setFont(new Font(null, Font.PLAIN, 11));
		fortschrittPB.setStringPainted(true);
		fortschrittPB.setPreferredSize(new Dimension(85,25));
		fortschrittP.add(fortschrittPB);
		
		//Panel für "Settings" anlegen und Button "Settings" anlegen
		JPanel settingsP = new JPanel();
		settingsP.setLayout(new GridBagLayout());
		GridBagConstraints gbs = new GridBagConstraints();
		gesamtP.add(settingsP);
		
		JButton settingsB = new JButton("Settings");
		settingsB.setFont(new Font(null, Font.PLAIN, 11));
		settingsB.setPreferredSize(new Dimension(90,25));
		settingsB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				einstellungen = new Settings(hf, hf.getLevel());
			}
		});
		settingsP.add(settingsB, gbs);
		
		//Toolbar-Eigenschaften definieren
		setFloatable(false);
		setRollover(true);
		
		add(gesamtP);
		
		//Der Blink-Thread
		munitionBlink = new MunitionBlink(this);
		blinkThread = new Thread(munitionBlink);
	}
	
/**
 * Setzt die Menge von Munitionsbildern in der Toolbar.
 */
	
	public void setMunition()
	{
		mun_menge = spiel.getShootAmount();
        if (mun_menge > 3) 
        {
            
            munitionL = new JLabel("*" + mun_menge);
            munitionL.setHorizontalAlignment(LEFT);
            munitionP.add(munitionL);
            munitionP.add(munitionBilder[0]);
            munitionP.add(munitionBilder[1]);
            munitionP.revalidate();
            munitionP.repaint();
        }
        else
        {
            for (int i = 0; i < mun_menge; i++) {
                 
                munitionP.add(munitionBilder[i]);
                munitionP.revalidate();
                munitionP.repaint();
            }
        }
		
		munitionP.setBorder(BorderFactory.createTitledBorder("Munition"));
		mun = (TitledBorder) munitionP.getBorder();
		mun.setTitleFont(new Font(null, Font.PLAIN, 11));
		munitionP.setToolTipText("Munitionskapazität");
	}
	
/**
 * Löscht die Munitionsbilder um die Anzeige zu aktualisieren.
 */
	
	public void deleteMunition()
	{
		if(mun_menge == 1)
		{
			stopBlinkThread();
            munitionP.removeAll();
            munitionP.revalidate();
            munitionP.repaint();
		}
		else if (mun_menge > 3) {
            
            munitionP.remove(munitionL);
            munitionP.remove(munitionBilder[0]);
            munitionP.remove(munitionBilder[1]);
            munitionP.revalidate();
            munitionP.repaint();
             
        }
        else
        {
            for (int i = 0; i < mun_menge; i++) 
            {    
                munitionP.removeAll();
                munitionP.revalidate();
                munitionP.repaint();
            }
        }
	}
	
/**
 * Stoppt das Blinken des MunitionBlink-Threads.
 */

	void stopBlinkThread() 
	{
		if(blinkThread.isAlive() == true) {
			blinkThread.interrupt();
		}
	}
	
/**
 * Startet das Blinken des MunitionBlink-Threads.
 */
	
	void startBlinkThread() {
		if(blinkThread.isAlive() == false) {
			blinkThread = new Thread(munitionBlink);
			blinkThread.start();
		}
	}
	
/**
 * Setzt und entfernt den Rahmen um die Munitionsanzeige um das Blinken zu realisieren.
 * @param blinkOn ein Boolean-Wert, der angibt, ob der Rahmen dargestellt wird oder nicht
 */
	
	public void ammoBlink(boolean blinkOn) {
		if(blinkOn)
		{
			munitionP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red, 1), "Munition"));
		}
		else
		{
			munitionP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Munition"));
		}
	}
}

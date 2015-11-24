import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
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

import java.awt.event.ActionListener;


public class Toolbar extends JToolBar
{
	private static final long serialVersionUID = 1L;
	String pfad = System.getProperty("user.dir")+"/images/squarehead/ammo.png";
	JPanel gesamt = new JPanel();
	JButton neu_spiel;
	JTextField punktestand;
	JProgressBar balken;

	public Toolbar()
	{	
		gesamt.setLayout(new GridLayout(1,5,0,0));
		gesamt.setPreferredSize(new Dimension(500,60));
		
		//Panel für Button "Neues Spiel" und Button "Neues Spiel" anlegen
		JPanel button_spiel = new JPanel();
		button_spiel.setPreferredSize(new Dimension(100,60));
		button_spiel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gesamt.add(button_spiel);
		
		neu_spiel = new JButton("Neues Spiel");
		neu_spiel.setEnabled(false);
		neu_spiel.setFont(new Font(null, Font.PLAIN, 11));
		neu_spiel.setPreferredSize(new Dimension(90,25));
		neu_spiel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				JButton tmp_taste = (JButton) event.getSource();
				Hauptfenster hf = (Hauptfenster) tmp_taste.getTopLevelAncestor();
				Navigator nav = (Navigator) hf.getNavigator();
				hf.dispose();
				nav.dispose();
				new Hauptfenster();
			}
		});
		button_spiel.add(neu_spiel, gbc);
		
		//Panel für "Punktestand" und Textfeld "Punktestand" anlegen
		JPanel punkte = new JPanel();
		punkte.setBorder(BorderFactory.createTitledBorder("Punktestand"));
		TitledBorder pkt = (TitledBorder) punkte.getBorder();
		pkt.setTitleFont(new Font(null, Font.PLAIN, 11));
		punkte.setToolTipText("Punktestand");
	
		punktestand = new JTextField("0");
		punktestand.setColumns(7);
		punktestand.setFont(new Font(null, Font.PLAIN, 11));
		punktestand.setEditable(false);
		
		punkte.add(punktestand);
		gesamt.add(punkte);
		
		//Panel für "Munition" anlegen und "Munition" Icons anlegen
		JPanel munition = new JPanel();
		ImageIcon ammo = new ImageIcon(pfad);
		ammo.setImage(ammo.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		
		for(int i=0;i<3;i++)
		{
			JLabel muni = new JLabel("", ammo, JLabel.LEFT);
			munition.add(muni);
		}
		munition.setBorder(BorderFactory.createTitledBorder("Munition"));
		TitledBorder mun = (TitledBorder) munition.getBorder();
		mun.setTitleFont(new Font(null, Font.PLAIN, 11));
		munition.setToolTipText("Munitionskapazität");
		gesamt.add(munition);
		
		//Panel für "Fortschritt" anlegen und Fortschritsbalken "Balken" anlegen
		JPanel fortschritt = new JPanel();
		fortschritt.setBorder(BorderFactory.createTitledBorder("Spielfortschritt"));
		TitledBorder fort = (TitledBorder) fortschritt.getBorder();
		fort.setTitleFont(new Font(null, Font.PLAIN, 11));
		fortschritt.setToolTipText("Spielfortschritt");
		gesamt.add(fortschritt);
		
		balken = new JProgressBar(0, 100);
		balken.setFont(new Font(null, Font.PLAIN, 11));
		balken.setStringPainted(true);
		balken.setPreferredSize(new Dimension(85,25));
		fortschritt.add(balken);
		
		//Panel für "Settings" anlegen und Button "Settings" anlegen
		JPanel set = new JPanel();
		set.setLayout(new GridBagLayout());
		GridBagConstraints gbs = new GridBagConstraints();
		gesamt.add(set);
		
		JButton settings = new JButton("Settings");
		settings.setFont(new Font(null, Font.PLAIN, 11));
		settings.setPreferredSize(new Dimension(90,25));
		set.add(settings, gbs);
		
		//Toolbar-Eigenschaften definieren
		setFloatable(false);
		setRollover(true);
		
		add(gesamt);
	}
}

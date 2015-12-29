import java.awt.Color;
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
import de.fhwgt.dionarap.model.data.DionaRapModel;

import java.awt.event.ActionListener;


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
	int mun_menge;

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
		//spiel_neuB.setPreferredSize(new Dimension(90,25));
		spiel_neuB.addActionListener(new ActionListener()
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
		
		//Panel für "Munition" anlegen und "Munition" Icons anlegen
		munitionP = new JPanel();
		munitionI = new ImageIcon(pfad);
		munitionI.setImage(munitionI.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
		gesamtP.add(munitionP);

		setMunition();
		
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
		settingsP.add(settingsB, gbs);
		
		//Toolbar-Eigenschaften definieren
		setFloatable(false);
		setRollover(true);
		
		add(gesamtP);
	}
	
	public void setMunition()
	{
		mun_menge = spiel.getShootAmount();
		System.out.println(mun_menge);
		muni = new JLabel[3];
		
		if(mun_menge>3)
		{
			muni[0] = new JLabel("*"+mun_menge);
			muni[1] = new JLabel("", munitionI, JLabel.LEFT);
			muni[2] = new JLabel("", munitionI, JLabel.LEFT);
			
			munitionP.add(muni[0]);
			munitionP.add(muni[1]);
			munitionP.add(muni[2]);
		}
		else if(mun_menge<=3 && mun_menge!=0)
		{
			for(int i=0;i<mun_menge;i++)
			{
				muni[i] = new JLabel("", munitionI, JLabel.LEFT);
				munitionP.add(muni[i]);
			}
		}
		else if(mun_menge==0)
		{
			munitionP.setBackground(Color.red);
		}
		
		munitionP.setBorder(BorderFactory.createTitledBorder("Munition"));
		TitledBorder mun = (TitledBorder) munitionP.getBorder();
		mun.setTitleFont(new Font(null, Font.PLAIN, 11));
		munitionP.setToolTipText("Munitionskapazität");
	}
	
	public void deleteMunition()
	{
		if(mun_menge>3)
		{
			for(int i=0;i<3;i++)
			{
				munitionP.remove(muni[i]);
			}
		}
		else
		{
			for(int i=0;i<mun_menge;i++)
			{
				munitionP.remove(muni[i]);
			}
		}
	}
}

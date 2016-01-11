import java.awt.Dimension;
import java.io.File;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Programm:	  DionaRap
 * Ueberschrift:  Erstellung des Beschreibungsdialogs
 * Beschreibung:  Zeigt die Spieleerklaerung von DionaRap in einem Fenster an
 * Organisation:  Hochschule Ravensburg-Weingarten
 * @author Daniel Landler-Gaertner und Steffen Hafner
 * @version 1.0
 */

public class BeschreibungDialog 
{
	String pfad = System.getProperty("user.dir")+"/Beschreibung/Spielbeschreibung.html";
	
	BeschreibungDialog(Hauptfenster hf)
	{
		JDialog dialogBe = new JDialog(hf, "Spielbeschreibung");
	
		JEditorPane editorPane = new JEditorPane();
		editorPane.setVisible(true);
		editorPane.setEditable(false);

		File f = new File(pfad);
		try 
		{
			URL url = f.toURI().toURL();
			editorPane.setPage(url);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane(editorPane);
	
		dialogBe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialogBe.setVisible(true);
		dialogBe.setSize(new Dimension(700, 400));
		dialogBe.setModal(true);
		dialogBe.setLocationRelativeTo(null);

		dialogBe.add(scrollPane);
	}

}

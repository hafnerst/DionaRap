import java.awt.Dimension;
import java.io.File;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Klasse BeschreibungDialog dient zur Erzeugung der Spielebeschreibung.
 *
 */
public class BeschreibungDialog 
{
	String pfad = System.getProperty("user.dir")+"/Beschreibung/Spielbeschreibung.html";
	
/**
 * Erzeugt eine Instanz der Klasse BeschreibungDialog und zeigt ein JDialog mit Inhalt der DionaRap Spielbeschreibung an.
 * 
 * @param hf das Hauptfenster
 */
	
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

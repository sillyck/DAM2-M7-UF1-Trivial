import org.xml.sax.SAXException;

import java.io.IOException;

public class mainTesting
{
	public static pantallaInicial pantallaInicial;
	
	public static Tauler tauler;
	
	public static void main(String[] args) throws IOException, SAXException
	{
		MetaController.isThisDebugMode = true;
		QuestionBank.Start();
		tauler = new Tauler("Unnamed testing player 1","Unnamed testing player 2",true);
		tauler.setVisible(true);
	}
}
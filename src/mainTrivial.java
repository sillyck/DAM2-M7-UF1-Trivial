import org.xml.sax.SAXException;

import java.io.IOException;

public class mainTrivial
{
	public static pantallaInicial pantallaInicial;
	
	public static Tauler tauler;

	public static void main(String[] args) throws IOException, SAXException
	{
		MetaController.isThisDebugMode = false;
		QuestionBank.Start();
		pantallaInicial = new pantallaInicial("","");
		pantallaInicial.setVisible(true);
	}

}
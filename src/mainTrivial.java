import org.xml.sax.SAXException;

import java.io.IOException;

public class mainTrivial {

	public static void main(String[] args) throws IOException, SAXException
	{
		
		pantallaInicial v = new pantallaInicial();
		v.setVisible(true);
		
		QuestionBank.Start();
	}

}
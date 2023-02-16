import org.xml.sax.SAXException;

import java.io.IOException;

public class mainTrivial {

	public static void main(String[] args) throws IOException, SAXException
	{
		
		pantallaPreguntes v = new pantallaPreguntes();
		v.setVisible(true);
		
		QuestionBank.Start();
	}

}
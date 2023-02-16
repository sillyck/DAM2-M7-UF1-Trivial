import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class QuestionXmlSaxReader extends DefaultHandler
{
	public QuestionXmlSaxReader()
	{
		super();
	}
	
	public void startDocument()
	{
//		System.out.println("\nPrincipi del document XML");
	
	}
	
	public void endDocument()
	{
//		System.out.println("Final del document XML\n");
	
	}
	
	public void startElement(String uri, String nom, String nomC, Attributes attributes)
	{
//		if(nom.equals("nombre")) nomActiu = true;
//		if(nom.equals("siglas")) siglesActiu = true;
//		if(nom.equals("maximo")) maximActiu = true;
//		if(nom.equals("valor")) valorActiu = true;
	}
	
	public void endElement(String uri, String nom, String nomC)
	{
//		if(nom.equals("nombre")) nomActiu = false;
//		if(nom.equals("siglas")) siglesActiu = false;
//		if(nom.equals("maximo")) maximActiu = false;
//		if(nom.equals("valor")) valorActiu = false;
//		if(nom.equals("cripto"))
//		{
//			total = (float)(maxim*valor);
//			System.out.println(nomEtiqueta+" ("+sigles+"): "+total+" millones de dolares");
//		}
	}
}
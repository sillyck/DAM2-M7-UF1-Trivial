import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe estatica que será la que interactuará amb els XMLs i els ArrayLists amb totes les preguntes.
 */
public class QuestionBank
{
	/**
	 * Em penso que aixo no funciona, aixi que millor no tocar-ho...
	 * <p>Pero en el cas de que funcionés, en posar aixo a <tt>false</tt> hauría
	 * d'ignorar les preguntes ja contestades i començar cada nova finestra amb totes les preguntes disponibles.
	 */
	private static final boolean CARGAR_PREGUNTES_JA_FETES = true;
	
	/**
	 * Un ArrayList amb totes les preguntes del XML <tt>preguntas.xml</tt>.
	 */
	private static List<Pregunta> totesLesPreguntes;
	
	/**
	 * Les preguntes disponibles per a la pantalla de mostrar preguntes.
	 * S'actualitza cada vegada que es llegeix un XML o que es pilla una pregunta.
	 */
	private static List<Pregunta> preguntesDisponibles;
	
	/**
	 * Un ArrayList amb les preguntes del fitxer <tt>preguntas-repe.xml</tt>.
	 */
	private static List<Pregunta> preguntesJaFetes;
	
	@SuppressWarnings("ForLoopReplaceableByForEach")
	public static void Start() throws IOException, SAXException
	{
		totesLesPreguntes = new ArrayList<>();
		preguntesJaFetes = new ArrayList<>();
		preguntesDisponibles = new ArrayList<>();
		
		llegirFitxerTotal();
		if(CARGAR_PREGUNTES_JA_FETES) llegirFitxerJaFet();
		
		CalcularPreguntesDisponibles();
	}
	
	@SuppressWarnings("ForLoopReplaceableByForEach")
	private static void CalcularPreguntesDisponibles()
	{
		System.out.println("Començant el calcul de preguntes disponibles");
		preguntesDisponibles = new ArrayList<>();
		if(totesLesPreguntes!=null && preguntesJaFetes!=null) for(int i=0; i<totesLesPreguntes.size(); i++)
		{
			boolean preguntaDisponible = true;
			for(int j=0; j<preguntesJaFetes.size(); j++) if(totesLesPreguntes.get(i).equals(preguntesJaFetes.get(j)))
			{
				preguntaDisponible = false;
				break;
			}
			if(preguntaDisponible) preguntesDisponibles.add(totesLesPreguntes.get(i));
		}
		System.out.println("Calcul de preguntes possibles acabat");
		System.out.println("preguntesDisponibles.size() = " + preguntesDisponibles.size());
	}
	
	@SuppressWarnings("unused")
	public static Pregunta ObtindrePregunta(boolean marcarComJaFeta)
	{
		if(preguntesDisponibles==null || preguntesDisponibles.size()==0) return null;
		
		if(preguntesDisponibles.size()==1)
		{
			return preguntesDisponibles.get(0);
		}
		else
		{
			Random random = new Random();
			int randomNumber = random.nextInt(preguntesDisponibles.size());
			CalcularPreguntesDisponibles();
			return preguntesDisponibles.get(randomNumber);
		}
	}
	
	private static void llegirFitxerTotal() throws SAXException, IOException
	{
		System.out.println("Començant lectura del XML de totes les preguntes");
		try
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File("preguntas.xml"));
			NodeList nodeList = document.getElementsByTagName("pregunta");
			
			for(int i=0; i<nodeList.getLength(); i++)
			{
				Node node = nodeList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE)
				{
					Element element = (Element)node;
					totesLesPreguntes.add(new Pregunta
					(
						getNode("texto", element),
						new String[]
						{
								element.getElementsByTagName("correcta").item(0).getTextContent(),
								element.getElementsByTagName("incorrecta").item(0).getTextContent(),
								element.getElementsByTagName("incorrecta").item(1).getTextContent(),
								element.getElementsByTagName("incorrecta").item(2).getTextContent()
						},
						element.getElementsByTagName("correcta").item(0).getTextContent()
					));
				}
			}
		}
		catch(ParserConfigurationException | FileNotFoundException exception)
		{
			exception.printStackTrace();
		}
		
		System.out.println("Lectura del XML de totes les preguntes acabada");
		System.out.println("totesLesPreguntes.size() = " + totesLesPreguntes.size());
	}
	
	@SuppressWarnings({ "SameParameterValue", "RedundantCast" })
	static String getNode(String etiqueta, Element element)
	{
		NodeList nodeList = element.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node node = (Node)nodeList.item(0);
		return node.getNodeValue();
	}
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	private static void llegirFitxerJaFet() throws SAXException, IOException
	{
		File file = new File("preguntas-repe.xml");
		if(!file.exists()) try
		{
			file.createNewFile();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Començant lectura del XML de preguntes fetes");
		if(file.length()!=0) try
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File("preguntas-repe.xml"));
			NodeList nodeList = document.getElementsByTagName("pregunta");
			
			for(int i=0; i<nodeList.getLength(); i++)
			{
				Node node = nodeList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE)
				{
					Element element = (Element)node;
					preguntesJaFetes.add(new Pregunta
					(
						getNode("texto", element),
						new String[]
						{
							element.getElementsByTagName("correcta").item(0).getTextContent(),
							element.getElementsByTagName("incorrecta").item(0).getTextContent(),
							element.getElementsByTagName("incorrecta").item(1).getTextContent(),
							element.getElementsByTagName("incorrecta").item(2).getTextContent()
						},
						element.getElementsByTagName("correcta").item(0).getTextContent()
					));
				}
			}
		}
		catch(ParserConfigurationException | FileNotFoundException exception)
		{
			exception.printStackTrace();
		}
		System.out.println("Lectura del XML de preguntes fetes");
		System.out.println("preguntesJaFetes.size() = " + preguntesJaFetes.size());
	}
}
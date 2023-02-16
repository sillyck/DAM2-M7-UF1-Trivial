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
@SuppressWarnings("SpellCheckingInspection")
public class QuestionBank
{
	/**
	 * Em penso que aixo no funciona, aixi que millor no tocar-ho...
	 * <p>Pero en el cas de que funcionés, en posar aixo a <tt>false</tt> hauría
	 * d'ignorar les preguntes ja contestades i començar cada nova finestra amb totes les preguntes disponibles.
	 */
	private static final boolean CARGAR_PREGUNTES_JA_FETES = true;
	
	/**
	 * Un ArrayList amb totes les preguntes de l'XML <tt>preguntas.xml</tt>.
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
	
	/**
	 *<b>Aquesta funció s'ha d'executar abans d'executar-ne qualsevol altra d'aquesta classe.</b>
	 * Funciona com un "constructor". Initialitza les variables i ho prepara tot.
	 *
	 * @throws IOException
	 * @throws SAXException
	 */
	@SuppressWarnings({ "javadoc" })
	public static void Start() throws IOException, SAXException
	{
		totesLesPreguntes = new ArrayList<>();
		preguntesJaFetes = new ArrayList<>();
		preguntesDisponibles = new ArrayList<>();
		
		llegirFitxerTotal();
		if(CARGAR_PREGUNTES_JA_FETES) llegirFitxerJaFet();
		
		CalcularPreguntesDisponibles();
	}
	
	/**
	 * Funció que calcula quines preguntes poden sortir en base a quines ja han sortit;
	 * i col·loca les preguntes disponibles a l'ArrayList {@link #preguntesDisponibles}.
	 */
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
		System.out.println("Calcul de preguntes possibles acabat\n\tpreguntesDisponibles.size() = " + preguntesDisponibles.size());
	}
	
	/**
	 * Obté una pregunta aleatoria des de {@link #preguntesDisponibles} i la retorna.
	 *
	 * @param marcarComJaFeta Si es <tt>true</tt> la pregunta s'apuntará a {@link #preguntesJaFetes} perque no torni a sortir.
	 * @return Un objecte de classe Pregunta amb tota la informació de la pregunta escollida.
	 */
	@SuppressWarnings("unused")
	public static Pregunta ObtindrePregunta(boolean marcarComJaFeta)
	{
		if(preguntesDisponibles==null || preguntesDisponibles.size()==0) return null;
		if(preguntesDisponibles.size()==1) return preguntesDisponibles.get(0);
		else
		{
			int randomNumber = new Random().nextInt(preguntesDisponibles.size());
			CalcularPreguntesDisponibles();
			return preguntesDisponibles.get(randomNumber);
		}
	}
	
	/**
	 * Llegeix l'XML <tt>preguntas.xml</tt> utilitzant DOM.
	 *
	 * @throws SAXException
	 * @throws IOException
	 */
	@SuppressWarnings("javadoc")
	private static void llegirFitxerTotal() throws SAXException, IOException
	{
		System.out.println("Començant lectura del XML de totes les preguntes");
		try
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File("preguntas.xml"));
			LlegirNodesDelXml(document, totesLesPreguntes);
		}
		catch(ParserConfigurationException | FileNotFoundException exception)
		{
			exception.printStackTrace();
		}
		System.out.println("Lectura del XML de totes les preguntes acabada\n\ttotesLesPreguntes.size() = " + totesLesPreguntes.size());
	}
	
	/**
	 * @param etiqueta L'etiqueta
	 * @param element L'element
	 * @return El node en format String
	 */
	@SuppressWarnings({ "SameParameterValue" })
	private static String getNode(String etiqueta, Element element)
	{
		return element.getElementsByTagName(etiqueta).item(0).getChildNodes().item(0).getNodeValue();
	}
	
	/**
	 * Llegeix l'XML <tt>preguntas-repe.xml</tt> utilitzant DOM.
	 *
	 * @throws SAXException
	 * @throws IOException
	 */
	@SuppressWarnings({ "ResultOfMethodCallIgnored", "javadoc" })
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
			LlegirNodesDelXml(document, preguntesJaFetes);
		}
		catch(ParserConfigurationException | FileNotFoundException exception)
		{
			exception.printStackTrace();
		}
		System.out.println("Lectura del XML de preguntes fetes\n\tpreguntesJaFetes.size() = " + preguntesJaFetes.size());
	}
	
	private static void LlegirNodesDelXml(Document document, List<Pregunta> totesLesPreguntes)
	{
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
}
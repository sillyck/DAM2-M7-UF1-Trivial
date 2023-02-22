import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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
	 * Un ArrayList amb les preguntes del fitxer <tt>preguntas-repe.dat</tt>.
	 */
	private static List<Pregunta> preguntesJaFetes;
	
	/**
	 * <b>Aquesta funció s'ha d'executar abans d'executar-ne qualsevol altra d'aquesta classe.</b>
	 * <p>Funciona com un "constructor". Initialitza les variables i ho prepara tot.
	 *
	 * @throws IOException
	 * @throws SAXException
	 */
	@SuppressWarnings({ "javadoc" })
	public static void Start() throws IOException, SAXException
	{
		System.out.println("Inicialitzant QuestionBank...");
		ZonedDateTime questionBankStart = ZonedDateTime.now();
		
		totesLesPreguntes = new ArrayList<>();
		preguntesJaFetes = new ArrayList<>();
		preguntesDisponibles = new ArrayList<>();
		
		llegirFitxerTotal();
		if(!CARGAR_PREGUNTES_JA_FETES) RecargarPreguntes();
//		LecturaXml();
		CalcularPreguntesDisponibles();
		System.out.println("Inicialitzant QuestionBank... OK en "+questionBankStart.until(ZonedDateTime.now(), ChronoUnit.MILLIS)+"ms");
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
		if(!HiHanPreguntesDisponibles()) RecargarPreguntes();
	}
	
	/**
	 * Obté una pregunta aleatoria des de {@link #preguntesDisponibles} i la retorna.
	 *
	 * @param marcarComJaFeta Si es <tt>true</tt> la pregunta s'apuntará a {@link #preguntesJaFetes} perque no torni a sortir.
	 * @return Un objecte de classe Pregunta amb tota la informació de la pregunta escollida.
	 */
	@SuppressWarnings("unused")
	public static Pregunta ObtindrePregunta(boolean marcarComJaFeta) throws IOException, ClassNotFoundException
	{
		if(preguntesDisponibles.size()==1 || preguntesDisponibles.isEmpty())
		{
			Pregunta ultimaPregunta = preguntesDisponibles.get(0);
			CalcularPreguntesDisponibles();
			return ultimaPregunta;
		}
		else
		{
			int randomNumber = (new Random().nextInt(preguntesDisponibles.size()));
			if(randomNumber==preguntesDisponibles.size() || randomNumber==(preguntesDisponibles.size()+1)) randomNumber--;
			System.out.println("He triat la pregunta aleatoria número "+randomNumber);
			CalcularPreguntesDisponibles();
			if(marcarComJaFeta) MarcarPreguntaComUtilitzada(randomNumber);
			
			CalcularPreguntesDisponibles();
			return preguntesDisponibles.get(randomNumber);
		}
	}
	
	public static void MarcarPreguntaComUtilitzada(int numPreguntaEscollida) throws IOException
	{
		WriteRandomJaFet(preguntesDisponibles.get(numPreguntaEscollida));
	}
	
	/**
	 * Retorna un boolean true o false segons si encara queden preguntes disponibles
	 * @return true si encara queden preguntes; false si no.
	 */
	public static boolean HiHanPreguntesDisponibles()
	{
		return preguntesDisponibles.size()!=0;
	}
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void RecargarPreguntes()
	{
		System.out.println("Recargant preguntes...");
		File file = new File("preguntas-repe.xml");
		if(file.exists())
		{
			file.delete();
		}
		if(!file.exists()) try
		{
			file.createNewFile();
			System.out.println("Fitxer de preguntes repetides recargat correctament");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		CalcularPreguntesDisponibles();
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
	@SuppressWarnings({ "ResultOfMethodCallIgnored", "javadoc", "unused" })
	private static void llegirFitxerJaFet() throws SAXException, IOException
	{
		File file = new File("preguntas-repe.xml");
		if(!file.exists()) try
		{
			file.createNewFile();
		}
		catch(IOException e)
		{
			System.out.println("Error!\n\t------\nEl fitxer preguntas-repe.xml no existeix, pero no l'he pogut crear");
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
	
	/**
	 * Llegeix els nodes des del document XML especificat i afegeix els nodes a la llista especificada.
	 *
	 * @param document El Document de l'XML des del cual es vulgui llegir.
	 * @param totesLesPreguntes La llista a on es vulguin guardar els nodes que es llegeixin.
	 */
	private static void LlegirNodesDelXml(Document document, List<Pregunta> totesLesPreguntes)
	{
		NodeList nodeList = document.getElementsByTagName("pregunta");
		
		for(int i=0; i<nodeList.getLength(); i++) if(nodeList.item(i).getNodeType()==Node.ELEMENT_NODE)
		{
			Element element = (Element)nodeList.item(i);
			totesLesPreguntes.add(new Pregunta
			(
				getNode("texto", (Element)nodeList.item(i)),
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
	
	public static void PrintStatsForNerds()
	{
		System.out.println("Stats desde QuestionBank:");
		System.out.println("\ttotesLesPreguntes.size()    = " + totesLesPreguntes.size());
		System.out.println("\tpreguntesDisponibles.size() = " + preguntesDisponibles.size());
		System.out.println("\tpreguntesJaFetes.size()     = " + preguntesJaFetes.size()+"\n");
	}
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	private static void WriteRandomJaFet(Pregunta preguntaJaFeta) throws IOException
	{
		if(!new File("preguntas-repe.dat").exists()) new File("preguntas-repe.dat").createNewFile();
		preguntesJaFetes.add(preguntaJaFeta);
		List<Pregunta> contingut = preguntesJaFetes;
		try
		{
			FileOutputStream fileOut = new FileOutputStream("preguntas-repe.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(contingut);
			out.close();
			fileOut.close();
		}
		catch(Exception e)
		{
			System.out.println("Error!\n------\n\tNo s'ha pogut escriure la pregunta actual al fitxer de preguntes repetides.");
			System.out.println("\tLa pregunta repetida es guardará sols per l'instancia actual, pero no per a les següents");
		}
	}
}
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionBank
{
	private static final boolean CARGAR_PREGUNTES_JA_FETES = true;
	private static List<Pregunta> totesLesPreguntes;
	private static List<Pregunta> preguntesDisponibles;
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
//		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
//		QuestionXmlSaxReader questionXmlSaxReader = new QuestionXmlSaxReader();
//		xmlReader.setContentHandler(questionXmlSaxReader);
//		InputSource inputSource = new InputSource("preguntas.xml");
//		xmlReader.parse(inputSource);
		System.out.println("Començant lectura del XML de totes les preguntes");
		try
		{
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
//						System.out.println("ID: " + getNode("id", element)+ "\tDNI: " + getNode("DNI", element)
//								+ "\tNom: " + getNode("nom", element)+ "\tCognom: " + getNode("cognom", element)
//								+ "\tEdat: " + getNode("edat", element)+ "\tSalari: " + getNode("salari", element));
						totesLesPreguntes.add
						(
							new Pregunta
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
							)
						);
					}
				}
			}
			catch(ParserConfigurationException parserConfigurationException)
			{
				System.out.println(parserConfigurationException);
			}
		}
		catch(FileNotFoundException fileNotFoundException)
		{
			System.out.println(fileNotFoundException);
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
	
	private static void llegirFitxerJaFet() throws SAXException, IOException
	{
//		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
//		QuestionXmlSaxReader questionXmlSaxReader = new QuestionXmlSaxReader();
//		xmlReader.setContentHandler(questionXmlSaxReader);
//		InputSource inputSource = new InputSource("preguntas-repe.xml");
//		xmlReader.parse(inputSource);
		
		File file = new File("preguntas-repe.xml");
		
		if(file.exists())
		{
//			System.out.println("The file exists.");
		}
		else
		{
			try
			{
				file.createNewFile();
//				System.out.println("The file was created.");
			}
			catch(IOException e)
			{
//				System.out.println("An error occurred while creating the file.");
				e.printStackTrace();
			}
		}
		System.out.println("Començant lectura del XML de preguntes fetes");
//		if(file.get)
		if(file.length()!=0) try
		{
			try
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
//						System.out.println("ID: " + getNode("id", element)+ "\tDNI: " + getNode("DNI", element)
//								+ "\tNom: " + getNode("nom", element)+ "\tCognom: " + getNode("cognom", element)
//								+ "\tEdat: " + getNode("edat", element)+ "\tSalari: " + getNode("salari", element));
						preguntesJaFetes.add
						(
							new Pregunta
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
							)
						);
					}
				}
			}
			catch(ParserConfigurationException parserConfigurationException)
			{
				System.out.println(parserConfigurationException);
			}
		}
		catch(FileNotFoundException fileNotFoundException)
		{
			System.out.println(fileNotFoundException);
		}
		System.out.println("Lectura del XML de preguntes fetes");
		System.out.println("preguntesJaFetes.size() = " + preguntesJaFetes.size());
	}
}
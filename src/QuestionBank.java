import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
		if(!CARGAR_PREGUNTES_JA_FETES) RecargarPreguntes();
		
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
		if(!HiHanPreguntesDisponibles()) RecargarPreguntes();
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
		if(preguntesDisponibles.size()==1)
		{
			Pregunta ultimaPregunta = preguntesDisponibles.get(0);
			CalcularPreguntesDisponibles();
			return ultimaPregunta;
		}
		else
		{
			int randomNumber = new Random().nextInt(preguntesDisponibles.size());
			CalcularPreguntesDisponibles();
			if(marcarComJaFeta) MarcarPreguntaComUtilitzada(randomNumber);
			return preguntesDisponibles.get(randomNumber);
		}
	}
	
	public static void MarcarPreguntaComUtilitzada(int numPreguntaEscollida)
	{
	
	}
	
	public static boolean HiHanPreguntesDisponibles()
	{
		if(preguntesDisponibles.size()==0)
//		if(preguntesDisponibles.size()==0 && preguntesJaFetes.size()==totesLesPreguntes.size())
		{
			return false;
		}
		else return true;
	}
	
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
	
	private static void EsciureXmlPreguntesRepetides(Pregunta pregunta)
	{
//		try
//		{
//			RandomAccessFile randomAccessFile = new RandomAccessFile("Treballadors.dat", "r");
//			randomAccessFile.seek(randomAccessFile.length());
//
//			int id = 0, posicio = 0;        // 4 bits
//			char[] dni = new char[9];       // 20 bits
//			char[] nom = new char[10];      // 20 bits
//			char[] cognom = new char[10];   // 20 bits
//			int edats;                      // 4 bits
//			double salaris;                 // 8 bits
//
//			String dnis, noms, cognoms;
//			char charDni, charNom, charCognom;
//
//			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//
//			try
//			{
//				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//				DOMImplementation domImplementation = documentBuilder.getDOMImplementation();
//				Document document = (Document)domImplementation.createDocument(null, "preguntas", null);
//				document.setXmlVersion("1.0");
//
//				for(;;)
//				{
//					randomAccessFile.seek(posicio);
//					id = randomAccessFile.readInt();
//
//					for(int i=0; i<dni.length; i++)
//					{
//						charDni = randomAccessFile.readChar();
//						dni[i] = charDni;
//					}
//					dnis = new String(dni);
//
//					for(int i=0; i<nom.length; i++)
//					{
//						charNom = randomAccessFile.readChar();
//						nom[i] = charNom;
//					}
//					noms = new String(nom);
//
//					for(int i=0; i<cognom.length;i++)
//					{
//						charCognom = randomAccessFile.readChar();
//						cognom[i] = charCognom;
//					}
//					cognoms = new String(cognom);
//
//					edats = randomAccessFile.readInt();
//					salaris = randomAccessFile.readDouble();
//					if(id>0)
//					{
//						Element raiz = document.createElement("Persona");
//						document.getDocumentElement().appendChild(raiz);
//
//						CrearElement("id", Integer.toString(id), raiz, document);
//						CrearElement("DNI", dnis.trim(), raiz, document);
//						CrearElement("nom", noms.trim(), raiz, document);
//						CrearElement("cognom", cognoms.trim(), raiz, document);
//						CrearElement("edat", Integer.toString(edats), raiz, document);
//						CrearElement("salari", Double.toString(salaris), raiz, document);
//					}
//					posicio += 74;
//
//					if(randomAccessFile.getFilePointer()==randomAccessFile.length()) break;
//				}
//				Source source = new DOMSource(document);
//				Result result = new StreamResult(new java.io.File("Treballadors.xml"));
//				Transformer transformer = TransformerFactory.newInstance().newTransformer();
//				transformer.transform(source, result);
//			}
//			catch(ParserConfigurationException e)
//			{
//				e.printStackTrace();
//			}
//			catch(IOException e)
//			{
//				e.printStackTrace();
//			}
//			catch(TransformerConfigurationException e)
//			{
//				e.printStackTrace();
//			}
//			catch(TransformerException e)
//			{
//				e.printStackTrace();
//			}
//			finally
//			{
//				try
//				{
//					randomAccessFile.close();
//				}
//				catch(IOException e)
//				{
//					e.printStackTrace();
//				}
//			}
//		}
//		catch(FileNotFoundException e)
//		{
//			e.printStackTrace();
//		}
		try
		{
//			RandomAccessFile randomAccessFile = new RandomAccessFile("Treballadors.dat", "r");
//			randomAccessFile.seek(randomAccessFile.length());
//
//			int id = 0, posicio = 0;        // 4 bits
//			char[] dni = new char[9];       // 20 bits
//			char[] nom = new char[10];      // 20 bits
//			char[] cognom = new char[10];   // 20 bits
//			int edats;                      // 4 bits
//			double salaris;                 // 8 bits
//
//			String dnis, noms, cognoms;
//			char charDni, charNom, charCognom;
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			try
			{
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				DOMImplementation domImplementation = documentBuilder.getDOMImplementation();
				Document document = (Document)domImplementation.createDocument(null, "preguntas", null);
				document.setXmlVersion("1.0");
				
				for(;;)
				{
//					randomAccessFile.seek(posicio);
//					id = randomAccessFile.readInt();
//
//					for(int i=0; i<dni.length; i++)
//					{
//						charDni = randomAccessFile.readChar();
//						dni[i] = charDni;
//					}
//					dnis = new String(dni);
//
//					for(int i=0; i<nom.length; i++)
//					{
//						charNom = randomAccessFile.readChar();
//						nom[i] = charNom;
//					}
//					noms = new String(nom);
//
//					for(int i=0; i<cognom.length;i++)
//					{
//						charCognom = randomAccessFile.readChar();
//						cognom[i] = charCognom;
//					}
//					cognoms = new String(cognom);
//
//					edats = randomAccessFile.readInt();
//					salaris = randomAccessFile.readDouble();
//					if(id>0)
//					{
//						Element raiz = document.createElement("pregunta");
//						document.getDocumentElement().appendChild(raiz);
//
//						CrearElement("id", Integer.toString(id), raiz, document);
//						CrearElement("DNI", dnis.trim(), raiz, document);
//						CrearElement("nom", noms.trim(), raiz, document);
//						CrearElement("cognom", cognoms.trim(), raiz, document);
//						CrearElement("edat", Integer.toString(edats), raiz, document);
//						CrearElement("salari", Double.toString(salaris), raiz, document);
//					}
					Element raiz = document.createElement("pregunta");
					document.getDocumentElement().appendChild(raiz);
					
					CrearElement("texto", pregunta.enunciat, raiz, document);
					Element elementPregunta = document.createElement("respuestas");
					
					CrearElement("correcta", pregunta.respostaCorrecta, elementPregunta, document);
					CrearElement("incorrecta", pregunta.respostes[1], elementPregunta, document);
					CrearElement("incorrecta", pregunta.respostes[2], elementPregunta, document);
					CrearElement("incorrecta", pregunta.respostes[3], elementPregunta, document);
					
//					posicio += 74;
//					if(randomAccessFile.getFilePointer()==randomAccessFile.length()) break;
				}
				Source source = new DOMSource(document);
				Result result = new StreamResult(new java.io.File("Treballadors.xml"));
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.transform(source, result);
			}
			catch(ParserConfigurationException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			catch(TransformerException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
//					randomAccessFile.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void CrearElement(String dades, String valor, Element arrel, Document document)
	{
		Element element = document.createElement(dades);
		Text text = document.createTextNode(valor);
		arrel.appendChild(element);
		element.appendChild(text);
	}
}
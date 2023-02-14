import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tauler extends JFrame
{
	/**
	 * Número que indica la ronda actual. Una ronda passa quan els dos jugadors han tingut els dos una oportunitat de moviment.
	 */
	public int round = 0;
	
	/**
	 * A qui li toca tirar?
	 * <li>0 = principi del joc; valor inicial</li>
	 * <li>1 = torn del jugador 1</li>
	 * <li>2 = torn del jugador 2</li>
	 * <li>3 = final de ronda, preparatius per començar una nova ronda</li>
	 */
	public int currentTurn = 0;
	
	private int activePlayerCell = 0;
	
	/**
	 * Simple array de dos llocs d'Strings que conte els noms del jugadors.
	 * <li><tt>playerName[0]</tt> es el nom del jugador 1.</li>
	 * <li><tt>playerName[1]</tt> es el nom del jugador 2.</li>
	 */
	@SuppressWarnings("CStyleArrayDeclaration")
	public String playerName[] = new String[2];
	
	/**
	 * Simple array de dos llocs d'ints que conte les puntuacions dels jugadors.
	 *
	 * <p>"Puntuacions" num "posicions" son sinonims per referir-se a aquesta variable.
	 * <li><tt>score[0]</tt> es el jugador 1.</li>
	 * <li><tt>score[1]</tt> es el jugador 2.</li>
	 */
	@SuppressWarnings("CStyleArrayDeclaration")
	public int score[] = new int[2];
	
	/**
	 * Array de JLabels (amb ImageIcons dins, que es lo que importa) que guarden les imatges que es mostraran en el GridLayout del tauler.
	 *
	 * <p>Els valors d'entre 0 num 7 son per la fila superior (jugador 1) num valors d'entre 8 num 15 son per la fila inferior (jugador 2).
	 */
	@SuppressWarnings("CStyleArrayDeclaration")
	public JLabel images[] = new JLabel[16];
	
	/**
	 * Una HasList que inclou imatges ja preparades num precuinades per a un rapid canvi
	 */
	@Deprecated
	private Map<String,ImageIcon> readyImage;
	
	/**
	 * Una HashList que inclou les rutes de les imatges.
	 */
	private Map<String,String> pathCollection;
	
	
	public Tauler() throws IOException
	{
		new Tauler("Jugador 1", "Jugador 2");
	}
	
	public Tauler(String player1, String player2) throws IOException
	{
		super("Trivial");
		setSize(1500, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPlayerNames(player1, player2);
		
		pathCollection = new HashMap<>();
		pathCollection.put("taulerClar",           System.getProperty("user.dir")+"/res/taulerClar.png");
		pathCollection.put("taulerFosc",           System.getProperty("user.dir")+"/res/taulerFosc.png");
		pathCollection.put("taulerClar-jugador50", System.getProperty("user.dir")+"/res/taulerClar-jugador50.png");
		pathCollection.put("taulerFosc-jugador50", System.getProperty("user.dir")+"/res/taulerFosc-jugador50.png");
		pathCollection.put("taulerClar-jugador95", System.getProperty("user.dir")+"/res/taulerClar-jugador95.png");
		pathCollection.put("taulerFosc-jugador95", System.getProperty("user.dir")+"/res/taulerFosc-jugador95.png");
		pathCollection.put("taulerClar-jugador100",System.getProperty("user.dir")+"/res/taulerClar-jugador100.png");
		pathCollection.put("taulerFosc-jugador100",System.getProperty("user.dir")+"/res/taulerFosc-jugador100.png");
		pathCollection.put("taulerClar-bandera50", System.getProperty("user.dir")+"/res/taulerClar-bandera50.png");
		pathCollection.put("taulerFosc-bandera50", System.getProperty("user.dir")+"/res/taulerFosc-bandera50.png");
		pathCollection.put("taulerClar-bandera95", System.getProperty("user.dir")+"/res/taulerClar-bandera95.png");
		pathCollection.put("taulerFosc-bandera95", System.getProperty("user.dir")+"/res/taulerFosc-bandera95.png");
		pathCollection.put("taulerClar-bandera100",System.getProperty("user.dir")+"/res/taulerClar-bandera100.png");
		pathCollection.put("taulerFosc-bandera100",System.getProperty("user.dir")+"/res/taulerFosc-bandera100.png");
		
		readyImage = new HashMap<>();
		readyImage.put("taulerClar",           new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerClar"))),175,175)));
		readyImage.put("taulerFosc",           new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerFosc"))),175,175)));
		readyImage.put("taulerClar-jugador50", new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerClar-jugador50"))),175,175)));
		readyImage.put("taulerFosc-jugador50", new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerFosc-jugador50"))),175,175)));
		readyImage.put("taulerClar-jugador95", new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerClar-jugador95"))),175,175)));
		readyImage.put("taulerFosc-jugador95", new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerFosc-jugador95"))),175,175)));
		readyImage.put("taulerClar-jugador100",new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerClar-jugador100"))),175,175)));
		readyImage.put("taulerFosc-jugador100",new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerFosc-jugador100"))),175,175)));
		readyImage.put("taulerClar-bandera50", new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerClar-bandera50"))),175,175)));
		readyImage.put("taulerFosc-bandera50", new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerFosc-bandera50"))),175,175)));
		readyImage.put("taulerClar-bandera95", new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerClar-bandera95"))),175,175)));
		readyImage.put("taulerFosc-bandera95", new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerFosc-bandera95"))),175,175)));
		readyImage.put("taulerClar-bandera100",new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerClar-bandera100"))),175,175)));
		readyImage.put("taulerFosc-bandera100",new ImageIcon(Utils.resize(ImageIO.read(new File(pathCollection.get("taulerFosc-bandera100"))),175,175)));
		
		score[0] = 0;
		score[1] = 0;
		firstPaintTiles();
		paintColoursTiles();
		paintPlayerPositions();
		
		ConstruirUI();
		
		pack();
		setLocationRelativeTo(null);
		
		toggleImage();
	}
	
	/**
	 * Posa els noms dels jugadors.
	 *
	 * @param player1 Nom per el primer jugador (fila de dalt)
	 * @param player2 Nom per el jugador dos (fila de baix)
	 */
	public void setPlayerNames(String player1, String player2)
	{
		playerName = new String[] { player1, player2 };
	}
	
	/**
	 * Metode que s'encarra de construir el la majoria de la finestra de dialeg.
	 */
	public void ConstruirUI()
	{
		Container c = getContentPane();
		c.setBackground(Color.decode("#F9F5E7"));
		BorderLayout borderLayout = new BorderLayout();
		c.setLayout(borderLayout);
		
		Container centerContainer = new Container();
		GridLayout gridLayout = new GridLayout(2, 8);
		centerContainer.setLayout(gridLayout);
		for(int i=0; i<images.length; i++) centerContainer.add(images[i]);
		c.add(centerContainer, BorderLayout.CENTER);
		
		FlowLayout topLayout = new FlowLayout();
		Container topContainer = new Container();
		topContainer.setLayout(topLayout);
		JLabel jLabel = new JLabel("\nTorn de: {nom_jugador}\n");
		jLabel.setFont(new Font("Tahoma",Font.BOLD, 32));
		Border jLabelBorder = jLabel.getBorder();
		Border jLabelMargin = new EmptyBorder(10,10,10,10);
		jLabel.setBorder(new CompoundBorder(jLabelBorder, jLabelMargin));
		
		topContainer.add(jLabel);
		c.add(topContainer, BorderLayout.NORTH);
		
		FlowLayout bottomLayout = new FlowLayout();
		Container bottomContainer = new Container();
		bottomContainer.setLayout(bottomLayout);
		JButton jButton = new JButton("Continuar");
		jButton.setBackground(new Color(167,114,125));
		jButton.setForeground(new Color(255,255,255));
		jButton.setFocusPainted(false);
		jButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		Border jButtonBorder = jButton.getBorder();
		Border jButtonMargin = new EmptyBorder(10,10,10,10);
		jButton.setBorder(new CompoundBorder(jButtonBorder, jButtonMargin));
		
		JButton j1m = new JButton("DBG1-");
		j1m.addActionListener(new TaulerDebugP1minus());
		
		JButton j1p = new JButton("DBG1+");
		j1p.addActionListener(new TaulerDebugP1plus());
		
		JButton j2m = new JButton("DBG2-");
		j2m.addActionListener(new TaulerDebugP2minus());
		
		JButton j2p = new JButton("DBG2+");
		j2p.addActionListener(new TaulerDebugP2plus());
		
		JButton j0 = new JButton("DBG0");
		j0.addActionListener(new TaulerDebugAllPos(0));
		
		JButton j6 = new JButton("DBG6");
		j6.addActionListener(new TaulerDebugAllPos(6));
		
		JButton j7 = new JButton("DBG7");
		j7.addActionListener(new TaulerDebugAllPos(7));
		
//		JLabel l1 = new JLabel();
//		JLabel l2 = new JLabel();
		JLabel ls = new JLabel("· Res");
		
		bottomContainer.add(j0);
		bottomContainer.add(j6);
		bottomContainer.add(j7);
		bottomContainer.add(j1m);
		bottomContainer.add(j1p);
		bottomContainer.add(j2m);
		bottomContainer.add(j2p);
		bottomContainer.add(jButton);
//		bottomContainer.add(l1);
//		bottomContainer.add(l2);
		bottomContainer.add(ls);
		c.add(bottomContainer, BorderLayout.SOUTH);
	}
	
	/**
	 * Pinta tot el tauler per primera vegada.
	 *
	 * <p>Es important que aquest metode s'executi abans de la primera crida a {@link #paintColoursTiles()}.
	 * Perque el que fa {@link #paintColoursTiles()} es actualitzar la imatge d'ImageIcon(s) ja existents;
	 * aquest metode els crea per primera vegada.
	 *
	 * @throws IOException
	 */
	public void firstPaintTiles() throws IOException
	{
		for(int i=0; i<16; i++)
		{
			BufferedImage bufferedImage;
			bufferedImage = i % 2==0
					? ImageIO.read(new File(pathCollection.get("taulerClar")))
					: ImageIO.read(new File(pathCollection.get("taulerFosc")));
			images[i] = new JLabel(new ImageIcon(Utils.resize(bufferedImage, 175, 175)));
		}
	}
	
	/**
	 * Pinta els colors clars num foscos del tauler num les banderes a les ultimes caselles.
	 *
	 * <p>Aqui no es pinten els jugadores, aixo ho fa el metode {@link #paintPlayerPositions()}.
	 *
	 * <p>El que fem aqui, es passar casella a casella del array {@link #images}; en les caselles 7 num 15 (les ultimes
	 * columnes de cada fila), pintem una bandera fosca directament. Per les altres caselles, mirem si el numero es
	 * parell o senar num pintem de color clar o fosc, segons correspongui.
	 *
	 * <p>Es important concretar de que l'array ha d'estar initialitzat en aquest punt (no ha d'estar null).
	 * Per aixo, en el constructor de la classe, s'executa el metode {@link #firstPaintTiles()} que es similar a aquest
	 * metode, pero creant nous objectes JLabel num ImageIcons enlloc de reutilitzar els ja existeints.
	 *
	 * @throws IOException
	 */
	public void paintColoursTiles() throws IOException
	{
		for(int i=0; i<16; i++)
		{
			BufferedImage bufferedImage;
			switch(i)
			{
				case 7: case 15:
					bufferedImage = ImageIO.read(new File(pathCollection.get("taulerFosc-bandera50")));
					break;
				default:
					bufferedImage = i % 2==0
							? ImageIO.read(new File(pathCollection.get("taulerClar")))
							: ImageIO.read(new File(pathCollection.get("taulerFosc")));
					break;
			}
			images[i].setIcon(new ImageIcon(Utils.resize(bufferedImage,175,175)));
		}
	}
	
	/**
	 * Aquest metode dibuixa als jugadors a allá on els hi toqui. Basicament canvia la imatge ja existent per la del
	 * jugador.
	 *
	 * <p>El <tt>bufferedImage1</tt> es refereix al primer jugador (el de la fila de dalt), num el <tt>bufferedImage2</tt>
	 * al segon jugador (fila de baix).
	 *
	 * @throws IOException
	 */
	public void paintPlayerPositions() throws IOException
	{
		System.out.println("mainTesting.tauler.score[0] = " + score[0]);
		System.out.println("mainTesting.tauler.score[1] = " + score[1]);
		BufferedImage bufferedImage1;
		bufferedImage1 = score[0] % 2==0
				? ImageIO.read(new File(pathCollection.get("taulerClar-jugador95")))
				: ImageIO.read(new File(pathCollection.get("taulerFosc-jugador95")));
		images[score[0]].setIcon(new ImageIcon(Utils.resize(bufferedImage1,175,175)));
		
		BufferedImage bufferedImage2;
		bufferedImage2 = score[1] % 2==0
				? ImageIO.read(new File(pathCollection.get("taulerClar-jugador95")))
				: ImageIO.read(new File(pathCollection.get("taulerFosc-jugador95")));
		images[score[1]+8].setIcon(new ImageIcon(Utils.resize(bufferedImage2,175,175)));
	}
	
	/**
	 * Actualitza les posicions del jugador via puntuacions relatives.
	 *
	 * <p>En quan a "puntuació relativa" es refereix, es la diferencia de puntuació.
	 * Es a dir, una diferencia de "-1", significa que al jugador corresponent se'l-hi restará un punt,
	 * mentres que "1" sumará un punt.
	 *
	 * <p>En el correcte funcionament del joc, un jugador sols podrá avançar una casella o quedar-se quiet; mai podrá
	 * avançar mes d'1 casella en un sol torn o anar endarrere, no obstant aixo es possible de fer via els botons
	 * "debug", que vam utilitzar a mode de proves.
	 *
	 * @param newScoreP1 La diferencia de puntuació del jugador 1
	 * @param newScoreP2 La diferencia de puntuació del jugador 2
	 * @throws IOException
	 */
	public void updatePlayerScore(int newScoreP1, int newScoreP2) throws IOException
	{
		score[0] = score[0] + newScoreP1;
		score[1] = score[1] + newScoreP2;
		
		for(int i=0; i<score.length; i++)
		{
			if(score[i]<=0) score[i] = 0;
			if(score[i]>=7) score[i] = 7;
		}
		updatePlayerPosition();
	}
	
	/**
	 * Actualitza la posició dels jugadors (redibuixa el tauler amb els jugadors amb les posicions actuals).
	 *
	 * <p>En executar aquest metode, s'intueix que la nova posició dels jugadors (si es que ha canviat), ja s'ha
	 * modificat abans d'executar aquest metode.
	 *
	 * <p>De lo contrarí, si es que cal especificar una nova posició, cal utilitzar {@link #updatePlayerPosition(int, int)}
	 * per canvis de posicions absoluts o {@link #updatePlayerScore(int, int)} per a canvis relatius.
	 *
	 * @throws IOException
	 */
	public void updatePlayerPosition() throws IOException
	{
		updatePlayerPosition(score[0],score[1]);
	}
	
	/**
	 * Actualitza la posició dels jugadors (redibuixa el tauler amb els jugadors amb les posicions actuals).
	 *
	 * <p>Amb aquesta crida, es passen per parametres les noves posicions absolutes dels jugadors.
	 * Aquestes noves posicions reemplaçaran les posicions actuals.
	 *
	 * <p>Després de posar les variables; aquest metode executa els metodes {@link #paintColoursTiles()} num
	 * {@link #paintPlayerPositions()}, per tal de redibuixar el tauler amb els jugadores en les noves posicions.
	 *
	 * <p>Els metodes {@link #updatePlayerScore(int, int)} num {@link #updatePlayerPosition()} també criden internament a aqui.
	 *
	 * @param posP1 La nova posició absoluta del jugador 1
	 * @param posP2 La nova posició absoluta del jugador 2
	 * @throws IOException
	 */
	public void updatePlayerPosition(int posP1, int posP2) throws IOException
	{
		score[0] = posP1;
		score[1] = posP2;
		
		paintColoursTiles();
		paintPlayerPositions();
	}
	
	public void overwriteWithEmptyCell(int cellPos) throws IOException
	{
//		BufferedImage bufferedImage1;
//		bufferedImage1 = score[0] % 2==0
//				? ImageIO.read(new File(pathCollection.get("taulerClar-jugador95")))
//				: ImageIO.read(new File(pathCollection.get("taulerFosc-jugador95")));
//		images[score[0]].setIcon(new ImageIcon(Utils.resize(bufferedImage1,175,175)));
		
		BufferedImage bufferedImage;
		bufferedImage = /*images[*/cellPos/*]*/ % 2==0
				? ImageIO.read(new File(pathCollection.get("taulerClar")))
				: ImageIO.read(new File(pathCollection.get("taulerFosc")));
		images[cellPos].setIcon(new ImageIcon(Utils.resize(bufferedImage,175,175)));
	}
	
	public void hideImage()
	{
		SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
		{
			@Override
			protected Void doInBackground() throws Exception
			{
				// Wait for some time in the background
				Thread.sleep(3000);
				return null;
			}
			
			@Override
			protected void done()
			{
				// Remove the image from the label
//				label.setIcon(null);
			}
		};
		worker.execute();
	}
	
	public void toggleImage()
	{
		SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
		{
			int startI = activePlayerCell;
			
			@Override
			protected Void doInBackground() throws Exception
			{
				// Wait for some time in the background
//				Thread.sleep(3000);
				
				for(;;)
				{
					overwriteWithEmptyCell(startI);
					Thread.sleep(1000);
					if(startI!=activePlayerCell) break;
					
//					paintColoursTiles();
					paintPlayerPositions();
					Thread.sleep(500);
					if(startI!=activePlayerCell) break;
				}
				return null;
			}
			
			@Override
			protected void done()
			{
				// Remove the image from the label
//				label.setIcon(null);
			}
		};
		worker.execute();
	}
}
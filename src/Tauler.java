import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Tauler extends JFrame implements ActionListener
{
	/**
	 * Si és true, fará visibles botons i labels que han estat utils pel desenvolupament d'aquesta aplicació.
	 */
	@SuppressWarnings("FieldMayBeFinal")
	private boolean debugMode;
	
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
	 *
	 * <p>Teoricament, aquesta variable hauría de passar ben poca estona en valors 0 o 3; hauría d'estar gairabé tot el rato a 1 o 2.
	 */
	public int currentTurn = 0;
	
	/**
	 * Si es true, es que actualment el popup de preguntes esta obert; fals si no ho esta.
	 * <p>En estar a true, moltes interacions d'aquesta finestra no estaran disponibles.
	 */
	@SuppressWarnings("unused")
	private boolean inQuestion = false;
	
	/**
	 * <li>0 = Cap jugador pot guanyar inminentment</li>
	 * <li>1 = el jugador 1 pot guanyar-li al jugador 2</li>
	 * <li>2 = el jugador 2 pot guanyar-li al jugador 1</li>
	 * <li>3 = jugador 1 ha gunyat</li>
	 * <li>3 = jugador 2 ha gunyat</li>
	 * <li>3 = empat</li>
	 */
	public int winningCondition = 0;
	
	/**
	 * La posició de l'array {@link #images} a on fará efecte l'animació d'apareixer i desapareixer.
	 * <p>Quan el jugador actual sigui l'1, aquest valor estará entre 0 i 7, perque aquestes son les caselles de la fila superior, on es mou el primer jugador; el segon jugador tindrá valors d'entre 8 i 15.
	 */
	private int activePlayerCell = 0;
	
	/**
	 * Simple array de dos llocs d'Strings que conte els noms del jugadors.
	 * <li><tt>playerName[0]</tt> es el nom del jugador 1.</li>
	 * <li><tt>playerName[1]</tt> es el nom del jugador 2.</li>
	 */
	@SuppressWarnings("CStyleArrayDeclaration")
	public String playerName[] = new String[2];
	
	/**
	 * Simple array de dos llocs d'ints que conti les puntuacions dels jugadors.
	 *
	 * <p>"Puntuacions" núm "posicions" són sinònims per referir-se a aquesta variable.
	 * <li><tt>score[0]</tt> es el jugador 1.</li>
	 * <li><tt>score[1]</tt> es el jugador 2.</li>
	 */
	@SuppressWarnings("CStyleArrayDeclaration")
	public int score[] = new int[2];
	
	/**
	 * Array de JLabels (amb ImageIcons dins, que es el que importa) que guarden les imatges que es mostraran en el
	 * GridLayout del tauler.
	 *
	 * <p>Els valors d'entre 0 núm 7 són per la fila superior (jugador 1) núm valors d'entre 8 núm 15 són per la fila
	 * inferior (jugador 2).
	 */
	@SuppressWarnings("CStyleArrayDeclaration")
	public JLabel images[] = new JLabel[16];
	
	/**
	 * Una HashList que inclou imatges ja preparades num precuinades per a un rapid canvi
	 */
	@SuppressWarnings({ "FieldCanBeLocal", "DeprecatedIsStillUsed", "MismatchedQueryAndUpdateOfCollection" })
	@Deprecated
	private Map<String,ImageIcon> readyImage;
	
	/**
	 * Una HashList que inclou les rutes de les imatges.
	 */
	private Map<String,String> pathCollection;
	
	/**
	 * L'unic component de la interficie que està declarat com una variable no local de {@link #ConstruirUI()},
	 * això és perquè {@link #updateTitle()} hi pugui accedir i es pugui canviar el text de dins mes facilment.
	 */
	private JLabel jlabelTitle;
	
	private JLabel jlabelStatus;
	
	@SuppressWarnings("unused")
	public Tauler() throws IOException
	{
		new Tauler("Jugador 1", "Jugador 2", false);
		debugMode = false;
	}
	
	public Tauler(String player1, String player2, boolean debugMode) throws IOException
	{
		super("Trivial");
		
		System.out.println("Construïnt Tauler.java...");
		ZonedDateTime now = ZonedDateTime.now();
		this.debugMode = debugMode;
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
		
		advance();
		System.out.println("Construïnt Tauler.java... OK en "+now.until(ZonedDateTime.now(), ChronoUnit.MILLIS)+"ms");
	}
	
	/**
	 * Posa els noms dels jugadors.
	 *
	 * @param player1 Nom per el primer jugador (fila de dalt)
	 * @param player2 Nom pel jugador dos (fila de baix)
	 */
	public void setPlayerNames(String player1, String player2)
	{
		playerName = new String[] { player1, player2 };
	}
	
	/**
	 * Metode que s'encarrega de construir el la majoria de la finestra de dialeg.
	 */
	@SuppressWarnings("ForLoopReplaceableByForEach")
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
		jlabelTitle = new JLabel("\nRonda NaN\nTorn de: null\n");
		jlabelTitle.setFont(new Font("Tahoma",Font.BOLD, 32));
		Border jLabelBorder = jlabelTitle.getBorder();
		Border jLabelMargin = new EmptyBorder(10,10,10,10);
		jlabelTitle.setBorder(new CompoundBorder(jLabelBorder, jLabelMargin));
		
		topContainer.add(jlabelTitle);
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
		jButton.addActionListener(this);
		
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
		
		JButton japm = new JButton("DBG-APM");
		japm.addActionListener(new TaulerDebugAPM());
		
		JButton jadv = new JButton("DBG-ADV");
		jadv.addActionListener(new TaulerDebugAdvance());
		
		jlabelStatus = new JLabel("· Res");
		
		if(debugMode)
		{
			bottomContainer.add(j0);
			bottomContainer.add(j6);
			bottomContainer.add(j7);
			bottomContainer.add(j1m);
			bottomContainer.add(j1p);
			bottomContainer.add(j2m);
			bottomContainer.add(j2p);
			bottomContainer.add(japm);
			bottomContainer.add(jadv);
		}
		bottomContainer.add(jButton);
		bottomContainer.add(jlabelStatus);
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
	@SuppressWarnings("javadoc")
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
	@SuppressWarnings("javadoc")
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
	@SuppressWarnings("javadoc")
	public void paintPlayerPositions() throws IOException
	{
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
	@SuppressWarnings("javadoc")
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
	@SuppressWarnings("javadoc")
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
	@SuppressWarnings("javadoc")
	public void updatePlayerPosition(int posP1, int posP2) throws IOException
	{
		score[0] = posP1;
		score[1] = posP2;
		
		System.out.println("Nova posició dels jugadors:\n\tJ1: "+score[0]+"\n\tJ2: "+score[1]);
		
		paintColoursTiles();
		paintPlayerPositions();
	}
	
	/**
	 * Aquesta es la funció que "borra el contingut" d'una casella. En veritat el que fa es posar com a imatge de la
	 * cel·la una imatge buida, sense jugador ni bandera.
	 *
	 * @param cellPos Posició del array {@link #images} a on es "borrará" la casella. Valors d'entre 0-7 per la fila
	 *                superior i d'entre 8-15 per la fila inferior.
	 * @throws IOException
	 */
	@SuppressWarnings("javadoc")
	public void overwriteWithEmptyCell(int cellPos) throws IOException
	{
		BufferedImage bufferedImage;
		bufferedImage = cellPos % 2==0
				? ImageIO.read(new File(pathCollection.get("taulerClar")))
				: ImageIO.read(new File(pathCollection.get("taulerFosc")));
		images[cellPos].setIcon(new ImageIcon(Utils.resize(bufferedImage,175,175)));
	}
	
	/**
	 * Un metode que s'encarga de crear i executar un SwingWorker que fa que la imatge del jugador actual aparegui i desaparegui.
	 */
	private void toggleImage()
	{
		SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
		{
			/**
			 * Guarda quina es la posició del jugador actiu en el moment de començar l'aniamció actual de mostrar/amagar.
			 * <p>En el loop del SwingWorker ({@link #doInBackground()}), es comprovará si el valor acutal de
			 * activePlayerCell coincideix amb aquest integer. Si difereixen, es que el jugador actual ha canviat i/o
			 * s'ha mogut i es parará l'animacio.
			 */
			final int startPlayerCell = activePlayerCell;
			
			/**
			 * El "main loop" del SwingWorker. Activa i desactiva la imatge del jugador actual utilitzant
			 * uns timers un poc imprecicos.
			 *
			 * @return
			 * @throws Exception
			 */
			@SuppressWarnings({ "BusyWait", "javadoc" })
			@Override
			protected Void doInBackground() throws Exception
			{
				for(;;)
				{
					overwriteWithEmptyCell(startPlayerCell);
					Thread.sleep(1000);
					if(startPlayerCell!=activePlayerCell)
					{
						System.out.println("Un SwingWorker en progrés ha notat que el jugador actiu ha canviat o s'ha mogut.\n\t(startPlayerCell: "+startPlayerCell+" != activePlayerCell: "+activePlayerCell+")\n\tAcabant amb l'animació actual.");
						break;
					}
					paintPlayerPositions();
					Thread.sleep(500);
					if(startPlayerCell!=activePlayerCell)
					{
						System.out.println("Un SwingWorker en progrés ha notat que el jugador actiu ha canviat o s'ha mogut.\n\t(startPlayerCell: "+startPlayerCell+" != activePlayerCell: "+activePlayerCell+")\n\tAcabant amb l'animació actual.");
						break;
					}
				}
				return null;
			}
		};
		System.out.println("Iniciant un nou SwingWorker per l'animació del jugador actiu actual.\n\tstartPlayerCell: "+activePlayerCell);
		worker.execute();
	}
	
	/**
	 * Progresa en el loop del joc. Finalitza el torn d'un jugador i fa que l'altre jugador sigui l'actiu.
	 *
	 * @throws IOException
	 */
	@SuppressWarnings("javadoc")
	public void advance() throws IOException
	{
		activePlayerCell = -1;
		inQuestion = false;
		paintColoursTiles();
		paintPlayerPositions();
		if(currentTurn!=0) updateTitle();
		switch(currentTurn)
		{
			case 0: case 2: case 3:
				currentTurn = 1;
				round++;
				updateTitle();
				activePlayerCell = score[0];
				toggleImage();
				break;
			case 1:
				currentTurn = 2;
				updateTitle();
				activePlayerCell = score[1]+8;
				toggleImage();
				break;
		}
		checkWinningConditions();
	}
	
	/**
	 * Registra el resultat de la resposta actual, actualitza la puntuació si s'ha encertat i després executa un
	 * {@link #advance()} per progresar en el joc.
	 *
	 * @param correct Si la pregunta actual s'ha respós bé o no
	 * @throws IOException
	 */
	@SuppressWarnings({ "unused", "javadoc" })
	public void answer(boolean correct) throws IOException
	{
		// ====================================================
		//       AL TANCAR UNA FINESTRA DE PREGUNTA,
		//       AQUESTA ES LA FUNCIÓ QUE S'EXECUTA
		//
		//  El boolean correct es true si la pregunta s'ha
		//  encertat i fals si s'ha fallat
		// ====================================================
		inQuestion = false;
		if(correct && (currentTurn==1 || currentTurn==2)) score[currentTurn-1]++;
		advance();
	}
	
	/**
	 * Actualitza el text de {@link #jlabelTitle} amb la ronda actual i el nom del jugador acutal.
	 */
	public void updateTitle()
	{
		jlabelTitle.setText("Ronda "+round+"; torn de: "+playerName[currentTurn-1]);
	}
	
	/**
	 * Invoked when an action occurs.
	 *
	 * @param e
	 */
	@SuppressWarnings("javadoc")
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(!inQuestion)
		{
			inQuestion = true;
			// ====================================================
			//       POSAR AQUI LA CRIDA CAP A LES PREGUNTES
			// ====================================================
			
			
			
		}
	}
	
	@SuppressWarnings({ "UnusedAssignment", "ConstantValue", "CommentedOutCode" })
	private void checkWinningConditions()
	{
		if(score[0]==7)
		{
			if(winningCondition==0)
			{
				if(score[1]==6) winningCondition = 2;
				if(score[1]==7) winningCondition = 5;
				else            winningCondition = 3;
			}
		}
		if(score[1]==7)
		{
			if(winningCondition==0)
			{
				if(score[0]==6) winningCondition = 1;
				if(score[0]==7) winningCondition = 5;
				else            winningCondition = 4;
			}
		}
		
		switch(winningCondition)
		{
			case 0:	jlabelStatus.setText("· Joc en curs..."); break;
			case 1:	jlabelStatus.setText("· El jugador 1 guanyará si encerta aquesta pregunta, si falla guanyará el jugador 2"); break;
			case 2: jlabelStatus.setText("· El jugador 2 guanyará si encerta aquesta pregunta, si falla guanyará el jugador 1"); break;
			case 3: jlabelStatus.setText("· Victoria per al jugador 1"); break;
			case 4: jlabelStatus.setText("· Victoria per al jugador 2"); break;
			case 5: jlabelStatus.setText("· Empat"); break;
		}
		if(winningCondition==3 || winningCondition==4 || winningCondition==5)
		{
			PopupFinal popupFinal = null;
			switch(winningCondition)
			{
				case 3: popupFinal = new PopupFinal(playerName[0],playerName[1],FinalPopupAction.J1_WINS); break;
				case 4: popupFinal = new PopupFinal(playerName[0],playerName[1],FinalPopupAction.J2_WINS); break;
				case 5: popupFinal = new PopupFinal(playerName[0],playerName[1],FinalPopupAction.BOTH); break;
			}
			popupFinal.setVisible(true);
			dispose();
		}
	}
}
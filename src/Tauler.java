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
	@SuppressWarnings("CStyleArrayDeclaration")
	public String playerName[] = new String[2];
	
	@SuppressWarnings("CStyleArrayDeclaration")
	public int score[] = new int[2];
	
	@SuppressWarnings("CStyleArrayDeclaration")
	public JLabel images[] = new JLabel[16];
	
	private Map<String,Image> readyImage;
	
//	private String pathCollection[] = new String[0];
	private Map<String,String> pathCollection;
	
	public Tauler() throws IOException
	{
		new Tauler("Jugador 1", "Jugador 2");
	}
	
	public Tauler(String player1, String player2) throws IOException
	{
		super("Trivial");
		setSize(1500, 800);
//		Utils.scaleWindowDimensions(this, );
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
//		readyImage.put("taulerClar",           System.getProperty("user.dir")+"/res/taulerClar.png");
//		readyImage.put("taulerFosc",           System.getProperty("user.dir")+"/res/taulerFosc.png");
//		readyImage.put("taulerClar-jugador50", System.getProperty("user.dir")+"/res/taulerClar-jugador50.png");
//		readyImage.put("taulerFosc-jugador50", System.getProperty("user.dir")+"/res/taulerFosc-jugador50.png");
//		readyImage.put("taulerClar-jugador95", System.getProperty("user.dir")+"/res/taulerClar-jugador95.png");
//		readyImage.put("taulerFosc-jugador95", System.getProperty("user.dir")+"/res/taulerFosc-jugador95.png");
//		readyImage.put("taulerClar-jugador100",System.getProperty("user.dir")+"/res/taulerClar-jugador100.png");
//		readyImage.put("taulerFosc-jugador100",System.getProperty("user.dir")+"/res/taulerFosc-jugador100.png");
//		readyImage.put("taulerClar-bandera50", System.getProperty("user.dir")+"/res/taulerClar-bandera50.png");
//		readyImage.put("taulerFosc-bandera50", System.getProperty("user.dir")+"/res/taulerFosc-bandera50.png");
//		readyImage.put("taulerClar-bandera95", System.getProperty("user.dir")+"/res/taulerClar-bandera95.png");
//		readyImage.put("taulerFosc-bandera95", System.getProperty("user.dir")+"/res/taulerFosc-bandera95.png");
//		readyImage.put("taulerClar-bandera100",System.getProperty("user.dir")+"/res/taulerClar-bandera100.png");
//		readyImage.put("taulerFosc-bandera100",System.getProperty("user.dir")+"/res/taulerFosc-bandera100.png");
		
		score[0] = 0;
		score[1] = 5;
		firstPaintTiles();
		paintColoursTiles();
		paintPlayerPositions();
		
		ConstruirUI();
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void setPlayerNames(String player1, String player2)
	{
		playerName = new String[] { player1, player2 };
	}
	
	public void /*JFrame*/ ConstruirUI(/*JFrame jframe*/)
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
//		j1m.setBackground(new Color(167,114,125));
//		j1m.setForeground(new Color(255,255,255));
//		j1m.setFocusPainted(false);
//		j1m.setFont(new Font("Tahoma", Font.BOLD, 24));
//		Border jButtonBorder1 = j1m.getBorder();
//		Border jButtonMargin1 = new EmptyBorder(10,10,10,10);
//		j1m.setBorder(new CompoundBorder(jButtonBorder1, jButtonMargin1));
		
		JButton j1p = new JButton("DBG1+");
		j1p.addActionListener(new TaulerDebugP1plus());
//		j1p.setBackground(new Color(167,114,125));
//		j1p.setForeground(new Color(255,255,255));
//		j1p.setFocusPainted(false);
//		j1p.setFont(new Font("Tahoma", Font.BOLD, 24));
//		Border jButtonBorder2 = j1p.getBorder();
//		Border jButtonMargin2 = new EmptyBorder(10,10,10,10);
//		j1p.setBorder(new CompoundBorder(jButtonBorder2, jButtonMargin2));
		
		JButton j2m = new JButton("DBG2-");
		j2m.addActionListener(new TaulerDebugP2minus());
//		j2m.setBackground(new Color(167,114,125));
//		j2m.setForeground(new Color(255,255,255));
//		j2m.setFocusPainted(false);
//		j2m.setFont(new Font("Tahoma", Font.BOLD, 24));
//		Border jButtonBorder3 = j2m.getBorder();
//		Border jButtonMargin3 = new EmptyBorder(10,10,10,10);
//		j2m.setBorder(new CompoundBorder(jButtonBorder3, jButtonMargin3));
		
		JButton j2p = new JButton("DBG2+");
		j2p.addActionListener(new TaulerDebugP2plus());
//		j2p.setBackground(new Color(167,114,125));
//		j2p.setForeground(new Color(255,255,255));
//		j2p.setFocusPainted(false);
//		j2p.setFont(new Font("Tahoma", Font.BOLD, 24));
//		Border jButtonBorder4 = j2p.getBorder();
//		Border jButtonMargin4 = new EmptyBorder(10,10,10,10);
//		j2p.setBorder(new CompoundBorder(jButtonBorder4, jButtonMargin4));
		
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel ls = new JLabel("· Res");
		
		bottomContainer.add(j1m);
		bottomContainer.add(j1p);
		bottomContainer.add(j2m);
		bottomContainer.add(j2p);
		bottomContainer.add(jButton);
		bottomContainer.add(l1);
		bottomContainer.add(l2);
		bottomContainer.add(ls);
		c.add(bottomContainer, BorderLayout.SOUTH);
		
//		return jframe;
	}
	
	public void firstPaintTiles() throws IOException
	{
		for(int i=0; i<16; i++)
		{
			BufferedImage bufferedImage;
//			switch(i)
//			{
//				case 0: case 8:
//					bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerClar-jugador95.png"));
//					break;
//				case 7: case 15:
//					bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerFosc-bandera50.png"));
//				bufferedImage = ImageIO.read(new File(pathCollection.get("taulerFosc-bandera50")));
//				break;
//				default:
//					bufferedImage = i % 2==0
//							? ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerClar.png"))
//							: ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerFosc
					bufferedImage = i % 2==0
							? ImageIO.read(new File(pathCollection.get("taulerClar")))
							: ImageIO.read(new File(pathCollection.get("taulerFosc")));
//					break;
//			}
			images[i] = new JLabel(new ImageIcon(Utils.resize(bufferedImage, 175, 175)));
//			images[i].setIcon((Icon)Utils.resize(bufferedImage,175,175));
//			images[i].setIcon(new ImageIcon(Utils.resize(bufferedImage,175,175)));
		}
	}
	
	public void paintColoursTiles() throws IOException
	{
		for(int i=0; i<16; i++)
		{
			BufferedImage bufferedImage;
			switch(i)
			{
//				case 0: case 8:
//					bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerClar-jugador95.png"));
//					break;
				case 7: case 15:
//					bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerFosc-bandera50.png"));
					bufferedImage = ImageIO.read(new File(pathCollection.get("taulerFosc-bandera50")));
					break;
				default:
//					bufferedImage = i % 2==0
//							? ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerClar.png"))
//							: ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerFosc
					bufferedImage = i % 2==0
							? ImageIO.read(new File(pathCollection.get("taulerClar")))
							: ImageIO.read(new File(pathCollection.get("taulerFosc")));
					break;
			}
//			images[i] = new JLabel(new ImageIcon(Utils.resize(bufferedImage, 175, 175)));
//			images[i].setIcon((Icon)Utils.resize(bufferedImage,175,175));
			images[i].setIcon(new ImageIcon(Utils.resize(bufferedImage,175,175)));
		}
	}
	
	public void paintPlayerPositions() throws IOException
	{
		System.out.println("mainTesting.tauler.score[0] = " + /*mainTesting.tauler.*/score[0]);
		System.out.println("mainTesting.tauler.score[1] = " + /*mainTesting.tauler.*/score[1]);
		BufferedImage bufferedImage1;
//		bufferedImage1 = score[0] % 2==0
//				? ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerClar-jugador95.png"))
//				: ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerFosc-jugador95.png"));
		bufferedImage1 = score[0] % 2==0
				? ImageIO.read(new File(pathCollection.get("taulerClar-jugador95")))
				: ImageIO.read(new File(pathCollection.get("taulerFosc-jugador95")));
//		images[score[0]] = new JLabel(new ImageIcon(Utils.resize(bufferedImage1, 175, 175)));
		images[score[0]].setIcon(new ImageIcon(Utils.resize(bufferedImage1,175,175)));
		
		BufferedImage bufferedImage2;
//		bufferedImage2 = score[1] % 2==0
//				? ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerClar-jugador95.png"))
//				: ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerFosc-jugador95.png"));
		bufferedImage2 = score[1] % 2==0
				? ImageIO.read(new File(pathCollection.get("taulerClar-jugador95")))
				: ImageIO.read(new File(pathCollection.get("taulerFosc-jugador95")));
//		images[score[1]+8] = new JLabel(new ImageIcon(Utils.resize(bufferedImage2, 175, 175)));
		images[score[1]+8].setIcon(new ImageIcon(Utils.resize(bufferedImage2,175,175)));
	}
	
	public void updatePlayerScore(int newScoreP1, int newScoreP2) throws IOException
	{
		score[0] = score[0] + newScoreP1;
		score[1] = score[1] + newScoreP2;
		
		for(int i=0; i<score.length; i++)
		{
			if(score[i]<=0) score[i] = 0;
			if(score[i]>=7) score[i] = 7; // score[i] = 8;
		}
		
		updatePlayerPosition();
	}
	
	public void updatePlayerPosition() throws IOException
	{
		updatePlayerPosition(score[0], score[1]);
	}
	
	public void updatePlayerPosition(int posP1, int posP2) throws IOException
	{
		score[0] = posP1;
		score[1] = posP2;
		
		paintColoursTiles();
		paintPlayerPositions();
		
//		for(int i=0; i<images.length; i++)
//		{
//			images[i].paint(images[i].getGraphics());
//			images[i].repaint();
//			images[i].revalidate();
//		}
		
//		SwingUtilities.updateComponentTreeUI(this);
//		invalidate();
//		validate();
//		repaint();
//		setVisible(false);
//		setVisible(true);
//		revalidate();
//		repaint();
//
//		this.repaint();
		
//		mainTesting.refresh();
		
//		SwingUtilities.updateComponentTreeUI(this);
		
//		invalidate();
//		validate();
//		repaint();
	}
}
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tauler extends JFrame
{
	@SuppressWarnings("CStyleArrayDeclaration")
	public String playerName[] = new String[2];
	
	@SuppressWarnings("CStyleArrayDeclaration")
	public int score[] = new int[2];
	
	@SuppressWarnings("CStyleArrayDeclaration")
	public JLabel images[] = new JLabel[16];
	
	public Tauler() throws IOException
	{
		new Tauler("Jugador 1", "Jugador 2");
	}
	
	public Tauler(String player1, String player2) throws IOException
	{
		super("Trivial");
		setSize(1500, 800);
		setPlayerNames(player1, player2);
		score[0] = 0;
		score[1] = 0;
		paintColoursTiles();
		paintPlayerPositions();
		
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
		
		bottomContainer.add(j1m);
		bottomContainer.add(j1p);
		bottomContainer.add(j2m);
		bottomContainer.add(j2p);
		bottomContainer.add(jButton);
		c.add(bottomContainer, BorderLayout.SOUTH);
		
		pack();
	}
	
	public void setPlayerNames(String player1, String player2)
	{
		playerName = new String[]{player1,player2};
	}
	
	public JFrame ConstruirUI(JFrame jframe)
	{
		
		
		return jframe;
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
					bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerFosc-bandera50.png"));
					break;
				default:
					bufferedImage = i % 2==0
							? ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerClar.png"))
							: ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerFosc.png"));
					break;
			}
			images[i] = new JLabel(new ImageIcon(Utils.resize(bufferedImage, 175, 175)));
		}
	}
	
	public void paintPlayerPositions() throws IOException
	{
		System.out.println("mainTesting.tauler.score[0] = " + /*mainTesting.tauler.*/score[0]);
		System.out.println("mainTesting.tauler.score[1] = " + /*mainTesting.tauler.*/score[1]);
		BufferedImage bufferedImage1;
		bufferedImage1 = score[0] % 2==0
				? ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerClar-jugador95.png"))
				: ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerFosc-jugador95.png"));
		images[score[0]] = new JLabel(new ImageIcon(Utils.resize(bufferedImage1, 175, 175)));
		
		BufferedImage bufferedImage2;
		bufferedImage2 = score[1] % 2==0
				? ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerClar-jugador95.png"))
				: ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerFosc-jugador95.png"));
		images[score[1]+8] = new JLabel(new ImageIcon(Utils.resize(bufferedImage2, 175, 175)));
	}
	
	public void updatePlayerPosition(int posP1, int posP2) throws IOException
	{
		score[0] = posP1;
		score[1] = posP2;
		
		paintColoursTiles();
		paintPlayerPositions();
	}
}
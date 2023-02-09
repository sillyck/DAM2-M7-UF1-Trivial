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
		
		for(int i=0; i<16; i++)
		{
			BufferedImage bufferedImage;
			switch(i)
			{
				case 0: case 8:
				bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerClar-jugador95.png"));
				break;
				case 7: case 15:
				bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerFosc-jugador95.png"));
				break;
				default:
					bufferedImage = i % 2==0
							? ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerClar.png"))
							: ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerFosc.png"));
					break;
			}
			images[i] = new JLabel(new ImageIcon(Utils.resize(bufferedImage, 175, 175)));
		}
		
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
		JLabel jLabel = new JLabel("Torn de:{nom_jugador}");
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
}
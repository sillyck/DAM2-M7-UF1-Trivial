import javax.imageio.ImageIO;
import javax.swing.*;
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
	public String imgPaths[] = new String[8];
	
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
		
		imgPaths = new String[]
		{
		
		};
		
//		images = new JLabel[]
//		{
//			new JLabel().setIcon(new ImageIcon("taulerClar.png")),
//		};
		GridLayout gridLayout = new GridLayout(2, 8);
		
		for(int i=0; i<16; i++)
		{
//			BufferedImage bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerClar.png"));
			BufferedImage bufferedImage;
			if(i==0) bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerClar-jugador95.png"));
			else if(i==8) bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerClar-jugador95.png"));
			else
			{
				if(i%2==0) bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerClar.png"));
				else bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerFosc.png"));
			}
			
			images[i] = new JLabel(new ImageIcon(Utils.resize(bufferedImage, 175, 175)));
		}
		
		
//		JLabel label = new JLabel(); //JLabel Creation
//		label.setIcon(new ImageIcon("taulerClar.png")); //Sets the image to be displayed as an icon
//		Dimension size = label.getPreferredSize(); //Gets the size of the image
//		label.setBounds(50, 30, size.width, size.height);
		
//		System.out.println("System.getProperty(\"user.dir\") = " + System.getProperty("user.dir"));
//		BufferedImage bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerClar.png"));
//		JLabel l = new JLabel(new ImageIcon(bufferedImage));
//		JLabel l = new JLabel(new ImageIcon(Utils.resize(bufferedImage, 175, 175)));
		
		
		
		Container container = getContentPane();
		container.setLayout(gridLayout);
//		container.add(gridLayout);
//		container.add(l);
		
		for(int i=0; i<images.length; i++) container.add(images[i]);
		
//		pack();
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
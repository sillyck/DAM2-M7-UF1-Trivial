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
		
		GridLayout gridLayout = new GridLayout(2, 8);
		
		for(int i=0; i<16; i++)
		{
			BufferedImage bufferedImage;
			switch(i)
			{
				case 0: case 8:
					bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/res/taulerClar-jugador95.png"));
					break;
				default:
					bufferedImage = i % 2==0
							? ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerClar.png"))
							: ImageIO.read(new File(System.getProperty("user.dir") + "/res/taulerFosc.png"));
					break;
			}
			images[i] = new JLabel(new ImageIcon(Utils.resize(bufferedImage, 175, 175)));
		}
		
		Container container = getContentPane();
		container.setLayout(gridLayout);
		
		for(int i=0; i<images.length; i++) container.add(images[i]);
		
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
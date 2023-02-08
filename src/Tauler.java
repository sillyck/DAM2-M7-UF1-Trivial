import javax.swing.*;
import java.awt.*;

public class Tauler extends JFrame
{
	public String playerName[] = new String[2];
	public int score[] = new int[2];
	public String imgPaths[] = new String[8];
	
	public Tauler()
	{
		new Tauler("Jugador 1", "Jugador 2");
	}
	
	public Tauler(String player1, String player2)
	{
		super("Trivial");
		setSize(1500, 800);
		
		imgPaths = new String[]
		{
		
		};
		
		Container container = getContentPane();
		
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
import javax.swing.*;

public class Tauler extends JFrame
{
	public String playerName[] = new String[2];
	public int score[] = new int[2];
	
	public Tauler()
	{
		new Tauler("Jugador 1", "Jugador 2");
	}
	
	public Tauler(String player1, String player2)
	{
		super("Trivial");
		setSize(1500, 800);
	}
	
	public void setPlayerNames(String player1, String player2)
	{
		playerName = new String[]{player1,player2};
	}
}
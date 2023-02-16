import java.io.IOException;

public class mainTesting
{
	public static Tauler tauler;
	
	public static void main(String[] args) throws IOException
	{
		tauler = new Tauler("Unnamed player 1","Unnamed player 2",true);
		tauler.setVisible(true);
	}
}
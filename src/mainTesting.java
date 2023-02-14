import javax.swing.*;
import java.io.IOException;

public class mainTesting
{
	public static Tauler tauler;
	
	public static void main(String[] args) throws IOException
	{
		tauler = new Tauler("","",true);
		tauler.setVisible(true);
	}
}
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TaulerDebugP2minus implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Click rebut a DBG2-");
		try
		{
			mainTesting.tauler.updatePlayerScore(0,-1);
		}
		catch(IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}
}
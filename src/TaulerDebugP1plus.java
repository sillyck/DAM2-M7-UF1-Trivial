import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TaulerDebugP1plus implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Click rebut a DBG1+");
		try
		{
			mainTesting.tauler.updatePlayerScore(1,0);
		}
		catch(IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}
}
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TaulerDebugP1plus implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Click rebut a DBG1+");
		mainTesting.tauler.score[0]++;
		try
		{
			mainTesting.tauler.updatePlayerPosition(mainTesting.tauler.score[0],mainTesting.tauler.score[1]);
		}
		catch(IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}
}
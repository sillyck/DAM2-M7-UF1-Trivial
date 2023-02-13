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
//		mainTesting.tauler.score[1]--;
//		try
//		{
//			mainTesting.tauler.updatePlayerPosition(mainTesting.tauler.score[0],mainTesting.tauler.score[1]);
//		}
//		catch(IOException ex)
//		{
//			throw new RuntimeException(ex);
//		}
	}
}
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TaulerDebugAllPos implements ActionListener
{
	int i = 0;
	
	public TaulerDebugAllPos(int i)
	{
		this.i = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			mainTesting.tauler.updatePlayerPosition(i,i);
		}
		catch(IOException ex)
		{
			throw new RuntimeException(ex);
		}
//		mainTesting.tauler.score[0]--;
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
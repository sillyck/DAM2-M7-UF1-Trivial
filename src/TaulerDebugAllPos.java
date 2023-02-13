import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TaulerDebugAllPos implements ActionListener
{
	private int num;
	
	public TaulerDebugAllPos(int num)
	{
		this.num = num;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Click rebut a DBG"+ num);
		try
		{
			mainTesting.tauler.updatePlayerPosition(num, num);
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
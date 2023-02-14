import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TaulerDebugAdvance implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			mainTesting.tauler.advance();
		}
		catch(IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}
}
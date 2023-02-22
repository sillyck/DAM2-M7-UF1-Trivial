import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BotoDePregunta implements ActionListener
{
	private int resposta_id;
	private String textAssignat;
	
	private pantallaPreguntes pantallaPreguntes;
	
	public BotoDePregunta(pantallaPreguntes pantallaPreguntes, int resposta_id, String textAssignat)
	{
		this.pantallaPreguntes = pantallaPreguntes;
		this.resposta_id = resposta_id;
		this.textAssignat = textAssignat;
	}
	
	/**
	 * Invoked when an action occurs.
	 *
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			pantallaPreguntes.ClickEnResposta(textAssignat,resposta_id);
		}
		catch(IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}
}
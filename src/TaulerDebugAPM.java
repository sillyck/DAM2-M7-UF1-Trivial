import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TaulerDebugAPM implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Pregunta pregunta = null;
		try
		{
			pregunta = QuestionBank.ObtindrePregunta(true);
		}
		catch(IOException | ClassNotFoundException ex)
		{
			throw new RuntimeException(ex);
		}
		
		System.out.println("S'ha obtingut una pregunta");
		System.out.println("==========================");
		System.out.println(pregunta.enunciat);
		System.out.println("\t"+pregunta.respostes[0]);
		System.out.println("\t"+pregunta.respostes[1]);
		System.out.println("\t"+pregunta.respostes[2]);
		System.out.println("\t"+pregunta.respostes[3]);
		System.out.println("\t -> "+pregunta.respostaCorrecta+" <-\n");
		QuestionBank.PrintStatsForNerds();
	}
}
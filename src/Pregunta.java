import java.util.InputMismatchException;

/**
 * Classe que representa una pregunta i guarda la seva informació.
 * <p>Llegida des de <tt>preguntas.xml</tt> i <tt>preguntas-repe.xml</tt> a la classe <tt>QuestionBank</tt>.
 */
public class Pregunta
{
	/**
	 * El text de l'enunciat de la pregunta.
	 * <p>En l'XML es diu <tt>&lt;texto&gt;</tt>.
	 */
	public String enunciat;
	
	/**
	 * Un simple array d'Strings amb totes les respostes possibles de la pregunta.
	 * <p><b>Compte!</b> Aqui hi han respostes correctes (<tt>&lt;correcta&gt;</tt> en l'XML)
	 * e incorrectes (<tt>&lt;incorrecta&gt;</tt>) barrejades!
	 */
	public String[] respostes;
	
	/**
	 * Un simple String amb la resposta correcta de la pregunta (pillat des de l'element <tt>&lt;correcta&gt;</tt> de l'XML).
	 * <p>El contingut d'aqui tmabé hauría d'estar dins l'array de respostes.
	 */
	public String respostaCorrecta;
	
	@SuppressWarnings("unused")
	public Pregunta()
	{
	}
	
	@SuppressWarnings("unused")
	public Pregunta(String enunciat, String[] respostes)
	{
		this.enunciat = enunciat;
		this.respostes = respostes;
		this.respostaCorrecta = respostes[0];
		
		ComprovarRespostaPossible();
	}
	
	public Pregunta(String enunciat, String[] respostes, String respostaCorrecta)
	{
		this.enunciat = enunciat;
		this.respostes = respostes;
		this.respostaCorrecta = respostaCorrecta;
		
		ComprovarRespostaPossible();
	}
	
	/**
	 * Comprova si la resposta del jugador es correcta o no.
	 *
	 * @param intent La resposta que ha proposat el jugador.
	 * @return Un boolean de <tt>true</tt> si la respota és correcta i <tt>false</tt> si és incorrecta.
	 */
	@SuppressWarnings("unused")
	public boolean ComprovarResposta(String intent)
	{
		return intent.equals(respostaCorrecta);
	}
	
	/**
	 * Verifica si el text que hi ha a {@link #respostaCorrecta} coincideix amb algun de {@link #respostes}.
	 * Si no coincideix, llança una excepció InputMismatchException.
	 */
	@SuppressWarnings({ "ForLoopReplaceableByForEach", "StringOperationCanBeSimplified" })
	private void ComprovarRespostaPossible()
	{
		for(int i=0; i<this.respostes.length; i++) if(this.respostes[i].toString().equals(this.respostaCorrecta)) return;
		throw new InputMismatchException(this.respostaCorrecta);
	}
}
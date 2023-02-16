import java.util.InputMismatchException;

public class Pregunta
{
	public String enunciat;
	public String[] respostes;
	public String respostaCorrecta;
	
	public Pregunta()
	{
	}
	
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
	
	@SuppressWarnings({ "ForLoopReplaceableByForEach", "StringOperationCanBeSimplified" })
	private void ComprovarRespostaPossible()
	{
		for(int i=0; i<this.respostes.length; i++) if(this.respostes[i].toString().equals(this.respostaCorrecta)) return;
		throw new InputMismatchException(this.respostaCorrecta);
	}
}
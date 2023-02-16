import java.util.InputMismatchException;

public class Pregunta
{
	public String enunciat;
	public String[] respostes;
	public String respotaCorrecta;
	
	public Pregunta()
	{
	}
	
	public Pregunta(String enunciat, String[] respostes)
	{
		this.enunciat = enunciat;
		this.respostes = respostes;
		this.respotaCorrecta = respostes[0];
		
		ComprovarRespostaPossible();
	}
	
	public Pregunta(String enunciat, String[] respostes, String respotaCorrecta)
	{
		this.enunciat = enunciat;
		this.respostes = respostes;
		this.respotaCorrecta = respotaCorrecta;
		
		ComprovarRespostaPossible();
	}
	
	@SuppressWarnings({ "ForLoopReplaceableByForEach", "StringOperationCanBeSimplified" })
	private void ComprovarRespostaPossible()
	{
		for(int i=0; i<this.respostes.length; i++) if(this.respostes[i].toString().equals(this.respotaCorrecta)) return;
		throw new InputMismatchException(this.respotaCorrecta);
	}
}
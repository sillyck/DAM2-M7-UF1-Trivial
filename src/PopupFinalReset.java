import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupFinalReset implements ActionListener
{
	private final PopupFinal popupFinal;
	private final String j1;
	private final String j2;
	
	public PopupFinalReset(PopupFinal popupFinal, String j1, String j2)
	{
		this.popupFinal = popupFinal;
		this.j1 = j1;
		this.j2 = j2;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		popupFinal.dispose();
		if(MetaController.isThisDebugMode)
		{
			mainTesting.pantallaInicial = new pantallaInicial(j1,j2);
			mainTesting.pantallaInicial.setVisible(true);
		}
		else
		{
			mainTrivial.pantallaInicial = new pantallaInicial(j1,j2);
			mainTrivial.pantallaInicial.setVisible(true);
		}
	}
}
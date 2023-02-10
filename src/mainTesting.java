import javax.swing.*;
import java.io.IOException;

public class mainTesting
{
	public static Tauler tauler;
	
	public static void main(String[] args) throws IOException
	{
		tauler = new Tauler("","");
		tauler.setVisible(true);
	}
	
	public static void refresh()
	{
//		SwingUtilities.updateComponentTreeUI(tauler);
//		tauler.invalidate();
//		tauler.validate();
//		tauler.repaint();
//		tauler.setVisible(false);
//		tauler.setVisible(true);
//		tauler.revalidate();
//		tauler.repaint();
		tauler.ConstruirUI();
//
//		this.repaint();
	}
}
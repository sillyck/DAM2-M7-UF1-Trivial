import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils
{
	/**
	 * Retorna un <tt>BufferedImage</tt> ja redimensionat segons s'especifiqui.
	 *
	 * @param img
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static BufferedImage resize(BufferedImage img, int newWidth, int newHeight)
	{
		Image tmp = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		
		return dimg;
	}
}
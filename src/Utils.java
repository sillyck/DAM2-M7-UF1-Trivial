import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils
{
	/**
	 * Retorna un <tt>BufferedImage</tt> ja redimensionat segons s'especifiqui.
	 *
	 * @param bufferedImage L'imatge a modificar
	 * @param newWidth L'amplada que volem que tingui l'imatge.
	 * @param newHeight L'alçada que volem que tingui l'imatge.
	 * @return Un BufferedImage amb totes les especificacions especificades.
	 */
	public static BufferedImage resize(BufferedImage bufferedImage, int newWidth, int newHeight)
	{
		Image image = bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		BufferedImage newBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D graphics2D = newBufferedImage.createGraphics();
		graphics2D.drawImage(image, 0, 0, null);
		graphics2D.dispose();
		
		return newBufferedImage;
	}
}
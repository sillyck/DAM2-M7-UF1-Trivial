import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

public class ButtonPregunta extends JButton
{
	private static final long serialVersionUID = 1L;
	private String originalText;
	
	public ButtonPregunta(String text)
	{
		super();
		originalText = text;
		setText(originalText);
		setPreferredSize(new Dimension(300, 80));
	}
	
	@Override
	public void setText(String s)
	{
		originalText = s;
		super.setText(s);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		String text = originalText;
		int width = getSize().width - 10;
		while(getFontMetrics(getFont()).stringWidth(text) > width) text = text.substring(0, text.length() - 1);
		if(!text.equals(originalText)) text = text.substring(0, text.length() - 3) + "...";
		setText(text);
		super.paintComponent(g);
		setText(originalText);
	}
}
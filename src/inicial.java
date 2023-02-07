import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class inicial extends JFrame{
	
	public inicial() {

		super("Trivial");
		setSize(1500, 800);
		
		Container container = getContentPane();
		
		JPanel titol = new JPanel();
		JPanel subtitol = new JPanel();
		
		
		JLabel label1 = new JLabel("Benvinguts al Trivial");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		titol.add(label1);
		
		JLabel label2 = new JLabel("Per a jugar introduïu els vostres noms");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		subtitol.add(label2);
		
		
		
		container.add(titol);
//		container.add(subtitol);
		
	}

}

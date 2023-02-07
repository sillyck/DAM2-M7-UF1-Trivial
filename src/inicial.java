import java.awt.Container;
import java.awt.GridLayout;

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
		JPanel jugadors = new JPanel();
		
		
		JLabel label1 = new JLabel("Benvinguts al Trivial");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		titol.add(label1);
		
		JLabel label2 = new JLabel("Per a jugar introduïu els vostres noms");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		titol.add(label2);
		
		
		
		
		titol.setLayout(new GridLayout(2, 1));
		jugadors.setLayout(new GridLayout(2, 4));
		
		
		container.add(titol);
		
	}

}

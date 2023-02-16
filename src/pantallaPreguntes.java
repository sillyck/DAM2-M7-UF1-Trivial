import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class pantallaPreguntes extends JFrame{

	public pantallaPreguntes() {
		
		super("Preguntes");
		setSize(1250, 720);
		
		JPanel preguntaPanell = new JPanel();
		JLabel preguntaText = new JLabel("¿Quién descubrió América?");
		preguntaText.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		preguntaPanell.add(preguntaText);
				
		JButton r1 = new JButton("Cristobal Colon");
		JButton r2 = new JButton("Xavier Martínez");
		JButton r3 = new JButton("Leo Messi");
		JButton r4 = new JButton("Pedro Sanchez");
		
		
		r1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		r2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		r3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		r4.setFont(new Font("Tahoma", Font.PLAIN, 16));		
		
		Container cp = getContentPane();
		cp.setLayout(new GridBagLayout());
		
		preguntaPanell.setPreferredSize(new Dimension(700, 700));
		
		Border jButtonBorderR1 = r1.getBorder();
		Border jButtonMarginR1 = new EmptyBorder(10, 10, 10, 10);
		r1.setBorder(new CompoundBorder(jButtonBorderR1, jButtonMarginR1));
		
		Border jButtonBorderR2 = r2.getBorder();
		Border jButtonMarginR2 = new EmptyBorder(10, 10, 10, 10);
		r2.setBorder(new CompoundBorder(jButtonBorderR2, jButtonMarginR2));
		
		Border jButtonBorderR3 = r3.getBorder();
		Border jButtonMarginR3 = new EmptyBorder(10, 10, 10, 10);
		r3.setBorder(new CompoundBorder(jButtonBorderR3, jButtonMarginR3));
		
		Border jButtonBorderR4 = r4.getBorder();
		Border jButtonMarginR4 = new EmptyBorder(10, 10, 10, 10);
		r4.setBorder(new CompoundBorder(jButtonBorderR4, jButtonMarginR4));
		
		GridBagConstraints preguntaGrid = new GridBagConstraints();
		preguntaGrid.insets = new Insets(0, 0, 500, 0);
		
		
		//Colors
		cp.setBackground(Color.decode("#F9F5E7"));
		preguntaPanell.setBackground(Color.decode("#F9F5E7"));
		
		
		//Afegir-ho tot al container
		cp.add(preguntaPanell, preguntaGrid);
		cp.add(r1);
		cp.add(r2);
		cp.add(r3);
		cp.add(r4);
	}
}

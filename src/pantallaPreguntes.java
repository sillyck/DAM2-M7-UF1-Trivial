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
		
		JPanel botonsPanellr1r2 = new JPanel();
		JPanel botonsPanellr3r4 = new JPanel();
		JButton r1 = new JButton("Cristobal Colon");
		JButton r2 = new JButton("Xavier Martínez");
		JButton r3 = new JButton("Leo Messi");
		JButton r4 = new JButton("Perro Sanchez");
		
		r1.setFont(new Font("Tahoma", Font.BOLD, 16));
		r2.setFont(new Font("Tahoma", Font.BOLD, 16));
		r3.setFont(new Font("Tahoma", Font.BOLD, 16));
		r4.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		botonsPanellr1r2.setBackground(Color.decode("#22222"));
		
		botonsPanellr3r4.add(r3);
		botonsPanellr3r4.add(r4);
		botonsPanellr3r4.setBackground(Color.decode("#BBBBB"));
		
		Container cp = getContentPane();
		cp.setLayout(new GridBagLayout());
		
		Border jButtonBorderR1 = r1.getBorder();
		Border jButtonMarginR1 = new EmptyBorder(10, 10, 10, 10);
		r1.setBorder(new CompoundBorder(jButtonBorderR1, jButtonMarginR1));
		r1.setBackground(new Color(167, 114, 125));
		r1.setForeground(new Color(255, 255, 255));
		r1.setFocusPainted(false);
		
		Border jButtonBorderR2 = r2.getBorder();
		Border jButtonMarginR2 = new EmptyBorder(10, 10, 10, 10);
		r2.setBorder(new CompoundBorder(jButtonBorderR2, jButtonMarginR2));
		r2.setBackground(new Color(167, 114, 125));
		r2.setForeground(new Color(255, 255, 255));
		r2.setFocusPainted(false);
		
		Border jButtonBorderR3 = r3.getBorder();
		Border jButtonMarginR3 = new EmptyBorder(10, 10, 10, 10);
		r3.setBorder(new CompoundBorder(jButtonBorderR3, jButtonMarginR3));
		r3.setBackground(new Color(167, 114, 125));
		r3.setForeground(new Color(255, 255, 255));
		r3.setFocusPainted(false);
		
		Border jButtonBorderR4 = r4.getBorder();
		Border jButtonMarginR4 = new EmptyBorder(10, 10, 10, 10);
		r4.setBorder(new CompoundBorder(jButtonBorderR4, jButtonMarginR4));
		r4.setBackground(new Color(167, 114, 125));
		r4.setForeground(new Color(255, 255, 255));
		r4.setFocusPainted(false);
		
		GridBagConstraints preguntaGrid = new GridBagConstraints();
		preguntaGrid.gridx = 0;
		preguntaGrid.gridy = 0;
		preguntaGrid.insets = new Insets(-350, 0, 0, -300);
		
		GridBagConstraints botonsGridr1 = new GridBagConstraints();
		botonsGridr1.gridx = 0;
		botonsGridr1.gridy = 1;
		botonsGridr1.insets = new Insets(0, 0, 100, 0);
				
		GridBagConstraints botonsGridr2 = new GridBagConstraints();
		botonsGridr2.gridx = 1;
		botonsGridr2.gridy = 1;
		botonsGridr2.insets = new Insets(0, 150, 100, 0);
		
		GridBagConstraints botonsGridr3 = new GridBagConstraints();
		botonsGridr3.gridx = 0;
		botonsGridr3.gridy = 2;
		botonsGridr3.insets = new Insets(0, 0, 0, 0);
		
		GridBagConstraints botonsGridr4 = new GridBagConstraints();
		botonsGridr4.gridx = 1;
		botonsGridr4.gridy = 2;
		botonsGridr4.insets = new Insets(0, 150, 0, 0);
		
		//Colors
		cp.setBackground(Color.decode("#F9F5E7"));
		preguntaPanell.setBackground(Color.decode("#F9F5E7"));
		
		//Afegir-ho tot al container
		cp.add(preguntaPanell, preguntaGrid);
		cp.add(r1, botonsGridr1);
		cp.add(r2, botonsGridr2);
		cp.add(r3, botonsGridr3);
		cp.add(r4, botonsGridr4);
	}
}
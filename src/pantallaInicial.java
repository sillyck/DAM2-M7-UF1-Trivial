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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class pantallaInicial extends JFrame {

	public pantallaInicial() {

		super("Trivial");
		setSize(1250, 720);

		JPanel benvinguda = new JPanel();
		JPanel j1panel = new JPanel();
		JPanel j2panel = new JPanel();
		JPanel j1panelResposta = new JPanel();
		JPanel j2panelResposta = new JPanel();

		JLabel textBenvinguda = new JLabel("Benvingut al Trivial");
		JLabel subTextBenvinguda = new JLabel("Introduïu els noms dels Jugadors per a poder jugar");

		textBenvinguda.setFont(new Font("Tahoma", Font.BOLD, 32));
		subTextBenvinguda.setFont(new Font("Tahoma", Font.BOLD, 32));
		
		JButton botoContinuar = new JButton("Continuar");

		JLabel j1 = new JLabel("Jugador 1");
		JLabel j2 = new JLabel("Jugador 2");

		j1.setFont(new Font("Tahoma", Font.BOLD, 24));
		j2.setFont(new Font("Tahoma", Font.BOLD, 24));

		JTextField j1JTF = new JTextField(10);
		JTextField j2JTF = new JTextField(10);

		Container cp = getContentPane();

		// Benvinguda
		benvinguda.add(textBenvinguda);
		benvinguda.add(subTextBenvinguda);

		j1panel.add(j1);
		j2panel.add(j2);

		j1panelResposta.add(j1JTF);
		j2panelResposta.add(j2JTF);

		cp.setLayout(new GridBagLayout());

		benvinguda.setPreferredSize(new Dimension(1000, 120));

		j1panel.setPreferredSize(new Dimension(150, 50));
		j2panel.setPreferredSize(new Dimension(150, 50));

		GridBagConstraints espaiBenvinguda = new GridBagConstraints();
		espaiBenvinguda.insets = new Insets(-500, 0, 0, 0);

		GridBagConstraints espaiJ1 = new GridBagConstraints();
		espaiJ1.gridx = 0;
		espaiJ1.gridy = 0;
		espaiJ1.insets = new Insets(0, 0, 100, 800);

		GridBagConstraints espaiJ2 = new GridBagConstraints();
		espaiJ2.gridx = 0;
		espaiJ2.gridy = 0;
		espaiJ2.insets = new Insets(0, 800, 100, 0);

		GridBagConstraints espaiJ1Resposta = new GridBagConstraints();
		espaiJ1Resposta.gridx = 0;
		espaiJ1Resposta.gridy = 0;
		espaiJ1Resposta.insets = new Insets(0, 0, 0, 800);

		GridBagConstraints espaiJ2Resposta = new GridBagConstraints();
		espaiJ2Resposta.gridx = 0;
		espaiJ2Resposta.gridy = 0;
		espaiJ2Resposta.insets = new Insets(0, 800, 0, 0);
		
		GridBagConstraints gridBoto = new GridBagConstraints();
		gridBoto.gridx = 0;
		gridBoto.gridy = 0;
		gridBoto.insets = new Insets(600, 0, 0, 0);

		// Boto
		botoContinuar.setBackground(new Color(167, 114, 125));
		botoContinuar.setForeground(new Color(255, 255, 255));
		botoContinuar.setFocusPainted(false);
		botoContinuar.setFont(new Font("Tahoma", Font.BOLD, 24));
		Border jButtonBorder = botoContinuar.getBorder();
		Border jButtonMargin = new EmptyBorder(10, 10, 10, 10);
		botoContinuar.setBorder(new CompoundBorder(jButtonBorder, jButtonMargin));

		//Assignar els colors del fons
		benvinguda.setBackground(Color.decode("#F9F5E7"));
		j1panel.setBackground(Color.decode("#F9F5E7"));
		j2panel.setBackground(Color.decode("#F9F5E7"));
		j1panelResposta.setBackground(Color.decode("#F9F5E7"));
		j2panelResposta.setBackground(Color.decode("#F9F5E7"));
		cp.setBackground(Color.decode("#F9F5E7"));

		cp.add(benvinguda, espaiBenvinguda);

		cp.add(j1panel, espaiJ1);
		cp.add(j2panel, espaiJ2);
		
		cp.add(j1panelResposta, espaiJ1Resposta);
		cp.add(j2panelResposta, espaiJ2Resposta);
		
		cp.add(botoContinuar, gridBoto);

	}

}

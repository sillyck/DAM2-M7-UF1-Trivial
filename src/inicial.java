import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class inicial extends JFrame {

	public inicial() {

		super("Trivial");
		setSize(1500, 800);
		
		JPanel benvinguda = new JPanel();
		JPanel j1panel = new JPanel();
		JPanel j2panel = new JPanel();
		JPanel j1panelResposta = new JPanel();
		JPanel j2panelResposta = new JPanel();
		
		JLabel textBenvinguda = new JLabel("Benvingut al Trivial");
		JLabel subTextBenvinguda = new JLabel("Introduïu els noms dels Jugadors per a poder jugar");
		JLabel j1 = new JLabel("Jugador 1");
		JLabel j2 = new JLabel("Jugador 2");
		
		JTextField j1JTF = new JTextField();
		JTextField j2JTF = new JTextField();
		
		Container cp = getContentPane();
		
		//Benvinguda
		benvinguda.setLayout(new GridBagLayout());		
		GridBagConstraints gridTextBenvinguda = new GridBagConstraints();
		gridTextBenvinguda.gridx = 0;
		gridTextBenvinguda.gridy = 0;
		gridTextBenvinguda.insets = new Insets(0, 0, 0, 0);
		benvinguda.add(textBenvinguda, gridTextBenvinguda);
		
		GridBagConstraints gridSubTextBenvinguda = new GridBagConstraints();
		gridSubTextBenvinguda.gridx = 0;
		gridSubTextBenvinguda.gridy = 1;
		gridSubTextBenvinguda.insets = new Insets(0, 0, 0, 0);
		benvinguda.add(subTextBenvinguda, gridSubTextBenvinguda);
		
		//Espais de text que posa jugador
		j1panel.setLayout(new GridBagLayout());
		GridBagConstraints gridJugador1 = new GridBagConstraints();
		gridJugador1.gridx = 0;
		gridJugador1.gridy = 0;
		gridJugador1.insets = new Insets(0, 0, 0, 0);
		j1panel.add(j1, gridJugador1);
		
		j2panel.setLayout(new GridBagLayout());
		GridBagConstraints gridJugador2 = new GridBagConstraints();
		gridJugador2.gridx = 0;
		gridJugador2.gridy = 0;
		gridJugador2.insets = new Insets(0, 0, 0, 0);
		j2panel.add(j2, gridJugador2);
		
		
		j1JTF.setLayout(new GridBagLayout());
		GridBagConstraints gridj1JTF = new GridBagConstraints();
		gridj1JTF.gridx = 0;
		gridj1JTF.gridy = 0;
		gridj1JTF.insets = new Insets(0, 0, 0, 0);
		j1panelResposta.add(j1JTF, gridj1JTF);
		
		j2JTF.setLayout(new GridBagLayout());
		GridBagConstraints gridj2JTF = new GridBagConstraints();
		gridj2JTF.gridx = 0;
		gridj2JTF.gridy = 0;
		gridj2JTF.insets = new Insets(0, 0, 0, 0);
		j2panelResposta.add(j2JTF, gridj2JTF);
		
		cp.setLayout(new GridBagLayout());
		
		benvinguda.setPreferredSize(new Dimension(300, 120));
		
		j1panel.setPreferredSize(new Dimension(100, 50));
		j2panel.setPreferredSize(new Dimension(100, 50));
		
		GridBagConstraints espaiBenvinguda = new GridBagConstraints();
		espaiBenvinguda.insets = new Insets(-500, 0, 0, 0);
		
		GridBagConstraints espaiJ1 = new GridBagConstraints();
		espaiJ1.gridx = 0;
		espaiJ1.gridy = 1;
		espaiJ1.insets = new Insets(-100, -1000, 0, 0);
		
		GridBagConstraints espaiJ2 = new GridBagConstraints();
		espaiJ2.gridx = 1;
		espaiJ2.gridy = 1;
		espaiJ2.insets = new Insets(-100, 0, 0, -700);
		
		
		
		benvinguda.setBackground(Color.decode("#567189"));
		
		j1panel.setBackground(Color.decode("#A7727D"));
		j1panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		j2panel.setBackground(Color.decode("#698269"));
		j2panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		cp.setBackground(Color.decode("#F9F5E7"));
		
		cp.add(benvinguda, espaiBenvinguda);
		cp.add(j1panel, espaiJ1);
		cp.add(j2panel, espaiJ2);

	}

}

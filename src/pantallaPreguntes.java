import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class pantallaPreguntes extends JFrame implements ActionListener
{
	public boolean answerMode = true;
	
	private int respostaEscollida = -1;
	
	private boolean respostaCorrecta = false;
	
	private Tauler tauler;
	
	private Pregunta preguntaActual;
	
	ButtonPregunta r1;
	ButtonPregunta r2;
	ButtonPregunta r3;
	ButtonPregunta r4;
	

	public pantallaPreguntes(Tauler tauler) throws IOException, ClassNotFoundException
	{
		super("Preguntes");
		setSize(1250, 720);
//		setSize(1400, 720);
		setLocationRelativeTo(null);
		this.tauler = tauler;
		
		JPanel preguntaPanell = new JPanel();
		JLabel preguntaText = new JLabel("¿Quién descubrió América?");
//		JLabel preguntaText = new JLabel("<html><body style='width: 1000px'>"+"¿Quién descubrió América?"+"</body></html>");
		preguntaText.setFont(new Font("Tahoma", Font.BOLD, 24));
		preguntaPanell.setBackground(Color.decode("#F9F5E7"));
		preguntaPanell.add(preguntaText);
		
		JPanel botonsPanellr1r2 = new JPanel();
		JPanel botonsPanellr3r4 = new JPanel();
		r1 = new ButtonPregunta("Cristobal Colon");
		r2 = new ButtonPregunta("Xavier Martínez");
		r3 = new ButtonPregunta("Leo Messi");
		r4 = new ButtonPregunta("Perro Sanchez");
		
		preguntaActual = QuestionBank.ObtindrePregunta(true);
		preguntaActual.BarrejarRespostes();
//		preguntaText.setText("<html><body style='width: 1000px'>"+preguntaActual.enunciat+"</body></html>");
//		preguntaText.setText(preguntaActual.enunciat);
		preguntaText.setText("<html>"+preguntaActual.enunciat.replaceAll("(?<=\\G.{1000})\\s", "<br>")+"</html>");
		
//		r1.setText(preguntaActual.respostes[0]);
		r1 = new ButtonPregunta(preguntaActual.respostes[0]);
//		r1.setText("<html>"+preguntaActual.respostes[0].replaceAll("(.{50})", "$1<br>")+"</html>");
//		r1.setText("<html>"+preguntaActual.respostes[0].replaceAll("(?<=\\G.{800})\\s", "<br>")+"</html>");
		r1.addActionListener(new BotoDePregunta(this,1,preguntaActual.respostes[0]));
		r2 = new ButtonPregunta(preguntaActual.respostes[1]);
//		r2.setText(preguntaActual.respostes[1]);
//		r2.setText("<html>"+preguntaActual.respostes[1].replaceAll("(.{50})", "$1<br>")+"</html>");
//		r2.setText("<html>"+preguntaActual.respostes[1].replaceAll("(?<=\\G.{800})\\s", "<br>")+"</html>");
		r2.addActionListener(new BotoDePregunta(this,2,preguntaActual.respostes[1]));
		r3 = new ButtonPregunta(preguntaActual.respostes[2]);
//		r3.setText(preguntaActual.respostes[2]);
//		r3.setText("<html>"+preguntaActual.respostes[2].replaceAll("(.{50})", "$1<br>")+"</html>");
//		r3.setText("<html>"+preguntaActual.respostes[2].replaceAll("(?<=\\G.{800})\\s", "<br>")+"</html>");
		r3.addActionListener(new BotoDePregunta(this,3,preguntaActual.respostes[2]));
		r4 = new ButtonPregunta(preguntaActual.respostes[3]);
//		r4.setText(preguntaActual.respostes[3]);
//		r4.setText("<html>"+preguntaActual.respostes[3].replaceAll("(.{50})", "$1<br>")+"</html>");
//		r4.setText("<html>"+preguntaActual.respostes[3].replaceAll("(?<=\\G.{800})\\s", "<br>")+"</html>");
		r4.addActionListener(new BotoDePregunta(this,4,preguntaActual.respostes[3]));
		
		r1.setFont(new Font("Tahoma", Font.BOLD, 16));
		r2.setFont(new Font("Tahoma", Font.BOLD, 16));
		r3.setFont(new Font("Tahoma", Font.BOLD, 16));
		r4.setFont(new Font("Tahoma", Font.BOLD, 16));
		
//		botonsPanellr1r2.setBackground(Color.decode("#22222"));
		botonsPanellr1r2.setBackground(Color.decode("#F9F5E7"));
		
		botonsPanellr3r4.add(r3);
		botonsPanellr3r4.add(r4);
//		botonsPanellr3r4.setBackground(Color.decode("#BBBBB"));
		botonsPanellr3r4.setBackground(Color.decode("#F9F5E7"));
		
		Container cp = getContentPane();
		cp.setLayout(new GridBagLayout());
		cp.setBackground(Color.decode("#F9F5E7"));
		
		Border jButtonBorderR1 = r1.getBorder();
		Border jButtonMarginR1 = new EmptyBorder(20, 40, 20, 40);
		r1.setBorder(new CompoundBorder(jButtonBorderR1, jButtonMarginR1));
		r1.setBackground(new Color(167, 114, 125));
		r1.setForeground(new Color(255, 255, 255));
		r1.setFocusPainted(false);
		
		Border jButtonBorderR2 = r2.getBorder();
		Border jButtonMarginR2 = new EmptyBorder(20, 40, 20, 40);
		r2.setBorder(new CompoundBorder(jButtonBorderR2, jButtonMarginR2));
		r2.setBackground(new Color(167, 114, 125));
		r2.setForeground(new Color(255, 255, 255));
		r2.setFocusPainted(false);
		
		Border jButtonBorderR3 = r3.getBorder();
		Border jButtonMarginR3 = new EmptyBorder(20, 40, 20, 40);
		r3.setBorder(new CompoundBorder(jButtonBorderR3, jButtonMarginR3));
		r3.setBackground(new Color(167, 114, 125));
		r3.setForeground(new Color(255, 255, 255));
		r3.setFocusPainted(false);
		
		Border jButtonBorderR4 = r4.getBorder();
		Border jButtonMarginR4 = new EmptyBorder(20, 40, 20, 40);
		r4.setBorder(new CompoundBorder(jButtonBorderR4, jButtonMarginR4));
		r4.setBackground(new Color(167, 114, 125));
		r4.setForeground(new Color(255, 255, 255));
		r4.setFocusPainted(false);
		
		GridBagConstraints preguntaGrid = new GridBagConstraints();
		preguntaGrid.gridx = 0;
		preguntaGrid.gridy = 0;
		preguntaGrid.insets = new Insets(-350, 0, 0, -500);
		
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
//		cp.setBackground(Color.decode("#F9F5E7"));
//		preguntaPanell.setBackground(Color.decode("#F9F5E7"));
//		preguntaPanell.setBackground(Color.decode("#BBBBBB"));
		preguntaPanell.setPreferredSize(new Dimension(1000, 300));
		
		preguntaText.setPreferredSize(new Dimension(1000, (preguntaText.getPreferredSize().height*3))); // set the preferred width to 1000 pixels
//		preguntaText.setHorizontalAlignment(JLabel.LEFT);
		preguntaText.setHorizontalAlignment(JLabel.CENTER);
		
//		botonsGridr1.setPreferredSize(new Dimension(300, 300));
//		botonsGridr1.setPreferredSize(new Dimension(300, 300));
//		botonsGridr1.setPreferredSize(new Dimension(300, 300));
//		botonsGridr1.setPreferredSize(new Dimension(300, 300));
		
		//Afegir-ho tot al container
		cp.add(preguntaPanell, preguntaGrid);
		cp.add(r1, botonsGridr1);
		cp.add(r2, botonsGridr2);
		cp.add(r3, botonsGridr3);
		cp.add(r4, botonsGridr4);
		
		if(MetaController.isThisDebugMode)
		{
			System.out.println("S'ha obtingut una pregunta");
			System.out.println("==========================");
			System.out.println(preguntaActual.enunciat);
			System.out.println("\t"+preguntaActual.respostes[0]);
			System.out.println("\t"+preguntaActual.respostes[1]);
			System.out.println("\t"+preguntaActual.respostes[2]);
			System.out.println("\t"+preguntaActual.respostes[3]);
			System.out.println("\t -> "+preguntaActual.respostaCorrecta+" <-\n");
			QuestionBank.PrintStatsForNerds();
		}
	}
	
	/**
	 * Invoked when an action occurs.
	 *
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
	
	}
	
	public void ClickEnResposta(String text, int num_boto) throws IOException
	{
		if(!answerMode) Tancar();
		answerMode = false;
		
		if(preguntaActual.ComprovarResposta(text))
		{
			respostaCorrecta = true;
			switch(num_boto)
			{
				case 1:
					r1.setBackground(new Color(162,240,163));
					break;
				case 2:
					r2.setBackground(new Color(162,240,163));
					break;
				case 3:
					r3.setBackground(new Color(162,240,163));
					break;
				case 4:
					r4.setBackground(new Color(162,240,163));
					break;
			}
		}
		else
		{
			respostaCorrecta = false;
			switch(num_boto)
			{
				case 1:
					r1.setBackground(new Color(225,123,123));
					break;
				case 2:
					r2.setBackground(new Color(225,123,123));
					break;
				case 3:
					r3.setBackground(new Color(225,123,123));
					break;
				case 4:
					r4.setBackground(new Color(225,123,123));
					break;
			}
			
			int respostaCorrecta = -1;
			
			for(int i=0; i<preguntaActual.respostes.length; i++)
			{
				if(preguntaActual.ComprovarResposta(preguntaActual.respostes[i]))
				{
					respostaCorrecta = i;
					break;
				}
			}
			
			switch(respostaCorrecta+1)
			{
				case 1:
					r1.setBackground(new Color(162,240,163));
					break;
				case 2:
					r2.setBackground(new Color(162,240,163));
					break;
				case 3:
					r3.setBackground(new Color(162,240,163));
					break;
				case 4:
					r4.setBackground(new Color(162,240,163));
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + respostaCorrecta);
			}
		}
	}
	
	private void Tancar() throws IOException
	{
		setVisible(false);
		tauler.setVisible(true);
		tauler.answer(respostaCorrecta);
	}
}
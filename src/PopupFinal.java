import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupFinal extends JFrame implements ActionListener
{
	private boolean debug = false;
	
	private String j1;
	private String j2;
	private FinalPopupAction finalPopupAction;
	
	private JLabel jlabelTitle;
	
	public PopupFinal(String j1, String j2, FinalPopupAction finalPopupAction, boolean debug)
	{
		super();
		setSize(1500, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.j1 = j1;
		this.j2 = j2;
		this.finalPopupAction = finalPopupAction;
		this.debug = debug;
		ConstruirUI();
		switch(finalPopupAction)
		{
			case J1_WINS: jlabelTitle.setText("Victoria per al jugador 1: "+j1+"!"); break;
			case J2_WINS:  jlabelTitle.setText("Victoria per al jugador 2: "+j1+"!"); break;
			case J1_WINS_BARELY: jlabelTitle.setText("Victoria per poc per al jugador 1: "+j1+"!"); break;
			case J2_WINS_BARELY: jlabelTitle.setText("Victoria per poc per al jugador 2: "+j1+"!"); break;
			case BOTH: jlabelTitle.setText("Empat!"); break;
		}
		pack();
		setLocationRelativeTo(null);
	}
	
	private void ConstruirUI()
	{
		Container c = getContentPane();
		c.setBackground(Color.decode("#F9F5E7"));
		BorderLayout borderLayout = new BorderLayout();
		c.setLayout(borderLayout);
		
		FlowLayout topLayout = new FlowLayout();
		Container topContainer = new Container();
		topContainer.setLayout(topLayout);
		jlabelTitle = new JLabel("\nnull\n");
		jlabelTitle.setFont(new Font("Tahoma",Font.BOLD, 32));
		Border jLabelBorder = jlabelTitle.getBorder();
		Border jLabelMargin = new EmptyBorder(10,10,10,10);
		jlabelTitle.setBorder(new CompoundBorder(jLabelBorder, jLabelMargin));
		
		topContainer.add(jlabelTitle);
		c.add(topContainer, BorderLayout.NORTH);
		
		FlowLayout bottomLayout = new FlowLayout();
		Container bottomContainer = new Container();
		bottomContainer.setLayout(bottomLayout);
		JButton jButton = new JButton("Sortir del joc");
		jButton.setBackground(new Color(167,114,125));
		jButton.setForeground(new Color(255,255,255));
		jButton.setFocusPainted(false);
		jButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		Border jButtonBorder1 = jButton.getBorder();
		Border jButtonMargin1 = new EmptyBorder(10,10,10,10);
		jButton.setBorder(new CompoundBorder(jButtonBorder1, jButtonMargin1));
		jButton.addActionListener(this);
		
		JButton jreset = new JButton("Un altre partida?");
		jreset.setBackground(new Color(167,114,125));
		jreset.setForeground(new Color(255,255,255));
		jreset.setFocusPainted(false);
		jreset.setFont(new Font("Tahoma", Font.BOLD, 24));
		Border jButtonBorder2 = jreset.getBorder();
		Border jButtonMargin2 = new EmptyBorder(10,10,10,10);
		jreset.setBorder(new CompoundBorder(jButtonBorder2, jButtonMargin2));
		jreset.addActionListener(new PopupFinalReset(this,j1,j2));
		
		bottomContainer.add(jreset);
		bottomContainer.add(jButton);
		c.add(bottomContainer, BorderLayout.SOUTH);
	}
	
	/**
	 * Invoked when an action occurs.
	 *
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Has fet click al botó de sortir. Adeu que vagi bé!");
		System.exit(0);
	}
}
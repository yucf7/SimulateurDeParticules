package visualisation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Controleur;
import observers.Observable;
import observers.Observateur;

public class VueApplication extends JFrame implements Observateur {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1697573906837405737L;
	private VueChampDeParticules affichageSimulation = null;
	private static final String menu = "Insertion particules";
	private static final String[] libelleTypesParticules = {"Particules A", "Particules B"};
	private static final int[] typesParticules = {0,1};
	private final Controleur controleur ;

	private JMenu m;
	private JOptionPane nbParticules;


	int deA;
	int deB;

	public VueApplication(String lib, Controleur c) {
		super(lib);
		this.controleur = c;
		JMenuBar mb = new JMenuBar();
		m = new JMenu(menu);
		nbParticules = new JOptionPane();


		deA=0;
		deB=0;
		JPanel panel = new JPanel();
		JLabel label=new JLabel("");
		panel.add(label);

		for(int i = 0; i<libelleTypesParticules.length;i++)
		{
			JMenuItem mi = new JMenuItem(libelleTypesParticules[i]);
			final int b = i;
			mi.addActionListener(new ActionListener(){

				@SuppressWarnings("static-access")
				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre = nbParticules.showInputDialog(null, "Saisir le nombre de particules à générer !", "Nombre de particules de type "+libelleTypesParticules[b], JOptionPane.QUESTION_MESSAGE);
					controleur.ajouterPopulation(Integer.parseInt(nombre), typesParticules[b]);

					if (b==0)
					{
						deA += Integer.parseInt(nombre);
					}
					else
					{
						deB+= Integer.parseInt(nombre);
					}

					label.setText(deA+" de "+ libelleTypesParticules[0]+" et "+deB+" de "+ libelleTypesParticules[1]);



				}});
			m.add(mi);

		}
		//

		this.getContentPane().add(panel,BorderLayout.NORTH);;
		mb.add(m);
		this.setJMenuBar(mb);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.affichageSimulation = new VueChampDeParticules(c);
		this.getContentPane().add(this.affichageSimulation,BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
		
	}


	public void majParticulesADessiner() {
		this.affichageSimulation.updateParticulesVisibles();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		this.majParticulesADessiner();
	}

}

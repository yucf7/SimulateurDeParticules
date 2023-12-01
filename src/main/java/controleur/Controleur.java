package controleur;

import java.util.HashMap;
import java.util.List;

import models.particules.*;
import observers.Observable;
import observers.Observateur;
import simulation.Simulateur;
import view.VueApplication;

public class Controleur implements Observateur {


	HashMap<String, Integer> nombreParticules;
	/**
	 * sim est l'objet permettant d'animer le modèle.
	 * C'est à dire qu'il gère le calcul du prochain état de chaque particule ainsi que le déplacement
	 * à l'interieur d'un thread.
	 */

	private Simulateur sim = null;



	/**
	 * champParticules est le modèle de notre application. Dans cet objet nous
	 * retrouvons entre autre toutes les particules.
	 */

	private Champ champParticules;


	/**
	 * Application est la vue de notre application.
	 */
	private VueApplication application;


	public VueApplication getApplication() {
		return application;

	}

	/**
	 * Création d'un champ contenant une population de particules d'une famille
	 *
	 * @param lib     : libelle de la fenetre representant le champ de particule
	 * @param largeur : largeur du champ de particule
	 * @param hauteur : hauteur du champ de particule
	 * @param nb      : nombre de particule a creer initialement
	 * @param type    : type des particules a creer initialement
	 */
	public Controleur(String lib, int largeur, int hauteur, int nb, int type) {
		nombreParticules = new HashMap<String, Integer>();
		champParticules = new ChampDeParticules(largeur, hauteur, nb, type);
		this.sim = new Simulateur(30, this);
		this.application = new VueApplication(lib, this);
	}

	/**
	 * Création d'un champ vide
	 *
	 * @param lib     : libelle de la fenetre representant le champ de particule
	 * @param largeur : largeur du champ de particules
	 * @param hauteur : hauteur du champ de particules
	 */


	public Controleur(String lib, int largeur, int hauteur) {
		champParticules = ChampDeParticules.getInstance(largeur, hauteur);
		this.sim = new Simulateur(30, this);
		this.application = new VueApplication(lib, this);
	}


	/**
	 * Permet de lancer la simulation
	 */
	public void lancerSimulation() {
		this.champParticules.setControleur(this);
		this.sim.demarre();
	}


	/**
	 * Permet d'ajouter une population de particules. Mise à jour de la vue dans la foulée.
	 *
	 * @param nb   : nombre de particules a creer
	 * @param type : type de particules a creer
	 */
	public void ajouterPopulation(int nb, int type) {
		this.champParticules.ajouterUnePopulation(type, nb);
		this.champParticules.updatePopulation();
		this.application.majParticulesADessiner();
	}


	/**
	 * Permet de signaler à la vue qu'il faut se redessiner étant donné qu'il
	 * y a eu des naissances de particules.
	 */

	public void populationEtendueInVivo() {
		this.application.majParticulesADessiner();
	}


	public Champ getchampParticules() {
		return this.champParticules;
	}

	public List<Particule> getPopulationModele() {
		return champParticules.getParticules();
	}

	/**
	 * Permet de lancer une maj au niveau du modèle et de supprimer toutes les particules
	 * décédées.
	 */

	public void majModele() {
		this.champParticules.supprimerLesParticulesDecedees();
	}


	public void majVue() {
		this.application.setNombreParticules(this.nombreParticules);
		this.application.repaint();
	}

	/**
	 * On demande au modèle d'ajouter les nouvelles particules créées au champ de
	 * particules. Puis on redessine le champ.
	 */

	public void integrationNouvelleGeneration() {
		this.champParticules.updatePopulation();
		this.application.repaint();
	}

	@Override
	public void update(Observable o) {
		if (o instanceof ChampDeParticules) {
			ChampDeParticules champ = (ChampDeParticules) o;

			List<Particule> updatedParticles = champ.getParticules();

			HashMap<String, Integer> particleCounts = new HashMap<>();
			int particleCountA = 0;
			int particleCountB = 0;

			// calcul du nombre de particules
			for (Particule particle : updatedParticles) {
				if (particle instanceof ParticuleA) {
					particleCountA++;
				} else if (particle instanceof ParticuleB) {
					particleCountB++;
				}
			}

			particleCounts.put("A", particleCountA);
			particleCounts.put("B", particleCountB);
			this.nombreParticules = particleCounts;
			this.application.updateNombreParticules(this.nombreParticules);
			this.majVue();
		}


	}
}

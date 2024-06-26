package models.particules;

import factories.EtatFactory;
import models.particules.etat.etatParticule.EtatParticule;
import models.particules.etat.etatVisibility.Invisible;
import models.particules.etat.etatVisibility.VisibilityParticule;
import models.particules.etat.etatVisibility.Visible;
import models.particules.etat.phaseParticule.*;


import java.util.ArrayList;
import java.util.List;


public abstract class Particule  {

	protected EtatFactory etatFactory = new EtatFactory();
	protected EtatParticule etatNormal = etatFactory.createEtatNormal();
	protected EtatParticule etatExcite = etatFactory.createEtatExcite();

	protected EtatParticule etatCourant;

	protected boolean isEpileptic;

	public boolean isEpileptic() {
		return isEpileptic;
	}

	public void setEpileptic(boolean epileptic) {
		isEpileptic = epileptic;
	}



	protected PhaseParticule phaseActive = new PhaseActive();
	protected PhaseParticule phaseMorte = new PhaseMorte();
	protected PhaseParticule phaseFINDEVIE = new PhaseFINDEVIE();
	protected PhaseParticule phaseJeune = new PhaseJeune();
	protected PhaseParticule phaseCourante;

	public EtatParticule getEtatExcite() {
		return etatExcite;
	}

	public PhaseParticule getPhaseActive() {
		return phaseActive;
	}

	public EtatParticule getEtatCourant() {
		return etatCourant;
	}

	public PhaseParticule getPhaseCourante() {
		return phaseCourante;
	}

	public void setPhaseCourante(PhaseParticule phaseCourante) {
		this.phaseCourante = phaseCourante;
	}

	public VisibilityParticule ParticuleVisible = new Visible();

	public VisibilityParticule ParticuleInvisible = new Invisible();

	public VisibilityParticule visibilityCourante;

	public Particule() {
		etatCourant = etatNormal;
		phaseCourante = phaseJeune;
	}



	public void gestionEtat() {
		etatCourant.gestionEtat(this);
	}

	public void gestionPhase(){
		phaseCourante.gestionPhase(this);
	}

	public void setPhaseActive() {
		phaseCourante = phaseActive;
	}

	public void setPhaseJeune() {
		phaseCourante = phaseJeune;
	}

	public void setPhaseMorte() {
		phaseCourante = phaseMorte;
	}

	public void setPhaseFINDEVIE() {
		phaseCourante = phaseFINDEVIE;
	}

	public void setEtatNormal() {
		etatCourant = etatNormal;
	}

	public void setEtatExcite() {
		etatCourant = etatExcite;
	}

	public Etat getEtatDeLaParticule() {
		return etatDeLaParticule;
	}

	public void setEtatDeLaParticule(Etat etat) {
		this.etatDeLaParticule = etat;
	}

	public Phase getPhaseDeLaParticule() {
		return phaseDeLaParticule;
	}

	public void setPhaseDeLaParticule(Phase phase) {
		this.phaseDeLaParticule = phase;
	}


	public Visibility getParticuleVisibility() {
		return this.particuleVisibility;
	}

	public void setParticuleVisibility(Visibility particuleVivibility) {
		this.particuleVisibility = particuleVivibility;
	}

	/**
	 * cette variable stocke doit etre reinitialiser a chaque tour de simulation. Elle stocke 
	 * les collisions simples traitees. Si a est en collision simple avec b (et reciproquement), a est stockee dans 
	 * cet ensemble. Ainsi au traitement de b, quand on verra que dans son voisinage direct, il y a a, on ne fera 
	 * pas de nouveau le traitement de la collision. 
	 */
	
	public static  List<Particule> collisionsSimplesTraitees = new ArrayList<Particule>();
	
	public static void oppositeDirection(Particule particule) {
        if (particule.directionCourante > Math.PI) {
            particule.prochaineDirection = particule.directionCourante - Math.PI;
        }
        else { 
            particule.prochaineDirection = particule.directionCourante + Math.PI;
        }
        
    }

	public static final int epaisseur = 10;
	
	
	/**
	 * Par defaut, une particule est dans son etat normal.
	 * Apres collision dans un etat normal, une particule devient excitee.
	 * Elle peut revenir dans son etat normal :
	 * 	- soit apres un certain temps sans nouvelle collision dependant de la nature de 
	 * 	la particule
	 * 	- soit en percutant une autre particule.
	 * @author YohanBoichut
	 *
	 */
	
	public enum Etat {NORMAL, EXCITE};

	/**
	 * Permet de definir dans quelle phase de vie se situe une particule
	 * JEUNE : une particule jeune peut etre excitee apres collision avec une autre particule, mais si 
	 * une nouvelle collision a lieu en etat d'excitation, alors elle redevient normale
	 * ACTIVE : une particule active se comporte comme une particule JEUNE sauf au moment d'une collision en etat 
	 * excite avec une autre particule egalement en etat excite. A cet instant, un evenement special peut avoir lieu : generation 
	 * d'une nouvelle particule, destruction simultanee des deux particules en collision. Cet evenement depend encore une fois de la 
	 * nature des deux particules en collision.
	 * FINDEVIE : une telle particule reste toujours en etat normal meme apres collision. Elle disparait une fois que la periode de 
	 * fin de vie est terminee.
	 * @author YohanBoichut
	 *
	 */
	public enum Phase {JEUNE,ACTIVE,FINDEVIE,MORTE};

	public enum Visibility {
		Visible,
		Invisible
	}

	
	
	/**
	 * Position a l'ecran en x et y
	 */
	protected double x, y;         
	
	/**
	 * Vitesse courante en pixels/s
	 */
	protected double vitesseCourante;   
	
	/**
	 * Direction exprimee en angle (en radians [0 : 2*PI[
	 */
	protected double directionCourante; 
	
	
	
	/**
	 * Variable permettant de savoir dans quelle phase se situe la particule
	 */

	protected Phase phaseDeLaParticule;

	
	
	/**
	 * Variable permettant de savoir dans quel etat se situe la particule
	 */
	protected Etat etatDeLaParticule;

	protected Visibility particuleVisibility;
	
	
	
	protected int nbTour =0;
	
	
	protected double prochaineVitesse;      
	protected double prochaineDirection;  // en radians [0 - 2 PI[
	

	
	protected int passageACTIVE;
	protected int passageFINDEVIE;
	protected int passageMORT;
	
	
	
	

	protected Champ champ;
	
	
	
	public double getX() {
		return x;
	}

	
	public void setX(double x) {
		this.x = x;
	}

	
	public double getY() {
		return y;
	}

	
	public void setY(double y) {
		this.y = y;
	}

	
	
	
	/**
	 * Methode appelee a chaque fois qu'un deplacement est fait
	 */
	
	
	public void gestionCycle() {

		if (this.nbTour == this.passageACTIVE) {
			this.phaseCourante = new PhaseActive();
		}
		
		if (this.nbTour == this.passageFINDEVIE) {
			this.phaseCourante = new PhaseFINDEVIE();
		}
		
		
		if (this.nbTour == this.passageMORT) {
			this.phaseCourante = new PhaseMorte();		}
		
	}


	public void printIt() {
		switch(this.phaseDeLaParticule) {
		case JEUNE : {
			System.out.println("Particule jeune");
			break;
		}
		case ACTIVE : {System.out.println("Particule active");
		break;
		}
		
		case FINDEVIE : {
			System.out.println("Particule Fin de vie");
			break;
		}
		case MORTE : {
			System.out.println("Particule morte");
			break;
		}
		}
	}
	
	
/**
 * Calcul de la prochaine direction et vitesse. Dans le cas normal, aucun changement concernant les 
 * deux champs. Dans le cas d'une collision simple ou multiples, la maj des champs est deleguee aux 
 * methodes associees. 
 * @param champ : liste des particules presentes dans le champ de particules.
 */
	
	
	final public void calculeDeplacementAFaire() {
		nbTour++;
		this.gestionCycle();
		if (!Particule.collisionsSimplesTraitees.contains(this)) {
			if (!this.collisionMultiple(this.champ.getParticules())) {
			
				if (!this.collisionSimple(this.champ.getParticules())) {
					prochaineVitesse = vitesseCourante;
					prochaineDirection = directionCourante;	
				}
			}
		}
	}
	
	
	
	
	
	public void effectueDeplacement() {
		
		
		vitesseCourante = prochaineVitesse;
		directionCourante = prochaineDirection;


			x += (int) (vitesseCourante * Math.cos((double) directionCourante));
			y += (int) (vitesseCourante * Math.sin((double) directionCourante));
			x %= champ.getLargeur();
			y %= champ.getHauteur();
			if (x < 0) {
				x += champ.getLargeur();
			}
			if (y < 0) {
				y += champ.getHauteur();
			}
	}


	public boolean estMorte() {
		return this.phaseCourante instanceof PhaseMorte;
	}

	public boolean estExcitee() {
		return this.etatDeLaParticule == Etat.EXCITE;
	}
	
	/**
	 * 
	 * @param c : particules presentes dans le champ de particules
	 * @return les particules presentes dans le champ d'action de la particule courante.
	 */
	
	protected List<Particule> extraireVoisins(List<Particule> c) {
		List<Particule> result = new ArrayList<Particule>();
		for (Particule p : c) {
			if (p != this && util.DistancesEtDirections.distanceDepuisUnPoint(this.getX(), this.getY(), p.getX(), p.getY()) <= Particule.epaisseur) {
				result.add(p);
			}
		}
		return result;
	}
	
	
	/**
	 * Cette methode retourne l'ensemble des voisins avec lesquels la particule correcte en en collision exclusive.
	 */
	
	protected List<Particule> collisionSimpleBilateral(List<Particule> voisins) {
		
		List<Particule> resultat = new ArrayList<Particule>();
		List<Particule> aRetirer = new ArrayList<Particule>();

		resultat = this.extraireVoisins(voisins);

		for (Particule p : resultat) {
			if (p.extraireVoisins(voisins).size() > 1 ) {


				aRetirer.add(p);
			}
			else {
				if (Particule.collisionsSimplesTraitees.contains(p)) {

					aRetirer.add(p);
				}
			}
		}

		resultat.removeAll(aRetirer);

		return resultat;
		
	}
	
	
	/**
	 * En cas de collision multiple, i.e. plus de deux particules sont au meme endroit a un instant t, 
	 * toutes les particules impliquees repartent en direction opposee dans l'etat excite (peu importe l'etat dans lequel 
	 * la particule etait avant la collision)
	 * @param c : represente l'ensemble des particules presentes dans le champ de particules
	 * @return : retourne vrai si une collision multiple a eu lieu et faux sinon.
	 */
	
	public boolean collisionMultiple(List<Particule> c) {
		List<Particule> voisins = this.extraireVoisins(c);
		if (voisins.size() > 1) {
			
			if (this.directionCourante > Math.PI) 
				this.prochaineDirection = Math.PI - this.directionCourante ;
			else
				this.prochaineDirection = Math.PI + this.directionCourante;
			
			
			if (! (this.etatDeLaParticule == Etat.EXCITE)) { 
				this.etatDeLaParticule = Etat.EXCITE;
				this.augmentationVitesse();
			}
			else {
				this.prochaineVitesse = this.vitesseCourante;
			}
			return true;
		}
		return false;
	}
	
	protected void augmentationVitesse() {
		this.prochaineVitesse = this.vitesseCourante * 1.5;
		
	}


	/**
	 * Cette methode sera appelee lorsqu'une collision a lieu 
	 * entre plusieurs particules.
	 * La variable champ represente l'ensemble des particules presentes dans le champ de particules. 
	 * Les nouvelles entitees eventuellement crees devront etre ajoutees dans le champ de particules.
	 */




	public abstract boolean collisionSimple(List<Particule> champ );


	public abstract void resetVitesse() ;


	private static int counter;
	public void setVisibility(){

		if(this.isEpileptic){

			if (nbTour % 4 < 2) {
				setParticuleVisibility(Visibility.Visible);
			} else {
				setParticuleVisibility(Visibility.Invisible);
			}

		} else {
			setParticuleVisibility(Visibility.Visible);
		}

	}




	public void guerisonEpilepsie(Particule otherParticle) {
		// Guérrir les particules epil

		System.out.println("Guerisé");
		otherParticle.isEpileptic = false;
		otherParticle.etatCourant = etatNormal;
		otherParticle.resetVitesse();

		this.etatCourant = etatNormal;
		this.resetVitesse();

	}




}

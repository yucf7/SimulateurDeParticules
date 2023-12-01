package models.particules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import controleur.Controleur;
import factories.ParticuleFactory;
import observers.Observable;
import observers.Observateur;

public class ChampDeParticules implements Champ, Observable {

	private static ChampDeParticules instance;
	private List<Observateur> observateurs = new ArrayList<>();


	private int largeur;
	private int hauteur;
	private List<Particule> population ;
	private List<Particule> nouvelleGeneration;
	private Controleur controleur ;

	ParticuleFactory factory = new ParticuleFactory();



	private ChampDeParticules(int largeur, int longeur) {
		
		this.largeur = largeur;
		this.hauteur = longeur;
		this.population = new ArrayList<Particule>();
		this.nouvelleGeneration = new ArrayList<Particule>();
	}

	public static ChampDeParticules getInstance(int largeur, int longeur) {
		if (instance == null) {
			instance = new ChampDeParticules(largeur, longeur);
		}
		return instance;
	}
	
	
	public void setControleur(Controleur c) {
		this.controleur = c;
		this.observateurs.add(c);
	}
	
	
	public ChampDeParticules(int largeur, int longeur, int nb, int typeParticule) {
		
		this.largeur = largeur;
		this.hauteur = longeur;
		this.population = new ArrayList<Particule>();
		this.nouvelleGeneration = this.generationParticule(nb,typeParticule);

	}
	
	
	public void ajouterUnePopulation(int type, int nb) {
		this.nouvelleGeneration.addAll(this.generationParticule(nb,type));
		this.notifierObservateurs();
	}
	
	
	
	@Override
	public void naissance(int type, double x, double y) {
		this.nouvelleGeneration.add(this.creationParticule(type, x, y));
		this.controleur.populationEtendueInVivo();
		this.notifierObservateurs();
	}
	
	@Override
	public int getLargeur() {
		return largeur;
	}

	@Override
	public int getHauteur() {
		return hauteur;
	}

	
	
	private Particule creationParticule(int typeParticule, double x, double y) {
		Random generateur = new Random();
		double direction =  (generateur.nextFloat() * 2 * Math.PI);
		
		
		Particule result = null;
		
		switch(typeParticule) {
		case 0: {
			result = factory.createParticuleA(this,x,y,direction);
			break;

		}
		
		case 1: {
			result = factory.createParticuleB(this,x,y,direction);
			break;
		}
		case 2: {
			result = factory.createParticuleC(this, x, y, direction);
			break;
		}
		}
		this.notifierObservateurs();
		return result;
	}
	
	private ArrayList<Particule> generationParticule(int nb, int typeParticule) {
		ArrayList<Particule> nouvelleGeneration = new ArrayList<Particule>();
		Random generateur = new Random();
		int epaisseur = 0;
		
		switch(typeParticule) {
		case 0: {
			epaisseur = ParticuleA.epaisseur;
			break;
		}
		
		case 1: {
			epaisseur = ParticuleB.epaisseur;
			break;
		}
		
		case 2:{
			epaisseur = ParticuleC.epaisseur;
			break;
		}
	}
		
	for (int i = 0; i < nb; i++) {
		int x = (int) (generateur.nextFloat() * largeur);
		if (x > largeur - epaisseur)
			x -= epaisseur;
		int y = (int) (generateur.nextFloat() * hauteur);
		if (y > hauteur - epaisseur)
			y -= epaisseur;

		nouvelleGeneration.add(this.creationParticule(typeParticule, x, y));
		}
		this.notifierObservateurs();
		return nouvelleGeneration;
	}

	@Override
	public List<Particule> getParticules() {
		return population;
	}

	@Override
	public void supprimerLesParticulesDecedees() {
		HashSet<Particule> particulesMortes  = new HashSet<Particule>();
		for(Particule p : this.population) {
			if (p.estMorte()) {
				particulesMortes.add(p);
			}
		}
		
		this.population.removeAll(particulesMortes);
		this.notifierObservateurs();

	}
	
	
	public void updatePopulation() {
		this.population.addAll(this.nouvelleGeneration);
		this.nouvelleGeneration = new ArrayList<Particule>();
	}

	@Override
	public void ajouterObservateur(Observateur observateur) {
		observateurs.add(observateur);
	}

	@Override
	public void supprimerObservateur(Observateur observateur) {
		observateurs.remove(observateur);
	}

	@Override
	public void notifierObservateurs() {
		for (Observateur observateur : observateurs) {
			observateur.update(this);
		}
	}
}

	
	
package models.particules;

import java.util.List;

import controleur.Controleur;
import observers.Observateur;


public interface Champ {
	
	public int getLargeur();
	
	public int getHauteur();
	
	public List<Particule> getParticules();
	
	public void ajouterUnePopulation(int type, int nb);
	
	public void supprimerLesParticulesDecedees();
	
	public void setControleur(Controleur c);

	public void naissance(int i, double x, double y);
	
	public void updatePopulation();

	public void ajouterObservateur(Observateur o);
	public void notifierObservateurs();

}

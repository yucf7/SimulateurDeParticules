package models.particules;

import models.particules.etat.etatParticule.EtatExcite;
import models.particules.etat.etatParticule.EtatNormal;
import models.particules.etat.phaseParticule.PhaseActive;

import java.util.List;


public class ParticuleA extends Particule {


	public ParticuleA (Champ c, double x, double y,
			double dC, boolean isEpileptic) {
		this.champ = c;
		this.x = x;
		this.y = y;
		directionCourante = dC;
		vitesseCourante = 10f;
		prochaineDirection = dC;
		prochaineVitesse = 10f;
		this.passageACTIVE = 500;
		this.passageFINDEVIE = 1500;
		this.passageMORT = 2000;
		this.etatCourant = etatNormal;
		this.visibilityCourante = ParticuleVisible;
		this.isEpileptic = isEpileptic;
	}
	
	


	@Override
	public boolean collisionSimple(List<Particule> c)  {
		
		List<Particule> enCollisionFrontale = this.collisionSimpleBilateral(this.champ.getParticules());
		
		if (enCollisionFrontale.size() != 1) 
			return false;
		else {
			Particule.collisionsSimplesTraitees.add(this);
			Particule.oppositeDirection(this);
			for (Particule p : enCollisionFrontale) {

				Particule.oppositeDirection(p);
				Particule.collisionsSimplesTraitees.add(p);

				if((this.isEpileptic  && this.phaseCourante instanceof PhaseActive) || (p.isEpileptic  && p.phaseCourante == p.phaseActive) ){
					if((p.getClass() != ParticuleC.class)){
						p.isEpileptic = true;
						this.isEpileptic = true;
					}
				}


				if (this.etatCourant instanceof EtatExcite && p.etatCourant instanceof EtatExcite
						&& this.phaseCourante instanceof PhaseActive && p.phaseCourante instanceof PhaseActive
						&& this.isEpileptic && p.getClass() == ParticuleC.class) {
					this.guerisonEpilepsie(this);
				}

				if (p.etatCourant instanceof EtatExcite
						&& this.etatCourant instanceof EtatExcite
						&& p.phaseCourante instanceof PhaseActive &&
						this.phaseCourante instanceof PhaseActive ) {

					if (p.getClass() == ParticuleA.class) {
						this.champ.naissance(0, this.x, this.y);
						p.phaseCourante = phaseMorte;
						this.phaseCourante = phaseMorte;
						phaseCourante.gestionPhase(this);
						phaseCourante.gestionPhase(p);
						this.champ.getParticules().remove(this);
						this.champ.getParticules().remove(p);
						
					}
				
					if (p.getClass() == ParticuleB.class) {
						this.resetVitesse();
						p.resetVitesse();
						this.champ.naissance(0,this.x, this.y);
							
					}


				}
				else  {
					if (this.etatCourant instanceof EtatNormal) {
						this.etatCourant = etatExcite;
						etatCourant.gestionEtat(this);
						this.augmentationVitesse() ;
						
					}
					else {
						this.etatCourant = etatNormal;
						etatCourant.gestionEtat(this);
						this.resetVitesse();
					}
					
					if (p.etatCourant instanceof EtatNormal) {
						this.etatCourant = etatExcite;
						etatCourant.gestionEtat(this);
						p.augmentationVitesse() ;
					} 
					else {
						this.etatCourant = etatNormal;
						etatCourant.gestionEtat(this);
						p.resetVitesse();
					}
					
					
					
				}
				
			}
		}
		
		
		
		return true;
	}





	@Override
	public void resetVitesse() {
		this.prochaineVitesse = 4f;
	}

	public void setProchaineVitesse(double prochaineVitesse){
		this.prochaineVitesse = prochaineVitesse;
	}


}

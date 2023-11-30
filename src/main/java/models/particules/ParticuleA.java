package models.particules;

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
				if (p.etatCourant == etatExcite && this.etatCourant == etatExcite && p.phaseCourante == phaseActive && this.phaseCourante == phaseActive ) {
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
					if (this.etatCourant == etatNormal) {
						this.etatCourant = etatExcite;
						etatCourant.gestionEtat(this);
						this.augmentationVitesse() ;
						
					}
					else {
						this.etatCourant = etatNormal;
						etatCourant.gestionEtat(this);
						this.resetVitesse();
					}
					
					if (p.etatCourant == etatNormal) {
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
		this.prochaineVitesse = 10f;
	}

	public void setProchaineVitesse(double prochaineVitesse){
		this.prochaineVitesse = prochaineVitesse;
	}


}

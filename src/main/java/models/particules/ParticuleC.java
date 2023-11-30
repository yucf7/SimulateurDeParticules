package models.particules;

import java.util.List;

public class ParticuleC extends Particule {

    

    public ParticuleC(Champ c, double x, double y, double dC) {
        this.champ = c;
        this.x = x;
        this.y = y;
        this.directionCourante = dC;
        this.vitesseCourante = 15f;
        this.prochaineDirection = dC;
        this.prochaineVitesse = 15f;
        this.isEpileptic = false;

        this.passageACTIVE = 10;
        this.passageFINDEVIE = 60;
        this.passageMORT = 70;
        this.etatCourant = etatNormal;
    }

    @Override
    public boolean collisionSimple(List<Particule> c) {
        List<Particule> enCollisionFrontale = this.collisionSimpleBilateral(this.champ.getParticules());

        if (enCollisionFrontale.size() != 1){
            return false;
        }
        else {
            Particule.collisionsSimplesTraitees.add(this);
            for(Particule p : enCollisionFrontale){
                if (this.etatCourant == etatExcite && p.etatCourant == etatExcite
                        && this.phaseCourante == phaseActive && p.phaseCourante == phaseActive && p.isEpileptic) {
                    this.guerisonEpilepsie(p);
                }
                else if (this.etatCourant == etatExcite && p.etatCourant == etatExcite) {
                      this.oppositeDirection(p);
                      this.oppositeDirection(this);
                      p.etatCourant = etatNormal;
                      p.resetVitesse();
                      this.etatCourant = etatNormal;
                      this.resetVitesse();
                }
                else if(this.etatCourant == etatExcite && p.etatCourant == etatNormal){
                      this.oppositeDirection(p);
                      this.oppositeDirection(this);
                      p.etatCourant = etatExcite;
                      p.augmentationVitesse();
                      this.etatCourant = etatNormal;
                      this.resetVitesse();
                }
                else if(this.etatCourant == etatNormal && p.etatCourant == etatExcite){
                      this.oppositeDirection(p);
                      this.oppositeDirection(this);
                      p.etatCourant = etatNormal;
                      p.resetVitesse();
                      this.etatCourant = etatExcite;
                      this.setProchaineVitesse(17f);
                }
                else if(this.etatCourant == etatNormal && p.etatCourant == etatNormal){
                      this.oppositeDirection(p);
                      this.oppositeDirection(this);
                      p.etatCourant = etatExcite;
                      p.augmentationVitesse();;
                      this.etatCourant = etatExcite;
                      this.resetVitesse();
                }
            }
            
        }

        return true;
    }

    private void guerisonEpilepsie(Particule otherParticle) {
        // Healing the epileptic particle
        otherParticle.isEpileptic = false;
        otherParticle.etatCourant = etatNormal;
        otherParticle.resetVitesse();
        this.etatCourant = etatNormal;
        otherParticle.resetVitesse();
        this.oppositeDirection(otherParticle);
        this.oppositeDirection(this);
    }


	public void setProchaineVitesse(double prochaineVitesse){
		this.prochaineVitesse = prochaineVitesse;
	}

    @Override
    public void resetVitesse() {
        this.prochaineVitesse = 15f;
    }

    public void oppositeDirection(Particule particule) {
        if (particule.directionCourante > Math.PI) {
            particule.prochaineDirection = particule.directionCourante - Math.PI;
        }
        else { 
            particule.prochaineDirection = particule.directionCourante + Math.PI;
        }
        
    }


}

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

        this.etatCourant = etatNormal;
        this.visibilityCourante = ParticuleVisible;


        this.passageACTIVE = 500;
        this.passageFINDEVIE = 1500;
        this.passageMORT = 2000;
    }

    @Override
    public boolean collisionSimple(List<Particule> c) {
        List<Particule> enCollisionFrontale = this.collisionSimpleBilateral(this.champ.getParticules());



        if (enCollisionFrontale.size() != 1){
            return false;
        }
        else {
            Particule.collisionsSimplesTraitees.add(this);
            Particule.oppositeDirection(this);
            for(Particule p : enCollisionFrontale){

                Particule.oppositeDirection(p);
                Particule.collisionsSimplesTraitees.add(p);
                if ((this.etatCourant == etatExcite) && (p.etatCourant == etatExcite)
                        && (this.phaseCourante == phaseActive) && (p.phaseCourante == phaseActive) && (p.isEpileptic)) {
                    System.out.println("Avant: " + p.isEpileptic);
                    this.guerisonEpilepsie(p);
                    System.out.println("Apres: " + p.isEpileptic);
                }
                else if (this.etatCourant == etatExcite && p.etatCourant == etatExcite) {

                      p.etatCourant = etatNormal;
                      p.resetVitesse();
                      this.etatCourant = etatNormal;
                      this.resetVitesse();
                }
                else if(this.etatCourant == etatExcite && p.etatCourant == etatNormal){

                      p.etatCourant = etatExcite;
                      p.augmentationVitesse();
                      this.etatCourant = etatNormal;
                      this.resetVitesse();
                }
                else if(this.etatCourant == etatNormal && p.etatCourant == etatExcite){

                      p.etatCourant = etatNormal;
                      p.resetVitesse();
                      this.etatCourant = etatExcite;
                      this.setProchaineVitesse(17f);
                }
                else if(this.etatCourant == etatNormal && p.etatCourant == etatNormal){

                      p.etatCourant = etatExcite;
                      p.augmentationVitesse();;
                      this.etatCourant = etatExcite;
                      this.resetVitesse();
                }
            }
            
        }

        return true;
    }




	public void setProchaineVitesse(double prochaineVitesse){
		this.prochaineVitesse = prochaineVitesse;
	}

    @Override
    public void resetVitesse() {
        this.prochaineVitesse = 15f;
    }


}

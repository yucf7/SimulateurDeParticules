package models.particules;

import models.particules.etat.etatParticule.EtatExcite;
import models.particules.etat.etatParticule.EtatNormal;
import models.particules.etat.phaseParticule.PhaseActive;

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

        this.passageACTIVE = 10;
        this.passageFINDEVIE = 50;
        this.passageMORT = 10;
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
                if ((this.etatCourant instanceof EtatExcite) && (p.etatCourant instanceof EtatExcite)
                        && (this.phaseCourante instanceof PhaseActive) && (p.phaseCourante instanceof PhaseActive) && (p.isEpileptic)) {
                    this.guerisonEpilepsie(p);
                }
                else if (this.etatCourant instanceof EtatExcite && p.etatCourant instanceof EtatExcite) {

                      p.etatCourant = etatNormal;
                      p.resetVitesse();
                      this.etatCourant = etatNormal;
                      this.resetVitesse();
                    etatCourant.gestionEtat(this);
                    etatCourant.gestionEtat(p);


                }
                else if(this.etatCourant instanceof EtatExcite && p.etatCourant instanceof EtatNormal){
                      p.etatCourant = etatExcite;
                      p.augmentationVitesse();
                      this.etatCourant = etatNormal;
                      this.resetVitesse();
                    etatCourant.gestionEtat(this);
                    etatCourant.gestionEtat(p);

                }

                else if(this.etatCourant instanceof EtatNormal && p.etatCourant instanceof EtatExcite){

                      p.etatCourant = etatNormal;
                      p.resetVitesse();
                      this.etatCourant = etatExcite;
                    etatCourant.gestionEtat(this);
                    etatCourant.gestionEtat(p);


                    this.setProchaineVitesse(17f);

                }
                else if(this.etatCourant instanceof EtatNormal && p.etatCourant instanceof EtatNormal){

                      p.etatCourant = etatExcite;
                      p.augmentationVitesse();;
                      this.etatCourant = etatExcite;
                      this.resetVitesse();
                      etatCourant.gestionEtat(this);
                    etatCourant.gestionEtat(p);

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

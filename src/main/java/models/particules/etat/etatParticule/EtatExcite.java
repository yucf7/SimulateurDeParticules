package models.particules.etat.etatParticule;

import models.particules.Particule;

public class EtatExcite implements EtatParticule {
    @Override
    public void gestionEtat(Particule particule) {
        particule.setEtatDeLaParticule(Particule.Etat.EXCITE);
    }
}

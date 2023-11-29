package particules.etat.etatParticule;

import particules.Particule;

public class EtatExcite implements EtatParticule {
    @Override
    public void gestionEtat(Particule particule) {
        particule.setEtatDeLaParticule(Particule.Etat.EXCITE);
    }
}

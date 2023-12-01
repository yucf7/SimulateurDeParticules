package factories;

import models.particules.etat.etatParticule.EtatExcite;
import models.particules.etat.etatParticule.EtatNormal;
import models.particules.etat.etatParticule.EtatParticule;

public class EtatFactory {
    public EtatParticule createEtatNormal() {
        return new EtatNormal();
    }

    public EtatParticule createEtatExcite() {
        return new EtatExcite();
    }

}

package factories;

import models.particules.Champ;
import models.particules.Particule;
import models.particules.ParticuleA;
import models.particules.ParticuleB;
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

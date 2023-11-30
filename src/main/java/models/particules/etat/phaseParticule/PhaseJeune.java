package models.particules.etat.phaseParticule;

import models.particules.Particule;

public class PhaseJeune implements PhaseParticule{
    @Override
    public void gestionPhase(Particule particule) {
        particule.setPhaseDeLaParticule(Particule.Phase.JEUNE);
    }
}

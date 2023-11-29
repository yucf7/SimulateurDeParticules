package particules.etat.phaseParticule;

import particules.Particule;

public class PhaseActive implements PhaseParticule{
    @Override
    public void gestionPhase(Particule particule) {
        particule.setPhaseDeLaParticule(Particule.Phase.ACTIVE);
    }
}

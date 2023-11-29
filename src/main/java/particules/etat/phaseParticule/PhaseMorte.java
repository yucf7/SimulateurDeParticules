package particules.etat.phaseParticule;

import particules.Particule;

public class PhaseMorte implements PhaseParticule{
    @Override
    public void gestionPhase(Particule particule) {
        particule.setPhaseDeLaParticule(Particule.Phase.MORTE);
    }
}

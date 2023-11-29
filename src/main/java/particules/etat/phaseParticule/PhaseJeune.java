package particules.etat.phaseParticule;

import particules.Particule;

public class PhaseJeune implements PhaseParticule{
    @Override
    public void gestionPhase(Particule particule) {
        particule.setPhaseDeLaParticule(Particule.Phase.JEUNE);
    }
}

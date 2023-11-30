package factories;

import models.particules.Champ;
import models.particules.Particule;
import models.particules.ParticuleA;
import models.particules.ParticuleB;

public class ParticuleFactory {

    public Particule createParticuleA(Champ c, double x, double y, double dC) {
        return new ParticuleA(c, x, y, dC);
    }

    public Particule createParticuleB(Champ c, double x, double y, double dC) {
        return new ParticuleB(c, x, y, dC);
    }
}

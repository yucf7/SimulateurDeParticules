package factories;

import particules.Champ;
import particules.Particule;
import particules.ParticuleA;
import particules.ParticuleB;

public class ParticuleFactory {

    public Particule createParticuleA(Champ c, double x, double y, double dC) {
        return new ParticuleA(c, x, y, dC);
    }

    public Particule createParticuleB(Champ c, double x, double y, double dC) {
        return new ParticuleB(c, x, y, dC);
    }
}

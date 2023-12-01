package factories;

import models.particules.Champ;
import models.particules.Particule;
import models.particules.ParticuleA;
import models.particules.ParticuleB;
import models.particules.ParticuleC;

public class ParticuleFactory {

    public Particule createParticuleB(Champ c, double x, double y, double dC) {
        if(Math.random() < 0.18){
            return new ParticuleB(c, x, y, dC,true);
        }
        else{
            return new ParticuleB(c, x, y, dC,false);
        }
    }
    public Particule createParticuleA(Champ c, double x, double y, double dC) {
        
        if(Math.random() < 0.80){ // !!!!!!!!!! TO CHANGE TO 0.18
            return new ParticuleA(c, x, y, dC,true);
        }
        else{
            return new ParticuleA(c, x, y, dC,false);
        }
        
    }
    public Particule createParticuleC(Champ c, double x, double y, double dC){
        return new ParticuleC(c, x, y, dC);
    }
}

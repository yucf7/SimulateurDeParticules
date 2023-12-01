package view;

import java.awt.Color;
import java.awt.Graphics;
import models.particules.Particule;
import models.particules.ParticuleA;
import models.particules.ParticuleB;
import models.particules.ParticuleC;
import models.particules.etat.phaseParticule.PhaseActive;

public class ParticulePainter {

    public static void paintParticule(Graphics g, Particule p) {
        Color couleurParticule = determineColor(p);

        int x = (int) p.getX();
        int y = (int) p.getY();
        g.setColor(couleurParticule);

        if (p instanceof ParticuleA) {
            g.fillOval(x - (ParticuleA.epaisseur / 2), y + (ParticuleA.epaisseur / 2), ParticuleA.epaisseur, ParticuleA.epaisseur);
        } else if (p instanceof ParticuleB) {
            g.fillOval(x - (ParticuleB.epaisseur / 2), y + (ParticuleB.epaisseur / 2), ParticuleB.epaisseur, ParticuleB.epaisseur);
        }else if (p instanceof ParticuleC) {
            g.fillOval(x - (ParticuleC.epaisseur / 2), y + (ParticuleC.epaisseur / 2), ParticuleC.epaisseur, ParticuleC.epaisseur);
        }
        if (p.getClass() != ParticuleC.class && p.isEpileptic()) {
            int rayon = 3;
            g.fillOval(x - rayon, y - rayon, rayon * 2, rayon * 2);

        }
        if(p.getPhaseCourante() instanceof PhaseActive){
            int rayon = 3;
            g.fillOval(x + rayon, y + rayon, rayon * 2, rayon * 2);

        }
        if (p.estExcitee()) {
            g.setColor(Color.BLACK);
            g.drawOval(x - (p.epaisseur / 2), y + (p.epaisseur / 2), p.epaisseur, p.epaisseur);
        }
    }

    private static Color determineColor(Particule p) {
        if (p instanceof ParticuleA) {
            return new Color(1.0f, 0.0f, 0.0f);
        } else if (p instanceof ParticuleB) {
            return new Color(0.0f, 1.0f, 0.0f);
        }else if (p instanceof ParticuleC) {
            return new Color(0.0f, 0.0f, 1.0f);
        }
        return null;
    }
}

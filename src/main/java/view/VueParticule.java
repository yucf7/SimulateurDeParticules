package view;

import java.awt.Color;
import java.awt.Graphics;

import models.particules.Particule;
import models.particules.ParticuleA;
import models.particules.ParticuleB;
import models.particules.ParticuleC;

public class VueParticule {


	private Particule p = null;
	private Color couleurParticule = null;
	private boolean estExcitee = false;

	public VueParticule(Particule p) {
		this.p = p;
		if (p.getClass() == ParticuleA.class) {
			this.couleurParticule = new Color(1.0f, 0.0f, 0.0f);
		}

		if (p.getClass() == ParticuleB.class) {
			this.couleurParticule = new Color(0.0f, 1.0f, 0.0f);
		}

		if (p.getClass() == ParticuleC.class) {
			this.couleurParticule = new Color(0.0f, 0.0f, 1.0f);
		}

		if (p.estExcitee()) {
			this.estExcitee = true;
		}


	}


	public Particule getParticule() {
		return p;
	}

	public void seDessine(Graphics g) {
		int x = (int) this.p.getX();
		int y = (int) this.p.getY();
		g.setColor(this.couleurParticule);


		if (p.isEpileptic()) {
			// Rajouter un point au haut de la particule épileptique
			int rayon = 3;
			g.fillOval(x - rayon, y - rayon, rayon * 2, rayon * 2);

		}

		if (p.getClass() == ParticuleA.class) {
			g.fillOval(x - (ParticuleA.epaisseur / 2), y + (ParticuleA.epaisseur / 2), (ParticuleA.epaisseur), ParticuleA.epaisseur);
		}

		if (p.getClass() == ParticuleB.class) {
			g.fillOval(x - (ParticuleB.epaisseur / 2), y + (ParticuleB.epaisseur / 2), (ParticuleB.epaisseur), ParticuleB.epaisseur);
		}

		if (estExcitee) {
			g.setColor(Color.BLACK);
			g.drawOval(x - (ParticuleB.epaisseur / 2), y + (ParticuleB.epaisseur / 2), (ParticuleB.epaisseur), ParticuleB.epaisseur);

			if (p.getClass() == ParticuleC.class) {
				g.fillOval(x - (ParticuleC.epaisseur / 2), y + (ParticuleC.epaisseur / 2), (ParticuleC.epaisseur), ParticuleC.epaisseur);
			}

		}




	}

	public boolean outOfDate() {
		return this.p.estMorte();
	}
}

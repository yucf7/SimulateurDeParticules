package view;

import java.awt.Color;
import java.awt.Graphics;

import models.particules.Particule;
import models.particules.ParticuleA;
import models.particules.ParticuleB;

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
		if (p.getClass() == ParticuleA.class) {
			g.fillOval(x-(ParticuleA.epaisseur/2), y+(ParticuleA.epaisseur/2), (ParticuleA.epaisseur), ParticuleA.epaisseur);
		}

		if (p.getClass() == ParticuleB.class) {
			g.fillOval(x-(ParticuleB.epaisseur/2), y+(ParticuleB.epaisseur/2), (ParticuleB.epaisseur), ParticuleB.epaisseur);
		}

		if (estExcitee) {
			g.setColor(Color.BLACK);
			g.drawOval(x-(ParticuleB.epaisseur/2), y+(ParticuleB.epaisseur/2), (ParticuleB.epaisseur), ParticuleB.epaisseur);
		}

	}

	public boolean outOfDate() {
		return this.p.estMorte();
	}


}
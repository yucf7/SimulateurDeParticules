package view;

import java.awt.*;

import models.particules.Particule;

public class VueParticule {


	private Particule p = null;

	public VueParticule(Particule p) {
		this.p = p;

	}

	public Particule getParticule() {
		return p;
	}

	public void seDessine(Graphics g) {
		ParticulePainter.paintParticule(g, p);
	}

	public boolean outOfDate() {
		return this.p.estMorte();
	}
}

package visualisation;

import java.awt.Graphics;
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

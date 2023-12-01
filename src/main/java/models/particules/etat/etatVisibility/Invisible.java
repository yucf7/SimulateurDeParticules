package models.particules.etat.etatVisibility;

import models.particules.Particule;

public class Invisible implements VisibilityParticule{

    @Override
    public void gestionVisibility(Particule particule) {
        particule.setParticuleVisibility(Particule.Visibility.Invisible);
    }
}

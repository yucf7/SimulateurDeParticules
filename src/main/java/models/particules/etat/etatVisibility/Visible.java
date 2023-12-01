package models.particules.etat.etatVisibility;

import models.particules.Particule;

public class Visible implements VisibilityParticule{
    @Override
    public void gestionVisibility(Particule particule) {
        particule.setParticuleVisibility(Particule.Visibility.Visible);
    }

}

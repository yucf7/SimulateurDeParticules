package observers;

public interface Observable {
    void ajouterObservateur(Observateur observateur);
    void supprimerObservateur(Observateur observateur);
    void notifierObservateurs();
}

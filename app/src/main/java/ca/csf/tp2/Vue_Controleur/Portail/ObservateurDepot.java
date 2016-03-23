package ca.csf.tp2.Vue_Controleur.Portail;

import ca.csf.tp2.Modele.Portail.InterfaceDepotEtudiant;

/**
 * Interface qui fait le lien entre l'activité de départ et le dépot d'étudiants
 * @author Alicia Lamontagne
 */
public interface ObservateurDepot {
    /**
     * Envoi un événement dans l'activité de départ lorsqu'un étudiant a été retiré du dépôt
     * @param interfaceDepotEtudiant l'activité où l'événement doit être envoyé
     */
    public void notifier(InterfaceDepotEtudiant interfaceDepotEtudiant);
}

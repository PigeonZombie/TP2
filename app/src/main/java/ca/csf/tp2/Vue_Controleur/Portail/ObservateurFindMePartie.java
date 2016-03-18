package ca.csf.tp2.Vue_Controleur.Portail;

import ca.csf.tp2.Modele.Portail.InterfaceDepotEtudiant;

/**
 * Created by Utilisateur on 2016-03-14.
 */
public interface ObservateurFindMePartie {
    public void notify(InterfaceDepotEtudiant portailModel);
}

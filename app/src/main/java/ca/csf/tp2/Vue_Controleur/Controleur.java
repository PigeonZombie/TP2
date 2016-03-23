package ca.csf.tp2.Vue_Controleur;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Etudiant;
import ca.csf.tp2.Modele.DepotEtudiant;
import ca.csf.tp2.R;

/**
 * Classe qui fait le lien entre l'activité de départ et le modèle de données.
 * S'occupe de valider les entrées provenant de la vue dans le but de vérifier
 * si le joueur qui utilise l'application fait partie de la liste dans le modèle.
 * @author Alicia Lamontagne
 */
public class Controleur {

    DepotEtudiant depot;
    ActiviteDepart vue;

    /**
     * Constructeur de la classe.
     * @param vue La vue que la classe controle
     */
    public Controleur(ActiviteDepart vue)
    {
        this.vue = vue;
        depot = new DepotEtudiant(vue);

    }

    /**
     *Retire le joueur de la liste des étudiants à trouver lors du début de l'activité.
     *
     * @param code Le code de l'utilisateur à retirer de la liste.
     * @return Le message qui sera affiché dans le toast indiquant au joueur de recommencer ou de commencer l'activité.
     */
    public String validerEntreeUtilisateur(String code) {
        String messagePourUtilisateur = "";
        if (depot.getEtudiantParCode(code) != null) {
            depot.retirerJoueurDeLaListe(code);
            messagePourUtilisateur = vue.getResources().getString(R.string.JoueurInscrit);
        }
        else
            messagePourUtilisateur =vue.getResources().getString(R.string.JoueurPasInscrit);


        return messagePourUtilisateur;
    }

    /**
     * Restaure la liste d'étudiant lors du changement d'orientation.
     * @param liste la liste d'étudiants
     */
    public void restorer(ArrayList<Etudiant> liste){
        depot.restorerEtudiants(liste);
    }
}

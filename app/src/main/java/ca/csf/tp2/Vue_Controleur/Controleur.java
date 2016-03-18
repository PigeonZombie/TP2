package ca.csf.tp2.Vue_Controleur;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Etudiant;
import ca.csf.tp2.Modele.DepotEtudiant;

/**
 * Created by Utilisateur on 2016-03-14.
 */
public class Controleur {

    DepotEtudiant depot;
    ActiviteDepart vue;

    public Controleur(ActiviteDepart vue)
    {
        this.vue = vue;
        depot = new DepotEtudiant(vue);

    }

    public String validerEntreeUtilisateur(String code) {
        String messagePourUtilisateur = "";
        if (depot.getEtudiantParCode(code) != null) {
            depot.retirerJoueurDeLaListe(code);
            messagePourUtilisateur = "You are ready for the activity, please procede.";
        }
        else
            messagePourUtilisateur = "You are not on our list for this activity. Please try again.";


        return messagePourUtilisateur;
    }

    public void restore(ArrayList<Etudiant> liste){
        depot.restoreStudents(liste);
    }
}

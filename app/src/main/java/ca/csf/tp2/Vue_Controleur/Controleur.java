package ca.csf.tp2.Vue_Controleur;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Etudiant;
import ca.csf.tp2.Modele.DepotEtudiant;
import ca.csf.tp2.R;

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
            messagePourUtilisateur = vue.getResources().getString(R.string.JoueurInscrit);
        }
        else
            messagePourUtilisateur =vue.getResources().getString(R.string.JoueurPasInscrit);


        return messagePourUtilisateur;
    }

    public void restorer(ArrayList<Etudiant> liste){
        depot.restorerEtudiants(liste);
    }
}

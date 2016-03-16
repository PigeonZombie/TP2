package ca.csf.tp2.Vue_Controleur;

import android.os.Build;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import ca.csf.tp2.Modele.Etudiant;
import ca.csf.tp2.Modele.DepotEtudiant;

/**
 * Created by Utilisateur on 2016-03-14.
 */
public class Controleur {

    DepotEtudiant depot;
    MainActivity vue;

    public Controleur(MainActivity vue)
    {
        this.vue = vue;
        depot = new DepotEtudiant(vue);
    }

    public String validerEntreeUtilisateur(String code) {
        String messagePourUtilisateur = "";
        if (depot.getEtudiantParCode(code) != null) {
            depot.retirerEtudiantDeLaListe(code);
            messagePourUtilisateur = "You are ready for the activity, please procede.";
        }
        else
            messagePourUtilisateur = "You are not on our list for this activity. Please try again.";

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(Locale.getDefault().getDisplayLanguage(), "English"))
            {


                if (depot.getEtudiantParCode(code) != null) {
                    depot.retirerEtudiantDeLaListe(code);
                    messagePourUtilisateur = "You are ready for the activity, please procede.";
                }
                else
                    messagePourUtilisateur = "You are not on our list for this activity. Please try again.";
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(Locale.getDefault().getDisplayLanguage(), "Francais")) {


                if (depot.getEtudiantParCode(code) != null) {
                    depot.retirerEtudiantDeLaListe(code);
                    messagePourUtilisateur = "Vous etes inscrit à cette activité, veuillez procéder.";
                }

                else
                messagePourUtilisateur = "VOus n'Etes aps inscrit à cette ativité, veuillez réessayer.";
            }
        }*/

        return messagePourUtilisateur;
    }

    public void restore(ArrayList<Etudiant> liste){
        depot.restoreStudents(liste);
    }
}

package ca.csf.tp2.Modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ca.csf.tp2.Modele.Portail.InterfaceDepotEtudiant;
import ca.csf.tp2.Vue_Controleur.Portail.ObservateurDepot;

/**
 * Simulation d'une base de données contenant des étudiants avec leur code unique
 *
 * @author Alicia Lamontagne
 */
public class DepotEtudiant implements InterfaceDepotEtudiant{
    /**
     * Liste contenant un ensemble d'objets Etudiant.
     *
     * @see DepotEtudiant#sauvegarderEtudiant()
     */
    ArrayList<Etudiant> etudiantList;
    /**
     * Le lien vers l'activité de départ
     *
     * @see ca.csf.tp2.Vue_Controleur.ActiviteDepart
     */
    ObservateurDepot observateurDepot;

    /**
     * Constructeur de DepotEtudiant. Initialise 3 nouveaux étudiants
     * et les mets dans la liste, puis mélange cette liste.
     * @param observateurDepot le lien vers l'activité de départ
     * @see DepotEtudiant#etudiantList
     */
    public DepotEtudiant (ObservateurDepot observateurDepot)
    {
        this.observateurDepot = observateurDepot;

        etudiantList = new ArrayList<Etudiant>();

        Etudiant etudiant1 = new Etudiant("BanabaBBQ Mcgehee","aa83dac4c78a");
        Etudiant etudiant2 = new Etudiant("Ron Busse","e8334a6b37c0");
        Etudiant etudiant3 = new Etudiant("Kandy Vangilder","3caea9dbc7c9");
        etudiantList.add(etudiant1);
        etudiantList.add(etudiant2);
        etudiantList.add(etudiant3);
        melangerListe();
    }

    /**
     * Constructeur par défaut utilisé pour les tests avec une liste non mélangée
     */
    public DepotEtudiant(){
        etudiantList = new ArrayList<Etudiant>();

        Etudiant etudiant1 = new Etudiant("BanabaBBQ Mcgehee","aa83dac4c78a");
        Etudiant etudiant2 = new Etudiant("Ron Busse","e8334a6b37c0");
        Etudiant etudiant3 = new Etudiant("Kandy Vangilder","3caea9dbc7c9");
        etudiantList.add(etudiant1);
        etudiantList.add(etudiant2);
        etudiantList.add(etudiant3);
    }

    /**
     * Place les étudiants de la liste dans un ordre aléatoire
     */
    public void melangerListe()
    {
        ArrayList<Etudiant> listeOrginale = new ArrayList<>(etudiantList);
        long seed;
        while(listeOrginale.equals(etudiantList)) {
            seed = System.nanoTime();
            Collections.shuffle(etudiantList, new Random(seed));
        }
    }

    /**
     * Obtient un Etudiant selon le code passé en paramètre.
     * @param code Le code de l'étudiant
     * @return Un étudiant si le code correspond à un Etudiant de la liste,
     * null si l'Etudiant n'existe pas
     * @see DepotEtudiant#etudiantList
     */
    public Etudiant getEtudiantParCode(String code)
    {
        if(code!=null) {
            for (int i = 0; i < etudiantList.size(); i++) {
                if (etudiantList.get(i).getCode().matches(code))
                    return etudiantList.get(i);
            }
        }
        return null;
    }

    /**
     * Enlève un étudiant de liste selon son code passé en paramètre
     * @param code Le code unique de l'Etudiant à retirer
     * @return vrai si un joueur a été retiré, faux sinon
     * @see DepotEtudiant#etudiantList
     */
    public boolean retirerJoueurDeLaListe(String code)
    {
        if(code!=null) {
            for (int i = 0; i < etudiantList.size(); i++) {
                if (etudiantList.get(i).getCode().matches(code)) {
                    etudiantList.remove(i);
                    // Si c'est pour un test, la vue n'est pas avertie
                    if (observateurDepot != null)
                        observateurDepot.notifier(this);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Assigne des nouvelles valeurs à la liste d'étudiants
     * @param etudiants la nouvelle liste d'étudiants
     */
    public void restorerEtudiants(ArrayList<Etudiant> etudiants) {
        this.etudiantList = etudiants;
    }

    /**
     * Obtient la liste d'étudiants pour pouvoir la sauvegarder dans une activité
     * @return la liste d'étudiants
     */
    @Override
    public ArrayList<Etudiant> sauvegarderEtudiant()
    {
        return etudiantList;
    }

}

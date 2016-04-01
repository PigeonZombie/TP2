package ca.csf.tp2.Modele;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Portail.InterfaceMinuteur;
import ca.csf.tp2.Modele.Portail.ObservateurMinuteur;
import ca.csf.tp2.Vue_Controleur.Portail.ObservateurFindMePartie;


/**
 *S'occupe de la logique du jeu, est utilisé pour à récupérer le score, s'occupe des minuteurs
 * confirme qu'un étudiant est dans la liste et le retire.
 *
 * @author Felix
 */
public class FindMePartie implements ObservateurMinuteur {

    private ArrayList<Etudiant> etudiants;
    private int pointage = 0;
    private final ObservateurFindMePartie observateurFindMePartie;
    private final InterfaceMinuteur interfacerMinuteur;

    /**
     * Constructeur de la classe
     * @param etudiants La liste d'étudiant
     * @param observateurFindMePartie quelle activité l'appel
     */
    public FindMePartie(ArrayList<Etudiant> etudiants,
                        ObservateurFindMePartie observateurFindMePartie){
        this.etudiants = etudiants;

        this.interfacerMinuteur = new Minuteur(this);
        this.observateurFindMePartie = observateurFindMePartie;
    }

    /**
     * Constructeur de la classe ne servant qu'aux tests
     * @param etudiants La liste d'étudiant
     * @param observateurFindMePartie quelle activité l'appel
     * @param minuteur le minuteur relié
     */
    public FindMePartie(ArrayList<Etudiant> etudiants,
                        ObservateurFindMePartie observateurFindMePartie, InterfaceMinuteur minuteur){
        this.etudiants = etudiants;

        this.interfacerMinuteur = minuteur;
        this.observateurFindMePartie = observateurFindMePartie;
    }


    /**
     *Vérifie qu'un étudiant fait partie de la liste, le retire si c'est le cas
     * s'occupe du minuteur de temps pour trouver un étudiant et incrémente le score.
     *
     * @param code Le code barre de l'étudiant scanné.
     */
    public void getEtudiantParCode(String code)
    {
        Etudiant etudiantScanne=null;
        //for(int i=0;i< etudiants.size();i++) {
            if (etudiants.get(0).getUniqueIdentifier().matches(code)) {
                etudiantScanne =  etudiants.get(0);
                etudiants.remove(0);
                pointage += interfacerMinuteur.quandEtudiantTrouvee();
                observateurFindMePartie.notifierChangementEtudiantATrouver(getProchainEtudiant());
            }
        //}
        if(etudiantScanne == null)
            observateurFindMePartie.notifierChangementEtudiantATrouver(null);
    }

    /**
     * Retourne le prochain étudiant à trouver
     *
     * @return le prochain étudiant à trouver
     */
    public String getProchainEtudiant()
    {
        if(!etudiants.isEmpty())
        {
            return etudiants.get(0).getFullName();
        }
        return null;
    }

    /**
     * Updater la liste d'étudiant lors du changement d'orientation
     *
     * @param etudiants La liste d'Étudiant
     */
    public void restorerEtudiants(ArrayList<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    /**
     * Obtenir le pointage du joueur
     *
     * @return le pointage du joueur
     */
    public int getPointage(){
        return pointage;
    }

    /**
     * Envoie le message que le temps pour trouver un étudiant est expiré et envoie un nouvel étudiant
     */
    @Override
    public void notifierTempsTrouverEtudiantExpire() {
        etudiants.remove(0);
        observateurFindMePartie.notifierTempsEcoulePourTrouverEtudiant(getProchainEtudiant());
    }

    /**
     * Envoie le message que le temps pour la partie est finie et envoie le pointage.
     */
    @Override
    public void notifierPartieTerminee() {
        observateurFindMePartie.notifierTempsPourLaPartieFinie(pointage);
    }

    /**
     * Envoie à la vue le temps formaté en String sous le format MM:SS indiquant le temps restant pour trouver un joueur
     */
    @Override
    public void notifierChangementAuTempsRestantPourJoueur(long tempsRestant) {

    }

    /**
     * Envoie à la vue le temps formaté en String sous le format MM:SS indiquant le temps restant pour trouver un joueur
     *
     * @param tempsRestant
     */
    @Override
    public void notifierChangementAuTempsRestantPourPartieTotale(long tempsRestant) {

    }


}

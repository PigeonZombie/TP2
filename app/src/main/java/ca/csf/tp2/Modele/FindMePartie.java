package ca.csf.tp2.Modele;

import java.util.ArrayList;
import java.util.Collections;

import ca.csf.tp2.Modele.Portail.InterfaceMinuteur;
import ca.csf.tp2.Modele.Portail.ObservateurMinuteur;
import ca.csf.tp2.Vue_Controleur.Portail.ObservateurFindMePartie;


/**
 * S'occupe de la logique du jeu, est utilisé pour à récupérer le score, s'occupe des minuteurs
 * confirme qu'un étudiant est dans la liste et le retire.
 *
 * @author Felix
 */
public class FindMePartie implements ObservateurMinuteur {

    private ArrayList<Etudiant> etudiants;
    private int pointage = 0;
    private ObservateurFindMePartie observateurFindMePartie;
    private InterfaceMinuteur interfacerMinuteur;

    /**
     * Constructeur de la classe
     *
     * @param etudiants La liste d'étudiant
     */
    public FindMePartie(ArrayList<Etudiant> etudiants) {
        this.etudiants = etudiants;

    }

    /**
     * Constructeur de la classe ne servant qu'aux tests
     *
     * @param etudiants               La liste d'étudiant
     * @param observateurFindMePartie quelle activité l'appel
     * @param minuteur                le minuteur relié
     */
    public FindMePartie(ArrayList<Etudiant> etudiants,
                        ObservateurFindMePartie observateurFindMePartie, InterfaceMinuteur minuteur) {
        this.etudiants = etudiants;

        this.interfacerMinuteur = minuteur;
        this.observateurFindMePartie = observateurFindMePartie;
    }


    /**
     * Vérifie qu'un étudiant fait partie de la liste, le retire si c'est le cas
     * s'occupe du minuteur de temps pour trouver un étudiant et incrémente le score.
     *
     * @param code Le code barre de l'étudiant scanné.
     */
    public void getEtudiantParCode(String code) {
        Etudiant etudiantScanne = null;

        if (etudiants.get(0).getCode().matches(code)) {
            etudiantScanne = etudiants.get(0);
            etudiants.remove(0);
            pointage += interfacerMinuteur.quandEtudiantTrouvee();
            if (observateurFindMePartie != null)
                observateurFindMePartie.notifierChangementEtudiantATrouver(getProchainEtudiant());
        }
        if (etudiantScanne == null)
            observateurFindMePartie.notifierChangementEtudiantATrouver(null);
    }

    public void setObservateurFindMePartie(ObservateurFindMePartie observateurFindMePartie) {
        this.observateurFindMePartie = observateurFindMePartie;
        this.interfacerMinuteur = new Minuteur(this);
        Collections.shuffle(etudiants);
    }

    /**
     * Retourne le prochain étudiant à trouver
     *
     * @return le prochain étudiant à trouver
     */
    public Etudiant getProchainEtudiant() {
        if (!etudiants.isEmpty()) {
            return etudiants.get(0);
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

    public ArrayList<Etudiant> getListeEtudiants() {
        return etudiants;
    }

    /**
     * Obtenir le pointage du joueur
     *
     * @return le pointage du joueur
     */
    public int getPointage() {
        return pointage;
    }

    public void setEtudiantATrouver(Etudiant etudiantATrouver) {
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).getCode().matches(etudiantATrouver.getCode())) {
                etudiants.add(0, etudiantATrouver);
                etudiants.remove(i + 1);
                break;
            }
        }
    }

    /**
     * Envoie le message que le temps pour trouver un étudiant est expiré et envoie un nouvel étudiant
     */
    @Override
    public void notifierTempsTrouverEtudiantExpire() {
        if(etudiants.size()>0) {
            etudiants.remove(0);
            observateurFindMePartie.notifierTempsEcoulePourTrouverEtudiant(getProchainEtudiant().getNom());
        }
    }

    /**
     * Envoie le message que le temps pour la partie est finie et envoie le pointage.
     */
    @Override
    public void notifierPartieTerminee() {
        observateurFindMePartie.notifierTempsPourLaPartieFinie(pointage);
    }

    /**
     * Envoie à la vue le temps  restant pour trouver un joueur
     *
     * @param tempsRestant Le temps restant en long
     */
    @Override
    public void notifierChangementAuTempsRestantPourJoueur(long tempsRestant) {
        //TODO Appeler observateurFindMePartie avec la bonne méthode
    }

    /**
     * Envoie à la vue le temps s restant pour trouver un joueur
     *
     * @param tempsRestant Le temps restant en long
     */
    @Override
    public void notifierChangementAuTempsRestantPourPartieTotale(long tempsRestant) {
        //TODO Appeler observateurFindMePartie avec la bonne méthode
    }

    public boolean enleverEtudiantParCode(String code) {
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).getCode().matches(code)) {
                etudiants.remove(i);
                return true;
            }
        }
        return false;
    }


}

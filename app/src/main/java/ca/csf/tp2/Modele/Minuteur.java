package ca.csf.tp2.Modele;

import java.util.Timer;
import java.util.TimerTask;

import ca.csf.tp2.Modele.Portail.InterfaceMinuteur;
import ca.csf.tp2.Modele.Portail.ObservateurMinuteur;

/**
 * Created by Utilisateur on 2016-03-18.
 */

/**
 * Classe pour faire le minuteur de la partie, contient deux timer Java un pour la partie , et un pour le joueur en cour
 *
 *@author Felix
 */
public class Minuteur implements InterfaceMinuteur {

    public static final int DUREE_PARTIE = 20000;
    public static final int DUREE_TROUVER_ETUDIANT = 5000;
    private ObservateurMinuteur observateurMinuteur = null;
    private Timer minuteurPartie = null;
    private Timer minuteurEtudiant = null;
    private long tempsPourEtudiant = 60000;

    /**
     * Constructeur de la classe minuteur, crée le minuteur globale ainci que la première instance du minuteur de joueur
     * @param _observateurMinuteur
     */
    public Minuteur(ObservateurMinuteur _observateurMinuteur){
        this.observateurMinuteur = _observateurMinuteur;
        minuteurPartie = new Timer();
        creerMinuteurEtudiant();
        minuteurPartie.schedule (initialiserTacheMinuteurPartie(), DUREE_PARTIE);
    }


    /**
     * Quand un étudiant est trouvé, crée un nouveau timer pour un autre étudiant et retourne le temps restant pour
     * trouver l'ancien étudiant pour servire de pointage
     *
     * @return le temps restant pour trovuer l'ancien étudiant
     */
    @Override
    public long quandEtudiantTrouvee() {
        long aRetourner = tempsPourEtudiant;
        minuteurEtudiant.cancel();
        creerMinuteurEtudiant();

        return aRetourner;
    }


    /**
     * Crée la tâche du minuteur globale
     * @return la tâche du minuteur globale
     */
    private TimerTask initialiserTacheMinuteurPartie(){
        TimerTask tacheMinuteurPartie = new TimerTask() {
            @Override
            public void run() {
                observateurMinuteur.notifierPartieTerminee();
            }
        };
        return tacheMinuteurPartie;
    }

    /**
     * Crée la tâche du minuteur du joueur servant à décrémenter le temps restant
     * @return la tâche du minuteur du joueur servant à décrémenter le temps restant
     */
    private TimerTask initialiserTacheMinuteurJoueurScore(){
        TimerTask tacheMinuteurJoueurScore = new TimerTask() {
            @Override
            public void run() {
                tempsPourEtudiant--;

            }
        };
        return tacheMinuteurJoueurScore;
    }

    /**
     * Crée la tâche du minuteur du joueur servant à dire quand la fin du temps pour ce joueur
     * @return la tâche du minuteur du joueur servant à dire quand la fin du temps pour ce joueur
     */
    private TimerTask initialiserTacheMinuteurJoueurTempsTotal(){
        TimerTask tacheMinuteurJoueurTempsTotal = new TimerTask() {
            @Override
            public void run() {
                observateurMinuteur.notifierTempsTrouverEtudiantExpire();
            }
        };
        return tacheMinuteurJoueurTempsTotal;
    }

    /**
     * Crée le minuteur pour le joueur et lui assigne ses tâches.
     */
    private void creerMinuteurEtudiant(){
        minuteurEtudiant = new Timer();
        tempsPourEtudiant = 60000;
        minuteurEtudiant.schedule(initialiserTacheMinuteurJoueurScore(),1);
        minuteurEtudiant.schedule(initialiserTacheMinuteurJoueurTempsTotal(), DUREE_TROUVER_ETUDIANT);
    }
}

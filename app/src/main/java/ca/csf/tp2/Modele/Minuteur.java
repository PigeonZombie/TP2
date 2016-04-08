package ca.csf.tp2.Modele;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import ca.csf.tp2.Modele.Portail.InterfaceMinuteur;
import ca.csf.tp2.Modele.Portail.ObservateurMinuteur;


/**
 * Classe pour faire le minuteur de la partie, contient deux timer Java. Un pour la partie , et un pour le joueur en cour
 *
 *@author Felix
 */
public class Minuteur implements InterfaceMinuteur {

    public static final int DUREE_PARTIE = 120000;
    public static final int DUREE_TROUVER_ETUDIANT = 15000;
    private ObservateurMinuteur observateurMinuteur = null;
    private Timer minuteurPartie = null;
    private Timer minuteurEtudiant = null;
    private long tempsPourEtudiant = DUREE_TROUVER_ETUDIANT;
    private long tempsPourPartieTotale = DUREE_PARTIE;
    private int passagePourDebug =0;

    /**
     * Constructeur de la classe minuteur, crée le minuteur global ainsi que la première instance du minuteur de joueur
     * @param _observateurMinuteur La classe qui observe le déroulement du minuteur
     */
    public Minuteur(ObservateurMinuteur _observateurMinuteur){
        this.observateurMinuteur = _observateurMinuteur;
        minuteurPartie = new Timer();
        Log.v("TIMER","Création d'un thread minuteur Partie dans le constructeur");
        creerMinuteurEtudiant();
        minuteurPartie.schedule(initialiserTacheMinuteurPartie(), DUREE_PARTIE, DUREE_PARTIE);
        minuteurPartie.schedule(initialiserTachePourAfficherTempsPartieTotale(), 1, 1);


    }


    /**
     * Quand un étudiant est trouvé, crée un nouveau timer pour un autre étudiant et retourne le temps restant pour
     * trouver l'ancien étudiant pour servire de pointage
     * @return le temps restant pour trovuer l'ancien étudiant
     */
    @Override
    public long quandEtudiantTrouvee() {
        long aRetourner = tempsPourEtudiant;
        minuteurEtudiant.cancel();
        Log.v("TIMER", "Arret d'un thread minuteur étudiant dans quand étudiant trouvé");
        //minuteurEtudiant = null;
        creerMinuteurEtudiant();
        return aRetourner;
    }

    @Override
    public long[] mettreLesMinuteursEnPause() {
        long[] aRetourner = new long[2];
        aRetourner[0] = tempsPourPartieTotale;
        aRetourner[1] = tempsPourEtudiant;
        minuteurEtudiant.cancel();
        Log.v("TIMER", "Arret d'un thread minuteur étudiant dans la mise en pause");
        minuteurPartie.cancel();
        Log.v("TIMER", "Arret d'un thread minuteur partie dans la mise en pause");

        return aRetourner;
    }

    @Override
    public void repartirLesMinuteurs(long[] tempsRestant) {

        minuteurEtudiant.cancel();
        Log.v("TIMER", "Arret d'un thread minuteur étudiant dans la reprise");
        minuteurPartie.cancel();
        Log.v("TIMER", "Arret d'un thread minuteur partie dans la reprise");

        tempsPourPartieTotale = tempsRestant[0];
        tempsPourEtudiant=tempsRestant[1];

        minuteurPartie = new Timer();
        Log.v("TIMER","Creation d'un thread minuteur partie dans la reprise");
        minuteurPartie.schedule(initialiserTacheMinuteurPartie(), tempsPourPartieTotale);
        minuteurPartie.schedule(initialiserTachePourAfficherTempsPartieTotale(), 1, 1);

        minuteurEtudiant = new Timer();
        Log.v("TIMER","creation d'un thread minuteur étudiant dans la reprise");
        minuteurEtudiant.schedule(initialiserTachePourAfficherTempsRestantEtudiantEtDecrementerScore(), 1, 1);
        minuteurEtudiant.schedule(initialiserTacheMinuteurJoueurTempsTotal(), tempsRestant[1], DUREE_TROUVER_ETUDIANT);
    }


    /**
     * Fonction appelée quand le temps alloué pour trouver un étudiant est fini.
     * Elle appele l'observateur du minuteur.
     */
    private void quandTempsPourTrouverEtudiantExpire(){
        passagePourDebug++;
        tempsPourEtudiant = DUREE_TROUVER_ETUDIANT;
        observateurMinuteur.notifierTempsTrouverEtudiantExpire();
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
    private TimerTask initialiserTachePourAfficherTempsRestantEtudiantEtDecrementerScore(){
        TimerTask tacheMinuteurJoueurScore = new TimerTask() {
            @Override
            public void run() {
                tempsPourEtudiant--;
                observateurMinuteur.notifierChangementAuTempsRestantPourJoueur(tempsPourEtudiant);
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
                quandTempsPourTrouverEtudiantExpire();
            }
        };
        return tacheMinuteurJoueurTempsTotal;
    }

    /**
     * Décrémente d'une miliseconde le temps et l'envoie à l'affichage.
     * @return La tâche initialisée à envoyé au timer
     */
    private TimerTask initialiserTachePourAfficherTempsPartieTotale(){
        TimerTask tacheMinuteurTempsTotalAfficher = new TimerTask() {
            @Override
            public void run() {
                tempsPourPartieTotale--;
                observateurMinuteur.notifierChangementAuTempsRestantPourPartieTotale(tempsPourPartieTotale);
            }
        };
                return tacheMinuteurTempsTotalAfficher;
    }


    /**
     * Crée le minuteur pour le joueur et lui assigne ses tâches.
     */
    private void creerMinuteurEtudiant(){

            minuteurEtudiant = new Timer();
        Log.v("TIMER","Creation d'un thread minuteur étudiant dans la fonction de création");
        tempsPourEtudiant = DUREE_TROUVER_ETUDIANT;
        minuteurEtudiant.schedule(initialiserTachePourAfficherTempsRestantEtudiantEtDecrementerScore(), 1, 1);
        minuteurEtudiant.schedule(initialiserTacheMinuteurJoueurTempsTotal(), DUREE_TROUVER_ETUDIANT, DUREE_TROUVER_ETUDIANT);
    }

}

package ca.csf.tp2.Modele;

import java.util.Timer;
import java.util.TimerTask;

import ca.csf.tp2.Modele.Portail.InterfaceMinuteur;
import ca.csf.tp2.Modele.Portail.ObservateurMinuteur;

/**
 * Created by Utilisateur on 2016-03-18.
 */
public class Minuteur implements InterfaceMinuteur {

    public static final int DUREE_PARTIE = 1200000;
    public static final int DUREE_TROUVER_ETUDIANT = 60000;
    private ObservateurMinuteur observateurMinuteur = null;
    private Timer minuteurPartie = null;
    private Timer minuteurEtudiant = null;
    private long tempsPourEtudiant = 60000;

    public Minuteur(ObservateurMinuteur _observateurMinuteur){
        this.observateurMinuteur = _observateurMinuteur;
        minuteurPartie = new Timer();
        creerMinuteurEtudiant();
        minuteurPartie.schedule (initialiserTacheMinuteurPartie(), DUREE_PARTIE);

    }


    @Override
    public long quandEtudiantTrouvee() {
        long aRetourner = tempsPourEtudiant;
        minuteurEtudiant.cancel();
        creerMinuteurEtudiant();

        return aRetourner;
    }


    private TimerTask initialiserTacheMinuteurPartie(){
        TimerTask tacheMinuteurPartie = new TimerTask() {
            @Override
            public void run() {
                observateurMinuteur.notifierPartieTerminee();
            }
        };
        return tacheMinuteurPartie;
    }

    private TimerTask initialiserTacheMinuteurJoueurScore(){
        TimerTask tacheMinuteurJoueurScore = new TimerTask() {
            @Override
            public void run() {
                tempsPourEtudiant--;

            }
        };
        return tacheMinuteurJoueurScore;
    }

    private TimerTask initialiserTacheMinuteurJoueurTempsTotal(){
        TimerTask tacheMinuteurJoueurTempsTotal = new TimerTask() {
            @Override
            public void run() {
                observateurMinuteur.notifierTempsTrouverEtudiantExpire();
            }
        };
        return tacheMinuteurJoueurTempsTotal;
    }


    private void creerMinuteurEtudiant(){
        minuteurEtudiant = new Timer();
        tempsPourEtudiant = 60000;
        minuteurEtudiant.schedule(initialiserTacheMinuteurJoueurScore(),1);
        minuteurEtudiant.schedule(initialiserTacheMinuteurJoueurTempsTotal(), DUREE_TROUVER_ETUDIANT);
    }
}

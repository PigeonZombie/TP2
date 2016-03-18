package ca.csf.tp2.Modele;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Portail.InterfaceMinuteur;
import ca.csf.tp2.Modele.Portail.ObservateurMinuteur;
import ca.csf.tp2.Vue_Controleur.Portail.ObservateurFindMePartie;


public class FindMePartie implements ObservateurMinuteur {

    private ArrayList<Etudiant> etudiants;
    private int pointage = 0;
    private final long dureeDePartie;
    private final long tempsPourTrouverEtudiant;
    private final ObservateurFindMePartie observateurFindMePartie;
    private final InterfaceMinuteur interfacerMinuteur;

    public FindMePartie(ArrayList<Etudiant> etudiants, Etudiant joueur,
                        long dureeDePartie, long tempsPourTrouverEtudiant, InterfaceMinuteur minuteur,
                        ObservateurFindMePartie observateurFindMePartie){
        this.etudiants = etudiants;
        this. dureeDePartie = dureeDePartie;
        this.tempsPourTrouverEtudiant = tempsPourTrouverEtudiant;
        this.interfacerMinuteur = minuteur;
        this.observateurFindMePartie = observateurFindMePartie;
    }


    public void getEtudiantParCode(String code)
    {
        for(int i=0;i< etudiants.size();i++) {
            if (etudiants.get(i).getCode().matches(code))
                 etudiants.get(i);{
                etudiants.remove(i);
                pointage += interfacerMinuteur.getTempsRestant();
                observateurFindMePartie.notifierChangementEtudiantATrouver(getProchainEtudiant());
            }
        }
    }


    private String getProchainEtudiant()
    {
        if(!etudiants.isEmpty())
        {
            return etudiants.get(0).getName();
        }
        return null;
    }


    public void restoreStudents(ArrayList<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public void commencerPartie(){

    }

    @Override
    public void notifierTempsTrouverEtudiantExpire() {
        Etudiant conteneurTemporaireEtudiant = etudiants.get(0);
        etudiants.remove(0);
        etudiants.add(conteneurTemporaireEtudiant);
        observateurFindMePartie.notifierTempsEcoulePourTrouverEtudiant(getProchainEtudiant());
    }

    @Override
    public void notifierPartieTerminee() {

    }
}

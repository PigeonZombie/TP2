package ca.csf.tp2.Modele;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Portail.ObservateurMinuteur;
import ca.csf.tp2.Vue_Controleur.Portail.ObservateurFindMePartie;


public class FindMePartie implements ObservateurMinuteur {

    private ArrayList<Etudiant> etudiants;
    private final long dureeDePartie;
    private final long tempsPourTrouverEtudiant;
    private final Minuteur findMeMinuteur;
    private final ObservateurFindMePartie observateurFindMePartie;

    public FindMePartie(ArrayList<Etudiant> etudiants, Etudiant joueur,
                        long dureeDePartie, long tempsPourTrouverEtudiant, Minuteur minuteur,
                        ObservateurFindMePartie observateurFindMePartie){
        this.etudiants = etudiants;
        this. dureeDePartie = dureeDePartie;
        this.tempsPourTrouverEtudiant = tempsPourTrouverEtudiant;
        this.findMeMinuteur = minuteur;
        this.observateurFindMePartie = observateurFindMePartie;
    }


    public void getEtudiantParCode(String code)
    {
        for(int i=0;i< etudiants.size();i++) {
            if (etudiants.get(i).getCode().matches(code))
                 etudiants.get(i);{
                etudiants.remove(i);
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

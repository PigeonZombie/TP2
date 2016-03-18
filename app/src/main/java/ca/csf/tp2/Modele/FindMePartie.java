package ca.csf.tp2.Modele;

import java.util.ArrayList;

import ca.csf.tp2.Vue_Controleur.Portail.ObservateurFindMePartie;


public class FindMePartie {

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


    public Etudiant getEtudiantParCode(String code)
    {
        for(int i=0;i< etudiants.size();i++) {
            if (etudiants.get(i).getCode().matches(code))
                return etudiants.get(i);
        }
        return null;
    }


    public String getProchainEtudiant()
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

}

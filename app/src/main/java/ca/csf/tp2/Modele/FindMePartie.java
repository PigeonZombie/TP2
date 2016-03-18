package ca.csf.tp2.Modele;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Portail.ObservateurFindMePartie;
import ca.csf.tp2.Vue_Controleur.Portail.InterfaceVue;


public class FindMePartie implements ObservateurFindMePartie {

    private ArrayList<Etudiant> etudiants;
    private final long dureeDePartie;
    private final long tempsPourTrouverEtudiant;
    private final Minuteur findMeMinuteur;
    private final InterfaceVue interfaceVue;

    public FindMePartie(ArrayList<Etudiant> etudiants, Etudiant joueur,
                        long dureeDePartie, long tempsPourTrouverEtudiant, Minuteur minuteur,
                        InterfaceVue interfaceVue){
        this.etudiants = etudiants;
        this. dureeDePartie = dureeDePartie;
        this.tempsPourTrouverEtudiant = tempsPourTrouverEtudiant;
        this.findMeMinuteur = minuteur;
        this.interfaceVue = interfaceVue;
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

package ca.csf.tp2.Modele;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Portail.InterfaceMinuteur;
import ca.csf.tp2.Modele.Portail.ObservateurMinuteur;
import ca.csf.tp2.Vue_Controleur.Portail.ObservateurFindMePartie;


public class FindMePartie implements ObservateurMinuteur {

    private ArrayList<Etudiant> etudiants;
    private int pointage = 0;
    private final ObservateurFindMePartie observateurFindMePartie;
    private final InterfaceMinuteur interfacerMinuteur;


    public FindMePartie(ArrayList<Etudiant> etudiants,
                        ObservateurFindMePartie observateurFindMePartie){
        this.etudiants = etudiants;

        this.interfacerMinuteur = new Minuteur(this);
        this.observateurFindMePartie = observateurFindMePartie;
    }


    public void getEtudiantParCode(String code)
    {
        Etudiant etudiantScanne=null;
        //for(int i=0;i< etudiants.size();i++) {
            if (etudiants.get(0).getCode().matches(code)) {
                etudiantScanne =  etudiants.get(0);
                etudiants.remove(0);
                pointage += interfacerMinuteur.quandEtudiantTrouvee();
                observateurFindMePartie.notifierChangementEtudiantATrouver(getProchainEtudiant());
            }
        //}
        if(etudiantScanne == null)
            observateurFindMePartie.notifierChangementEtudiantATrouver(null);
    }


    public String getProchainEtudiant()
    {
        if(!etudiants.isEmpty())
        {
            return etudiants.get(0).getName();
        }
        return null;
    }


    public void restorerEtudiants(ArrayList<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public int getScore(){
        return pointage;
    }
    @Override
    public void notifierTempsTrouverEtudiantExpire() {
        etudiants.remove(0);
        observateurFindMePartie.notifierTempsEcoulePourTrouverEtudiant(getProchainEtudiant());
    }

    @Override
    public void notifierPartieTerminee() {
        observateurFindMePartie.notifierTempsPourLaPartieFinie(pointage);
    }
}

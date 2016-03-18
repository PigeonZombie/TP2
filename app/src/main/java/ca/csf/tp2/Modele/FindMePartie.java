package ca.csf.tp2.Modele;

import java.util.ArrayList;
import java.util.List;

import ca.csf.tp2.Modele.Portail.InterfaceDepotEtudiant;
import ca.csf.tp2.Vue_Controleur.Portail.InterfaceVue;


public class FindMePartie implements FindMeMinuteurListener, InterfaceDepotEtudiant {

    private ArrayList<Etudiant> etudiants;
    private final long dureeDePartie;
    private final long tempsPourTrouverEtudiant;
    private final FindMeMinuteur findMeMinuteur;
    private final InterfaceVue interfaceVue;

    public FindMePartie(ArrayList<Etudiant> etudiants, Etudiant joueur,
                        long dureeDePartie, long tempsPourTrouverEtudiant, FindMeMinuteur minuteur,
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

    public void retirerEtudiantDeLaListe(String code)
    {
        for(int i=0;i< etudiants.size();i++) {
            if (etudiants.get(i).getCode().matches(code)) {
                etudiants.remove(i);
                interfaceVue.notify(this);
                break;
            }
        }
    }

    public String getProchainEtudiant()
    {
        if(!etudiants.isEmpty())
        {
            return etudiants.get(0).getName();
        }
        return null;
    }

    @Override
    public String creerVue()
    {
        return "";
    }

    @Override
    public ArrayList<Etudiant> sauvegarderEtudiant()
    {
        return etudiants;
    }

    public void restoreStudents(ArrayList<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public void commencerPartie(){
        findMeMinuteur.demarrerIntervalle(1000,20000,this);
    }

}

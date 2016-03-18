package ca.csf.tp2.Modele;

import java.util.List;


public class FindMePartie implements  FindMeMinuteurListener{

    private final List<Etudiant> etudiants;
    private final Etudiant joueur;
    private final long dureeDePartie;
    private final long tempsPourTrouverEtudiant;
    private final FindMeMinuteur findMeMinuteur;

    public FindMePartie(List<Etudiant> etudiants, Etudiant joueur,
                        long dureeDePartie, long tempsPourTrouverEtudiant, FindMeMinuteur minuteur){
        this.etudiants = etudiants;
        this.joueur = joueur;
        this. dureeDePartie = dureeDePartie;
        this.tempsPourTrouverEtudiant = tempsPourTrouverEtudiant;
        this.findMeMinuteur = minuteur;
    }

    public void testerEtudiant(String codeBarre){

    }

    public void commencerPartie(){
        findMeMinuteur.demarrerIntervalle(1000,20000,this);
    }

}

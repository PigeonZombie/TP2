package ca.csf.tp2.Modele.Portail;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Etudiant;

/**
 * Created by Utilisateur on 2016-03-14.
 */
public interface InterfaceDepotEtudiant {
    public String creerVue();
    public ArrayList<Etudiant> sauvegarderEtudiant();
}

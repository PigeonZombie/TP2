package ca.csf.tp2.Modele.Portail;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Etudiant;

/**
 * Classe qui obtient la liste d'étudiants présente dans le dépôt
 *
 * @author Alicia Lamontagne
 */
public interface InterfaceDepotEtudiant {
    public ArrayList<Etudiant> sauvegarderEtudiant();
}

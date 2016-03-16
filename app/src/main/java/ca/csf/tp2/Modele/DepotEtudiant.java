package ca.csf.tp2.Modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ca.csf.tp2.Modele.Portail.InterfaceDepotEtudiant;
import ca.csf.tp2.Vue_Controleur.Portail.InterfaceVue;

/**
 * Simulation d'une base de données
 */
public class DepotEtudiant implements InterfaceDepotEtudiant {
    ArrayList<Etudiant> etudiantList;
    InterfaceVue interfaceVue;

    public DepotEtudiant(InterfaceVue interfaceVue)
    {
        this.interfaceVue = interfaceVue;

        etudiantList = new ArrayList<Etudiant>();

        Etudiant etudiant1 = new Etudiant("BanabaBBQ Mcgehee","aa83dac4c78a");
        Etudiant etudiant2 = new Etudiant("Ron Busse","e8334a6b37c0");
        Etudiant etudiant3 = new Etudiant("Kandy Vangilder","3caea9dbc7c9");
        etudiantList.add(etudiant1);
        etudiantList.add(etudiant2);
        etudiantList.add(etudiant3);
        melangerListe();
    }

    public Etudiant getEtudiantParCode(String code)
    {
        for(int i=0;i< etudiantList.size();i++) {
            if (etudiantList.get(i).getCode().matches(code))
                return etudiantList.get(i);
        }
        return null;
    }

    public void retirerEtudiantDeLaListe(String code)
    {
        for(int i=0;i< etudiantList.size();i++) {
            if (etudiantList.get(i).getCode().matches(code)) {
                etudiantList.remove(i);
                interfaceVue.notify(this);
                break;
            }
        }
    }

    public String getProchainEtudiant()
    {
        if(!etudiantList.isEmpty())
        {
            return etudiantList.get(0).getName();
        }
        return null;
    }

    private void melangerListe()
    {
        long seed = System.nanoTime();
        Collections.shuffle(etudiantList, new Random(seed));
    }

    public ArrayList<Etudiant> getEtudiantList(){
        return etudiantList;
    }

    @Override
    public String creerVue()
    {
        return "";
    }

    @Override
    public ArrayList<Etudiant> sauvegarderEtudiant()
    {
        return etudiantList;
    }

    public void restoreStudents(ArrayList<Etudiant> etudiants) {
        etudiantList = etudiants;
    }
}

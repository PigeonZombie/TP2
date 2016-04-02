package ca.csf.tp2.Modele;

import junit.framework.TestCase;

/**
 * @author Alicia Lamontagne
 */
public class DepotEtudiantTest extends TestCase {

    /*ArrayList<Etudiant> etudiants;
    DepotEtudiant depotEtudiant;
    ObservateurDepot observateurDepot= new ObservateurDepot() {
        @Override
        public void notifier(InterfaceDepotEtudiant interfaceDepotEtudiant) {

        }
    };

    @Override
    public void setUp(){
        etudiants = new ArrayList<Etudiant>();
        depotEtudiant = new DepotEtudiant();
        etudiants = depotEtudiant.sauvegarderEtudiant();
    }



    @Test
    public void testGetEtudiantParCodeExistant() throws Exception {
        assertEquals(etudiants.get(0), depotEtudiant.getEtudiantParCode("aa83dac4c78a"));
        assertEquals(etudiants.get(1), depotEtudiant.getEtudiantParCode("e8334a6b37c0"));
        assertEquals(etudiants.get(2), depotEtudiant.getEtudiantParCode("3caea9dbc7c9"));
    }

    @Test
    public void testGetEtudiantParCodeInexistant() throws Exception {
        assertEquals(null, depotEtudiant.getEtudiantParCode("aa92dr49fjal"));
        assertEquals(null, depotEtudiant.getEtudiantParCode(""));
        assertEquals(null, depotEtudiant.getEtudiantParCode("      "));
        assertEquals(null, depotEtudiant.getEtudiantParCode(null));
    }

    @Test
    public void testRetirerJoueurExistantDeLaListe() throws Exception {
        assertEquals(true, depotEtudiant.retirerJoueurDeLaListe("aa83dac4c78a"));
        assertEquals(true, depotEtudiant.retirerJoueurDeLaListe("e8334a6b37c0"));
        assertEquals(true, depotEtudiant.retirerJoueurDeLaListe("3caea9dbc7c9"));
    }

    @Test
    public void testRetirerEtudiantsListeVide() throws Exception {
        depotEtudiant.retirerJoueurDeLaListe("aa83dac4c78a");
        depotEtudiant.retirerJoueurDeLaListe("e8334a6b37c0");
        depotEtudiant.retirerJoueurDeLaListe("3caea9dbc7c9");

        assertFalse(depotEtudiant.retirerJoueurDeLaListe("aa83dac4c78a"));
    }

    @Test
    public void testRetirerEtudiantEtudiantInexistant() throws Exception {

        assertEquals(false, depotEtudiant.retirerJoueurDeLaListe("aa84dac4c88b"));
    }

    @Test
    public void testRetirerEtudiantAvecCodeInvalide() throws Exception {

        assertEquals(false, depotEtudiant.retirerJoueurDeLaListe("aa84dac4c88b"));
        assertEquals(false, depotEtudiant.retirerJoueurDeLaListe("aa84c88b"));
        assertEquals(false, depotEtudiant.retirerJoueurDeLaListe(""));
        assertEquals(false, depotEtudiant.retirerJoueurDeLaListe("            "));
        assertEquals(false, depotEtudiant.retirerJoueurDeLaListe(null));
    }


    @Test
    public void testRetirerJoueurValideAvecObservateurExistant(){

        depotEtudiant = new DepotEtudiant(observateurDepot);
        assertEquals(true, depotEtudiant.retirerJoueurDeLaListe("aa83dac4c78a"));
        assertEquals(true, depotEtudiant.retirerJoueurDeLaListe("e8334a6b37c0"));
        assertEquals(true, depotEtudiant.retirerJoueurDeLaListe("3caea9dbc7c9"));

        assertFalse(depotEtudiant.retirerJoueurDeLaListe("aa83dac4c78a"));
    }

    @Test
    public void testRestorerEtudiantsListeVide() throws Exception{
        etudiants = new ArrayList<Etudiant>();
        depotEtudiant.restorerEtudiants(etudiants);
        assertEquals(etudiants, depotEtudiant.sauvegarderEtudiant());
    }

    @Test
    public void testRestorerEtudiantsListeNull() throws Exception{
        etudiants = null;
        depotEtudiant.restorerEtudiants(etudiants);
        assertEquals(null, depotEtudiant.sauvegarderEtudiant());
    }

    @Test
    public void testRestorerEtudiantsListeAvecEtudiants() throws Exception{
        etudiants = new ArrayList<Etudiant>();
        Etudiant e1 = new Etudiant("Allo","123456789000");
        Etudiant e2 = new Etudiant("Allo", "123456789001");
        Etudiant e3 = new Etudiant("Allo","123456789002");
        etudiants.add(e1);
        etudiants.add(e2);
        etudiants.add(e3);
        depotEtudiant.restorerEtudiants(etudiants);
        assertEquals(etudiants, depotEtudiant.sauvegarderEtudiant());
    }


    @Test
    public void testMelangerListeEtudiants() throws Exception{
        ArrayList<Etudiant> listeOriginiale = new ArrayList<>(etudiants);
        depotEtudiant.melangerListe();
        assertFalse(listeOriginiale.equals(etudiants));
    }

    @Test
    public void testConstructeurAvecParametre(){
        depotEtudiant = new DepotEtudiant(observateurDepot);
        assertFalse(etudiants.equals(depotEtudiant.sauvegarderEtudiant()));
    }*/

}
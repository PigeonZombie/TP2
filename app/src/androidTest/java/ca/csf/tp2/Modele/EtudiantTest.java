package ca.csf.tp2.Modele;

import junit.framework.TestCase;

import org.junit.Before;

/**
 * Created by Utilisateur on 2016-03-23.
 */
public class EtudiantTest extends TestCase {

    Etudiant etudiant;

    @Before
    public void before(){
        etudiant = new Etudiant("Etudiant1","123456789000");
    }


    public void testSetCodeValide() throws Exception {
        etudiant.setCode("555555555a5a");
        assertEquals("555555555a5a", etudiant.getCode());
    }

    public void testSetCodeInvalide() throws Exception {
        etudiant.setCode("");
        assertEquals("Defaut", etudiant.getCode());
        etudiant.setCode(" ");
        assertEquals("Defaut", etudiant.getCode());
        etudiant.setCode("            ");
        assertEquals("Defaut", etudiant.getCode());
        etudiant.setCode(null);
        assertEquals("Defaut", etudiant.getCode());
    }

    public void testSetNomValide() throws Exception {

    }

    public void testSetNomInvalide() throws Exception {

    }
}

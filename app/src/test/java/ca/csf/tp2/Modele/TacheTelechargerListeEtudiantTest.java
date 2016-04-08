package ca.csf.tp2.Modele;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * @author Alicia
 */
public class TacheTelechargerListeEtudiantTest extends TestCase implements  TacheTelechargerListeEtudiant.Callback {

    private TacheTelechargerListeEtudiant tacheTelechargement;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TacheTelechargerListeEtudiant telechargerListeEtudiant = new TacheTelechargerListeEtudiant(this);
        telechargerListeEtudiant.execute("https://findme-acodebreak.rhcloud.com/students.json");
    }

    public void testDoInBackgroundMauvaisFormatURL() throws Exception {

    }

    public void testOnPostExecute() throws Exception {

    }

    @Override
    public void onEtudiantsTelecharges(ArrayList etudiants) {

    }
}
package ca.csf.tp2.Modele;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Portail.InterfaceMinuteur;
import ca.csf.tp2.Vue_Controleur.Portail.ObservateurFindMePartie;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Felix on 2016-03-23.
 */
public class FindMePartieTest extends TestCase {
    private  ArrayList<Etudiant> etudiants = new ArrayList<>();
    private long pointage = 0;
    private  ObservateurFindMePartie observateurFindMePartie = null;
    private  InterfaceMinuteur interfacerMinuteur = null;
    private FindMePartie findMePartie = null;


    @Before
            void initialiserPourTest(){
        etudiants.add(new Etudiant("A","1"));
        etudiants.add(new Etudiant("B","2"));
        etudiants.add(new Etudiant("C","3"));
        observateurFindMePartie = mock(ObservateurFindMePartie.class);
        interfacerMinuteur = mock(InterfaceMinuteur.class);
        findMePartie = new FindMePartie(etudiants,observateurFindMePartie);
        when(interfacerMinuteur.quandEtudiantTrouvee()).thenReturn((long) 100);
    }


    @Test
    public void testGetEtudiantParCodeRetraitEtudiant() throws Exception {
        findMePartie.getEtudiantParCode("1");
        assertEquals("B",etudiants.get(0));
    }

    @Test
    public void testGetEtudiantParCodeIncrementerPointage() throws Exception {
        findMePartie.getEtudiantParCode("1");
        assertEquals(pointage,100);
    }

    @Test
    public void testGetEtudiantParCodeRetournerAutreEtudiant() throws Exception {
        findMePartie.getEtudiantParCode("1");
        verify(observateurFindMePartie,times(1)).notifierChangementEtudiantATrouver("B");
    }

    @Test
    public void testGetEtudiantParCodeMauvaisEtudiant() throws Exception {
        findMePartie.getEtudiantParCode("Badger");
        verify(observateurFindMePartie,times(1)).notifierChangementEtudiantATrouver(null);
    }

    @Test
    public void testGetProchainEtudiantListeNonVide() throws Exception {
        Assert.assertEquals("A",findMePartie.getProchainEtudiant());
        etudiants.remove(0);
        Assert.assertEquals("B",findMePartie.getProchainEtudiant());
    }

    @Test
    public void testGetProchainEtudiantListeVide() throws Exception {
        etudiants.clear();
        Assert.assertNull(findMePartie.getProchainEtudiant());

    }

    @Test
    public void testNotifierTempsTrouverEtudiantExpireRetraitEtudiant() throws Exception {
        Assert.assertEquals("B", etudiants.get(0));
    }

    @Test
    public void testNotifierTempsTrouverEtudiantExpireAppelMethodeNotifier() throws Exception {
        verify(observateurFindMePartie,times(1)).notifierTempsEcoulePourTrouverEtudiant("B");
    }

    @Test
    public void testNotifierPartieTerminee() throws Exception {
        verify(observateurFindMePartie,times(1)).notifierTempsPourLaPartieFinie(0);
    }
}
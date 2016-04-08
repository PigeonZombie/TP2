package ca.csf.tp2.Modele;

import android.opengl.ETC1;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Portail.InterfaceMinuteur;
import ca.csf.tp2.Modele.Portail.ObservateurMinuteur;
import ca.csf.tp2.Vue_Controleur.Portail.ObservateurFindMePartie;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Felix on 2016-04-08.
 */
public class MinuteurTest {

    private ObservateurMinuteur observateurMinuteur;



    @Before
    public void setUp() throws Exception {
        observateurMinuteur = mock(ObservateurMinuteur.class);
    }

    @Test
    public void testQuandEtudiantTrouvee() throws Exception {

    }

    @Test
    public void testMettreLesMinuteursEnPause() throws Exception {

    }

    @Test
    public void testObtenirTempsRestantPourLesMinuteurs() throws Exception {

    }
}
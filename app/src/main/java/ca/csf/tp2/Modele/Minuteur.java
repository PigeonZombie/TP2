package ca.csf.tp2.Modele;

/**
 * Created by Utilisateur on 2016-03-18.
 */
public interface Minuteur {
    void demarrerIntervalle(long intervalle, long dureeMax, MinuteurListener listener);
}

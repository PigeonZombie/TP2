package ca.csf.tp2.Vue_Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ca.csf.tp2.R;

/**
 * L'activité de fin permet au joueur de voir son score final et lui
 * indique d'attendre la fin de la partie ou d'en recommencer une.
 * @author Alicia Lamontagne
 */
public class ActiviteFin extends AppCompatActivity {

    /**
     * Le titre annonçant la fin de la partie
     */
    TextView titreFin;
    /**
     * Le texte donnant des instructions au joueur
     */
    TextView texteFin;
    /**
     * Le pointage final du joueur
     */
    Integer pointage;
    /**
     * Le texte qui affiche le score du joueur
     */
    TextView score;
    /**
     * La constante qui permet d'identifier le score du joueur quand
     * il est reçu dans le bundle lors de la création de l'activité
     */
    public static final String SCORE = "SCORE";


    /**
     * Crée la vue et assigne tous les textes dans des attributs.
     * Restore aussi le score si nécessaire.
     * @param savedInstanceState Le bundle contenant les données sauvegardées
     * @see ActiviteFin#titreFin
     * @see ActiviteFin#texteFin
     * @see ActiviteFin#score
     * @see ActiviteFin#pointage
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin);

        titreFin = (TextView)findViewById(R.id.titreFin);
        titreFin.setText(getResources().getString(R.string.TitreFin));
        texteFin = (TextView)findViewById(R.id.texteFin);
        texteFin.setText(getResources().getString(R.string.TexteFin));
        score = (TextView)findViewById(R.id.texteScore);

        Intent extras = getIntent();
        if(extras!=null){
            pointage = extras.getIntExtra(SCORE,0);
            score.setText(pointage.toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Sauvegarde le pointage du joueur pour le restorer à la reprise de l'application.
     * L'attribut utilisé pour sauvegarder le pointage est SCORE
     * @param outState le bundle dans lequel sont sauvegardées les données
     * @see ActiviteFin#SCORE
     * @see ActiviteFin#pointage
     * @see ActiviteFin#onRestoreInstanceState(Bundle)
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SCORE, pointage);
    }

     /**
      * Restore le pointage du joueur et met à jour le texte dans la vue.
      * L'attribut utilisé pour récupérer le pointage est SCORE.
      * @param savedInstanceState le bundle contenant les données sauvegardées
      * @see ActiviteFin#score
      * @see ActiviteFin#pointage
      * @see ActiviteFin#onSaveInstanceState(Bundle)
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        pointage = savedInstanceState.getInt(SCORE);
        score.setText(pointage.toString());
    }


}
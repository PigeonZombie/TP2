package ca.csf.tp2.Vue_Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ca.csf.tp2.R;

/**
 * L'activité de fin permet au joueur de voir son score final et lui
 * indique d'attendre la fin de la partie ou d'en recommencer une.
 */
public class ActiviteFin extends AppCompatActivity {

    // Le titre annonçant la fin de la partie
    TextView titreFin;
    // Le texte donnant des instructions au joueur
    TextView texteFin;
    // Le pointage final du joueur
    Integer pointage;
    // Le texte qui affiche le score du joueur
    TextView score;
    // La constante qui permet d'identifier le score du joueur quand
    // il est reçu dans le bundle lors de la création de l'activité
    public static final String SCORE = "SCORE";

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
     * Sauvegarde le score du joueur
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SCORE, pointage);
    }

    /**
     * Restore le score du joueur
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        pointage = savedInstanceState.getInt(SCORE);
        score.setText(pointage.toString());
    }


}

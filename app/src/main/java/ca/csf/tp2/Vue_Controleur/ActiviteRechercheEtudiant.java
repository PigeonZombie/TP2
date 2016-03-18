package ca.csf.tp2.Vue_Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ca.csf.tp2.Modele.FindMePartie;
import ca.csf.tp2.R;

/**
 * Created by Utilisateur on 2016-03-18.
 */
public class ActiviteRechercheEtudiant extends AppCompatActivity {

    private Button boutonScan;
    private static final int CODE_REQUETE = 42;
    private FindMePartie partie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        boutonScan = (Button)findViewById(R.id.boutonScanEtudiant);
        boutonScan.setOnClickListener(clickScan);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private View.OnClickListener clickScan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Utiliser la caméra pour scanner un code barre
            Intent barCodeIntent = new Intent("com.google.zxing.client.android.SCAN");
            barCodeIntent.putExtra("SCAN_FORMATS","CODE_128");
            startActivityForResult(barCodeIntent, CODE_REQUETE);
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODE_REQUETE){
            if(resultCode == Activity.RESULT_OK){

            }
        }
    }
}

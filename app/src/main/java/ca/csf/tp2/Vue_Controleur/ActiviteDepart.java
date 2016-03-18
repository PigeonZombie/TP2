package ca.csf.tp2.Vue_Controleur;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Portail.InterfaceDepotEtudiant;
import ca.csf.tp2.Modele.Etudiant;
import ca.csf.tp2.R;
import ca.csf.tp2.Vue_Controleur.Portail.ObservateurFindMePartie;

public class ActiviteDepart extends AppCompatActivity implements ObservateurFindMePartie {

    InterfaceDepotEtudiant portalModel;
    Button scanButton;
    Controleur controller;
    public static final int CODE_REQUETE = 42;
    private String codeEtudiant;
    public static final String ETUDIANTS_ACTUELS = "ETUDIANTS_ACTUELS";
    private ArrayList<Etudiant> etudiantsRestants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = (Button)findViewById(R.id.buttonScan);
        scanButton.setOnClickListener(clickScan);
        codeEtudiant = new String("");


        if(savedInstanceState!=null) {
            etudiantsRestants = savedInstanceState.getParcelableArrayList(ETUDIANTS_ACTUELS);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller = new Controleur(this);
        if(etudiantsRestants!=null)
            controller.restore(etudiantsRestants);
        // Établir la connexion à la base de données
        }

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODE_REQUETE){
            if(resultCode == Activity.RESULT_OK){
                codeEtudiant =data.getStringExtra("SCAN_RESULT");
                String messageErreur = controller.validerEntreeUtilisateur(codeEtudiant);
                if(messageErreur==null){
                    Intent partie = new Intent(this, ActiviteRechercheEtudiant.class);
                }
                else{
                    Toast.makeText(ActiviteDepart.this, messageErreur, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(ETUDIANTS_ACTUELS, etudiantsRestants);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        etudiantsRestants = outState.getParcelableArrayList(ETUDIANTS_ACTUELS);
        if(etudiantsRestants!=null)
            controller.restore(etudiantsRestants);
    }

    @Override
    public void notify(InterfaceDepotEtudiant portalModel)
    {
        etudiantsRestants = portalModel.sauvegarderEtudiant();
    }



}


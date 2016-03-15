package ca.csf.tp2.Vue_Controleur;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ca.csf.tp2.Modele.Portail.PortailStudentRep;
import ca.csf.tp2.Modele.StudentRepository;
import ca.csf.tp2.R;
import ca.csf.tp2.Vue_Controleur.Portail.PortalView;

public class MainActivity extends AppCompatActivity implements PortalView{

    PortailStudentRep portalModel;
    Button scanButton;
    Controleur controller;
    public static final int REQUEST_CODE = 42;
    private String studentCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = (Button)findViewById(R.id.buttonScan);
        scanButton.setOnClickListener(clickScan);
        studentCode = new String("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller = new Controleur(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Établir la connexion à la base de données
    }


    private View.OnClickListener clickScan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Utiliser la caméra pour scanner un code barre
            Intent barCodeIntent = new Intent("com.google.zxing.client.android.SCAN");
            barCodeIntent.putExtra("SCAN_FORMATS","CODE_128");
            startActivityForResult(barCodeIntent, REQUEST_CODE);



        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                studentCode =data.getStringExtra("SCAN_RESULT");
                String errorMessage = controller.validateUserInput(studentCode);
                if(errorMessage==null){
                    //commencerPartie
                }
                else{
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void notify(PortailStudentRep portalModel)
    {

    }

}


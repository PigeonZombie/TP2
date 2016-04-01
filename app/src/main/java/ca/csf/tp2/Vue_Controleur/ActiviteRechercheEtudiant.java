package ca.csf.tp2.Vue_Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Etudiant;
import ca.csf.tp2.Modele.FindMePartie;
import ca.csf.tp2.Modele.Portail.InterfaceDepotEtudiant;
import ca.csf.tp2.R;
import ca.csf.tp2.Vue_Controleur.Portail.ObservateurFindMePartie;

/**
 * L'activité de recherche d'étudiant est le coeur de l'application. Elle demande au joueur d'identifier
 * des étudiants en scannant leur code barre. Lorsque tous les étudiants on été trouvés ou que le temps
 * de la partie est écoulé, l'activité de fin est lancée.
 * @author Alicia Lamontagne
 * @author Félix Rivard
 *
 */
public class ActiviteRechercheEtudiant extends AppCompatActivity implements ObservateurFindMePartie {

    /**
     * Le bouton dans la vue servant à ouvrir l'application de scan
     */
    private Button boutonScan;
    /**
     *  Le code qui sert à s'assurer que le résultat de l'activité de scan
     *  est celui auquel on s'attend
     */
    private static final int CODE_REQUETE = 42;
    /**
     * Constante qui permet d'identifier la liste d'étudiants à trouver quand on
     * la passe en extra à un intent
     */
    public static final String ETUDIANTS_ACTUELS = "ETUDIANTS_ACTUELS";
    /**
     * La partie s'occupe de gérer les joueurs, le temps et le pointage
     */
    private FindMePartie partie;
    /**
     * La liste d'étudiants à trouver
     */
    private ArrayList<Etudiant> etudiants;
    /**
     * Le TextView permettant d'afficher le nom de l'étudiant actuellement recherché
     */
    private TextView nomEtudiantAChercher;

    /**
     * Crée la vue, assigne le bouton scan en attribut, crée un nouvelle
     * liste d'étudiants et la restore au besoin, démarre un nouvelle partie
     * et affiche le nom de l'étudiant à trouver
     * @param savedInstanceState le bundle contenant les paramètres sauvegardées
     * @see ActiviteRechercheEtudiant#etudiants
     * @see ActiviteRechercheEtudiant#partie
     * @see ActiviteRechercheEtudiant#nomEtudiantAChercher
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);


        boutonScan = (Button)findViewById(R.id.boutonScanEtudiant);
        boutonScan.setOnClickListener(clickScan);

        Intent extras = getIntent();
        etudiants = new ArrayList<Etudiant>();

        if(extras!=null && etudiants.isEmpty()) {
            etudiants = extras.getParcelableArrayListExtra(ETUDIANTS_ACTUELS);
        }
        else if(savedInstanceState!=null){
            etudiants = savedInstanceState.getParcelableArrayList(ETUDIANTS_ACTUELS);
        }

        partie = new FindMePartie(etudiants,this);

        nomEtudiantAChercher = (TextView)findViewById(R.id.textViewNom);
        nomEtudiantAChercher.setText(partie.getProchainEtudiant());

    }

    /**
     * Restore la liste d'étudiants dans le modèle de données (FindMePartie)
     * @see FindMePartie#restorerEtudiants(ArrayList)
     */
    @Override
    protected void onStart() {
        super.onStart();

        // Si notre liste comprend déjà des étudiants, on la réassigne dans le
        // modèle de données (dans la partie)
        if(etudiants!=null){
            partie.restorerEtudiants(etudiants);
        }
    }

    /**
     * Sauvegarde la liste d'étudiants restants à trouver en la
     * plaçant dans le bundle qui sera restoré lors de la reprise
     * de l'application. La liste est identifiée par l'attribut ETUDIANTS_ACTUELS.
     * @param outState le bundle servant à sauvegarder les paramètres
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(ETUDIANTS_ACTUELS, etudiants);
    }

    /**
     * Restore la liste d'étudiants à trouver, entre autre après un changement d'orientation
     * La liste d'étudiants est récupérée avec l'attribut ETUDIANTS_ACTUELS
     * @param outState le bundle contenant les données sauvegardées.
     * @see ActiviteRechercheEtudiant#etudiants
     * @see ActiviteRechercheEtudiant#ETUDIANTS_ACTUELS
     */
    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);

        etudiants = outState.getParcelableArrayList(ETUDIANTS_ACTUELS);
        if(etudiants!=null)
            partie.restorerEtudiants(etudiants);
    }

    /**
     * Méthode qui lance l'application de scan de code barre lorsque
     * le bouton boutonScan est cliqué
     */
    private View.OnClickListener clickScan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Utiliser la caméra pour scanner un code barre
            Intent barCodeIntent = new Intent("com.google.zxing.client.android.SCAN");
            barCodeIntent.putExtra("SCAN_FORMATS","CODE_128");
            startActivityForResult(barCodeIntent, CODE_REQUETE);
        }
    };

    /**
     * Vérifie, lors du retour de l'application de scan, si le code barre scanné
     * correspond à un élève de la liste à trouver. La partie s'occupe ensuite
     * de choisir le prochain étudiant ou d'afficher un message d'erreur.
     * @param requestCode le code de la requête
     * @param resultCode le résultat de la requête
     * @param data les données dans l'intent
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODE_REQUETE){
            if(resultCode == Activity.RESULT_OK){
                partie.getEtudiantParCode(data.getStringExtra("SCAN_RESULT"));
            }
        }
    }

    /**
     * Événement lancé par FindMePartie. Lors d'un changement d'étudiant à trouver,
     * un nouveau nom est passé en paramètre. Si le nom est null, c'est soit que le
     * mauvais étudiant a été scanné, dans quel cas on affiche un message d'erreur, soit
     * qu'il ne reste plus d'étudiants, dans quel cas on lance l'activité de fin.
     * @param nomEtudiant le nom du nouvel étudiant à trouver
     * @see ActiviteFin
     */
    @Override
    public void notifierChangementEtudiantATrouver(String nomEtudiant) {

        if(nomEtudiant!=null){
            nomEtudiantAChercher.setText(nomEtudiant);
        }
        else if(partie.getProchainEtudiant() == null){
            Intent intent = new Intent(this, ActiviteFin.class);
            intent.putExtra(ActiviteFin.SCORE, partie.getPointage());
            startActivity(intent);
        }
        else{
            Toast.makeText(ActiviteRechercheEtudiant.this, getResources().getString(R.string.MauvaisJoueur), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void notifierTempsEcoulePourTrouverEtudiant(final String nomEtudiant) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                nomEtudiantAChercher.setText(nomEtudiant);
            }
        });
    }

    @Override
    public void notifierTempsPourLaPartieFinie(long pointage) {

//        this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    @Override
    public void notifierEtudiantRetire(InterfaceDepotEtudiant interfaceDepotEtudiant) {


    }


    //TODO Rafrachir et formater le temps restant pour trouver joueur et pour la partie
}

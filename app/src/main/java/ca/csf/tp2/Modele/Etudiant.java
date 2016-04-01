package ca.csf.tp2.Modele;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Classe servant à la simulation d'un étudiant qui sera plus tard obtenu
 * par un service Web. L'étudiant possède un nom et un code. Il implémente
 * la classe Parcelable afin de pouvoir être sauvegardé et récupéré lors
 * des sauvegardes et resaurations d'instances.
 *
 * @author Alicia Lamontagne
 * @see Etudiant#nom
 * @see Etudiant#code
 */
public class Etudiant implements Parcelable{

    /**
     * Le prénom et le nom de famille de l'étudiant.
     * @see Etudiant#getNom()
     * @see Etudiant#setNom(String)
     */
    private String nom;
    /**
     * Le code venant d'un code barre représentant l'étudiant
     * @see Etudiant#code
     * @see Etudiant#setCode(String)
     */
    private String code;

    /**
     * Constructeur de la classe étudiant. Assigne le nom du joueur et son code selon les valeurs
     * passées en paramètre, si non nulles. Si le paramètre est null, l'attribut est initialisé
     * à "defaut".
     * @param nom Le nom du joueur
     * @param code Le code du joueur
     */
    public Etudiant(String nom, String code){
        if(!setNom(nom))
            this.nom = new String("defaut");

        if(!setCode(code))
            this.code  = new String("defaut");
    }

    /**
     * Constructeur privé pour le parcelable
     * @param in
     */
    private Etudiant(Parcel in) {
        nom = in.readString();
        code = in.readString();
    }

    /**
     * Décrit le type d'objet spécial contenu dans le parcelable
     *
     * @return un bitmask indiquant le type d'objet
     */
    @Override
    public int describeContents() {
        return 0;
    }


    /**
     * Écrit l'objet dans un Parcel
     *
     * @param dest  Le Parcel dans lequel l'objet doit être écrit
     * @param flags Flag indiquant comment l'objet doit être écrit
     *              Peut être 0 ou {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(code);
    }

    /**
     * Créateur de parcel
     */
    public static final Parcelable.Creator<Etudiant> CREATOR = new Parcelable.Creator<Etudiant>() {
        public Etudiant createFromParcel(Parcel in) {
            return new Etudiant(in);
        }

        public Etudiant[] newArray(int size) {
            return new Etudiant[size];
        }
    };

    /**
     * Obtient le code de l'étudiant
     * @return le code de l'étudiant
     * @see Etudiant#code
     */
    public String getCode() {
        return code;
    }

    /**
     * Modifie la valeur du code de l'étudiant selon la valeur du paramètre
     * @param code le nouveau code de l'étudiant
     * @see Etudiant#code
     */
    public boolean setCode(String code) {
        if(code!=null) {
            code = code.trim();
            if (code.length() == 12) {
                this.code = code;
                return true;
            }
        }
        return false;
    }

    /**
     * Obtient le nom de l'étudiant
     * @return le nom de l'étudiant
     * @see Etudiant#nom
     */
    public String getNom() {
        return nom;
    }


    public boolean setNom(String nom) {
        if(nom!=null) {
            nom = nom.trim();
            if(nom.length()>0) {
                this.nom = new String(nom);
                return true;
            }
        }
        return false;
    }


}

package ca.csf.tp2.Modele;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Classe servant à la simulation d'un étudiant qui sera plus tard obtenu
 * par un service Web. L'étudiant possède un fullName et un uniqueIdentifier. Il implémente
 * la classe Parcelable afin de pouvoir être sauvegardé et récupéré lors
 * des sauvegardes et resaurations d'instances.
 *
 * @author Alicia Lamontagne
 * @see Etudiant#fullName
 * @see Etudiant#uniqueIdentifier
 */
public class Etudiant implements Parcelable{

    /**
     * Le prénom et le fullName de famille de l'étudiant.
     * @see Etudiant#getFullName()
     * @see Etudiant#setNom(String)
     */
    private String fullName;
    /**
     * Le uniqueIdentifier venant d'un uniqueIdentifier barre représentant l'étudiant
     * @see Etudiant#uniqueIdentifier
     * @see Etudiant#setCode(String)
     */
    private String uniqueIdentifier;

    /**
     * Constructeur de la classe étudiant. Assigne le fullName du joueur et son uniqueIdentifier selon les valeurs
     * passées en paramètre, si non nulles. Si le paramètre est null, l'attribut est initialisé
     * à "defaut".
     * @param fullName Le fullName du joueur
     * @param uniqueIdentifier Le uniqueIdentifier du joueur
     */
    public Etudiant(String fullName, String uniqueIdentifier){
        if(!setNom(fullName))
            this.fullName = new String("defaut");

        if(!setCode(uniqueIdentifier))
            this.uniqueIdentifier = new String("defaut");
    }

    public Etudiant(){

    }

    /**
     * Constructeur privé pour le parcelable
     * @param in
     */
    private Etudiant(Parcel in) {
        fullName = in.readString();
        uniqueIdentifier = in.readString();
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
        dest.writeString(fullName);
        dest.writeString(uniqueIdentifier);
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
     * Obtient le uniqueIdentifier de l'étudiant
     * @return le uniqueIdentifier de l'étudiant
     * @see Etudiant#uniqueIdentifier
     */
    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    /**
     * Modifie la valeur du uniqueIdentifier de l'étudiant selon la valeur du paramètre
     * @param code le nouveau uniqueIdentifier de l'étudiant
     * @see Etudiant#uniqueIdentifier
     */
    public boolean setCode(String code) {
        if(code!=null) {
            code = code.trim();
            if (code.length() == 12) {
                this.uniqueIdentifier = code;
                return true;
            }
        }
        return false;
    }

    /**
     * Obtient le fullName de l'étudiant
     * @return le fullName de l'étudiant
     * @see Etudiant#fullName
     */
    public String getFullName() {
        return fullName;
    }


    public boolean setNom(String nom) {
        if(nom!=null) {
            nom = nom.trim();
            if(nom.length()>0) {
                this.fullName = new String(nom);
                return true;
            }
        }
        return false;
    }


}

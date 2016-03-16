package ca.csf.tp2.Modele;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Simulation d'un étudiant
 */
public class Etudiant implements Parcelable{
    private String name;
    private String code;

    public Etudiant(String name, String code){
        if(name!=null)
            this.name = new String(name);
        else
            name = new String("default");
        this.code = code;
    }

    private Etudiant(Parcel in) {
        name = in.readString();
        code = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(code);
    }

    public static final Parcelable.Creator<Etudiant> CREATOR = new Parcelable.Creator<Etudiant>() {
        public Etudiant createFromParcel(Parcel in) {
            return new Etudiant(in);
        }

        public Etudiant[] newArray(int size) {
            return new Etudiant[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name!=null)
            this.name = new String(name);
        else
            name = new String("default");
    }
}

package ca.csf.tp2.Modele;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Simulation d'un étudiant
 */
public class Student implements Parcelable{
    private String name;
    private String code;

    public Student(String name, String code){
        if(name!=null)
            this.name = new String(name);
        else
            name = new String("default");
        this.code = code;
    }

    private Student(Parcel in) {
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

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
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

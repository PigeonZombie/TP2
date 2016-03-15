package ca.csf.tp2.Modele;

/**
 * Simulation d'un étudiant
 */
public class Student {
    private String name;
    private String code;

    public Student(String name, String code){
        if(name!=null)
            this.name = new String(name);
        else
            name = new String("default");
        this.code = code;
    }

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

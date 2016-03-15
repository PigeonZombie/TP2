package ca.csf.tp2.Vue_Controleur;

import java.util.ArrayList;
import java.util.ResourceBundle;

import ca.csf.tp2.Modele.Student;
import ca.csf.tp2.Modele.StudentRepository;

/**
 * Created by Utilisateur on 2016-03-14.
 */
public class Controleur {

    StudentRepository repository;
    MainActivity vue;

    public Controleur(MainActivity vue)
    {
        this.vue = vue;
        repository = new StudentRepository(vue);
    }

    public String validateUserInput(String code)
    {
        String messagePourUtilisateur;
        if(repository.getStudentByCode(code)!=null) {
            repository.removeStudentFromList(code);
            messagePourUtilisateur ="You are ready for the activity, please procede.";
        }
        else
            messagePourUtilisateur = "You are not on our list for this activity. Please try again.";

        return messagePourUtilisateur;
    }

    public void restore(ArrayList<Student> liste){
        repository.restoreStudents(liste);
    }
}

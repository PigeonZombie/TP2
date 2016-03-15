package ca.csf.tp2.Vue_Controleur;

import java.util.ResourceBundle;

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
        repository = new StudentRepository();
    }

    public String validateUserInput(String code)
    {
        String errorMessage = null;
        if(repository.getStudentByCode(code)!=null)
            repository.removeStudentFromList(code);
        else
            errorMessage = "You are not on our list for this activity. Please try again.";

        return errorMessage;
    }
}

package ca.csf.tp2.Modele.Portail;

import java.util.ArrayList;

import ca.csf.tp2.Modele.Student;

/**
 * Created by Utilisateur on 2016-03-14.
 */
public interface PortailStudentRep {
    public String createView();
    public ArrayList<Student> saveStudents();
}

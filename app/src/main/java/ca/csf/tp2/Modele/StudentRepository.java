package ca.csf.tp2.Modele;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import ca.csf.tp2.Modele.Portail.PortailStudentRep;

/**
 * Simulation d'une base de données
 */
public class StudentRepository implements PortailStudentRep{
    ArrayList<Student> studentList;

    public StudentRepository()
    {
        studentList = new ArrayList<Student>();

        Student student1 = new Student("BanabaBBQ Mcgehee","aa83dac4c78a");
        Student student2 = new Student("Ron Busse","e8334a6b37c0");
        Student student3 = new Student("Kandy Vangilder","3caea9dbc7c9");
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        randomizeList();
    }

    public Student getStudentByCode(String code)
    {
        for(int i=0;i<studentList.size();i++) {
            if (studentList.get(i).getCode().matches(code))
                return studentList.get(i);
        }
        return null;
    }

    public void removeStudentFromList(String code)
    {
        for(int i=0;i<studentList.size();i++) {
            if (studentList.get(i).getCode().matches(code)) {
                studentList.remove(i);
                break;
            }
        }
    }

    public String getNextStudent()
    {
        if(!studentList.isEmpty())
        {
            return studentList.get(0).getName();
        }
        return null;
    }

    private void randomizeList()
    {
        long seed = System.nanoTime();
        Collections.shuffle(studentList, new Random(seed));
    }

    @Override
    public String createView()
    {
        return "";
    }
}

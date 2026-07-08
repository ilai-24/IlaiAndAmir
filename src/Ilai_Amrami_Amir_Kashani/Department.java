package Ilai_Amrami_Amir_Kashani;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Department  implements java.io.Serializable{
    private String name;
    private ArrayList<Lecturer> lecturers;
    private int numOfStudents;

    public Department(String name, int numOfStudents)throws ActionException {
        setName(name);
        setNumOfStudents(numOfStudents);
        this.lecturers = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumOfStudents(int numOfStudents) throws ActionException {
        if (numOfStudents < 0)
            throw new ActionException("number of students cannot be negative.");
        this.numOfStudents = numOfStudents;
    }


    public String getName() {
        return name;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public int getNumOfLecturers() {
        return lecturers.size();
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public void addStudent() {
        this.numOfStudents += 1;
    }

    public int findLecturerIndexByName(String name) {
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void addLecturer(Lecturer lecturer) throws ActionException {
        if (findLecturerIndexByName(lecturer.getName()) != -1) {
            throw new ActionException("Lecturer already exists in this department.");
        }
        if (lecturer.getDepartment() != null)
            lecturer.getDepartment().removeLecturer(lecturer);
        lecturer.setDepartment(this);

        lecturers.add(lecturer);
    }

    public void removeLecturer(Lecturer lecturer) {
        lecturers.remove(lecturer);
    }
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer("Ilai_Amrami_Amir_Kashani.Department: " + name + "\n");
        for (int i = 0; i < lecturers.size(); i++) {
            str.append(" Ilai_Amrami_Amir_Kashani.Lecturer name " + i + ": " + lecturers.get(i).getName() + ", ");
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Department))
            return false;
        Department department = (Department) o;
        return department.getName().equals(name);
    }

}
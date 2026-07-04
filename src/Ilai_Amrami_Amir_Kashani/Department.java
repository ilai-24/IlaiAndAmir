package Ilai_Amrami_Amir_Kashani;

import java.util.Arrays;
import java.util.Objects;

public class Department {
    private String name;
    private Lecturer[] lecturers;
    private int numOfStudents;
    private int numOfLecturers = 0;

    public Department(String name, int numOfStudents)throws ActionException {
        setName(name);
        setNumOfStudents(numOfStudents);
        this.lecturers = new Lecturer[1];
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    public void setNumOfStudents(int numOfStudents) throws ActionException {
        if (numOfStudents < 0)
            throw new ActionException("number of students cannot be negative.");
        this.numOfStudents = numOfStudents;
    }

    public void setNumOfLecturers(int numOfLecturers) {
        this.numOfLecturers = numOfLecturers;
    }

    public String getName() {
        return name;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public int getNumOfLecturers() {
        return numOfLecturers;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public void addStudent() {
        this.numOfStudents += 1;
    }

    public int findLecturerIndexByName(String name) {
        for (int i = 0; i < numOfLecturers; i++) {
            if (lecturers[i].getName().equals(name)) {
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
        Lecturer[] temp = new Lecturer[lecturers.length * 2];
        for (int i = 0; i < numOfLecturers; i++) {
            temp[i] = lecturers[i];
        }
        temp[numOfLecturers] = lecturer;
        numOfLecturers++;
        lecturers = temp;
    }

    public void removeLecturer(Lecturer lecturer) {
        int index = findLecturerIndexByName(lecturer.getName());
        if (index == -1) {
            return;
        }
        numOfLecturers--;
        Lecturer temp = lecturers[numOfLecturers];
        lecturers[numOfLecturers] = null;
        if (index != numOfLecturers) {
            lecturers[index] = temp;
        }
    }
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer("Ilai_Amrami_Amir_Kashani.Department: " + name + "\n");
        for (int i = 0; i < numOfLecturers; i++) {
            str.append(" Ilai_Amrami_Amir_Kashani.Lecturer name " + i + ": " + lecturers[i].getName() + ", ");
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

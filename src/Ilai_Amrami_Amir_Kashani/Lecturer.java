package Ilai_Amrami_Amir_Kashani;

import java.util.ArrayList;

public abstract class Lecturer implements java.io.Serializable{
    protected String name;
    protected int id;
    protected String degreeName;
    protected Department department;
    protected double salary;
    protected ArrayList<Committee<?>> committees;


    public Lecturer(String name,int id,String degreeName,double salary)throws ActionException {
        setName(name);
        setId(id);
        setDegreeName(degreeName);
        setSalary(salary);
        committees=new ArrayList<>();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary)throws  ActionException {
        if(salary<0)
            throw new ActionException("The salary cant be negative.Try again");
        this.salary = salary;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setId(int id)throws ActionException {
        if(id<0)
            throw new ActionException("The ID cant be negative.Try again");
        this.id=id;
    }
    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;

    }

    public void setDepartment(Department department){
        this.department=department;
    }




    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public Department getDepartment() {
        return department;
    }

    public int getNumOfLecturerCommittees() {
        return committees.size();
    }

    public ArrayList<Committee<?>> getCommittees() {
        return committees;
    }
    public void addCommittee(Committee committee){
        committees.add(committee);

    }
    public void removeCommittee(Committee committee){
        committees.remove(committee);
    }
    public int findCommitteeIndexByName(String name) {
        for (int i=0;i<committees.size();i++)
            if (name.equals(committees.get(i).getName()))
                return i;
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Lecturer)){
            return false;
        }
        Lecturer lecturer=(Lecturer)obj;
        return name.equals(lecturer.getName()) && id==lecturer.getId();
    }
    @Override
    public abstract String toString();

}

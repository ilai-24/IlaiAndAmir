package Ilai_Amrami_Amir_Kashani;

import java.util.ArrayList;

public abstract class Lecturer {
    protected String name;
    protected int id;
    protected String degreeName;
    protected Department department;
    protected double salary;
    protected Committee<?>[]committees;
    protected int numOfLecturerCommittees;


    public Lecturer(String name,int id,String degreeName,double salary)throws ActionException {
        setName(name);
        setId(id);
        setDegreeName(degreeName);
        setSalary(salary);
        committees=new Committee[1];
        numOfLecturerCommittees=0;
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
        return numOfLecturerCommittees;
    }

    public Committee[] getCommittees() {
        return committees;
    }
    public void addCommittee(Committee committee){
        Committee[]temp=new Committee[committees.length*2];
        for (int i=0;i<numOfLecturerCommittees;i++)
            temp[i]=committees[i];
        temp[numOfLecturerCommittees]=committee;
        numOfLecturerCommittees++;
        committees=temp;
    }
    public void removeCommittee(Committee committee){
        if (numOfLecturerCommittees==0)
            return;
        int index = findCommitteeIndexByName(committee.getName());

        numOfLecturerCommittees--;
        Committee temp = committees[numOfLecturerCommittees];
        committees[numOfLecturerCommittees] = null;
        committees[index] =temp;
    }
    public int findCommitteeIndexByName(String name) {
        for (int i=0;i<numOfLecturerCommittees;i++)
            if (name.equals(committees[i].getName()))
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

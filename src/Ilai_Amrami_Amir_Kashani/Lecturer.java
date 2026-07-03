package Ilai_Amrami_Amir_Kashani;

import java.util.Arrays;
import java.util.Objects;

public class Lecturer {
    public enum eTitle{Bachelor, Master, Doctor,Professor}
    private eTitle title;
    private String name;
    private int id;
    private String degreeName;
    private Department department;
    private double salary;
    private Committee[]committees;
    private int numOfLecturerCommittees;


    public Lecturer(String name,int id,eTitle title,String degreeName,double salary)throws ActionException {
        setName(name);
        setId(id);
        setTitle(title);
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
    public void setTitle(eTitle title){

        this.title=title;
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

    public eTitle getTitle() {
        return title;
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

    public String toString() {
        String department_str ="none";
        if(department !=null)
            department_str=department.getName();
        StringBuffer str=new StringBuffer("name: "+name+", id: "+id+", title: "+title+", degree name: "+degreeName+", salary: "+salary+",department: "+department_str+",committees: ");
        for (int i=0;i<numOfLecturerCommittees;i++)
            str.append(committees[i].getName()+", ");
        return str.toString();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Lecturer lecturer = (Lecturer) o;
//        return id == lecturer.id && Double.compare(salary, lecturer.salary) == 0 && numOfLecturerCommittees == lecturer.numOfLecturerCommittees && numOfArticles == lecturer.numOfArticles && title == lecturer.title && Objects.equals(name, lecturer.name) && Objects.equals(degreeName, lecturer.degreeName) && Objects.equals(department, lecturer.department) && Objects.deepEquals(committees, lecturer.committees) && Objects.deepEquals(articles, lecturer.articles) && Objects.equals(professionInstitute, lecturer.professionInstitute);
//    }

}

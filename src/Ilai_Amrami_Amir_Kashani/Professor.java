package Ilai_Amrami_Amir_Kashani;

public class Professor extends Doctor {
    private String grantedProfessor;


    public Professor(String name, int id, String degreeName, double salary, String article) throws ActionException {
        super(name, id, degreeName, salary);
        this.grantedProfessor=grantedProfessor;
    }
    public String toString() {
        String department_str ="none";
        if(department !=null)
            department_str=department.getName();
        StringBuffer str=new StringBuffer("name: "+name+", id: "+id+", title: Professor"+", degree name: "+this.degreeName+", salary: "+salary+",department: "+department_str+",committees: ");
        for (int i=0;i<numOfLecturerCommittees;i++)
            str.append(committees[i].getName()+", ");
        return str.toString();
    }
}

package Ilai_Amrami_Amir_Kashani;

public class Professor extends HighDegrees {
    private String grantedProfessor;

    public Professor(String name, int id, String degreeName, double salary, String grantedProfessor) throws ActionException {
        super(name, id, degreeName, salary);
        this.grantedProfessor=grantedProfessor;
    }

    public String toString() {
        String department_str ="none";
        if(department !=null)
            department_str=department.getName();
        StringBuffer str=new StringBuffer("name: "+name+", id: "+id+", title: Professor"+", degree name: "+this.degreeName+", salary: "+salary+" ,granted Professor from:"+grantedProfessor+" ,department: "+department_str+",committees: ");
        for (int i=0;i<numOfLecturerCommittees;i++)
            str.append(committees[i].getName()+", ");
        str.append(", articles: ");
        for (int j=0;j<articlesNum;j++)
            str.append(articles[j]+", ");
        return str.toString();
    }
}

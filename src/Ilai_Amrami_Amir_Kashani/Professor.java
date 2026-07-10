package Ilai_Amrami_Amir_Kashani;

public class Professor extends HighDegrees implements java.io.Serializable{
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
        for (int i=0;i<committees.size();i++)
            str.append(committees.get(i).getName()+", ");
        str.append(", articles: ");
        for (int j=0;j<articles.size();j++)
            str.append(articles.get(j)+", ");
        return str.toString();
    }
}

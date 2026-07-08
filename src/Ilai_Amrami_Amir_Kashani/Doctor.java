package Ilai_Amrami_Amir_Kashani;

public class Doctor extends HighDegrees implements java.io.Serializable {

    public Doctor(String name, int id, String degreeName, double salary) throws ActionException {
        super(name, id, degreeName, salary);

    }

    @Override
    public String toString() {
        String department_str ="none";
        if(department !=null)
            department_str=department.getName();
        StringBuffer str=new StringBuffer("name: "+name+", id: "+id+", title: Doctor"+", degree name: "+this.degreeName+", salary: "+salary+ " ,department: "+department_str+",committees: ");
        for (int i=0;i<numOfLecturerCommittees;i++)
            str.append(committees[i].getName()+", ");
        str.append(", articles: ");
        for (int j=0;j<articlesNum;j++)
            str.append(articles[j]+", ");
        return str.toString();
    }

}

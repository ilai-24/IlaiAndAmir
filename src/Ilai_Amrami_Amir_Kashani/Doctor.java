package Ilai_Amrami_Amir_Kashani;

public class Doctor extends Lecturer {
    protected String[] articles;


    public Doctor(String name, int id, String degreeName, double salary) throws ActionException {
        super(name, id, degreeName, salary);
        this.articles=new String[1];
    }

    @Override
    public String toString() {
        String department_str ="none";
        if(department !=null)
            department_str=department.getName();
        StringBuffer str=new StringBuffer("name: "+name+", id: "+id+", title:Doctor "+", degree name: "+degreeName+", salary: "+salary+",department: "+department_str+",committees: ");
        for (int i=0;i<numOfLecturerCommittees;i++)
            str.append(committees[i].getName()+", ");
        return str.toString();
    }


}

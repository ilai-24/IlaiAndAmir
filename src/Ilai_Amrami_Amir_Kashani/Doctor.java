package Ilai_Amrami_Amir_Kashani;

public class Doctor extends Lecturer {
    protected String[] articles;
    protected int articlesNum;


    public Doctor(String name, int id, String degreeName, double salary) throws ActionException {
        super(name, id, degreeName, salary);
        this.articles=new String[1];
        articlesNum=0;
    }

    public void addArticles(String article) {
        String[]temp=new String[articles.length*2];

        for(int i=0;i<articles.length;i++){
            temp[i]=articles[i];
        }
        articles=temp;
        articles[articlesNum]=article;
        articlesNum++;
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

package Ilai_Amrami_Amir_Kashani;

public class Doctor extends Lecturer implements Comparable<Doctor> {
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

    public int getArticlesNum() {
        return articlesNum;
    }

    public boolean isMoreArticles(Doctor doctor) {
        if(doctor.getArticlesNum()>articlesNum){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String department_str ="none";
        if(department !=null)
            department_str=department.getName();
        StringBuffer str=new StringBuffer("name: "+name+", id: "+id+", title:Doctor "+", degree name: "+degreeName+", salary: "+salary+",department: "+department_str+",committees: ");
        for (int i=0;i<numOfLecturerCommittees;i++)
            str.append(committees[i].getName()+", ");
        str.append(", articles: ");
        for (int j=0;j<articlesNum;j++)
            str.append(articles[j]+", ");

        return str.toString();
    }
    @Override
    public int compareTo(Doctor doctor) {
        if (doctor.getArticlesNum()>articlesNum) {
            return -1;
        }
        if (doctor.getArticlesNum()<articlesNum) {
            return 1;
        }
        return 0;
    }
}

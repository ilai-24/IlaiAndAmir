package Ilai_Amrami_Amir_Kashani;

public abstract class HighDegrees extends Lecturer implements Comparable<HighDegrees> {
    protected String[] articles;
    protected int articlesNum;


    public HighDegrees(String name, int id, String degreeName, double salary) throws ActionException {
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
    public int compareTo(HighDegrees HighDegree) {
        if (HighDegree.getArticlesNum()>articlesNum) {
            return -1;
        }
        if (HighDegree.getArticlesNum()<articlesNum) {
            return 1;
        }
        return 0;
    }

}

package Ilai_Amrami_Amir_Kashani;

import java.util.ArrayList;

public abstract class HighDegrees extends Lecturer implements Comparable<HighDegrees>, java.io.Serializable{
    protected ArrayList<String> articles;


    public HighDegrees(String name, int id, String degreeName, double salary) throws ActionException {
        super(name, id, degreeName, salary);
        articles=new ArrayList<>();
    }

    public void addArticles(String article) {
        articles.add(article);
    }

    public int getArticlesNum() {
        return articles.size();
    }

    public boolean isMoreArticles(Doctor doctor) {
        if(doctor.getArticlesNum()>articles.size()){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(HighDegrees HighDegree) {
        if (HighDegree.getArticlesNum()>articles.size()) {
            return -1;
        }
        if (HighDegree.getArticlesNum()<articles.size()) {
            return 1;
        }
        return 0;
    }

}

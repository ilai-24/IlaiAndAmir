package Ilai_Amrami_Amir_Kashani;

import java.util.Comparator;

public class CompareCommitteesByArticles implements Comparator<Committee> {
    @Override
    public int compare(Committee committee1, Committee committee2){
        int articles1=committee1.getNumArticles();
        int articles2=committee2.getNumArticles();
        if (articles1==articles2)
            return 0;
        if(articles1>articles2)
            return 1;
        return -1;

    }
}

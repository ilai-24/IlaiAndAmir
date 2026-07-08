package Ilai_Amrami_Amir_Kashani;

import java.util.Comparator;

public class CompareCommitteesByFriends implements Comparator<Committee>,java.io.Serializable {
    @Override
    public int compare(Committee committee1, Committee committee2) {
        if (committee1.getNumOfFriends() ==committee2.getNumOfFriends())
            return 0;
        if (committee1.getNumOfFriends()>committee2.getNumOfFriends())
            return 1;
        return -1;

    }
}

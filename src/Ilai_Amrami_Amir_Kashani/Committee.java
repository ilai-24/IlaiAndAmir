package Ilai_Amrami_Amir_Kashani;

import java.util.Arrays;
import java.util.Objects;

public class Committee {
    private String name;
    private Lecturer[] committeeFriends;
    private Lecturer chairMan;
    private int numOfFriends;

    public Committee(String name) {
        setName(name);
        committeeFriends = new Lecturer[1];
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumOfFriends() {
        return numOfFriends;
    }

    public void setChairMan(Lecturer chairMan) {
        if (chairMan.getTitle() != Lecturer.eTitle.Doctor)
           throw new ActionException("the chair man is not a doctor");
        if (findFriendIndexByName(chairMan.getName()) != -1) {
            throw new ActionException("the chair man is already a friend in the committee");
        }
        if (this.chairMan != null)
            this.chairMan.removeCommittee(this);
        chairMan.addCommittee(this);
        this.chairMan = chairMan;
    }

    public void addFriend(Lecturer friend) {
        if (chairMan.getName().equals(friend.getName()))
            throw new ActionException("the chair man is already the chair man of the committee");
        if (findFriendIndexByName(friend.getName()) != -1)
            throw new ActionException("the chair man is already a friend in the committee");

        friend.addCommittee(this);
        Lecturer[] temp = new Lecturer[committeeFriends.length * 2];
        for (int i = 0; i < numOfFriends; i++)
            temp[i] = committeeFriends[i];
        temp[numOfFriends] = friend;
        numOfFriends++;
        committeeFriends = temp;
    }

    public void removeFriend(Lecturer friend) {
        int friendIndex = findFriendIndexByName(friend.getName());
        if (friendIndex == -1)
            throw new ActionException("the lecturer isn't a friend in the committee");
        friend.removeCommittee(this);
        numOfFriends--;

        Lecturer tempLecturer = committeeFriends[numOfFriends];
        committeeFriends[numOfFriends] = null;
        committeeFriends[friendIndex] = tempLecturer;

    }

    public int findFriendIndexByName(String name) {
        for (int i = 0; i < numOfFriends; i++)
            if (committeeFriends[i].getName().equals(name))
                return i;
        return -1;
    }

    public String toString() {
        StringBuffer str = new StringBuffer("committee: " + name + "\n");
        for (int i = 0; i < numOfFriends; i++)
            str.append(" Friends name " + i + ": " + committeeFriends[i].getName() + ", ");
        str.append("\n ChairmanName" + chairMan);
        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Committee committee = (Committee) o;
        return numOfFriends == committee.numOfFriends && Objects.equals(name, committee.name) && Objects.deepEquals(committeeFriends, committee.committeeFriends) && Objects.equals(chairMan, committee.chairMan);
    }

}

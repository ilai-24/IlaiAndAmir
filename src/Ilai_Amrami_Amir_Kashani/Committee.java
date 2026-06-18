package Ilai_Amrami_Amir_Kashani;

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

    public College.eAddChairman setChairMan(Lecturer chairMan) {
        if (chairMan.getTitle() != Lecturer.eTitle.Doctor)
            return College.eAddChairman.FailChairManIsNotDr;

        if (findFriendIndexByName(chairMan.getName()) != -1) {
            return College.eAddChairman.FailChairmanIsFriend;
        }
        if (this.chairMan != null)
            this.chairMan.removeCommittee(this);
        chairMan.addCommittee(this);
        this.chairMan = chairMan;
        return College.eAddChairman.Succeed;
    }

    public College.eAddFriendToCommittee addFriend(Lecturer friend) {
        if (chairMan.getName().equals(friend.getName()))
            return College.eAddFriendToCommittee.FailFriendIsChairMan;
        if (findFriendIndexByName(friend.getName()) != -1)
            return College.eAddFriendToCommittee.FailFriendIsInCommittee;

        friend.addCommittee(this);
        Lecturer[] temp = new Lecturer[committeeFriends.length * 2];
        for (int i = 0; i < numOfFriends; i++)
            temp[i] = committeeFriends[i];
        temp[numOfFriends] = friend;
        numOfFriends++;
        committeeFriends = temp;
        return College.eAddFriendToCommittee.Succeed;

    }

    public College.eRemoveFriend removeFriend(Lecturer friend) {
        int friendIndex = findFriendIndexByName(friend.getName());
        if (friendIndex == -1)
            return College.eRemoveFriend.FailFriendNotInCommittee;
        friend.removeCommittee(this);
        numOfFriends--;

        Lecturer tempLecturer = committeeFriends[numOfFriends];
        committeeFriends[numOfFriends] = null;
        committeeFriends[friendIndex] = tempLecturer;
        return College.eRemoveFriend.Succeed;

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
}

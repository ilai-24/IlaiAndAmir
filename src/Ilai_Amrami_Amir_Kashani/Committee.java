package Ilai_Amrami_Amir_Kashani;

import java.io.Serializable;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;


public class Committee <T extends Lecturer> implements Cloneable, Serializable{
    private String name;
    private ArrayList<T> committeeFriends;
    private Lecturer chairMan;
    private Class<T> friendType;

    public Committee(String name, Lecturer chairMan,Class<T> friendType) throws ActionException {
        setName(name);
        setChairMan(chairMan);
        committeeFriends =new ArrayList<>();
        this.friendType = friendType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumOfFriends() {
        return committeeFriends.size();
    }

    public Lecturer getChairMan() {
        return chairMan;
    }

    public void setChairMan(Lecturer chairMan) throws  ActionException {
        if (!(chairMan instanceof HighDegrees)) {
            throw new ActionException("the chair man is not a doctor or professor");
        }
        try {
            if (committeeFriends.contains(chairMan))
                throw new ActionException("the chair man is already a friend in the committee");
        }
        catch (NullPointerException e) {}



        if (this.chairMan != null)
            this.chairMan.removeCommittee(this);
        chairMan.addCommittee(this);
        this.chairMan = chairMan;
    }

    public void addFriend(Lecturer friend) throws ActionException {
        if (chairMan.getName().equals(friend.getName()))
            throw new ActionException("the friend is already the chair man of the committee");
        if (committeeFriends.contains(friend))
            throw new ActionException("the friend is already a friend in the committee");

        if (!friendType.isInstance(friend))
            throw new ActionException("The lecturer is not of the correct type");

        friend.addCommittee(this);
        committeeFriends.add(friendType.cast(friend));
    }

    public void removeFriend(Lecturer friend) throws ActionException {
        try {
            if (!(committeeFriends.contains(friend)))
                throw new ActionException("the lecturer isn't a friend in the committee");
        }
        catch (ClassCastException e) {
            throw new ActionException("The lecturer is not int he correct type");
        }
            T friendAdd=(T)friend;
            committeeFriends.remove(friendAdd);
            friend.removeCommittee(this);
        }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer("committee: " + name + "\n");
        str.append("committeeFriends: " +committeeFriends.toString());
        str.append("\n ChairmanName" + chairMan);
        return str.toString();
    }

    public int getNumArticles(){
        int articles = 0;
        Iterator<T> iterator = committeeFriends.iterator();
        while (iterator.hasNext()) {
            T friend = iterator.next();
            if (friend instanceof HighDegrees)
                articles += ((HighDegrees) friend).getArticlesNum();
        }
        return articles;
        }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Committee))
            return false;
        Committee committee = (Committee)o;
        return this.name.equals(committee.getName());
    }

    @Override
    public Committee<?> clone() throws CloneNotSupportedException {
        Committee<?> committee = (Committee<?>)super.clone();
        committee.name = name+"New";
        return committee;
    }


}
